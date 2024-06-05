package it.pit.security.authservice.security.repository;

import it.pit.security.authservice.security.entity.ERole;
import it.pit.security.authservice.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
