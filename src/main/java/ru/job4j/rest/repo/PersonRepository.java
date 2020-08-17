package ru.job4j.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.rest.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
