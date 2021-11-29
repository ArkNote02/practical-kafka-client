package com.github.jiantailang.kafka.adapters.subscriber;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.PersistDataProxy;
import com.github.jiantailang.messages.DataForMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);
  private final PersistDataProxy proxy;

  public MessageSubscriber(final PersistDataProxy proxy) {
    this.proxy = proxy;
  }

  @KafkaListener(topics = "test-topic")
  public void subscribe(final ConsumerRecord<String, DataForMessage> record,
      final Acknowledgment ack) {

    String topic = record.topic();
    int partition = record.partition();
    String key = record.key();
    DataForMessage message = record.value();
    LOGGER.info("topic:" + topic + " partition:" + partition + " key:" + key
        + " value:" + message.getValue());
    proxy.persist(new Data(message.value()));
    ack.acknowledge();
  }
}
