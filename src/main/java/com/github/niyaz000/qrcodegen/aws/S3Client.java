package com.github.niyaz000.qrcodegen.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Component
public class S3Client {

  @Autowired
  AmazonS3 amazonS3;

  @Value("${aws.s3.bucket}")
  private String bucket;

  public void putObject(String key, ByteArrayOutputStream data) {
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(data.size());
    InputStream stream = new ByteArrayInputStream(data.toByteArray());
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, stream, metadata);
    amazonS3.putObject(putObjectRequest);
  }

  public void getObject(String key) {

  }

  public void deleteObject(String key) {

  }

}
