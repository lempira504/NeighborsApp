package com.neighbors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
@Autowired
private MailSender mailSender;


private SimpleMailMessage simplemailmessage;

public void sendmail(String from, String to, String subject, 
    String body){
   /*Code */
}
 
public void sendmail(String from, String []to, String subject, 
    String body){
   /*Code */
}
 
}
