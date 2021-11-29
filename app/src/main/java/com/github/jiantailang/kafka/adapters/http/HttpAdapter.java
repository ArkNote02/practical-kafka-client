package com.github.jiantailang.kafka.adapters.http;

import com.github.jiantailang.kafka.domain.models.Data;
import com.github.jiantailang.kafka.domain.ports.PublishDataProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpAdapter {

  private final PublishDataProxy proxy;

  public HttpAdapter(final PublishDataProxy proxy) {
    this.proxy = proxy;
  }

  @GetMapping("/")
  public String test() {
    proxy.execute(new Data("1"));
    return "OK";
  }
}
