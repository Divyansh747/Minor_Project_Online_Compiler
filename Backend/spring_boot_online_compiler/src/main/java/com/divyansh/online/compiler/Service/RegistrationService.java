package com.divyansh.online.compiler.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divyansh.online.compiler.Entity.RegistrationEntity;
import com.divyansh.online.compiler.Repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	public RegistrationService(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}
	
	public Optional<RegistrationEntity> createUser(RegistrationEntity registrationRequest) throws Exception {

		if (registrationRepository.existsByEmail(registrationRequest.getEmail())) {
			throw new Exception("Email Already Exists!");
		}

		System.out.println("Data received: " + registrationRequest.toString());

		RegistrationEntity registrationEntity = registrationRepository
				.save(new RegistrationEntity(registrationRequest.getEmail(), registrationRequest.getUsername(), registrationRequest.getPassword()));

		return Optional.ofNullable(registrationEntity);
	}
	
	public List<RegistrationEntity> findAll() {
		return registrationRepository.findAll(); 
	}
	
}
