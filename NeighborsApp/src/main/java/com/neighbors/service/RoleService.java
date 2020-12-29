package com.neighbors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbors.models.Role;
import com.neighbors.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> roles()
	{
		return roleRepository.findAll();
	}

}
