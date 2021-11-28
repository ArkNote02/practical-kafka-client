package com.github.jiantailang.kafka.adapters.publisher;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class AvoidOfflinePartitioner implements Partitioner {

  private Random random = new Random();

  @Override
  public void configure(final Map<String, ?> configs) {

  }

  @Override
  public int partition(final String topic, final Object key, final byte[] keyBytes, final Object value, final byte[] valueBytes, final Cluster cluster) {
    // TODO
    List<PartitionInfo> list = cluster.availablePartitionsForTopic(topic);
    return random.nextInt(list.size());
  }

  @Override
  public void close() {

  }
}
