package com.github.niyaz000.qrcodegen.service;

import com.github.niyaz000.qrcodegen.aws.SqsClient;
import com.github.niyaz000.qrcodegen.constant.ApplicationConstants;
import com.github.niyaz000.qrcodegen.constant.QrDefaults;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.exception.QrCodeNotFound;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import com.github.niyaz000.qrcodegen.model.QrCode;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

  public QrCode get(Long id) throws QrCodeNotFound {
    Optional<QrCode> qrCode = qrCodeDao.findById(id);
    if(qrCode.isEmpty()) {
      throw new QrCodeNotFound();
    }
    return qrCode.get();
  }

  public void generate(QrCode qrCode) {
    QRCode.from(qrCode.getId().toString())
            .withColor(qrCode.getBackGroundColor(), qrCode.getForeGroundColor())
            .withSize(qrCode.getWidth(), qrCode.getHeight())
            .withCharset(QrDefaults.DEFAULT_ENCODING)
            .svg();

  }

  private void generateSvg(QRCode qrCode) {
    qrCode.svg();
  }

}
