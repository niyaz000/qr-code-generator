package com.github.niyaz000.qrcodegen.aws;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SqsClient {

  @Autowired
  ObjectMapper mapper;

  @Autowired
  AmazonSQS amazonSQS;

  @Value("{cloud.aws.sqs.url}")
  private String sqsQueueUrl;

  public void sendMessage(Object message) throws Exception {
    String msg = mapper.writeValueAsString(message);
    amazonSQS.sendMessage(sqsQueueUrl, msg);
  }

}
