package com.github.niyaz000.qrcodegen.aws;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class S3Client {

  @Autowired
  AmazonS3 amazonS3Client;

  public void putObject(String key, byte[] data) {

  }

  public void getObject(String key) {

  }

  public void deleteObject(String key) {

  }

}
