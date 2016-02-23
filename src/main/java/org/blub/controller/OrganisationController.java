package org.blub.controller;

import org.blub.domain.Organisation;
import org.blub.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationRepository organisationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Organisation> list() {
        return organisationRepository.findAll(1);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Organisation create(@RequestBody Organisation organisation){
        organisationRepository.save(organisation, 1);
        return organisationRepository.findOne(organisation.getGraphId(), 1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Organisation find(@PathVariable Long id){
        return organisationRepository.findOne(id, 1);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        organisationRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Organisation update(@PathVariable Long id, @RequestBody Organisation organisation){
        if(null == organisation.getGraphId()){
            organisation.setGraphId(id); //would otherwise create new node in neo4J instead of updating this one
        }
        organisationRepository.save(organisation, 1);
        return organisationRepository.findOne(id, 1);
    }
}
