package com.github.jiantailang.kafka.domain.ports;

import com.github.jiantailang.kafka.domain.models.Data;

public interface Repository {

  void save(Data data);
}
