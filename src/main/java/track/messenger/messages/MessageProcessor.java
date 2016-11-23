package track.messenger.messages;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import track.messenger.db.exceptions.DbException;
import track.messenger.db.exceptions.DuplicateException;
import track.messenger.db.model.Chat;
import track.messenger.db.model.User;
import track.messenger.db.services.DbService;
import track.messenger.net.ProtocolException;
import track.messenger.net.Session;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Aynulin on 21.11.2016.
 */
public class MessageProcessor {
    private final Session session;
    static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    public MessageProcessor(Session session) {
        this.session = session;
    }

    public void process(Message message, ConcurrentHashMap<Long, Session> idToSession) {
        switch (message.getType()) {
            case MSG_TEXT:
                processText(message, idToSession);
                break;
            case MSG_REGISTRATION:
                processRegistration(message);
                break;
            case MSG_LOGIN:
                processLogin(message, idToSession);
                break;
            default:
                break;
        }
    }

    private void processText(Message message, ConcurrentHashMap<Long, Session> idToSession) {
        TextMessage textMessage = (TextMessage) message;
        if (session.getUser() == null) {
            //todo unauthorised
        } else {
            Chat chat = null;
            try {
                DbService.getInstance().getMessageService().persist(
                        new track.messenger.db.model.Message(textMessage.getText(),
                                textMessage.getSenderId(), textMessage.getChatId(), System.currentTimeMillis()));
                chat = DbService.getInstance().getChatService().findById(textMessage.getId());
            } catch (HibernateException e) {
                logger.error("Hibernate Exception for message " + textMessage);
            }

            if (chat != null) {
                for (User user : chat.getParticipants()) {
                    if (idToSession.contains(user.getId())) { //user is online
                        try {
                            idToSession.get(user.getId()).send(textMessage);
                            /* todo add field delivered in db and at this point
                             set that the message is delivered to this user */
                        } catch (ProtocolException e) {
                            logger.error("ProtocolException during sending message to " + user + ". " + e.getMessage());
                        } catch (IOException e) {
                            logger.error("IOException during sending message to " + user + ". " + e.getMessage());
                        }
                    }
                }
            }
        }

    }

    private void processRegistration(Message message) {
        RegistrationMessage registrationMessage = (RegistrationMessage) message;
        User user = new User(registrationMessage.getUsername(), registrationMessage.getPassword());
        try {
            DbService.getInstance().getUserService().persist(user);
        } catch (DuplicateException e) {
            logger.error("User with username + " + user.getUsername() + " already exists");
            // todo send message to user about this
        }
    }

    private void processLogin(Message message, ConcurrentHashMap<Long, Session> idToSession) {
        LoginMessage loginMessage = (LoginMessage) message;
        User user;
        try {
            user = DbService.getInstance().getUserService().findByUsername(loginMessage.getUsername());
            if (user == null) {
                // todo send message to user about this
            } else if (!user.getPassword().equals(loginMessage.getPassword())) {
                // todo send message to user about this
            } else {
                // ok
                // todo send id to user
            }
        } catch (DbException e) {
            // todo end message to user about this
        }
    }
}
