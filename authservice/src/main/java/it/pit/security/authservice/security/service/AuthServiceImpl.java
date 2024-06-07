package it.pit.security.authservice.security.service;

import it.pit.security.authservice.security.entity.ConfirmationToken;
import it.pit.security.authservice.security.entity.ERole;
import it.pit.security.authservice.security.entity.Role;
import it.pit.security.authservice.security.entity.User;
import it.pit.security.authservice.security.exception.MyAPIException;
import it.pit.security.authservice.security.payload.LoginDto;
import it.pit.security.authservice.security.payload.RegisterDto;
import it.pit.security.authservice.security.repository.ConfirmationTokenRepository;
import it.pit.security.authservice.security.repository.RoleRepository;
import it.pit.security.authservice.security.repository.UserRepository;
import it.pit.security.authservice.security.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;







@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired private ConfirmationTokenRepository confirmationTokenRepository;
//    @Autowired private EmailService emailService;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getUsername(), loginDto.getPassword()
        		)
        ); 
    	System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setFullname(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        
        if(registerDto.getRoles() != null) {
	        registerDto.getRoles().forEach(role -> {
	        	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	        	roles.add(userRole);
	        });
        } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
        }
        
        user.setRoles(roles);
        System.out.println(user);
        userRepository.save(user);
     
//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//        confirmationTokenRepository.save(confirmationToken);
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registration! This Email is valid to 24 Hours");
//        mailMessage.setText("To confirm your account, please click here : "
//                +"http://localhost:8081/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
//        emailService.sendEmail(mailMessage);
//
//        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
//
        return "Check your email to confirm.";
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ADMIN")) return ERole.ROLE_ADMIN;
    	else if(role.equals("MODERATOR")) return ERole.ROLE_MODERATOR;
    	else if(role.equals("COLLABORATOR")) return ERole.ROLE_CONTRIBUTOR;
        else return ERole.ROLE_USER ;
    }



	@Override
	public ResponseEntity<?> confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		 if (token != null) {
		        Date dataAttuale = new Date();
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(token.getCreatedDate());
		        cal.add(Calendar.HOUR, 24); // validità del token 24 ore
		        System.out.println(cal);
		        if (cal.getTime().after(dataAttuale)) {
		            Optional<User> user = userRepository.findByEmail(token.getUser().getEmail());

		            if (user.isPresent()) {
		                User userPresent = user.get();
		                userPresent.setIsActive(true);
		                userRepository.save(userPresent);
                        confirmationTokenRepository.delete(token);
		                return ResponseEntity.ok("Email verified successfully!");
		            }
		        }
		    }
		    
		    return ResponseEntity.badRequest().body("Error: Couldn't verify email");
		}




    
}
