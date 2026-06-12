package io.zipcoder.crudapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(repo.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPersonList() {
        List<Person> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        Person person = repo.findOne(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p, @PathVariable int id) {
        boolean exists = repo.exists(id);
        p.setId(id);
        Person saved = repo.save(p);
        if (exists) {
            return new ResponseEntity<>(saved, HttpStatus.OK);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        repo.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
