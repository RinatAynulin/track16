package track.messenger.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aynulin on 22.11.2016.
 */
public interface DaoInterface<T, L extends Serializable> {
    public void persist(T entity);

    public void update(T entity);

    public T findById(L id);

    public void delete(T entity);

    public List<T> findAll();

    public void deleteAll();

}
