package com.neighbors.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.neighbors.models.Event;

@CrossOrigin()
public interface EventRepository extends JpaRepository<Event, Long>{
	
	Page<Event> findById(@RequestParam("id") Long id, Pageable pageable);
	
	Page<Event> findByDescriptionContaining(@RequestParam("description") String description, Pageable pageable);
	
	//http://localhost:8080/neighbors/events/

}
