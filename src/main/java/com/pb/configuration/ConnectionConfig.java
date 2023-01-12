package com.pb.configuration;

import com.rabbitmq.client.Connection;
import com.pb.configuration.properties.RabbitPropertiesConfig;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author @bkalika
 */
@Configuration
public class ConnectionConfig {
    @Bean
    public Connection connection(RabbitPropertiesConfig rabbitPropertiesConfig) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitPropertiesConfig.host());
        connectionFactory.setUsername(rabbitPropertiesConfig.username());
        connectionFactory.setPassword(rabbitPropertiesConfig.password());
        connectionFactory.setPort(rabbitPropertiesConfig.port());
        connectionFactory.setRequestedHeartbeat(30);

        return connectionFactory.newConnection();
    }
}
