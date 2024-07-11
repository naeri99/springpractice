package com.example.demo.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
  private String type; 
  private String message;
}
