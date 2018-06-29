package com.example.infra.config.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {
  public static final String EXAMPLE_CREATION_TOPIC = "kafka7111.example.creation";

  private KafkaProperties kafkaProperties;

  @Autowired
  public KafkaConfig(KafkaProperties kafkaProperties) {
    this.kafkaProperties = kafkaProperties;
  }

  @Bean
  public KafkaAdmin admin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    configs.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaProperties.getRequestTimeoutMs());
    return new KafkaAdmin(configs);
  }

  @Bean("exampleCreationTopic")
  @ConditionalOnClass(KafkaAdmin.class)
  public NewTopic userAgreementsUpdateTopic() {
    return new NewTopic(EXAMPLE_CREATION_TOPIC, 10, (short) 2);
  }

  @Bean("defaultProducerConfig")
  public Map<String, Object> defaultProducerConfig() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
    properties.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());
    properties.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetries());
    properties.put(
        ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
        kafkaProperties.getMaxInFlightRequestsPerConnection());
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
    properties.put(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
    properties.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcks());
    properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaProperties.getRequestTimeoutMs());
    properties.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProperties.getBatchSize());
    properties.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProperties.getLingerMs());
    properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, kafkaProperties.getMaxBlockMs());
    properties.put(
        KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
        kafkaProperties.getSchemaRegistryURL());
    return properties;
  }
}
