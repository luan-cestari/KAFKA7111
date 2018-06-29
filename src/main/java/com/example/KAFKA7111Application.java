package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableKafka
public class KAFKA7111Application {
  public static void main(String[] args) {
    SpringApplication.run(KAFKA7111Application.class);
  }
}
