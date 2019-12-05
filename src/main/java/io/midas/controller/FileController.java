package io.midas.controller;


import io.midas.model.Student;
import io.midas.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {

    @Autowired
    private FileService fileService;




    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upLoadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String msg = String.format("The file name=%d could not be uploaded.",file.getOriginalFilename(),file.getSize());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String homeDir = System.getProperty("catalina.base")!=null?System.getProperty("catalina.base"):"/tmp/";
        String s3key = FilenameUtils.getBaseName(file.getOriginalFilename()+"_"+ UUID.randomUUID()+"."+extension);

        File f = new File(homeDir+s3key);
        try{
            file.transferTo(f);
            fileService.putObject(f);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        return ResponseEntity.status(HttpServletResponse.SC_OK).body(msg);
    }


}
