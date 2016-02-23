package org.blub.controller;

import org.blub.domain.Person;
import org.blub.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person> list() {
        return personRepository.findAll(1);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Person create(@RequestBody Person person){
        personRepository.save(person, 1);
        return personRepository.findOne(person.getGraphId(), 1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Person find(@PathVariable Long id){
        return personRepository.findOne(id, 1);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        personRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Person update(@PathVariable Long id, @RequestBody Person person){
        if(null == person.getGraphId()){
            person.setGraphId(id); //would otherwise create new node in neo4J instead of updating this one
        }
        personRepository.save(person, 1);
        return personRepository.findOne(id, 1);
    }
}
