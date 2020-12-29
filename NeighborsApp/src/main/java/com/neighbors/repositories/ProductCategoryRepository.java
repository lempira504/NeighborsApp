package com.neighbors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.neighbors.models.ProductCategory;


@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
//	Page<Product> categoryName(@RequestParam("categoryName") Long categoryName, Pageable pageable);
}
