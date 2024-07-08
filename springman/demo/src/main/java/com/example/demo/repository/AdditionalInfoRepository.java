package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.Additionanllnfo;
import com.example.demo.dto.ProfileDto;
import java.util.HashMap;
import java.util.Map;
import reactor.core.publisher.Mono;


@Repository
public class AdditionalInfoRepository {
    public Map<String, Additionanllnfo> addContainer;


    public AdditionalInfoRepository() {
      addContainer = new HashMap<String, Additionanllnfo>();
      addContainer.put("lower", new Additionanllnfo(false, "no house"));
      addContainer.put("middle", new Additionanllnfo(true, "no house"));
      addContainer.put("higher", new Additionanllnfo(true, "buy house"));
    }


    public Additionanllnfo getAdditionalInfo(String keys){
      Additionanllnfo searchValue ; 
      if (this.addContainer.get(keys) == null){
        searchValue= new Additionanllnfo(false, "null");
      }else{
        searchValue = this.addContainer.get(keys);
       }
      return searchValue;
    }


}
