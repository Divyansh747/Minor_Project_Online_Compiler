package com.divyansh.online.compiler.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class RegistrationEntity {

	@Id
	private String email;
	@NaturalId(mutable=true)
	private String username;
	@NaturalId(mutable=true)
	private String password;
	
	public RegistrationEntity() {
		
	}
	
	public RegistrationEntity(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistrationEntity [email=" + email + ", username=" + username + ", password=" + password + "]";
	}
	
}
