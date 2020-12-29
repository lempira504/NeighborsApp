package com.neighbors.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.neighbors.models.User;

@CrossOrigin()
//@PreAuthorize("hasAuthority('ADMIN')")
//@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	Page<User> findById(@RequestParam("id") Long id, Pageable pageable);
	
//	Page<User> findByFirstNameContaining(@RequestParam("firstname") String firstName, Pageable pageable);
//	Page<User> findByUsernameContaining(@RequestParam("username") String username, Pageable pageable);
	Page<User> findByEmailContaining(@RequestParam("username") String username, Pageable pageable);
	
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	/**for mail registration purpose*/
	User findByEmailIgnoreCase(String email);

//	Changed
	@Query("select u from User u where u.username = :username")
	List<User> findUserDetails(@Param("username") String username);
}
