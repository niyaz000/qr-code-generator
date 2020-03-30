package com.github.niyaz000.qrcodegen.repository;

import com.github.niyaz000.qrcodegen.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {

}
