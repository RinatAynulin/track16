package track.messenger.db.dao;

import track.messenger.db.model.User;
import java.util.List;

/**
 * Created by Aynulin on 22.11.2016.
 */

public class UserDao extends Dao<User, Long> {
    @Override
    public User findById(Long id) {
        User entity = getSession().get(User.class, id);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getSession().createQuery("select u from User u").list();
        return users;
    }
}