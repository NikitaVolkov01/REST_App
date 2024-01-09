package ru.volkov.RESTApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkov.RESTApp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
