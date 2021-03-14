package com.shahnawaz.todo.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "todousers")
public class User {
	@Id
	@GeneratedValue
	private int userid;
	
	private String username;
	public User(String username, String passwordString) {
		super();
		
		this.username = username;
		this.passwordString = passwordString;
	}
	public User() {
		super();
	}
	@JsonIgnore
	@Column(name="password")
	private String passwordString;
	
	@OneToMany(mappedBy = "user")
	private List<Todo> todos;
	
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
	public String getPasswordString() {
		return passwordString;
	}
	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
	public void removeTodo(int id) {
		this.todos.remove(id);
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", passwordString=" + passwordString
				+ "]";
	}
	
	

}
