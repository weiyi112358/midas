package io.midas.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import io.midas.ApplicationBoot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBoot.class)
public class MessageServiceMockTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AmazonSQS amazonSQS;
    @Mock
    private GetQueueUrlResult getQueueUrlResult;
    @InjectMocks
    private MessageService messageService;

    private List<Message> messages;
    private String fakeQueueUrl = "www.fakeQueueUrl.com/abc/123/fake";
    private String queueName = "midas-sqs";
    @Before
    public void setUp(){

        messages = new ArrayList();
        messages.add(new Message());
        //Mocks are initialized before each test method
        MockitoAnnotations.initMocks(this);
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(getQueueUrlResult);
        when(getQueueUrlResult.getQueueUrl()).thenReturn(fakeQueueUrl);
        when(amazonSQS.receiveMessage(any(ReceiveMessageRequest.class)).getMessages()).thenReturn(messages);
        when(amazonSQS.createQueue(any(CreateQueueRequest.class)).getQueueUrl()).thenReturn(fakeQueueUrl);

    }
    @Test
    public void getQueueUrlTest()
    {
        String queueUrl = messageService.getQueueUrl(queueName);
        Assert.assertEquals(queueUrl,fakeQueueUrl);
    }

    @Test
    public void sendEventTest(){

        //mokito unittest
        messageService.sendMessage(queueName,"hello world");
        verify(amazonSQS,times(1)).sendMessage(any());
    }

    @Test
    public void getMessagesTest()
    {
        List<Message> messageList = messageService.getMessages(queueName);
        Assert.assertEquals(messageList,messages);

    }


































}

