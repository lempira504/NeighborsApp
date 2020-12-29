package com.neighbors.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.neighbors.models.Address;

@CrossOrigin()
public interface AddressRepository extends JpaRepository<Address, Long> {

	Page<Address> findById(@RequestParam("userid") Long id, Pageable pageable);
	Page<Address> findByUserId(@RequestParam("userid") Long userid, Pageable pageable);
	
}
