package com.divyansh.online.compiler.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divyansh.online.compiler.Entity.RegistrationEntity;
import com.divyansh.online.compiler.Request.AuthRequest;
import com.divyansh.online.compiler.Service.RegistrationService;
import com.divyansh.online.compiler.Utility.JwtUtil;

@RestController
public class HomeController {

	@Autowired
	RegistrationService registrationService;

	@Autowired
    private JwtUtil jwtUtil;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
        	System.out.println("## "+ex);
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }	    
	
    @CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping("/userlogin")
	public String userValidation() {
		return "User Login successfull";
	}
	
}