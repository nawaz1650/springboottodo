package com.shahnawaz.todo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shahnawaz.todo.entities.User;
import com.shahnawaz.todo.repository.Userrepo;

@Service
public class MyuserdetailService implements UserDetailsService {
	@Autowired
	Userrepo userRepo;
 @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// TODO Auto-generated method stub
	 User u=userRepo.findByName(username);
	 //f(u.isPresent())
	
	return new org.springframework.security.core.userdetails.User(u.getusername(),u.getPasswordString(),new ArrayList<>());
	
	// else
		//return null;
	
}
}
