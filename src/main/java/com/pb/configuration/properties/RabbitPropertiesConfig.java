package com.pb.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author @bkalika
 */
@ConfigurationProperties(value = "rabbit-config")
public record RabbitPropertiesConfig (
    String host,
    int port,
    String username,
    String password,
    List<ExchangePropertiesConfig> exchange
) {
}
