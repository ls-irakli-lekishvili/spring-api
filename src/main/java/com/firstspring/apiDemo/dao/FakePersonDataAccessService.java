package com.firstspring.apiDemo.dao;

import com.firstspring.apiDemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// @Repository and @Component is same
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static ArrayList<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> isInDB = selectPersonById(id);
        if(isInDB.isEmpty()) {
            return 0;
        }
        DB.remove(isInDB.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {

        for (int i = 0; i < DB.size(); i++) {
            if (DB.get(i).getId().equals(id)) {
                DB.set(i, new Person(id, person.getName()));
                break;
            }
        }
        return 1;
    }
}
