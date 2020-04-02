package com.github.niyaz000.qrcodegen.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.niyaz000.qrcodegen.aws.S3Client;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import com.github.niyaz000.qrcodegen.model.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.stereotype.Component;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import java.util.Optional;

@Component
public class QrCodeMessageListener {

  @Value("${aws.sqs.url}")
  private String sqsUrl;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  QrCodeDao qrCodeDao;

  @Autowired
  S3Client s3Client;

  @SqsListener(value = "${aws.sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(String message) throws Exception {
    QrCodeMessage qrCodeMessage = mapper.readValue(message, QrCodeMessage.class);
    Optional<QrCode> qrCode = qrCodeDao.findById(qrCodeMessage.getId());
    if(qrCode.isEmpty()) {

    } else {

    }
  }

  private String getUrl(Long accountId, Long id, String name) {
    return String.format("%s/%s/%s", accountId, id, name);
  }

}
