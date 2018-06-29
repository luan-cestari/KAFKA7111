package com.example.application;

import com.example.event.avro.example.creation.ExampleEvent;
import com.example.infra.config.kafka.KafkaConfig;
import com.example.model.Example;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ExampleApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleApplication.class);
  private static final int MAX = 1000000;
  private static final int MIN = 1;

  private KafkaTemplate<Long, ExampleEvent> kafkaTemplate;

  @Autowired
  public ExampleApplication(
      @Value("#{defaultProducerConfig}") Map<String, Object> defaultProducerConfig) {
    this.kafkaTemplate =
        new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(defaultProducerConfig));
  }

  public ExampleApplication(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public Example create() {
    sendSilently(
        ExampleEvent.newBuilder()
            .setExampleId(String.valueOf(new Random().nextInt((MAX - MIN) + 1) + MIN))
            .setOwner("Fixed")
            .build());
    return null;
  }

  private void sendSilently(final ExampleEvent exampleEvent) {
    try {
      LOGGER.info(
          "external=kafka status=sending messageId=" + exampleEvent.getExampleId().hashCode());

      final ListenableFuture<SendResult<Long, ExampleEvent>> future =
          kafkaTemplate.send(
              KafkaConfig.EXAMPLE_CREATION_TOPIC,
              Long.valueOf(exampleEvent.getExampleId().hashCode()),
              exampleEvent);
      future.addCallback(
          new ListenableFutureCallback<SendResult<Long, ExampleEvent>>() {
            @Override
            public void onSuccess(final SendResult<Long, ExampleEvent> message) {
              LOGGER.info(
                  "external=kafka status=sent messageId=" + exampleEvent.getExampleId().hashCode());
            }

            @Override
            public void onFailure(final Throwable throwable) {
              LOGGER.error(
                  "external=kafka status=error messageId="
                      + exampleEvent.getExampleId().hashCode()
                      + " message='"
                      + exampleEvent
                      + "' exception=\"{}\"",
                  throwable.getMessage());
            }
          });
    } catch (Exception e) {
      LOGGER.error(
          "cause=exception_from_kafka impact=\"could not send ExampleEvent\" exception=\"{}\" message='"
              + exampleEvent
              + "'",
          e.getMessage());
    }
  }
}
