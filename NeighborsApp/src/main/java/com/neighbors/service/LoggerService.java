package com.neighbors.service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.stereotype.Service;

import com.neighbors.controllers.EmailController;

@Service
public class LoggerService {
	private Logger logger = Logger.getLogger(EmailController.class.getName());
	private FileHandler fileHandler;

	/*
	 * this method creates a logger.
	 * @param Level level (Level.info)
	 * @param String description (new event created.)
	 * @param String loggerPath (log/Event.log)
	 * 
	 */
//	public void eventLogger(Level level, String description, String loggerName)
	public void eventLogger(String description, String loggerName)
	{

		logger.setLevel(Level.INFO);
	    
	    try {
			fileHandler = new FileHandler("logs/"+loggerName);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			

			logger.info(description);
		} catch (SecurityException | IOException e) {

			System.out.println("e.getMessage()>>>>>>>>>"+e.getMessage());
			logger.setLevel(Level.SEVERE);
			logger.info("Error created "+ description);
		}
	}
}
