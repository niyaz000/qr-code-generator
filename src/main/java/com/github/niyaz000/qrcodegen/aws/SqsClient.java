package com.github.niyaz000.qrcodegen.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqsClient {

  @Autowired
  ObjectMapper mapper;

  public void sendMessage(Object message) {

  }

}
