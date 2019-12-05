package io.midas.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private AmazonSQS sqlClient;


    private String queueUrl;

    public MessageService(@Autowired AmazonSQS amazonSQS,@Value("${aws.sqs.name}") String queueName){
        this.sqlClient = amazonSQS;
        queueUrl = queueUrl(queueName);

    }

    public void sendEvent(String msg){

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(msg)
                .withDelaySeconds(5);
        sqlClient.sendMessage(send_msg_request);

    }

    public String queueUrl(String queueName)
    {
        GetQueueUrlResult res = sqlClient.getQueueUrl(queueName);
        return res.getQueueUrl();

    }


}
