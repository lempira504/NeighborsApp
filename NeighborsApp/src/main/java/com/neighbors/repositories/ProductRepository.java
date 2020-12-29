package com.neighbors.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.neighbors.models.Event;
import com.neighbors.models.Product;


@CrossOrigin()
public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
	
	public Optional<Product> findBySku(String sku);
//	Page<Product> findBySku(@RequestParam("sku") String sku, Pageable pageable);
	
	public Optional<Product> findByName(String name);
	
//	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	Page<Event> findByDescriptionContaining(@RequestParam("description") String description, Pageable pageable);
	
	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	

}
