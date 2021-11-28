package com.github.jiantailang.kafka.adapters.http;

import com.github.jiantailang.kafka.domain.ports.ServiceProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HttpAdapter {

    private final ServiceProxy proxy;

    public HttpAdapter(final ServiceProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/")
    public String test() {
        return "";
    }
}
