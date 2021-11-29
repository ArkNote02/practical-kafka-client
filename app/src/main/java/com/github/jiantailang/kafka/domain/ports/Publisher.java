package com.github.jiantailang.kafka.domain.ports;

import com.github.jiantailang.kafka.domain.models.Data;

public interface Publisher {

  void publish(String topic, Data data);
}
