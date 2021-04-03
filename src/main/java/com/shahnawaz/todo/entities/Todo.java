package com.shahnawaz.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@JsonIgnore
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date updated_date;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date created_date;
	
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


	@Override
	public String toString() {
		return "Todo [todoid=" + todoid + ", taskString=" + taskString + ", completedString=" + completedString
				+ ", updated_date=" + updated_date + ", created_date=" + created_date + "]";
	}
}
