package io.midas.controller;


import com.amazonaws.services.ec2.model.transform.RequestSpotFleetResultStaxUnmarshaller;
import io.midas.model.Student;
import io.midas.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/files"})
public class FileController {

    @Autowired
    private FileService fileService;
    private String bucketName = "ywei2-tutorial";

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upLoadFile(@RequestParam("file") MultipartFile file) throws IOException {

        String url = fileService.uploadFile(bucketName,file);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(url);
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity downloadFile(@RequestParam(name="filename") String fileName)
    {
        Resource resource = null;
        String msg = "The file doesn't exist";
        ResponseEntity responseEntity;
        String homeDir = System.getProperty("aws.tempFilePath");
        try
        {
            Path filePath = Paths.get(homeDir).toAbsolutePath().resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());

            responseEntity = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

            msg = String.format("The file %s was downloaded", resource.getFilename());

        }
        catch (Exception e)
        {
            responseEntity = ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

        return responseEntity;
    }
}


