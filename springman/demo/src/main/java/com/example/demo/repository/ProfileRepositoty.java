package com.example.demo.repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProfileDto;

@Repository
public class ProfileRepositoty {

    public Map<Integer, ProfileDto> userContainer;


    ProfileRepositoty(){
      userContainer = new HashMap<Integer, ProfileDto>();
      userContainer.put(1 , new ProfileDto("sung", "lower", 12 ) );
      userContainer.put(2,  new ProfileDto("bum", "lower", 16 ));
      userContainer.put(3,  new ProfileDto("park", "middle", 20 ));
      userContainer.put(4,  new ProfileDto("professor", "higher", 24 ) );
    }


    public ProfileDto getProfileById(Integer  id ){
      return userContainer.get(id);
    }


}
