package track.messenger.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.messenger.User;
import track.messenger.messages.Message;
import track.messenger.messages.MessageProcessor;

/**
 * Сессия связывает бизнес-логику и сетевую часть.
 * Бизнес логика представлена объектом юзера - владельца сессии.
 * Сетевая часть привязывает нас к определнному соединению по сети (от клиента)
 */
public class Session {

    /**
     * Пользователь сессии, пока не прошел логин, user == null
     * После логина устанавливается реальный пользователь
     */

    static final Logger logger = LoggerFactory.getLogger(Session.class);
    private User user;

    // сокет на клиента
    private Socket socket;

    /**
     * С каждым сокетом связано 2 канала in/out
     */
    private InputStream in;
    private OutputStream out;
    private Protocol protocol;
    private MessageProcessor processor;
    private boolean connected;

    public Session(Socket socket) {
        try {
            this.socket = socket;
            this.in = socket.getInputStream();
            this.out = socket.getOutputStream();
            this.protocol = new StringProtocol();
            this.connected = true;
        } catch (IOException e) {
            logger.error("Error during session creation.");
        }
    }

    public void send(Message msg) throws ProtocolException, IOException {
        // TODO: Отправить клиенту сообщение
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
            connected = false;
        } catch (IOException e) {
            logger.info("Exception during" +
                    "closing socket " + socket + " of user " + user);
        }

    }

    public void process() {
        byte[] data;
        Message message = null;
        try {
            data = new byte[2048];
            in.read(data);
            message = protocol.decode(data);
        } catch (IOException e) {
            close();
            logger.info("Socket " + socket + " of user " + user + " disconnected");
        } catch (ProtocolException e) {
            logger.info("Protocol exception. Socket: " + socket + "\n Message: " + e.getMessage());
        }
        if (message != null) {
            processor.process(message);
        }
    }

    public boolean isConnected() {
        return connected;
    }
}