package com.divyansh.online.compiler.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.divyansh.online.compiler.Entity.RegistrationEntity;
import com.divyansh.online.compiler.Repository.RegistrationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RegistrationRepository repository;

    private String email;
    private String password;    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	if(repository.existsByEmail(username)) {
        	RegistrationEntity user = repository.findByEmail(username);
        	email = user.getEmail();
        	password = user.getPassword();
        
        }
        
    	return new org.springframework.security.core.userdetails.User(email, password, new ArrayList<>());
        }
	
}
