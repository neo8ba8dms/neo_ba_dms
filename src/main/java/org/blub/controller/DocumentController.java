package org.blub.controller;

import org.blub.domain.Document;
import org.blub.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(value = "/documents2", method = RequestMethod.POST, consumes = "application/json")
    public Document create (@RequestBody Document doc){
        return documentRepository.save(doc);
    }

    @RequestMapping(value = "/documents2", method = RequestMethod.GET)
    public Iterable<Document> list(){
        return documentRepository.findAll();
    }


}
