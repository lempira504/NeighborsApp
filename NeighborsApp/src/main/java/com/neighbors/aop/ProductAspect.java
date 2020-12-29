package com.neighbors.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbors.service.LoggerService;

@Aspect
@Component
public class ProductAspect {
	
	@Autowired
	LoggerService loggerService;

	/** adds logger every time a product is added */
//	@Before("execution(* com.company.service.ProductService.*(..))")
	public void productAspect()
	{
		System.out.println("Displaying product name");
		loggerService.eventLogger("Displaying product name", "product.log");
	}
	

}
