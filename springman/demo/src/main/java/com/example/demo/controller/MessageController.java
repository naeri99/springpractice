package com.example.demo.controller;



import com.example.demo.dto.MessageRequest;
import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class MessageController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/check")
    public Mono<String> sendMessageTwo(){
        return Mono.just("helllo");
    }

    @GetMapping("/sendtest")
    public Mono<String> sendMessageTwo(@RequestBody MessageRequest message, @RequestParam String topicName){
        return Mono.just(message.getMessage());
    }

    @PostMapping("/send")
    public Mono<Void> sendMessage(@RequestBody MessageRequest message, @RequestParam String topicName) {
        return kafkaProducerService.sendMessage(topicName, message.getMessage());
    }
}
