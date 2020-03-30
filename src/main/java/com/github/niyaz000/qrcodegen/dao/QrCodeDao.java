package com.github.niyaz000.qrcodegen.dao;

import com.github.niyaz000.qrcodegen.model.QrCode;
import com.github.niyaz000.qrcodegen.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QrCodeDao {

  @Autowired
  QrCodeRepository repository;

  public void findById(Long id) {

  }

  public void deleteById(Long id) {

  }

  public QrCode save(QrCode qrCode) {
    return repository.save(qrCode);
  }

}
