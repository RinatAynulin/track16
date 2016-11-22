package track.messenger.db.services;

import track.messenger.db.dao.ChatDao;
import track.messenger.db.model.Chat;

/**
 * Created by Aynulin on 22.11.2016.
 */
public class ChatService extends Service<Chat, Long> {
    public ChatService() {
        super(new ChatDao());
    }
}
