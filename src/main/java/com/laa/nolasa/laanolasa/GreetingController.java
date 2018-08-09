package com.laa.nolasa.laanolasa;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {

  @RequestMapping("/hello/{name}")
  String hello(@PathVariable String name) {
    return "Hello, " + name + "!";
  }
}