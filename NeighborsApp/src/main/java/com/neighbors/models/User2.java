//package com.neighbors.models;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "user")
//public class User2 {
//	

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id")
//	private Long Id;
//	
//	@Column(name = "firstname")
//	private String firstName;
//	
//	@Column(name = "lastname")
//	private String lasttName;
//	
//	@Column(name = "email", unique = true)
//	private String email;
//	
//	@Column(name = "password")
//	private String password;
//	
//	public User2()
//	{
//		super();
//	}
//
//	public Long getId() {
//		return Id;
//	}
//
//	public void setId(Long id) {
//		Id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLasttName() {
//		return lasttName;
//	}
//
//	public void setLasttName(String lasttName) {
//		this.lasttName = lasttName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	
//	
//
//}
