package it.pit.security.authservice.security.service;

import it.pit.security.authservice.security.payload.LoginDto;
import it.pit.security.authservice.security.payload.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    ResponseEntity<?> confirmEmail(String confirmationToken);
    
}
