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
@RequestMapping(EndPoints.IMAGE_ENDPOINT)
public class ImageController {

  @GetMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void get(@PathVariable("id") Long id) {

  }

}
