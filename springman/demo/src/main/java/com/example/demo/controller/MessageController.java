package com.example.demo.controller;



import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.repository.ProfileRepositoty;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class MessageController {

    private final KafkaProducerService kafkaProducerService;
    private final UserService userService;

    @Autowired
    public MessageController(KafkaProducerService kafkaProducerService, UserService userService) {
        this.kafkaProducerService = kafkaProducerService;
        this.userService = userService;
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


    @GetMapping("/user")
    public Mono<UserInfoDto> getProfile(@RequestParam String userId) {
        Integer userIndex= Integer.valueOf(userId);
        return userService.getUserInfo(userIndex);
        
    }
    


}
