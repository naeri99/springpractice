package com.example.demo.chatapp;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;


@Component
public class ChatWebSocketHandler implements WebSocketHandler{

    @Override
    public Mono<Void> handle (WebSocketSession webSocketSession){
      return null;
    }
}