package com.neighbors.service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.stereotype.Service;

import com.neighbors.controllers.EmailController;

@Service
public class EventLoggerService {
	private Logger logger = Logger.getLogger(EmailController.class.getName());
	private FileHandler fileHandler;

	/*
	 * this method creates a logger.
	 * @param Level level (Level.info)
	 * @param String description (new event created.)
	 * @param String userEmail (user email)
	 * @param String loggerPath (log/Event.log)
	 * 
	 */
	public void createEventLogger(Level level, String description, String userEmail, String loggerName)
	{

		logger.setLevel(level);
	    
	    try {
			fileHandler = new FileHandler("logs/"+loggerName);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			
//			logger.info("new event created by: " + userEmail);
			logger.info(description + " " + userEmail);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("e.getMessage()>>>>>>>>>"+e.getMessage());
			logger.setLevel(Level.SEVERE);
			logger.info("Error created by either an empty user email or not folder to include the log file. " + userEmail);
		}
	}
}
