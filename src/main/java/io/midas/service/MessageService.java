package io.midas.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

//    @Autowired
//    private AmazonSQS sqlClient;
//
//
//    private String queueUrl;
//
//    public MessageService(@Autowired AmazonSQS amazonSQS,@Value("${aws.sqs.name}") String queueName){
//        this.sqlClient = amazonSQS;
//        queueUrl = queueUrl(queueName);
//
//    }
//
//    public void sendEvent(String msg){
//
//        SendMessageRequest send_msg_request = new SendMessageRequest()
//                .withQueueUrl(queueUrl)
//                .withMessageBody(msg)
//                .withDelaySeconds(5);
//        sqlClient.sendMessage(send_msg_request);
//
//    }
//
//    public String queueUrl(String queueName)
//    {
//        GetQueueUrlResult res = sqlClient.getQueueUrl(queueName);
//        return res.getQueueUrl();
//
//    }

    @Autowired
    private AmazonSQS amazonSQS;

    public  String getQueueUrl(String queueName){
        GetQueueUrlResult res = amazonSQS.getQueueUrl(queueName);
        if(res!=null)
        {
            return res.getQueueUrl();
        }
        else
        {
            return amazonSQS.createQueue(queueName).getQueueUrl();
        }

    };


    public void sendMessage(String queueName,String msg){

        Map<String, MessageAttributeValue> messageAttributes = new HashMap();
        MessageAttributeValue messageAttributeValue = new MessageAttributeValue();
        messageAttributeValue.withDataType("String").withStringValue("File URL in S3");
        messageAttributes.put("message", messageAttributeValue);
        String queueUrl = getQueueUrl(queueName);
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withQueueUrl(queueUrl)
                .withMessageBody(msg)
                .withMessageAttributes(messageAttributes);
        amazonSQS.sendMessage(sendMessageRequest);

    };

    public List<Message> getMessages(String queueName){
        String queueUrl = getQueueUrl(queueName);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        return messages;
    }




}
