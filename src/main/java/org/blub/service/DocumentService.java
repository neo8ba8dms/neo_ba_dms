package org.blub.service;

import org.springframework.stereotype.Component;

import java.io.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


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

    public void downloadDocument(HttpServletResponse response, String documentrepository, String documentname, String filename, String fileending){

        try {
            File file = new File(documentrepository + "/" + documentname + "/" + filename + "." + fileending);
            InputStream inputStream = new FileInputStream(file);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + file.getName() + "\"");
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[2048];
            int length;
            while((length = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
