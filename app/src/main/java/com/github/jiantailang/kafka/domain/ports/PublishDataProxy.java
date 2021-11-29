package com.github.jiantailang.kafka.domain.ports;

import com.github.jiantailang.kafka.domain.models.Data;

public interface PublishDataProxy {

  void execute(Data data);
}
