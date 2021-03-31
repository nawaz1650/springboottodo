package com.shahnawaz.todo.entities;

public class JwtResponse {
	private String token;
private  String username;
private int userid;
public JwtResponse(String token, String username, int userid) {
	System.out.println("from constructor");
	this.token = token;
	this.username = username;
	this.userid = userid;
}


	public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public int getUserid() {
	return userid;
}


public void setUserid(int userid) {
	this.userid = userid;
}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
public JwtResponse() {
		
		
	}
	
	public JwtResponse(String token) {
		
		this.token = token;
	}


	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", username=" + username + ", userid=" + userid + "]";
	}
	
	
}
