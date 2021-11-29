package com.github.jiantailang.kafka.adapters.repository;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataRepository implements Repository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataRepository.class);

  @Override
  public void save(final Data data) {
    LOGGER.info("DataRepository#save has received " + data);
  }
}
