package com.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.demo.*;

@Data
@AllArgsConstructor
public class UserDto {
  
  private Integer userId; 
  private ProfileDto profile;
  // public UserDto(Integer id ,ProfileDto profileDto){
  //   this.userId = id ;
  //   this.profile =profileDto;
  // }
}
