package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import com.example.demo.chatapp.ChatWebSocketHandler;


@Configuration
public class MappingConfig{

  @Bean
  SimpleUrlHandlerMapping SimpleUrlHandlerMapping(ChatWebSocketHandler chatWebSocketHandler){
    Map<String, WebSocketHandler>  urlMapper = Map.of(
        "/chat", chatWebSocketHandler

    );
    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setOrder(1);
    mapping.setUrlMap(urlMapper);
    return mapping;

  }

}