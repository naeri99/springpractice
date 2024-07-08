package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Additionanllnfo;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.repository.*;

import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final ProfileRepositoty profileRepository;
    private final AdditionalInfoRepository additionalInfoRepository;

    @Autowired
    public UserService(ProfileRepositoty profileRepository, AdditionalInfoRepository additionalInfoRepository) {
      this.profileRepository = profileRepository;
      this.additionalInfoRepository = additionalInfoRepository;
    }


    public Mono<UserInfoDto> getUserInfo(Integer id){
        return Mono.just(profileRepository.getProfileById(id)).flatMap(profile-> {
          Additionanllnfo adinfo=  additionalInfoRepository.getAdditionalInfo(profile.getPosition()); 
          return Mono.just(new UserInfoDto( id, profile, adinfo));
        });
      } 
  
}
