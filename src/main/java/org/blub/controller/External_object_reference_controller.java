package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.External_object_reference;
import org.blub.repository.External_object_reference_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/eor")
public class External_object_reference_controller {

    
    //// TODO: 31.01.16 deleting External_object_references can lead to an inconsistent state(for this prototype a benevolent user is assumed)
    @Autowired
    private External_object_reference_repository eorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<External_object_reference> list() {
        return eorRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public External_object_reference create(@RequestBody External_object_reference eor){
        eorRepository.save(eor);
        return eorRepository.findOne(eor.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public External_object_reference find(@PathVariable Long id){
        return eorRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        eorRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public External_object_reference update(@PathVariable Long id, @RequestBody External_object_reference eor){
        eorRepository.save(eor);
        return eorRepository.findOne(id);
    }

}
