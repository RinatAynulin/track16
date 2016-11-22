package track.messenger.db.services;

import track.messenger.db.dao.Dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aynulin on 22.11.2016.
 */
public abstract class Service<T, L extends Serializable> {
    private Dao<T, L> dao;

    public Service(Dao<T, L> dao) {
        this.dao = dao;
    }

    public void persist(T entity) {
        dao.openSessionWithTransaction();
        dao.persist(entity);
        dao.closeSessionWithTransaction();
    }

    public void update(T entity) {
        dao.openSessionWithTransaction();
        dao.update(entity);
        dao.closeSessionWithTransaction();
    }

    public T findById(L id) {
        dao.openSession();
        T entity = dao.findById(id);
        dao.closeSession();
        return entity;
    }

    public void delete(L id) {
        dao.openSessionWithTransaction();
        T entity = dao.findById(id);
        dao.delete(entity);
        dao.closeSessionWithTransaction();
    }

    public List<T> findAll() {
        dao.openSession();
        List<T> entityList = dao.findAll();
        dao.closeSession();
        return entityList;
    }

    public void deleteAll() {
        dao.openSessionWithTransaction();
        dao.deleteAll();
        dao.closeSessionWithTransaction();
    }

    public Dao<T, L> getDao() {
        return dao;
    }
}
