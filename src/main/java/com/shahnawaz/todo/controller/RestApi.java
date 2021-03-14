package com.shahnawaz.todo.controller;

import java.util.List;
import java.util.Optional;

import com.shahnawaz.todo.requestbody.Todoreq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahnawaz.todo.Exception.LoginException;
import com.shahnawaz.todo.entities.Todo;
import com.shahnawaz.todo.entities.User;
import com.shahnawaz.todo.newuser.Newuser;
import com.shahnawaz.todo.repository.Todorepo;
import com.shahnawaz.todo.repository.Userrepo;





@RestController
@CrossOrigin(origins = "https://nawaz1650/github.io/")
public class RestApi {
	@Autowired
	private Userrepo userrepo;
	@Autowired
	private Todorepo todorepo;
	//BCryptPasswordEncoder bcrypt=	new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public User signup(@RequestBody Newuser user ) {
		System.out.println("from signup point");
		int count=(int)userrepo.count();
		System.out.println(user.toString());
		if(userrepo.findByName(user.getUsername()).isEmpty()) {
			return userrepo.save(new User( user.getUsername(),user.getPassword() /*hashpswd*/));
			//return ResponseEntity.created(null).build();
		}
		else {
			throw new UserNotFoundException("username already taken...");
			//return ResponseEntity.badRequest().build();
		}
		//String hashpswd=bcrypt.encode(user.getPassword());
		 	
		
	}
	@PostMapping(value = "User/{id}/Todo")
	public Todo createTodo(@PathVariable String id,@RequestBody Todoreq todo) {
		System.out.println(id+" "+todo);
		System.out.println("##########");
	Optional<User> user=	userrepo.findById(Integer.parseInt(id));
	if(user.isPresent()) {
		Todo td=new Todo(todo.getTaskString(),todo.getCompletedString());
		//System.out.println();
		
		td.setUser(user.get());
		return todorepo.save(td);
	}else {
		return null;
	}
		
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "https://nawaz1650/github.io")
	public Object login(@RequestBody Newuser user) {
		User u=new User();
		System.out.println(user);
		u.setPasswordString(user.getPassword());
		u.setusername(user.getUsername());
		Optional<User> user2=userrepo.findbyusernameStringandpasswordString(u.getusername(),u.getPasswordString()); 
		if(user2.isPresent()&& user2.get().getusername().equals(u.getusername())) {
			System.out.println("returning success result");
			return user2;
		}
		else
			
		throw new LoginException("user doesnt exist or bad credentials");
	}
	@CrossOrigin(origins = "https://nawaz1650/github.io")
	@GetMapping(value = "User/{id}/Todos")
	public List<Todo> GetTodo(@PathVariable String id) {
		
	Optional<User> user=	userrepo.findById(Integer.parseInt(id));
	if(user.isPresent()) {
		List<Todo> todos=user.get().getTodos();
		System.out.println(todos);
		
		
		return todos;
	}else {
		throw new UserNotFoundException("user not found ");
	}
		
	}
	@Transactional
	@DeleteMapping(value="/users/{userid}/Todos/{todoid}")
	public ResponseEntity<Object> deleteTodo(@PathVariable String userid,@PathVariable String todoid) {
		System.out.println(userid+""+todoid);
		Optional<Todo> todo=todorepo.findById(Integer.parseInt(todoid));
		Optional<User> user=userrepo.findById(Integer.parseInt(userid));
		System.out.println("##############");
		System.out.println(todo.get()+" "+user.get().toString());
		System.out.println("##############");
		if(todo.isPresent()) {
			//user.get().removeTodo(Integer.parseInt(todoid));
			
			 // Todo todo2=todo.get(); //// 
			todo.get().setUser(user.get()); ////
			System.out.println("user.todos  "+user.get().getTodos());
			  System.out.println("##############"); ////
			  System.out.println(todo.toString()); ////
			  System.out.println("##############"); ////
			  todorepo.deleteById(Integer.parseInt(todoid)); //
			 // return {msg:"deleted"};
			 			 //todorepo.flush();
			  return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			  return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@PutMapping("/users/{userid}")
	public Todo updateTodo(@RequestBody Todo todo,@PathVariable String userid) {
		int todoid=todo.getTodoid();
		todo.setUser(userrepo.findById(Integer.parseInt(userid)).get());
		return todorepo.save(todo);
		
	}

}
