package org.blub.controller;

import org.blub.domain.Document;
import org.blub.domain.External_object_reference;
import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.cypher.internal.compiler.v2_2.perty.recipe.Pretty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

@Controller
public class DocumentUploadController {
    
    //// TODO: 25.01.16 Response header bearbeiten, um Fehlermeldung bei Response zu verhindern(oder, was ihn verursacht) 

    @RequestMapping(value = "/documentUpload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("documentString") String documentString,
                                                 @RequestParam(value= "file", required=false) MultipartFile file
    ){
        Document document = deserializeDocumentString(documentString);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File directory = new File("documentrepository/" + document.getName());
                directory.mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(directory, file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + file.getOriginalFilename() + "!";
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
        }
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
