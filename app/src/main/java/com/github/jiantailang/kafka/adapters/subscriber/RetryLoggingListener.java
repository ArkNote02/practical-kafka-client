package com.github.jiantailang.kafka.adapters.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class RetryLoggingListener implements RetryListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(RetryLoggingListener.class);

  @Override
  public <T, E extends Throwable> boolean open(final RetryContext context,
      final RetryCallback<T, E> callback) {

    return false;
  }

  @Override
  public <T, E extends Throwable> void close(final RetryContext context,
      final RetryCallback<T, E> callback, final Throwable throwable) {

  }

  @Override
  public <T, E extends Throwable> void onError(final RetryContext context,
      final RetryCallback<T, E> callback, final Throwable throwable) {

    LOGGER.error("log in retry listener", throwable);
  }
}
