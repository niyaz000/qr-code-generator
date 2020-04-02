package com.github.niyaz000.qrcodegen.service;

import com.github.niyaz000.qrcodegen.aws.S3Client;
import com.github.niyaz000.qrcodegen.aws.SqsClient;
import com.github.niyaz000.qrcodegen.constant.Action;
import com.github.niyaz000.qrcodegen.constant.ApplicationConstants;
import com.github.niyaz000.qrcodegen.constant.QrDefaults;
import com.github.niyaz000.qrcodegen.constant.Status;
import com.github.niyaz000.qrcodegen.dao.QrCodeDao;
import com.github.niyaz000.qrcodegen.exception.QrCodeNotFound;
import com.github.niyaz000.qrcodegen.message.QrCodeMessage;
import com.github.niyaz000.qrcodegen.model.QrCode;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class QrCodeEncodeService {

  @Autowired
  SqsClient sqsClient;

  @Autowired
  QrCodeDao qrCodeDao;

  @Autowired
  S3Client s3Client;

  public QrCode create(QrCode qrCode) throws Exception {
    qrCode.setStatus(Status.CREATED);
    qrCode.setUrl(getUrl(qrCode));
    qrCode = qrCodeDao.save(qrCode);
    QrCodeMessage message = QrCodeMessage.builder()
            .id(qrCode.getId())
            .xRequestId(MDC.get(ApplicationConstants.X_REQUEST_ID))
            .action(Action.CREATE)
            .build();
    sqsClient.sendMessage(message);
    return qrCode;
  }

  public void delete(Long id) throws Exception {
    Optional<QrCode> qrCode = qrCodeDao.findById(id);
    if(qrCode.isEmpty()) {
      return;
    }
    QrCodeMessage message = QrCodeMessage.builder()
            .id(id)
            .xRequestId(MDC.get(ApplicationConstants.X_REQUEST_ID))
            .action(Action.DELETE)
            .build();
    sqsClient.sendMessage(message);
  }

  public QrCode get(Long id) throws QrCodeNotFound {
    Optional<QrCode> qrCode = qrCodeDao.findById(id);
    if(qrCode.isEmpty()) {
      throw new QrCodeNotFound();
    }
    return qrCode.get();
  }

  public void generate(QrCode qrCode) {
    ByteArrayOutputStream stream = QRCode.from(qrCode.getId().toString())
            .withColor(qrCode.getBackGroundColor(), qrCode.getForeGroundColor())
            .withSize(qrCode.getWidth(), qrCode.getHeight())
            .to(ImageType.valueOf(qrCode.getType().toString()))
            .withCharset(QrDefaults.DEFAULT_ENCODING).stream();
    String key = generateS3Key(qrCode);
    s3Client.putObject(key, stream);
  }

  private void generateSvg(QRCode qrCode) {
    qrCode.svg();
  }

  private String generateS3Key(QrCode qrCode) {
    return String.format("%s/%s", qrCode.getId(), qrCode.getName());
  }

  public void deleteQrCode(QrCode qrCode) {
    s3Client.deleteObject(generateS3Key(qrCode));
    qrCodeDao.deleteById(qrCode.getId());
  }

  private String getUrl(QrCode qrCode) {
    return String.format("%s/%s", s3Client.getEndpoint(), generateS3Key(qrCode));
  }

}
