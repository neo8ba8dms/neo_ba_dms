package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.External_object_reference;
import org.blub.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class VersionController {

    @Autowired
    DocumentRepository documentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "versionHistory/{idOfNewestDocument}")
    public Iterable<Document> getVersionHistory(@PathVariable Long idOfNewestDocument){

        return documentRepository.versionHistoryForADocument(idOfNewestDocument);
    }

    @RequestMapping(method = RequestMethod.GET, value = "versionDetail/{documentVersionId}")
    public Document getOneVersion(@PathVariable Long documentVersionId){
        return documentRepository.findOne(documentVersionId);
    }
}
