package com.halzfaller.app.repository;

import com.halzfaller.app.common.DatabaseWrapper;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Repository<T> {

    private DatabaseWrapper wrapper = new DatabaseWrapper();
    private Class<T> clasz;

    public Repository(Class<T> clasz) {
        this.clasz = clasz;
    }

    public List<T> findAll() {
        return wrapper.executeWithReturn(entityManager -> entityManager.createQuery("select p from " + clasz.getCanonicalName() + " p", this.clasz)
                                                                       .getResultList());
    }

    public void save(T entity) {
        wrapper.executeWithoutReturn(entityManager -> entityManager.persist(entity));
    }

    public void delete(T entity) {
        wrapper.executeWithoutReturn(entityManager -> entityManager.remove(entity));
    }

    public T update(T entity) {
        return wrapper.executeWithReturn(entityManager -> entityManager.merge(entity));
    }

    protected void executeWithoutReturn(Consumer<EntityManager> action) {
        wrapper.executeWithoutReturn(action);
    }

    protected <R> R executeWithReturn(Function<EntityManager, R> action) {
        return wrapper.executeWithReturn(action);
    }

}
