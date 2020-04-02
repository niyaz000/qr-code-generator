package com.github.niyaz000.qrcodegen.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.region.static}")
  private String region;

  @Value("${aws.s3.endpoint}")
  private String endpoint;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, accessKey);
    final AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    return AmazonS3ClientBuilder
            .standard()
            .enablePathStyleAccess()
            .withEndpointConfiguration(endpointConfig)
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();
  }
}
