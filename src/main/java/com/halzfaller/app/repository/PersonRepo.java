package com.halzfaller.app.repository;

import com.halzfaller.app.entities.Person;

public class PersonRepo extends Repository<Person, Long> {

    public PersonRepo() {
        super(Person.class);
    }

}
