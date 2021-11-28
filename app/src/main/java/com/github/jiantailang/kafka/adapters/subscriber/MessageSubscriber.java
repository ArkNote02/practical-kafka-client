package com.github.jiantailang.kafka.adapters.subscriber;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageSubscriber {

  @KafkaListener(topics = "test-topic")
  public void subscribe(final ConsumerRecord<String, Message> record, final Acknowledgment ack) {
    String topic = record.topic();
    String key = record.key();
    Message message = record.value();
    ack.acknowledge();
  }
}
