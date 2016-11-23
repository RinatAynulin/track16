package track.messenger.db.services;

import track.messenger.db.dao.UserDao;
import track.messenger.db.model.User;

/**
 * Created by Aynulin on 22.11.2016.
 */
public class UserService extends Service<User, Long> {
    public UserService() {
        super(new UserDao());
    }

    public User findByUsername(String username) {
        getDao().openSession();
        User entity = ((UserDao)getDao()).findByUsername(username);
        getDao().closeSession();
        return entity;
    }
}
