package com.dayuanit.atm.domain;


public class User {
	private Integer id; 
	private String username; 
	private String password; 
	private String sex; 
	private String address;
	
	public User() {

	}
	
	public User(String username, String password, String sex, String address) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.address = address;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		
		return "----"+username+"----"+password+"----"+sex+"----"+address;
	}
	

}