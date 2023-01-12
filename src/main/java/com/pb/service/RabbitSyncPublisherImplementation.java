package com.pb.service;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author @bkalika
 */
@Service
public class RabbitSyncPublisherImplementation implements RabbitSyncPublisher {
    private final Connection connection;

    public RabbitSyncPublisherImplementation(Connection connection) {
        this.connection = connection;
    }

    public int publishMessageAndGetReplyCode(byte[] body, Map<String, String> properties) throws TimeoutException, IOException {
        try {
            Channel channel = connection.createChannel();
            AMQP.BasicProperties prop = new AMQP.BasicProperties().builder().headers(new HashMap<>(properties)).build();
            channel.basicPublish("poc-exchange-direct", "route1", true, prop, body);
            channel.close();
        } catch (ShutdownSignalException s) {
            if(s.getReason() instanceof AMQP.Connection.Close) {
                return ((AMQP.Connection.Close) s.getReason()).getReplyCode();
            } else if (s.getReason() instanceof AMQP.Channel.Close) {
                return ((AMQP.Channel.Close) s.getReason()).getReplyCode();
            }
        }

        return 200;
    }
}
