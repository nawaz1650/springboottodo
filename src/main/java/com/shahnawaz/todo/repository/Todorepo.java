package com.shahnawaz.todo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shahnawaz.todo.entities.Todo;
@Repository
public interface Todorepo extends JpaRepository<Todo, Integer> {
	
	
}
