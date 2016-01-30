package org.blub.service;

import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;


@Component
public class DocumentService {

    public void uploadDocument(MultipartFile file, String filename, String directoryWhereFileGetsSaved){

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
    }

    public void updateMetaData(){

    }
}
