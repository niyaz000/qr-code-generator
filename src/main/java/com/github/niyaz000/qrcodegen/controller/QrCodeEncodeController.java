package com.github.niyaz000.qrcodegen.controller;

import com.github.niyaz000.qrcodegen.constant.EndPoints;
import com.github.niyaz000.qrcodegen.dto.QrCodeDto;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(EndPoints.ENCODE_ENDPOINT)
public class QrCodeEncodeController {

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void post(@Valid QrCodeDto qrCodeDto) {

  }

  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable("id") Long id) {

  }

}
