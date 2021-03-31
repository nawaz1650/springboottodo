package com.shahnawaz.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shahnawaz.todo.entities.User;

@Repository
public interface Userrepo extends JpaRepository<User, Integer>{
	@Query("select U from User U where U.passwordString=:password and U.username=:name")
	java.util.Optional<User> findbyusernameStringandpasswordString(String name,String password);
	@Query("select u from User u where u.username=:name")
	User findByName(String name);
	@Query("select u from User u where u.username=:username and u.email=:email")
	User findByusernameStringAndemail(String username,String email);
}
