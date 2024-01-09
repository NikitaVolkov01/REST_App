package ru.volkov.RESTApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkov.RESTApp.models.Person;
import ru.volkov.RESTApp.repository.PeopleRepository;
import ru.volkov.RESTApp.util.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new); //Если нашли person в БД, то возвращаем его, иначе выбрасываем кастомное исключение
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
