package com.neighbors.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighbors.models.Event;
import com.neighbors.payload.response.MessageResponse;
import com.neighbors.service.EventService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EventContoller.NEIGHBORS_EVENT)
public class EventContoller {

	public static final String NEIGHBORS_EVENT = "/neighbors/event";
	
	@Autowired
	private final EventService eventService;
	
	public EventContoller(EventService eventService)
	{
		this.eventService = eventService;
	}
	
	@GetMapping(path = "", produces = "application/json")
//	@PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
	public List<Event> getEvents()
	{
//		return "Hi";
		return eventService.getEvents();
//		return ResponseEntity.ok(new MessageResponse("Events"));
	}
	
	
	@GetMapping(path = "/{eventId}", produces= MediaType.APPLICATION_JSON_VALUE)
	public String getEventById(@PathVariable String eventId)
	{
		return eventId;
	}
	
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
//	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<?> createEvent(@RequestBody Event event)
	{
		eventService.createEvent(event);
		return ResponseEntity.ok(new MessageResponse("Created"));
	}
	
	@PutMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEvent(@PathVariable String eventId, @RequestBody Event event)
	{
		eventService.updateEvent(event);
		System.out.println("**************getAddress"+event.getAddress());
		return ResponseEntity.ok(new MessageResponse("updated"));
	}
	
	@DeleteMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteEvent(@PathVariable Long eventId)
	{
		eventService.delete(eventId);
		return ResponseEntity.ok(new MessageResponse("Deleted."));
	}
	

}
