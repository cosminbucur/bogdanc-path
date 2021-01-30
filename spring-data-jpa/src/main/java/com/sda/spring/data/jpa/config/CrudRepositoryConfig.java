package com.sda.spring.data.jpa.config;

import com.sda.spring.data.jpa.domain.Person;
import com.sda.spring.data.jpa.repository.PersonCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Supplier;

// TODO: uncomment when needed
@Configuration
public class CrudRepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(CrudRepositoryConfig.class);

    @Autowired
    public PersonCrudRepository personCrudRepository;

    // create command line runner as a bean
    @Bean
    public CommandLineRunner crudData() {
        return args -> {
            // use person crud repository
//            testCrudMethod();

            testCustomQueries();
        };
    }

    private void testCrudMethod() {
        // create
        Person ana = personCrudRepository.save(new Person("ana", 25));
        Person paul = personCrudRepository.save(new Person("paul", 30));

        // read
        personCrudRepository.findById(ana.getId())
                .orElseThrow(() -> new RuntimeException("person not found"));

        personCrudRepository.findAll()
                .forEach(person -> log.info("person: {} - {}", person.getId(), person));

        boolean existsById = personCrudRepository.existsById(ana.getId());
        log.info("ana exists by id: {}", existsById);

        // update
        paul.setAge(35);
        personCrudRepository.save(paul);

        // delete
        long beforeDeleteCount = personCrudRepository.count();
        log.info("count before delete: {}", beforeDeleteCount);

        personCrudRepository.deleteById(ana.getId());

        personCrudRepository.deleteAll();
    }

    private void testCustomQueries() {
        personCrudRepository.save(new Person("cristi", 30));
        Person foundPerson = personCrudRepository.findByName("cristi")
                .orElseThrow(() -> new RuntimeException("not found"));
        log.info("found: {}", foundPerson);


//        personCrudRepository.findPeopleOlderThan(30)
//            .forEach(person -> log.info("person: {}", person));
    }

    private void wrongOptional() {
        Person paul = personCrudRepository.save(new Person("paul", 30));

        // read
        Optional<Person> personOptional = personCrudRepository.findById(paul.getId());

        /*
            // similar to null check
            if (result != null) {
                System.out.println(result);
            }
         */

        if (personOptional.isPresent()) {
            Person foundPerson = personOptional.get();
            System.out.println(foundPerson);
        }
    }

    private void rightOptional() {
        Person paul = personCrudRepository.save(new Person("paul", 30));

        // supplier
        Supplier<Person> personSupplier = () -> {
            return new Person();
        };
        Supplier<Throwable> throwableSupplier = () -> {
            return new RuntimeException();
        };

        // read
        Person foundPerson = personCrudRepository.findById(paul.getId())
                .orElseThrow(() -> new RuntimeException("person not found"));
        System.out.println(foundPerson);
    }
}
