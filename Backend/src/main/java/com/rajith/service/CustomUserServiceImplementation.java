package com.rajith.service;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rajith.model.User;
import com.rajith.repository.UserRepository;

//to avoid generating spring security password, Now user entered password can be used
@Service
public class CustomUserServiceImplementation implements UserDetailsService{
	
	private UserRepository userRepository;
	
	public CustomUserServiceImplementation(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found with email-"+username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
}
