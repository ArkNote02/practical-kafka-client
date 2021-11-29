package com.github.jiantailang.kafka.adapters.publisher;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.Publisher;
import com.github.jiantailang.messages.DataForMessage;
import java.util.concurrent.ExecutionException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class MessagePublisher implements Publisher {

  private final KafkaTemplate<String, DataForMessage> kafkaTemplate;

  public MessagePublisher(final KafkaTemplate<String, DataForMessage> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publish(final String topic, final Data data) {
    try {
      ListenableFuture<SendResult<String, DataForMessage>> future
          = kafkaTemplate.send(topic, new DataForMessage(data.value()));
      future.get();
    } catch (final InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}
