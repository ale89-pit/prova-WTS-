package it.pit.security.authservice.security.runner;

import it.pit.security.authservice.security.entity.ERole;
import it.pit.security.authservice.security.entity.Role;
import it.pit.security.authservice.security.payload.RegisterDto;
import it.pit.security.authservice.security.repository.RoleRepository;
import it.pit.security.authservice.security.repository.UserRepository;
import it.pit.security.authservice.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;









@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired
	AuthService authService;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Metodo da lanciare solo la prima volta
		// Serve per salvare i ruoli nel DB
		if(roleRepository.findAll().isEmpty() || roleRepository.findAll().size() < ERole.values().length) {
			
			setRoleDefault();
		}
		if(!userRepository.existsByEmail("admin@admin.it")) {
			creatAdminUser();
		}

		
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);

		Role contributor = new Role();
		contributor.setRoleName(ERole.ROLE_CONTRIBUTOR);
		roleRepository.save(contributor);
		

	}


	private void creatAdminUser(){
		RegisterDto registerDto = new RegisterDto();
		registerDto.setEmail("admin@admin.it");
		registerDto.setName("admin");
		registerDto.setUsername("admin");
		Set <String> roles = new HashSet<String>();
		roles.add("ADMIN");
		registerDto.setRoles(roles);
		registerDto.setPassword("admin");
		authService.register(registerDto);


	}
	
	

}
