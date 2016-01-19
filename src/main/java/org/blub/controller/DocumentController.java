package org.blub.controller;

import org.blub.domain.Document;
import org.blub.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Document> list() {
        return documentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Document create(@RequestBody Document document){
        documentRepository.save(document);
        return documentRepository.findOne(document.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Document find(@PathVariable Long id){
        return documentRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        documentRepository.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Document update(@PathVariable Long id, @RequestBody Document document){
        documentRepository.save(document);
        return documentRepository.findOne(id);
    }

}
