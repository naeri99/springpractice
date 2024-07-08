package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {
  private Integer userId; 
  private ProfileDto profile;
  private Additionanllnfo additionanllnfo;
}
