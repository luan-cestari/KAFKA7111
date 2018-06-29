package com.example.infra.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
  private String bootstrapServers;
  private String requestTimeoutMs;
  private String schemaRegistryURL;
  private String maxBlockMs;
  private String clientId;
  private Integer retries;
  private Integer maxInFlightRequestsPerConnection;
  private String keySerializer;
  private String valueSerializer;
  private String acks;
  private String lingerMs;
  private String batchSize;

  public String getBootstrapServers() {
    return bootstrapServers;
  }

  public void setBootstrapServers(String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }

  public String getRequestTimeoutMs() {
    return requestTimeoutMs;
  }

  public void setRequestTimeoutMs(String requestTimeoutMs) {
    this.requestTimeoutMs = requestTimeoutMs;
  }

  public String getSchemaRegistryURL() {
    return schemaRegistryURL;
  }

  public void setSchemaRegistryURL(String schemaRegistryURL) {
    this.schemaRegistryURL = schemaRegistryURL;
  }

  public String getMaxBlockMs() {
    return maxBlockMs;
  }

  public void setMaxBlockMs(String maxBlockMs) {
    this.maxBlockMs = maxBlockMs;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Integer getRetries() {
    return retries;
  }

  public void setRetries(Integer retries) {
    this.retries = retries;
  }

  public Integer getMaxInFlightRequestsPerConnection() {
    return maxInFlightRequestsPerConnection;
  }

  public void setMaxInFlightRequestsPerConnection(Integer maxInFlightRequestsPerConnection) {
    this.maxInFlightRequestsPerConnection = maxInFlightRequestsPerConnection;
  }

  public String getKeySerializer() {
    return keySerializer;
  }

  public void setKeySerializer(String keySerializer) {
    this.keySerializer = keySerializer;
  }

  public String getValueSerializer() {
    return valueSerializer;
  }

  public void setValueSerializer(String valueSerializer) {
    this.valueSerializer = valueSerializer;
  }

  public String getAcks() {
    return acks;
  }

  public void setAcks(String acks) {
    this.acks = acks;
  }

  public String getLingerMs() {
    return lingerMs;
  }

  public void setLingerMs(String lingerMs) {
    this.lingerMs = lingerMs;
  }

  public String getBatchSize() {
    return batchSize;
  }

  public void setBatchSize(String batchSize) {
    this.batchSize = batchSize;
  }
}
