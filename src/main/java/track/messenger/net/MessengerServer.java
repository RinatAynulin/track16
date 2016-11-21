package track.messenger.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class MessengerServer {
    private static final int PORT = 10000;
    private static final int MAX_CLIENT_COUNT = 1000;
    private static final int THREADS_COUNT = 8;
    static final Logger logger = LoggerFactory.getLogger(MessengerServer.class);
    private ArrayBlockingQueue<Session> sessions;
    private ExecutorService executorService;
    private boolean running;

    public MessengerServer() {
    }

    public void start() {
        running = true;
        sessions = new ArrayBlockingQueue<>(MAX_CLIENT_COUNT);
        executorService = Executors.newFixedThreadPool(THREADS_COUNT);
        listen();
        processSessions();
    }

    private void listen() {
        Thread listener = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT, 100, InetAddress.getByName("0.0.0.0"))) {
                while (!Thread.currentThread().isInterrupted() && running) {
                    try {
                        Socket socket = serverSocket.accept();
                        Session session = new Session(socket);
                        sessions.add(session);
                    } catch (IOException e) {
                        logger.error("Error during accepting of socket");
                    }
                }
            } catch (IOException e) {
                logger.error("Error during creation of serverSocket");
                Thread.currentThread().interrupt();
            }
        });
        listener.setDaemon(true);
        listener.start();
    }

    public void stop() {
        running = false;
    }

    private void processSessions() {
        Thread processor = new Thread(() -> {
            try {
                while (running && !Thread.currentThread().isInterrupted()) {
                    Session session = sessions.take();
                    processSession(session);
                    if (session.isConnected()) {
                        sessions.add(session);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                for (Session session : sessions) {
                    if (session.isConnected()) {
                        session.close();
                    }
                }
                logger.info("Closed all sessions");
            }
        });
        processor.start();
        try {
            processor.join();
        } catch (InterruptedException e) {
            logger.info("Error during joining to socket processor");
        }
    }

    private void processSession(Session session) {
        executorService.submit(session::process);
    }

    public boolean isRunning() {
        return running;
    }
}
