package track.messenger.db.dao;

import org.hibernate.Query;
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

    public User findByUsername(String username) {
        User entity = null;
        List users = getSession()
                .createQuery("from User u where u.username = :username")
                .setParameter("username", username)
                .list();
        if (users.size() > 0) {
            entity = (User) users.get(0);
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        List<User> users = (List<User>) getSession().createQuery("from User u").list();
        return users;
    }
}