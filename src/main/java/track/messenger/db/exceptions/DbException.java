package track.messenger.db.exceptions;

public class DbException extends RuntimeException { //runtime because of Dao hierarchy, I want to use it only in UserDao
    public DbException(Throwable throwable) {
        super(throwable);
    }
}
