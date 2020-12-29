package com.neighbors.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbors.models.Address;
import com.neighbors.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public List<Address> addressess()
	{
		return addressRepository.findAll();
	}

}
