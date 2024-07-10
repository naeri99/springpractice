package com.example.demo.security;

import com.example.demo.security.IamAuthentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.demo.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.util.context.Context;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
@Component
public class SecuritybFilter implements WebFilter{
  private final AuthService authService;


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
    final ServerHttpResponse resp = exchange.getResponse();
    String iam = exchange.getRequest().getHeaders().getFirst("X-I-AM");
    


    if (iam == null){
      resp.setStatusCode(HttpStatus.UNAUTHORIZED);
      return resp.setComplete();
    }


    return authService.getNameByToken(iam).switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Iam token")))
                      .map(name -> {
                        System.out.println("check ->"+name);
                          if (name == null) {
                              throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid IAM token");
                           }
                          return name;
                      })
                      .map(token -> new IamAuthentication(token))
                      .flatMap(authentication -> 
                          chain.filter(exchange)
                               .contextWrite(context -> {
                                   Context newContext = ReactiveSecurityContextHolder.withAuthentication(authentication);
                                   return context.putAll(newContext);
                               })
                      );

    // return authService.getNameByToken(iam)
    //                   .map(token -> new IamAuthentication(token))
    //                   .flatMap(authentication -> 
    //                       chain.filter(exchange)
    //                            .contextWrite(context -> {
    //                                Context newContext = ReactiveSecurityContextHolder.withAuthentication((Authentication) authentication);
    //                                return context.putAll(newContext);
    //                            })
    //                   );

    // }
  }
}
