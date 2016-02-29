package org.blub.controller;

import com.google.common.collect.Sets;
import org.blub.domain.Organisation;
import org.blub.domain.Organisation_organisation_relationship;
import org.blub.domain.Organisation_person_relationship;
import org.blub.repository.OrganisationRepository;
import org.blub.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping(value = "/api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Organisation> list() {
        //fixes an error, where on first access here an Organisation gets returned multiple times, on following access normal
        HashSet organisations = Sets.newHashSet(organisationRepository.findAll(1));
        return organisations;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Organisation create(@RequestBody Organisation organisation){
        for(Organisation_person_relationship rel:organisation.getOrganisation_person_relationships()){
            rel.setRelates_party(organisation);
            rel.setRelating_party(personRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        for(Organisation_organisation_relationship rel:organisation.getOrganisation_organisation_relationships()){
            rel.setRelates_party(organisation);
            rel.setRelating_party(organisationRepository.findOne(rel.getRelating_party().getGraphId()));
        }
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
        for(Organisation_person_relationship rel:organisation.getOrganisation_person_relationships()){
            rel.setRelates_party(organisation);
            rel.setRelating_party(personRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        for(Organisation_organisation_relationship rel:organisation.getOrganisation_organisation_relationships()){
            rel.setRelates_party(organisation);
            rel.setRelating_party(organisationRepository.findOne(rel.getRelating_party().getGraphId()));
        }
        organisationRepository.save(organisation, 1);
        return organisationRepository.findOne(id, 1);
    }
}
