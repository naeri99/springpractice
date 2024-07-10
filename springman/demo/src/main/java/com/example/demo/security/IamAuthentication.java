package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;
import java.security.Principal;



@RequiredArgsConstructor
public class IamAuthentication implements Authentication{
  
  private final String name;
  
  @Override
	public String getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCredentials'");
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getDetails'");
	}

	@Override
	public Principal getPrincipal() {
    return new Principal(){
        @Override 
        public String getName(){
          return name;
        }
    };
    // TODO Auto-generated method stub
	}

	@Override
	public boolean isAuthenticated() {
    return true;
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("Unimplemented method 'isAuthenticated'");
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setAuthenticated'");
	}


}