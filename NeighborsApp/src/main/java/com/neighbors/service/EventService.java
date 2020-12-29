package com.neighbors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbors.models.Event;
import com.neighbors.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	public List<Event> getEvents()
	{
		return eventRepository.findAll();
	}
	
	public Event createEvent(Event event)
	{
		return eventRepository.save(event);
	}
	
	public Event updateEvent(Event event)
	{
		return eventRepository.save(event);
	}

	public void delete(Long id)
	{
		eventRepository.deleteById(id);
	}
}
