package io.zipcoder.crudapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final Map<Integer, Person> people = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person p) {
        int id = idCounter.incrementAndGet();
        p.setId(id);
        people.put(id, p);
        return p;
    }

    @GetMapping("/people")
    public List<Person> getPersonList() {
        return new ArrayList<>(people.values());
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable int id) {
        return people.get(id);
    }

    @PutMapping("/people/{id}")
    public Person updatePerson(@RequestBody Person p, @PathVariable int id) {
        p.setId(id);
        people.put(id, p);
        return p;
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable int id) {
        people.remove(id);
    }
}
