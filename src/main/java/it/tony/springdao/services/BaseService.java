package it.tony.springdao.services;

import it.tony.springdao.model.WithId;
import it.tony.springdao.tables.StampedSerializedTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseService<TRepository extends CrudRepository<TTable, TId>, TTable extends StampedSerializedTable<TEntity>, TEntity extends WithId, TId extends Serializable> {
    @Autowired
    protected TRepository repository;

    private Class<TTable> wrappedTable;

    public BaseService(Class<TTable> wrappedTable) {
        this.wrappedTable = wrappedTable;
    }

    public void save(TEntity e) {
        if (e.getId() != null && repository.exists((TId) e.getId())) {
            TTable t = repository.findOne((TId) e.getId());
            t.setEntity(e);
            t.setModifiedAt(new Date());
            repository.save(t);
        } else {
            try {
                TTable t = wrappedTable.newInstance();
                t.setEntity(e);
                repository.save(t);
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean exist(TId id) {
        return repository.exists(id);
    }

    public void delete(TId id) {
        repository.delete(id);
    }

    public void delete(TEntity e) {
        try {
            TTable t = wrappedTable.newInstance();
            t.setEntity(e);
            repository.delete(t);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public TEntity get(TId id) {
        TTable t = repository.findOne(id);
        return t == null ? null : t.getEntity();
    }

    public Iterable<TEntity> getAll() {
        Iterable<TTable> tList = repository.findAll();
        List<TEntity> cList = new ArrayList<>();
        tList.forEach(t -> cList.add(t.getEntity()));
        return cList;
    }
}
