package track.messenger.db.dao;

import track.messenger.db.model.Message;
import track.messenger.db.model.User;

import java.util.List;

/**
 * Created by Aynulin on 22.11.2016.
 */

public class MessageDao extends Dao<Message, Long> {
    @Override
    public Message findById(Long id) {
        Message entity = getSession().get(Message.class, id);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> findAll() {
        List<Message> messages = (List<Message>) getSession().createQuery("select m from Message m").list();
        return messages;
    }
}
