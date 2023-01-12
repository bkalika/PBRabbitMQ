package com.pb.controllers;

import com.pb.service.RabbitSyncPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author @bkalika
 */
@RestController
@RequestMapping("/v1/producers")
public class ProducerController {
    private final RabbitSyncPublisher rabbitSyncPublisher;

    public ProducerController(RabbitSyncPublisher rabbitSyncPublisher) {
        this.rabbitSyncPublisher = rabbitSyncPublisher;
    }

    @PostMapping
    private ResponseEntity<Integer> publishMessage(@RequestParam byte[] body) throws IOException, TimeoutException {
        Map<String, String> rabbitProps = new HashMap<>();
        rabbitProps.put("delivery_mode", "1");
        rabbitProps.put("priority", "0");
        rabbitProps.put("content_encoding", "UTF-8");
        rabbitProps.put("content_type", "application/json");

        return ResponseEntity.ok(rabbitSyncPublisher.publishMessageAndGetReplyCode(body, rabbitProps));
    }
}
