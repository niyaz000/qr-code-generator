package com.github.niyaz000.qrcodegen.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.niyaz000.qrcodegen.aws.S3Client;
import com.github.niyaz000.qrcodegen.constant.Action;
import com.github.niyaz000.qrcodegen.constant.ApplicationConstants;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import com.github.niyaz000.qrcodegen.model.QrCode;
import com.github.niyaz000.qrcodegen.service.QrCodeEncodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.stereotype.Component;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import java.util.Optional;

@Component
public class QrCodeMessageListener {

  private static Logger LOGGER = LoggerFactory.getLogger(QrCodeMessageListener.class);
  @Value("${aws.sqs.url}")
  private String sqsUrl;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  QrCodeDao qrCodeDao;

  @Autowired
  S3Client s3Client;

  @Autowired
  QrCodeEncodeService service;

  @SqsListener(value = "${aws.sqs.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
  public void receiveMessage(String message) throws Exception {
    try {
      QrCodeMessage msg = mapper.readValue(message, QrCodeMessage.class);
      MDC.put(ApplicationConstants.X_REQUEST_ID, msg.getXRequestId());
      LOGGER.info("Received msg from queue {}, msg {}", sqsUrl, msg);
      Optional<QrCode> qrCode = qrCodeDao.findById(msg.getId());
      if (qrCode.isEmpty()) {
        LOGGER.info("Could not find qrCode with id {}", msg.getId());
      } else {
        if(msg.getAction().equals(Action.CREATE)) {
          service.generate(qrCode.get());
        } else {
          service.deleteQrCode(qrCode.get());
        }
      }
    } catch (Exception ex) {
      LOGGER.error("error while processing message {}", message);
      throw ex;
    } finally {
      MDC.clear();
    }
  }

}
