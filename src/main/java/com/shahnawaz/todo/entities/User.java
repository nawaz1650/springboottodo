package com.shahnawaz.todo.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "todousers")
public class User {
	@Id
	@GeneratedValue
	private int userid;
	
	private String username;
	
	@JsonIgnore
	@Column(name="password")
	private String passwordString;
	@JsonIgnore
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date updated_date;
	
	@JsonIgnore
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date created_date;
	
	
	//private 
	@JsonIgnore
	@Column(name="otp")
	private String otp;
	private String email;
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Todo> todos;
	
	
	
	

	public User() {
		//super();
	}
	
	public User(String username, String passwordString) {
		super();
		
		this.username = username;
		this.passwordString = passwordString;
	}
	
	public java.util.Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(java.util.Date updated_date) {
		this.updated_date = updated_date;
	}
	public java.util.Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(java.util.Date created_date) {
		this.created_date = created_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Todo> getTodos() {
		return todos;
	}
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtp() {
		return otp;
	}
	
	
	public String getPasswordString() {
		return passwordString;
	}
	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
	public void removeTodo(int id) {
		this.todos.remove(id-1);
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", passwordString=" + passwordString
				+ ", updated_date=" + updated_date + ", created_date=" + created_date + ", otp=" + otp + ", email="
				+ email + ", todos=" + " " + "]";
	}
	
	
	

}
