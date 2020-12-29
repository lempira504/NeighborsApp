package com.neighbors.controllers;

import java.util.logging.Level;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighbors.models.MailMessage;
import com.neighbors.service.EventLoggerService;
import com.neighbors.service.MailService;
import com.neighbors.service.UserService;


/**
 * This class contains a Mail API developed using Spring Boot
 * 
 * @author MukulJaiswal
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="mail")
public class EmailController {
	
	@Autowired
	EventLoggerService eventLoggerService;
	
//	private final static Logger logger = Logger.getLogger(EmailController.class.getName());
//	private FileHandler fileHandler;

	@Autowired
	private MailService notificationService;
	
	@Autowired
	private MailMessage message;
	
	@Autowired
	private final UserService userService;
	
	private final JavaMailSender javaMailSender;
	
	@Autowired
	EmailController(JavaMailSender javaMailSender, UserService userService, EventLoggerService eventLoggerService) {
	   this.javaMailSender = javaMailSender;
	   this.userService = userService;
	   this.eventLoggerService = eventLoggerService;
	}

//	@GetMapping("/send-mail")
	@GetMapping(value= {"/send-mail/{userEmail}"}, produces = "application/json")
//	public String send() {
	public ResponseEntity<?> send(@PathVariable("userEmail") String userEmail) {

			/**
			 * ---------------------------------Creating an instance and sending the email
			 * */
			
		    SimpleMailMessage message = new SimpleMailMessage(); //creating message   
		    message.setFrom("pavontest12@gmail.com"); //set from who is the email
		    message.setTo(userEmail);//sends email to recipient coming from FrontEnd
		    message.setCc("pavontest12@gmail.com");
//		    message.setTo("pavontest12@gmail.com");//send one email
//		    message.setTo("pavontest12@gmail.com", "pavonsig@yahoo.com"); //passing array of recipients 
//		    message.setTo(recipients);//sends an array of recipients (emails) 
		    
	    	message.setSubject("Neighbors"); //set title of email  
		    message.setText("Thank you for your purchase! " + userEmail + " cc: pavontest12@gmail.com");//set description of email
		    
		    javaMailSender.send(message); //sends email
		    
		    
		    /**
			 * ---------------------------------end Creating an instance and sending the email
			 * */
			

		    /**Create logger to report send emails*/
		    
		    eventLoggerService.createEventLogger(Level.INFO, "Event created by: ",userEmail, "event.log");
		    
		    return new ResponseEntity<>(message.getTo(), HttpStatus.CREATED); 
//		    throw new ProductAppException("Product code: " + product.getProductCode() + " already exists.");
//			return ResponseEntity.ok(new MessageResponse("Congratulations! Your mail has been send to the user"));
	}
	

	/**
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@GetMapping("/send-mail-attachment")
	public String sendWithAttachment(@RequestBody MailMessage message) throws MessagingException {

		/*
		 * Creating a MailMessage with the help of MailMessage class that we have declared. Setting
		 * the First,Last and Email address of the sender.
		 */

		message.setEmailAddress("pavontest12@gmail.com");// Receiver's email address
		message.setSubject("testAttachement");
		message.setBodyText("You have done a good job! \n Please find attached file");
		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {

			notificationService.sendEmailWithAttachment(message);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

}
