package com.github.niyaz000.qrcodegen.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

  @Value("${aws.s3.accessKey}")
  private String accessKey;

  @Value("${aws.s3.secretKey}")
  private String secretKey;

  @Value("${aws.s3.region}")
  private String region;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, accessKey);
    return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.fromName(region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();
  }

}
