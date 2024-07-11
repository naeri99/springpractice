package com.example.demo.controller;


import java.util.concurrent.Executors;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.ProfileDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.repository.ProfileRepositoty;
import com.example.demo.service.KafkaProducerService;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;
import java.time.Duration;


@RestController
public class MessageController {

    private final KafkaProducerService kafkaProducerService;
    private final UserService userService;
    private final WebClient webClient;

    @Autowired
    public MessageController(KafkaProducerService kafkaProducerService, UserService userService ,WebClient webClient ) {
        this.kafkaProducerService = kafkaProducerService;
        this.userService = userService;
        this.webClient= webClient;
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


    @GetMapping("/source")
    public Mono<String> sendSource(){
        return Mono.just("I am source");
    }



    @GetMapping("/webtest")
    public Mono<String> testWebClient(){
        return  webClient.get().uri("http://34.85.123.208:30080/source")
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(item -> item+" and add this type");
    }


    @GetMapping("/auth/{user}")
    public Mono<String> testAuth(@PathVariable String user ){
        return ReactiveSecurityContextHolder.getContext()
        .flatMap(context ->{
            System.out.println(context);
            return Mono.just("recevier->"+ user);
        });

        // return Mono.just(user);
    }

    @GetMapping("/flux")
    public Flux<String> sendInteger(){

        // return Flux.create(sink -> {
        //     Executors.newFixedThreadPool(1).execute(() -> {
        //         for (int i = 0 ; i <100 ; i++){
        //             sink.next(i);
        //             try{
        //                 Thread.sleep(1);
        //             }catch(Exception e){
        //                 sink.error(e);
        //                 break;
        //             }   
        //         }
        //         sink.complete();
                
        //     });
        // });

        return Flux.range(0,100).delayElements(Duration.ofSeconds(1)).map(item -> String.valueOf(item)).doOnNext(item-> {
            System.out.println(item);
        });

        
        

    }



    


}
