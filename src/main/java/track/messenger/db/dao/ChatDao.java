package track.messenger.db.dao;

import track.messenger.db.model.Chat;

import java.util.List;

/**
 * Created by Aynulin on 22.11.2016.
 */

public class ChatDao extends Dao<Chat, Long> {
    @Override
    public Chat findById(Long id) {
        Chat entity = getSession().get(Chat.class, id);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Chat> findAll() {
        List<Chat> chats = (List<Chat>) getSession().createQuery("select u from Chat u").list();
        return chats;
    }
}
