package io.midas.service;


import com.amazonaws.services.s3.AmazonS3;
import io.midas.ApplicationBoot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;


    private String bucketName = "ywei2-tutorial";
    private MultipartFile multipartFile;
    private String dir = System.getProperty("aws.tempFilePath");

    private String newFileName;

    @Before
    public void setUp() throws IOException {

        String path = dir + File.separator + "temp.txt";
        File f = new File(path);
        if(!f.exists())
        {
            f.createNewFile();
        }
        FileInputStream input = new FileInputStream(f);
        multipartFile = new MockMultipartFile("file", f.getName(), "text/plain", input);
    }

    @After
    public void tearDown()
    {
        File f = new File(dir + File.separator + newFileName);
        f.delete();
    }

    @Test
    public void putObjectTest() throws IOException {

        String url = fileService.uploadFile(bucketName,multipartFile);
        String[] parts = url.split("/");
        int num = parts.length;
        newFileName = parts[num-1];
        String filePath = dir + File.separator + newFileName;
        File f = new File(filePath);
        boolean isExisted = f.exists();
        Assert.assertTrue(isExisted);
    }



}
