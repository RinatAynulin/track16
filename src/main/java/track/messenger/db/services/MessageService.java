package track.messenger.db.services;

import track.messenger.db.dao.MessageDao;
import track.messenger.db.model.Message;

/**
 * Created by Aynulin on 22.11.2016.
 */
public class MessageService extends Service<Message, Long> {
    public MessageService() {
        super(new MessageDao());
    }
}
