package track.messenger.db.exceptions;

/**
 * @author R. Anyulin
 */
public class DuplicateException extends DbException {

    public DuplicateException(Throwable throwable) {
        super(throwable);
    }
}
