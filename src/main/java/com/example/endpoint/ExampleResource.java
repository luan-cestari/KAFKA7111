package com.example.endpoint;

import com.example.model.Example;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExampleResource {

  public ExampleResource(Example persistedExample) {}
}
