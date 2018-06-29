package com.example.endpoint;

import com.example.application.ExampleApplication;
import com.example.model.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleEndpoint {

  private final ExampleApplication application;

  @Autowired
  public ExampleEndpoint(ExampleApplication application) {
    this.application = application;
  }

  @ResponseBody
  @PostMapping("/rs/examples")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity create() {
    Example persistedExample = application.create();

    ExampleResource exampleResource = new ExampleResource(persistedExample);

    return new ResponseEntity<>(exampleResource, HttpStatus.CREATED);
  }
}
