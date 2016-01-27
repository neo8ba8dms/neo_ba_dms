package org.blub.controller;

import org.blub.domain.Document;
import org.blub.repository.DocumentRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

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

/*delete, when versioning is done
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
    public Document update(@PathVariable Long id, @RequestBody Document document){
        documentRepository.save(document);
        return documentRepository.findOne(id);
    }
*/

    //// TODO: 25.01.16 Response header bearbeiten, um Fehlermeldung bei Response zu verhindern(oder, was ihn verursacht)

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public @ResponseBody Document update(@RequestParam("documentString") String documentString,
                                                 @RequestParam(value= "file", required=false) MultipartFile file
    ){
        Document recievedDocument = deserializeDocumentString(documentString);
        String filename = "";
        String directoryWhereFileGetsSaved = "documentrepository/" + recievedDocument.getName() +
                new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

        if (file != null && !file.isEmpty()) {
            filename = file.getOriginalFilename();
        }
        //example: /documentrepository/document12016-01-27 01:10:46.367/produktiv.ods
        String pathToFileForNewDocument =  directoryWhereFileGetsSaved + "/" + filename;

        //old document
        Document oldDocument = documentRepository.findOne(recievedDocument.getId());

        //new document
        Document newDocument = new Document();
        newDocument.setName(recievedDocument.getName());
        newDocument.setExternalObjects(recievedDocument.getExternalObjects());
        newDocument.setPathToFile(pathToFileForNewDocument);
        documentRepository.save(newDocument); //newDocument.id is generated here

        //reference from (old) --> (new)
        oldDocument.setSuccessorDocument(newDocument);
        documentRepository.save(oldDocument);

        if (file != null && !file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File directory = new File(directoryWhereFileGetsSaved);
                directory.mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(directory, filename)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newDocument;
    }


    /*
    This method is necessary, because the automatic mapping does not work.
    I spend several hours on that and could just come up with this workaround.
    Feel free to fix this.
     */
    private Document deserializeDocumentString(String documentString){
        ObjectMapper mapper = new ObjectMapper();
        Document document = new Document();
        try {
            document = mapper.readValue(documentString, Document.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;

    }
}
