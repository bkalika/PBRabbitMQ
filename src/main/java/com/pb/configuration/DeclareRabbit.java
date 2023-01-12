package com.pb.configuration;

import com.pb.configuration.properties.BindingPropertiesConfig;
import com.pb.configuration.properties.ExchangePropertiesConfig;
import com.pb.configuration.properties.RabbitPropertiesConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author @bkalika
 */
@Component
public class DeclareRabbit implements CommandLineRunner {
    private final Connection connection;
    private final RabbitPropertiesConfig rabbitPropertiesConfig;

    public DeclareRabbit(Connection connection, RabbitPropertiesConfig rabbitPropertiesConfig) {
        this.connection = connection;
        this.rabbitPropertiesConfig = rabbitPropertiesConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        Channel channel = connection.createChannel();
        for(ExchangePropertiesConfig ex : rabbitPropertiesConfig.exchange()) {
            channel.exchangeDeclare(ex.name(), ex.type(), true);

            for(BindingPropertiesConfig config : ex.binding()) {
                channel.queueDeclare(config.queue(), true, false, false, null);
                channel.queueBind(
                        config.queue(),
                        ex.name(),
                        config.route(),
                        config.header() == null ? null : new HashMap<>(config.header())
                );
            }
        }
    }
}
