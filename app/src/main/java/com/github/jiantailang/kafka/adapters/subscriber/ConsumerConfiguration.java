package com.github.jiantailang.kafka.adapters.subscriber;

import com.github.jiantailang.messages.DataForMessage;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class ConsumerConfiguration {

  @Bean
  public RetryPolicy retryPolicy() {
    return new AlwaysRetryPolicy();
  }

  @Bean
  public BackOffPolicy backOffPolicy() {
    ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
    backOffPolicy.setInitialInterval(100);
    backOffPolicy.setMaxInterval(1000);
    backOffPolicy.setMultiplier(1.1);
    return backOffPolicy;
  }

  @Bean
  public RetryTemplate retryTemplate(final RetryPolicy retryPolicy,
      final BackOffPolicy backOffPolicy, final RetryListener retryListener) {

    RetryTemplate retryTemplate = new RetryTemplate();
    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setBackOffPolicy(backOffPolicy);
    retryTemplate.setListeners(new RetryListener[]{ retryListener });
    return retryTemplate;
  }

  @Bean
  public ConsumerFactory<String, DataForMessage> consumerFactory(
      final KafkaProperties kafkaProperties) {

    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
  }

  @Bean
  public ErrorHandler errorHandler() {
    SeekToCurrentErrorHandler errorHandler = new SeekToCurrentErrorHandler();
    errorHandler.setCommitRecovered(true);
    return errorHandler;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, DataForMessage> listenerContainerFactory(
      final ConsumerFactory<String, DataForMessage> consumerFactory,
      final ErrorHandler errorHandler, final RetryTemplate retryTemplate,
      final KafkaProperties kafkaProperties) {

    ConcurrentKafkaListenerContainerFactory<String, DataForMessage> listenerContainerFactory
        = new ConcurrentKafkaListenerContainerFactory<>();
    listenerContainerFactory.setConsumerFactory(consumerFactory);
    listenerContainerFactory.setErrorHandler(errorHandler);
    listenerContainerFactory.setRetryTemplate(retryTemplate);

    KafkaProperties.Listener listenerProperties = kafkaProperties.getListener();
    ContainerProperties containerProperties = listenerContainerFactory.getContainerProperties();
    containerProperties.setAckMode(listenerProperties.getAckMode());

    return listenerContainerFactory;
  }
}
