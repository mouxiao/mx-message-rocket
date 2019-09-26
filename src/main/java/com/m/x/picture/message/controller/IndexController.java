package com.m.x.picture.message.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class IndexController {

  @RequestMapping(value = "index")
  public Mono<String> index() {
    return Mono.just("index");
  }
}
