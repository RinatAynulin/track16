package track.messenger.messages;

import org.apache.log4j.Logger;
import track.messenger.net.Session;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Aynulin on 21.11.2016.
 */
public class MessageProcessor {

    private final Session session;

    public MessageProcessor(Session session) {
        this.session = session;
    }

    public void process(Message message, ConcurrentHashMap<Long, Session> idToSession) {
        switch (message.getType()) {
            case MSG_TEXT:
                processText(message, idToSession);
                break;
            default:
                break;
        }
    }

    private void processText(Message message, ConcurrentHashMap<Long, Session> idToSession) {
        if (session.getUser() == null) {
            //todo
        } else {
            TextMessage textMessage = (TextMessage) message;

        }

    }
}
