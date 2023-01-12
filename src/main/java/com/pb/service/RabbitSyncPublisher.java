package com.pb.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author @bkalika
 */
public interface RabbitSyncPublisher {
    int publishMessageAndGetReplyCode(byte[] body, Map<String,String> properties) throws TimeoutException, IOException;
}
