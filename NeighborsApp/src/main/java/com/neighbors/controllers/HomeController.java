package com.neighbors.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighbors.models.User;
import com.neighbors.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(HomeController.NEIGHBORS_EVENT)
public class HomeController {
	public static final String NEIGHBORS_EVENT = "/neighbors/";
	@Autowired
	private final UserService userService;
	
	public HomeController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(value = "", produces = "application/json")
	public String index()
	{
		return "Index";
	}
	
	@GetMapping(value = "/signup", produces = "application/json")
	public String signup()
	{
		return "Signup";
	}
	
	@GetMapping(value = "/login", produces = "application/json")
	public String login()
	{
		return "Loggin in";
	}
	
	@GetMapping(value = "/show-users", produces = "application/json")
	public List<User> users()
	{
		return userService.users();
	}

}
