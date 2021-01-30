package com.sda.spring.data.jpa.repository;

import com.sda.spring.data.jpa.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

// CrudRepository< entity type , entity id type >
public interface PersonCrudRepository extends CrudRepository<Person, Long> {

    // define queries here

    // derived query
    Optional<Person> findByName(String name);

    // FIXME
    // custom native query
//    @Query("select * from person where person.age > :age")
//    List<Person> findPeopleOlderThan(int age);
}
