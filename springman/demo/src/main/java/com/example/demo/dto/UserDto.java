package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.demo.*;

@Data
@AllArgsConstructor
public class UserDto {
  
  private Integer userId; 
  private String profile;

}
