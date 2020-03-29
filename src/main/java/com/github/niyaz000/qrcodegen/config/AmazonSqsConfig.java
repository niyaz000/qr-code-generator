package com.github.niyaz000.qrcodegen.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AmazonSqsConfig {

  @Bean
  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
    factory.setAmazonSqs(amazonSqs);
    factory.setAutoStartup(true);
    factory.setMaxNumberOfMessages(10);
    factory.setTaskExecutor(createDefaultTaskExecutor());
    return factory;
  }

  private AsyncTaskExecutor createDefaultTaskExecutor() {
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix("QrCodeMessageThread-");
    threadPoolTaskExecutor.afterPropertiesSet();
    return threadPoolTaskExecutor;
  }

}
