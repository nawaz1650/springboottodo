package com.shahnawaz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shahnawaz.todo.entities.User;


public interface Userrepo extends JpaRepository<User, Integer>{
	@Query("select U from User U where U.passwordString=:password and U.username=:name")
	java.util.Optional<User> findbyusernameStringandpasswordString(String name,String password);
	@Query("select u from User u where u.username=:name")
	java.util.Optional<User> findByName(String name);
}
