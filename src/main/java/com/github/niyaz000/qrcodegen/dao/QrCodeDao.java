package com.github.niyaz000.qrcodegen.dao;

import com.github.niyaz000.qrcodegen.model.QrCode;
import com.github.niyaz000.qrcodegen.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QrCodeDao {

  @Autowired
  QrCodeRepository repository;

  public Optional<QrCode> findById(Long id) {
    return repository.findById(id);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public QrCode save(QrCode qrCode) {
    return repository.save(qrCode);
  }

}
