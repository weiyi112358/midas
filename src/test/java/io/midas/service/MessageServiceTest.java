package io.midas.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import io.midas.ApplicationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS amazonSQS;

    private String queueName = "midas-sqs";



    @Test
    public void sendMessageTest2()
    {
        List<Message> messageList = messageService.getMessages(queueName);


        messageService.sendMessage(queueName,"hello");

        List<Message> messageList2 = messageService.getMessages(queueName);



        Assert.assertNotEquals(messageList.get(0).getMessageId(),messageList2.get(0).getMessageId());

    }

}
