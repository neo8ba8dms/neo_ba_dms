package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.Person_role;
import org.blub.repository.DocumentRepository;
import org.blub.repository.PersonRepository;
import org.blub.repository.Person_role_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/personRole")
public class Person_role_controller {

    @Autowired
    private Person_role_repository person_role_repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person_role> list() {
        return person_role_repository.findAll(1);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Person_role create(@RequestBody Person_role role){
        for(Document doc:role.getReferes_to()){
            documentRepository.findOne(doc.getGraphId());
        }
        if(role.getIs_role_of().getGraphId() != null){
            role.setIs_role_of(personRepository.findOne(role.getIs_role_of().getGraphId()));
        }else{
            role.setIs_role_of(null);
        }
        person_role_repository.save(role, 1);
        return person_role_repository.findOne(role.getGraphId(), 1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Person_role find(@PathVariable Long id){
        return person_role_repository.findOne(id, 2);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        person_role_repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Person_role update(@PathVariable Long id, @RequestBody Person_role role){
        if(null == role.getGraphId()) {
            role.setGraphId(id); //would otherwise create new node in neo4J instead of updating this one
        }
        for(Document doc:role.getReferes_to()){
            documentRepository.findOne(doc.getGraphId());
        }
        if(role.getIs_role_of().getGraphId() != null) { //there is a person referenced
            role.setIs_role_of(personRepository.findOne(role.getIs_role_of().getGraphId()));
        }else{
            role.setIs_role_of(null); //no person --> no new object/node
        }
        person_role_repository.save(role, 1);
        return person_role_repository.findOne(id, 1);
    }
}
