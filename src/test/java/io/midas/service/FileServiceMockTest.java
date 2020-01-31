package io.midas.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import io.midas.ApplicationBoot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class FileServiceMockTest {

    @Mock
    private AmazonS3 amazonS3;
    @Mock
    private  S3Object s3Object;

    @InjectMocks
    private FileService fileService;

    private String bucketName = "ywei2-tutorial";
    private MultipartFile multipartFile;
    private String dir = System.getProperty("aws.tempFilePath");
    private URL fakeFileUrl;
    private String newFileName;
    private String path = dir + File.separator + "temp.txt";


    @Before
    public void setUp() throws IOException {

        fakeFileUrl = new URL("http://www.fakeQueueUrl.com/abc/123/fake");
        File f = new File(path);
        if(!f.exists())
        {
            f.createNewFile();
        }
        FileInputStream input = new FileInputStream(f);
        multipartFile = new MockMultipartFile("file", f.getName(), "text/plain", input);

        //Stubbing for the method doesObjectExist and generatePresignedUrl
        //when(amazonS3.doesObjectExist(anyString(), anyString())).thenReturn(false);
        when(amazonS3.getObject(anyString(),anyString())).thenReturn(s3Object);
        when(s3Object.getKey()).thenReturn(String.valueOf(fakeFileUrl));
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void uploadFileTest()
    {
        fileService.uploadFile(bucketName,multipartFile);
        verify(amazonS3,times(1)).doesBucketExistV2(anyString());
        verify(amazonS3,times(1)).putObject(anyString(),anyString(),any(File.class));
        verify(amazonS3,times(1)).getObject(anyString(),anyString());
    }

    @Test
    public void saveFileTest() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("sss",new byte[1]);
        FileService fs = Mockito.mock(FileService.class);
        doReturn(new File(path)).when(fs).saveFile(any());

        File f = fs.saveFile(multipartFile);

        Assert.assertEquals(f,new File(path));

    }

    @Test
    public void getObjectUrl(){
        String fileUrl = fileService.getObjectUrl(bucketName,path);
        Assert.assertEquals(fakeFileUrl.toString(),fileUrl);


    }


}
