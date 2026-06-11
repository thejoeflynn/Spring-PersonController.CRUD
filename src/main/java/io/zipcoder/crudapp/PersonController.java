package io.zipcoder.crudapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Person createPerson(@RequestBody Person p) {
        return repo.save(p);
    }

    @GetMapping("/people")
    public List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        repo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable int id) {
        return repo.findOne(id);
    }

    @PutMapping("/people/{id}")
    public Person updatePerson(@RequestBody Person p, @PathVariable int id) {
        p.setId(id);
        return repo.save(p);
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable int id) {
        repo.delete(id);
    }
}
