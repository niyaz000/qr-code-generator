package com.github.niyaz000.qrcodegen.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.stereotype.Component;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

@Component
public class QrCodeMessageListener {

  @Value("${aws.sqs.url}")
  private String sqsUrl;

  @SqsListener(value = "${aws.sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(String message) {

  }

}
