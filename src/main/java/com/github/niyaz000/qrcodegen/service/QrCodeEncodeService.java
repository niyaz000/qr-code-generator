package com.github.niyaz000.qrcodegen.service;

import com.github.niyaz000.qrcodegen.aws.SqsClient;
import com.github.niyaz000.qrcodegen.constant.ApplicationConstants;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import com.github.niyaz000.qrcodegen.model.QrCode;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrCodeEncodeService {

  @Autowired
  SqsClient sqsClient;

  @Autowired
  QrCodeDao qrCodeDao;

  public QrCode create(QrCode qrCode) throws Exception {
    qrCode = qrCodeDao.save(qrCode);
    QrCodeMessage message = QrCodeMessage.builder()
            .id(qrCode.getId())
            .xRequestId(MDC.get(ApplicationConstants.X_REQUEST_ID))
            .accountId(Long.valueOf(MDC.get(ApplicationConstants.ACCOUNT_KEY)))
            .build();
    sqsClient.sendMessage(message);
    return qrCode;
  }

  public void delete(Long id) {
    qrCodeDao.deleteById(id);
  }

  public void get(Long id) {
    qrCodeDao.findById(id);
  }

}
