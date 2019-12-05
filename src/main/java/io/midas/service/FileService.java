package io.midas.service;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileService {

    private String bucket = "ywei2-tutorial";
    private AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();

    public void putObject(File f){
        amazonS3.putObject(bucket,f.getName(),f);

    }

    public void getObject(){


    }

}
