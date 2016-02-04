package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.DocumentRelationship;
import org.blub.repository.DocumentRepository;
import org.blub.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Transactional //try to avoid evtl. errors, don't know if it works
@RestController
@RequestMapping(value = "/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Document> listMostRecentDocumentsOfEachVersioningPath() {

        return documentRepository.allNewestDocumentVersions();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Document create(@RequestBody Document document){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        document.setWasVersionedAt(timestamp);
        documentRepository.save(document);
        return documentRepository.findOne(document.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Document find(@PathVariable Long id){
        Document doc = documentRepository.findOne(id); //does add the predecessor as successor sometimes
        Document trueSuccessor = documentRepository.getSuccessorDocument(id);
        doc.setSuccessorDocument(trueSuccessor);
        return doc;
    }

    //// TODO: 31.01.16 doesn't return anything, which makes the frontend return error(error has no consequences)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id){
        documentRepository.delete(id);
    }

    /*
    When updating a document without adding a file, then the path to the file
    shall remain and nothing in the repository changes.
    When updating a document and adding a file, then there shall be a new path and a changed repository.
    Remember with every update there are new relationships generated(with identical metadata except id),
    even if nothing has changed in the frontend.
     */
    //// TODO: 30.01.16 move code into service(not that easy)
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Document update(@RequestParam("documentString") String documentString,
                                                 @RequestParam(value= "file", required=false) MultipartFile file){

        /*
        The recievedDocument has the new data except for the old id.
         */
        Document recievedDocument = documentService.deserializeDocumentString(documentString);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Document oldDocument = documentRepository.findOne(recievedDocument.getId());
        Document newDocument = new Document();
        String filename = "";
        String directoryWhereFileGetsSaved = "documentrepository/" + recievedDocument.getName() +
                timestamp;

        //example: /documentrepository/document12016-01-27 01:10:46.367/produktiv.ods
        String pathToFileForNewDocument;

        if (file != null && !file.isEmpty()) {
            filename = file.getOriginalFilename();
            pathToFileForNewDocument =  directoryWhereFileGetsSaved + "/" + filename;
        }else{
            pathToFileForNewDocument = oldDocument.getPathToFile();
        }

        //new document
        newDocument.setName(recievedDocument.getName());
        newDocument.setExternalObjects(recievedDocument.getExternalObjects());
        newDocument.setWasVersionedAt(timestamp);
        newDocument.setPathToFile(pathToFileForNewDocument);

        ////////////////////////////////////handle DocumentRelationships//////////////////////
        if(null != recievedDocument.getDocumentRelationships()){
            Set<DocumentRelationship> documentRelationships = new HashSet<DocumentRelationship>();
            for(DocumentRelationship rel:recievedDocument.getDocumentRelationships()){
                rel.setId(null); //so SDN can generate a new one later
                rel.setStartDocument(newDocument);
                documentRelationships.add(rel);
            }
            newDocument.setDocumentRelationships(documentRelationships);
        }
        //////////////////////////end handle DocumentRelationships/////////////////////



        Document waitResult = documentRepository.save(newDocument); //newDocument.id & documentRelationship.id are generated here

        /*
            Make shure, that there is a newDocument, before saving the old one(not shure if this works or is even necessary).
            It seems to fix an issue, where sometimes the oldDocument has no ref to the new one.
            This is really bad code and should be replaced.
         */
        while(waitResult == null);

        //reference from (old) --> (new)
        oldDocument.setSuccessorDocument(newDocument);
        documentRepository.save(oldDocument);

        documentService.uploadDocument(file, filename, directoryWhereFileGetsSaved);

        return newDocument;
    }

    @RequestMapping(value = "/download/{documentrepository}/{documentname}/{filename}.{fileending}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
                             @PathVariable("documentrepository") String documentrepository,
                             @PathVariable("documentname") String documentname,
                             @PathVariable("filename") String filename,
                             @PathVariable("fileending") String fileending){

        documentService.downloadDocument(response, documentrepository, documentname, filename, fileending);
    }
}
