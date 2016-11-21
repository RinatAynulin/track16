package track.messenger.messages;

/**
 * Created by Aynulin on 21.11.2016.
 */
public class MessageProcessor {
    public void process(Message message) {
        switch (message.getType()) {
            case MSG_TEXT:
                processText(message);
        }
    }

    private void processText(Message message) {
        TextMessage textMessage = (TextMessage) message;

    }
}
