package com.example.demo.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.event.Event;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/notifications")
@RestController
public class NotificationController {

  private static Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();
  private static AtomicInteger lastEventId = new AtomicInteger(1);

  // @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  // public Flux<String> NotificationController(){
  //     return Flux.interval(Duration.ofMillis(0)).map(v-> "hello world");
  // }


  // @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  // public Flux<String> NotificationController(){
  //     return sink.asFlux();
  // }

  @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent> getNotifications(){
      return sink.asFlux().map(message -> {
        String idman = ""+lastEventId.getAndIncrement();              
        return ServerSentEvent
                              .builder(message).event("notification")
                              .id(idman).build();
      
  });
  }


  @PostMapping()
  public Mono<String> addNotification(@RequestBody Event event) {
      String notificationMessage  =event.getType() + ": " + event.getMessage();
      sink.tryEmitNext(notificationMessage);
      return Mono.just("ok");
  }
  
  
}
