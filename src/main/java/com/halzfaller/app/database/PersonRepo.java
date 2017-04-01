package com.halzfaller.app.database;

import com.halzfaller.app.entities.Person;

import java.util.List;

public class PersonRepo extends DatabaseWrapper implements Repository<Person> {

    @Override
    public List<Person> findAll() {
        return super.getAll("select p from Person p", Person.class);
    }

    @Override
    public void save(Person entity) {
        super.create(entity);
    }

    @Override
    public void delete(Person entity) {
        super.remove(entity);
    }

    @Override
    public Person update(Person entity) {
        return super.merge(entity);
    }
}
