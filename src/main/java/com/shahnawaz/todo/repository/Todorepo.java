package com.shahnawaz.todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.shahnawaz.todo.entities.Todo;

public interface Todorepo extends JpaRepository<Todo, Integer> {
	
	
}
