package com.shahnawaz.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue
	@Column(name="todoid")
	private int todoid;
	
	@Column(name="task")
	private String taskString;
	
	
	@Column(name="completed")
	private String completedString;
	@JsonBackReference
	@ManyToOne
	private User user;
	
	public Todo() {}
	public Todo(String taskString, String completedString) {
		super();
		this.taskString = taskString;
		this.completedString = completedString;
	}
	
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}	
	
	
	public int getTodoid() {
		return todoid;
	}
	public void setTodoid(int todoid) {
		this.todoid = todoid;
	}
	public String getTaskString() {
		return taskString;
	}
	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}
	public String getCompletedString() {
		return completedString;
	}
	public void setCompletedString(String completedString) {
		this.completedString = completedString;
	}
	
	@Override
	public String toString() {
		return "Todo [todoid=" + todoid + ", taskString=" + taskString + ", completedString=" + completedString
				 +"user "+""+ "]";
	}
}
