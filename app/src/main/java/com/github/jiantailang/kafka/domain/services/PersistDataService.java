package com.github.jiantailang.kafka.domain.services;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.PersistDataProxy;
import com.github.jiantailang.kafka.domain.ports.Repository;
import org.springframework.stereotype.Component;

@Component
public class PersistDataService implements PersistDataProxy {

  private final Repository repository;

  public PersistDataService(final Repository repository) {
    this.repository = repository;
  }

  @Override
  public void persist(final Data data) {
    repository.save(data);
  }
}
