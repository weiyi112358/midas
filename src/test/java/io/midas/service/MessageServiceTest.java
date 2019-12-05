package io.midas.service;

import com.amazonaws.services.s3.AmazonS3;
import io.midas.ApplicationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void sendEventTest(){

        //mokito unittest
        messageService.sendEvent("hello world");
        Assert.assertTrue(false);
    }

}

