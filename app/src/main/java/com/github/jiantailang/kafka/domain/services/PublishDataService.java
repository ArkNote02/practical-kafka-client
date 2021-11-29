package com.github.jiantailang.kafka.domain.services;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.PublishDataProxy;
import com.github.jiantailang.kafka.domain.ports.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublishDataService implements PublishDataProxy {

  private static final String TOPIC_NAME = "test-topic";
  private final Publisher publisher;

  public PublishDataService(final Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void execute(final Data data) {
    publisher.publish(TOPIC_NAME, data);
  }
}
