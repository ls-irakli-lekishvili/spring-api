package com.firstspring.apiDemo.service;

import com.firstspring.apiDemo.dao.PersonDao;
import com.firstspring.apiDemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deleteUser(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updateUser(UUID id, Person person) {
        return personDao.updatePersonById(id, person);
    }
}
