package com.github.niyaz000.qrcodegen.controller;

import com.github.niyaz000.qrcodegen.constant.EndPoints;
import com.github.niyaz000.qrcodegen.dto.QrCodeDto;
import com.github.niyaz000.qrcodegen.mapper.QrCodeMapper;
import com.github.niyaz000.qrcodegen.model.QrCode;
import com.github.niyaz000.qrcodegen.service.QrCodeEncodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(EndPoints.ENCODE_ENDPOINT)
public class QrCodeEncodeController {

  @Autowired
  QrCodeEncodeService service;

  @Autowired
  QrCodeMapper mapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public QrCodeDto post(@Valid QrCodeDto qrCodeDto) throws Exception {
    QrCode qrCode = mapper.mapQrCodeDtoToQrCode(qrCodeDto);
    qrCode = service.create(qrCode);
    return mapper.mapQrCodeToQrCodeDto(qrCode);
  }

  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable("id") Long id) {

  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void get(@PathVariable("id") Long id) {

  }

}
