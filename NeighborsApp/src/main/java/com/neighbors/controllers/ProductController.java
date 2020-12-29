package com.neighbors.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighbors.appexceptions.ProductAppException;
import com.neighbors.messages.MessageResponse;
import com.neighbors.models.Product;
import com.neighbors.service.ProductService;


//@CrossOrigin
//@RestController
//@RequestMapping(value="/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/neighbors/products")
public class ProductController {
	
	@Autowired
	private final ProductService productService;
	
	@Autowired
//	OrderService orderService;
	
	ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	
	/**------------------------------------------------------------------------------------
	 * CREATE
	 * ------------------------------------------------------------------------------------
	 * @param "/"
	 * */
	@GetMapping(value = {"/"}, produces = "application/json")
	public List<Product> index()
	{
		return productService.all();
//		return "{\"message\":\"index\"}";
	}
	
	/**------------------------------------------------------------------------------------
	 * CREATE
	 * ------------------------------------------------------------------------------------
	 * @param /add
	 * */
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product)
	{

		productService.save(product);
		return ResponseEntity.ok(new MessageResponse("Added successfully!"));

	}
	
	/**------------------------------------------------------------------------------------
	 * BUY
	 * ------------------------------------------------------------------------------------
	 * @param /buy
	 * */
	@PostMapping(value = {"/buy"}, produces = "application/json")
	@Transactional
//	public ResponseEntity<?> buyProduct(@RequestBody Product product) throws ProductAppException
	public Product buyProduct(@RequestBody Product product) throws ProductAppException
	{
		Optional<Product> findProduct = productService.findByIdAndUpdate(product);
		
//		return new ResponseEntity<>("Your order has been placed. " + findProduct.get(), HttpStatus.CREATED); 
		return findProduct.get();
	}
	
	/**------------------------------------------------------------------------------------
	 * PRODUCTS
	 * ------------------------------------------------------------------------------------
	 * */
	@GetMapping(value = {"/show"}, produces = "application/json")
	public List<Product> products()
	{
		return productService.all();
	}
	
	/**------------------------------------------------------------------------------------
	 * /find/product/{productCode}
	 * ------------------------------------------------------------------------------------
	 * @author HP
	 * @
	 * */
	@GetMapping(value = {"/find/{productCode}"}, produces = "application/json")
	public ResponseEntity<Object> findProductById(@PathVariable("productCode") String productCode) throws ProductAppException
	{
		Optional<Product> findPro = productService.findBySku(productCode);
		
		if(!findPro.isPresent())
		{
			throw new ProductAppException("Product # "+productCode+" not found");
		}
		
		return new ResponseEntity<>(findPro.get(), HttpStatus.OK);
	}
	
	
	/**------------------------------------------------------------------------------------
	 * /update/product
	 * ------------------------------------------------------------------------------------
	 * */
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) throws ProductAppException
	{
		Product pro = null;
		
		Optional<Product> findCode = productService.findById(product.getId());
		
		if(!findCode.isPresent())
		{
			throw new ProductAppException("Product # "+product.getId()+" not found");
		}
		
		pro = productService.save(product);
		 
		return new ResponseEntity<>(pro, HttpStatus.CREATED); 
	}
	
	/**------------------------------------------------------------------------------------
	 * path: /neighbors/products/{eventId}
	 * ------------------------------------------------------------------------------------
	 * */
	@DeleteMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<?> deleteEvent(@PathVariable Long eventId)
	{
		Optional<Product> pr = productService.findById(eventId);
		productService.delete(pr.get());

		return ResponseEntity.ok(new MessageResponse("Deleted."));
	}
	
	
//	/**
//	 * @getUserNameFromLoggedInUser()
//	 * returns actual user logged in
//	 * */
//	private String getUserNameFromLoggedInUser()
//	{
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String currentPrincipalName = authentication.getName();
//		
//		return currentPrincipalName;
//	}
	
	
	

	
	
//	
//	
//	@RequestMapping(value = "/update/product/", method = RequestMethod.PUT)  
//	public ResponseEntity<Object> updateProduct( 
//			@RequestParam("productId") int productId, 
//			@RequestParam("productCode") String productCode, 
//			@RequestParam("productName") String productName,
//			@RequestParam("productSize") String productSize,
//			@RequestParam("productQuantity") int productQuantity,
//			@RequestParam("productPrice") double productPrice) throws ProductAppException   
//	{  
//		//Optional<Product> findId = productService.finById(productId);
//		
//		
//		//search for repeated id, or code
//		Optional<Product> findCode = productService.findByCode(productCode);
//		
//		//if unique value exists, it throws a message saying it exists.
//		if(!findCode.isPresent())
//		{
//			throw new ProductAppException("Product code: " + productCode + " not found.");
//		}
//
//		//creates the new product
//		Product pro = productService.create(productId, productCode, productName, productSize, productQuantity, productPrice);
//
//		//returns created product in a JSON format.
//		return new ResponseEntity<>(pro, HttpStatus.CREATED); 
//	} 
//
//	
	

}
