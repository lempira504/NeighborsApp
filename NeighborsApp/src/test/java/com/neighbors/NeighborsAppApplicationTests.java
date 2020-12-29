//package com.neighbors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.neighbors.controllers.EmailController;
//import com.neighbors.controllers.ProductController;
//import com.neighbors.controllers.TestController;
//
//@SpringBootTest
//class NeighborsAppApplicationTests {
//
//	@Autowired
//	private EmailController emailController;
//	
//	@Autowired
//	private TestController testController;
//	
//	@Autowired
//	private ProductController productController;
//	
//	@Test
//	void contextLoads() throws Exception {
////		emailController.send("pavontest12@gmail.com");
//		
//		/* -------------------------------------------------------
//		 * Email Controller
//		 * -------------------------------------------------------
//		 */
//		System.out.println("Checking EmailController");
//		assertThat(emailController.send("pavontest12@gmail.com")).isNotNull();//sends email
//		System.out.println("Passed EmailController Checking ");
//		
//		
//		/* -------------------------------------------------------
//		 * Test Controller
//		 * -------------------------------------------------------
//		 */
//		System.out.println("Checking TestController");
////		assertThat(testController.adminAccess()).isNotNull();
//		assertThat(testController.allAccess()).isNotNull();
//		System.out.println("Passed TestController Checking ");
//		
//		
//		/* -------------------------------------------------------
//		 * Test Controller
//		 * -------------------------------------------------------
//		 */
//		System.out.println("Checking ProductController");
////		assertThat(testController.adminAccess()).isNotNull();
//		assertThat(productController.products()).isNotNull();
//		System.out.println("Passed ProductController Checking ");
//	}
//
//}
