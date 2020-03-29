package com.github.niyaz000.qrcodegen.controller;

import com.github.niyaz000.qrcodegen.constant.EndPoints;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(EndPoints.DECODE_ENDPOINT)
public class QrCodeDecodeController {

  @GetMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void get(@PathVariable("id") Long id) {

  }

}
