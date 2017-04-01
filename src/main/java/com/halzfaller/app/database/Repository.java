package com.halzfaller.app.database;

import java.util.List;

public interface Repository<T> {

    List<T> findAll();
    void save(T entity);
    void delete(T entity);
    T update(T entity);

}
