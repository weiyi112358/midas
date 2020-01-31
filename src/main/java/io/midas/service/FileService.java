package io.midas.service;


import com.amazonaws.HttpMethod;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class FileService {

//    private String bucket = "ywei2-tutorial";
//    private AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
//            .build();
//
//    public void putObject(File f){
//        amazonS3.putObject(bucket,f.getName(),f);
//
//    }
//
//    public void getObject(){
//
//
//    }

    private String filePath = System.getProperty("aws.tempFilePath");

    @Autowired
    AmazonS3 s3Client;
    public String uploadFile(String bucketName,MultipartFile multipartFile)
    {
        String ret = "";
        try
        {
            File file = saveFile(multipartFile);
            createBucket(bucketName);
            s3Client.putObject(bucketName,file.getName(),file);
            ret = getObjectUrl(bucketName,file.getName());

        }
        catch (Exception e)
        {
            ret = e.getMessage();
        }
        return ret;


    }

    public File saveFile(MultipartFile file) throws IOException {
        File directory = new File(filePath);
        if(!directory.exists())
        {
            directory.mkdir();
        }
        String newName = FilenameUtils.getBaseName(file.getOriginalFilename())+ UUID.randomUUID()+"."+FilenameUtils.getExtension(file.getOriginalFilename());
        File f = new File(filePath+"/"+newName);
        file.transferTo(f);
        return f;
    }

    public String getPresignedUrl(String bucketName,String fileName)
    {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String getObjectUrl(String bucketName,String fileName)
    {
        return s3Client.getObject(bucketName,fileName).getKey();
    }

    public void createBucket(String bucketName) {
        if (!s3Client.doesBucketExistV2(bucketName)) s3Client.createBucket(bucketName);
    }




}
