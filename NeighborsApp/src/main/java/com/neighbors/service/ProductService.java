package com.neighbors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbors.models.Product;
import com.neighbors.repositories.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	
	public List<Product> all()
	{
		List<Product> productList = productRepository.findAll();
		
		return productList;
	}
	
	public Optional<Product> findById(Long value)
	{

		Optional<Product> optPro = productRepository.findById(value);

		return optPro;
	}
	
	/*	---------------------------------------------------------------------------
	 * finds product by ID and updates its stock quantity and its active field if 
	 * stocks are zero
	 * @param Product product
	 */
	public Optional<Product> findByIdAndUpdate(Product product)
	{
		
		Optional<Product> findProduct = productRepository.findById(product.getId());
		
		if(findProduct.isPresent() && findProduct.get().isActive())
		{
			if((findProduct.get().getUnitsInStock() - product.getUnitsInStock()) > 0)
			{
				product.setUnitsInStock(findProduct.get().getUnitsInStock() - product.getUnitsInStock());
				
				productRepository.save(product);
				
			}else
			{
				product.setActive(false);
				productRepository.save(product);
				
				return null;
			}
				
		}
		
		return findProduct;
	}
	

	public Optional<Product> findBySku(String sku)
	{
//		Optional<Product> pro = productRepository.findByCode(code);
		Optional<Product> pro = productRepository.findBySku(sku);
		
		return pro;
	}
	
	public Product save(Product product)
	{
		
		return productRepository.save(product);
		
	}
	
	public void delete(Product product)
	{
		productRepository.delete(product);
		
	}
	
	public void delete(Long id)
	{
		productRepository.deleteById(id);
	}

	
//	public Product create(int productId, String productCode,String productName,String productSize, int productQuantity, double productPrice)
//	{
//		Product newProduct = new Product();
//		newProduct.setProductId(productId);
//		newProduct.setProductCode(productCode);
//		newProduct.setProductName(productName);
//		newProduct.setProductSize(productSize);
//		newProduct.setProductQuantity(productQuantity);
//		newProduct.setProductPrice(productPrice);
//		
//		
//		Product proList = productRepository.save(newProduct);
//		
//		return proList;
//	}
	
	
//	public Product buyProduct(Product product)
//	{
//		
//		Product newOrder = new Product();
//		
//		
//		Optional<Product> findPro = findByProductCode(product.getProductCode());
//		if(findPro.get().getProductQuantity() >= product.getProductQuantity())
//		{
//			newOrder.setProductQuantity(findPro.get().getProductQuantity() - product.getProductQuantity());
//			
//			product.setProductQuantity(newOrder.getProductQuantity());
//			
//			
//			save(product);
//			
//			return product;
//		}
//		
//		return null;
//	}

}
