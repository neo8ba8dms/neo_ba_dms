package org.blub.controller;

import com.google.common.collect.Sets;
import org.blub.domain.Person;
import org.blub.domain.Person_organisation_relationship;
import org.blub.domain.Person_person_relationship;
import org.blub.repository.OrganisationRepository;
import org.blub.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person> list() {
        //fixes an error, where on first access here a Person gets returned multiple times, on following access normal
        HashSet persons = Sets.newHashSet(personRepository.findAll(1));
        return persons;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Person create(@RequestBody Person person){
        for(Person_person_relationship rel:person.getPerson_person_relationships()){
            rel.setRelates_party(person);
            rel.setRelating_party(personRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        for(Person_organisation_relationship rel:person.getPerson_organisation_relationships()){
            rel.setRelates_party(person);
            rel.setRelating_party(organisationRepository.findOne(rel.getRelating_party().getGraphId()));
        }
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
        for(Person_person_relationship rel:person.getPerson_person_relationships()){
            rel.setRelates_party(person);
            rel.setRelating_party(personRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        for(Person_organisation_relationship rel:person.getPerson_organisation_relationships()){
            rel.setRelates_party(person);
            rel.setRelating_party(organisationRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        personRepository.save(person, 1);
        return personRepository.findOne(id, 1);
    }
}
