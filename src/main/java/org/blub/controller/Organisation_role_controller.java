package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.Organisation_role;
import org.blub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping(value = "/api/organisationRole")
public class Organisation_role_controller {

    @Autowired
    private Organisation_role_repository organisation_role_repository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Organisation_role> list() {
        return organisation_role_repository.findAll(1);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Organisation_role create(@RequestBody Organisation_role role){
        HashSet<Document> newReferesTo = new HashSet<Document>();
        for(Document doc:role.getReferes_to()){
            newReferesTo.add(documentRepository.findOne(doc.getGraphId()));
        }
        role.setReferes_to(newReferesTo);
        if(role.getIs_role_of().getGraphId() != null){
            role.setIs_role_of(organisationRepository.findOne(role.getIs_role_of().getGraphId()));
        }else{
            role.setIs_role_of(null);
        }
        organisation_role_repository.save(role, 1);
        return organisation_role_repository.findOne(role.getGraphId(), 1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Organisation_role find(@PathVariable Long id){
        return organisation_role_repository.findOne(id, 2);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        organisation_role_repository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Organisation_role update(@PathVariable Long id, @RequestBody Organisation_role role){
        if(null == role.getGraphId()) {
            role.setGraphId(id); //would otherwise create new node in neo4J instead of updating this one
        }
        HashSet<Document> newReferesTo = new HashSet<Document>();
        for(Document doc:role.getReferes_to()){
            newReferesTo.add(documentRepository.findOne(doc.getGraphId()));
        }
        role.setReferes_to(newReferesTo);
        role.setIs_role_of(organisationRepository.findOne(role.getIs_role_of().getGraphId()));
        organisation_role_repository.save(role, 1);
        return organisation_role_repository.findOne(id, 1);
    }
}
