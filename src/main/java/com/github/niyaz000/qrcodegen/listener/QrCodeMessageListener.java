package com.github.niyaz000.qrcodegen.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.niyaz000.qrcodegen.aws.S3Client;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.stereotype.Component;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

@Component
public class QrCodeMessageListener {

  @Value("${cloud.aws.sqs.url}")
  private String sqsUrl;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  QrCodeDao qrCodeDao;

  @Autowired
  S3Client s3Client;

  @SqsListener(value = "${cloud.aws.sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(String message) throws Exception {
    QrCodeMessage qrCodeMessage = mapper.readValue(message, QrCodeMessage.class);
    qrCodeDao.findById(qrCodeMessage.getId());
  }

  private String getUrl(Long accountId, Long id, String name) {
    return String.format("%s/%s/%s", accountId, id, name);
  }

}
