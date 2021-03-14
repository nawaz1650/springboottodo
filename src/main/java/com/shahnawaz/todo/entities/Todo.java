package com.shahnawaz.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue
	@Column(name="todoid")
	private int todoid;
	@Column(name="task")
	private String taskString;
	public Todo() {}
	public Todo(String taskString, String completedString) {
		super();
		this.taskString = taskString;
		this.completedString = completedString;
	}
	@Column(name="completed")
	private String completedString;
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	private User user;
	@Override
	public String toString() {
		return "Todo [todoid=" + todoid + ", taskString=" + taskString + ", completedString=" + completedString
				 +"user "+user+ "]";
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
	
	
}
