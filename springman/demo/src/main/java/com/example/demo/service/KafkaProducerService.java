package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public Mono<Void> sendMessage(String topic, String message) {
        return Mono.fromRunnable(() -> kafkaTemplate.send(topic, message));
    }
}
