package com.shahnawaz.todo.controller;


import java.awt.PageAttributes.MediaType;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import com.shahnawaz.todo.requestbody.ForgotPasswordReq;
import com.shahnawaz.todo.requestbody.Todoreq;
import com.shahnawaz.todo.responsebody.ForgotPasswordResponse;
import com.shahnawaz.todo.services.MyuserdetailService;



//import jdk.jfr.internal.PrivateAccess;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
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

import org.springframework.web.bind.annotation.RestController;


import com.shahnawaz.todo.ExportTodos;
import com.shahnawaz.todo.Exception.LoginException;
import com.shahnawaz.todo.entities.JwtResponse;
import com.shahnawaz.todo.entities.Todo;
import com.shahnawaz.todo.entities.User;
import com.shahnawaz.todo.jwt.JwtUtil;
import com.shahnawaz.todo.newuser.Newuser;
import com.shahnawaz.todo.repository.Todorepo;
import com.shahnawaz.todo.repository.Userrepo;





@RestController
@CrossOrigin(origins = "https://nawaz1650.github.io")
//@CrossOrigin(origins = "http://localhost:4200",maxAge = 0L)
						
public class RestApi {
	@Autowired
	private Userrepo userrepo;
	@Autowired
	private Todorepo todorepo;
	@Autowired
	AuthenticationManager authmanager;
	@Autowired
	JwtUtil jwtUtil;
	@Autowired
	MyuserdetailService myuserdetailservice;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	PasswordEncoder passwordEncoder;
	//BCryptPasswordEncoder bcrypt=	new BCryptPasswordEncoder();
	
	
	
	
//	@GetMapping("/export/{userid}")
//	public void export(@PathVariable String userid,HttpServletResponse response) throws IOException {
//		System.out.println(response);
//		System.out.println(userid);
//		response.setContentType("application/octet-stream");
//		String hedaerkey="Content-Disposition";
//		String hedaerValue="attachement; filename=Todos.xlsx";
//				response.setHeader(hedaerkey, hedaerValue);
//				//List<Todo> todos=todorepo.
//				Optional<User> user=userrepo.findById(Integer.parseInt(userid));
//				List<Todo> todos;
//				if(user.isPresent()) {
//					todos=user.get().getTodos();
//					System.out.println(todos);
//					ExportTodos exportTodos=new ExportTodos(todos);
//					exportTodos.export(response);
//					
//				}
//				
//		
//		
//		
//	}
//	
	
	
	@GetMapping("/export/{userid}")
	public void export(@PathVariable String userid,HttpServletResponse response) throws IOException {
		System.out.println(response);
		System.out.println(userid);
		//response.setContentType("application/octet-stream");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String hedaerkey="Content-Disposition";
		String hedaerValue="attachment;filename=Todos.xlsx";
				response.setHeader(hedaerkey, hedaerValue);
		//org.springframework.http.HttpHeaders headers=new org.springframework.http.HttpHeaders();
		//headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
		//headers.add("Content-Disposition", "attachment ; filename=Todos.xlsx");
		
		
				//List<Todo> todos=todorepo.
				Optional<User> user=userrepo.findById(Integer.parseInt(userid));
				List<Todo> todos;
				if(user.isPresent()) {
					todos=user.get().getTodos();
					System.out.println(todos);
					ExportTodos exportTodos=new ExportTodos(todos);
					exportTodos.export(response);

					
				}
				
		return ;
		
		
	}
	
	@PostMapping("/resetPassword")
	public ForgotPasswordResponse resetPassword(@RequestBody ForgotPasswordReq fpr) {
		 User user=userrepo.findByusernameStringAndemail(fpr.getUsername(), fpr.getEmail());
		 if(user!=null) {
			 user.setPasswordString(passwordEncoder.encode(fpr.getNewpswd()));
			 userrepo.save(user);
			 return new ForgotPasswordResponse(true);
		 }
		 else
		throw new LoginException("error in reseting password");
		 //return null;
	}
	
	@PostMapping("/validateOtp")
	public ForgotPasswordResponse validateOtp(@RequestBody ForgotPasswordReq fpr) {
		System.out.println("from validateOtp endpoint");
		User user=userrepo.findByusernameStringAndemail(fpr.getUsername(), fpr.getEmail());
		System.out.println(user);
		System.out.println("otp was created at "+user.getUpdated_date().getTime()+"  "+user.getUpdated_date());
		System.out.println("and now time is "+new Date().getTime()+"   "+new Date());
		
		 Long crnttime=new Date().getTime(); 
		 Long createdtime=user.getUpdated_date().getTime();
	
		System.out.println("and diff is " + (crnttime - createdtime ) );
		long difference_In_Time= (crnttime - createdtime );
		
		 long difference_In_Seconds
         = (difference_In_Time
            / 1000)
           % 60;
		 
		 long difference_In_Minutes
         = (difference_In_Time
            / (1000 * 60))
           % 60;

     long difference_In_Hours
         = (difference_In_Time
            / (1000 * 60 * 60))
           % 24;

     long difference_In_Years
         = (difference_In_Time
            / (1000l * 60 * 60 * 24 * 365));

     long difference_In_Days
         = (difference_In_Time
            / (1000 * 60 * 60 * 24))
           % 365;
     
     
     

     System.out.println(
         difference_In_Years
         + " years, "
         + difference_In_Days
         + " days, "
         + difference_In_Hours
         + " hours, "
         + difference_In_Minutes
         + " minutes, "
         + difference_In_Seconds
         + " seconds");
		 
		 
		 
		 
		 System.out.println("diff in seconds is "+difference_In_Seconds);
		
		 System.out.println("user provided otp is "+fpr.getOtp()+" and actual otp is  "+user.getOtp());
		 
		 
		 System.out.println((user.getOtp()).compareTo(fpr.getOtp()));
				 //equals(fpr.getOtp()));
		 System.out.println(difference_In_Minutes <=4 &&difference_In_Seconds <= 60);
		 System.out.println(difference_In_Days==0);
		 System.out.println();
		 System.out.println();
		 
		 
		if((user.getOtp()).compareTo(fpr.getOtp()) == 0 && (difference_In_Minutes <=4 &&difference_In_Seconds <= 60)&& difference_In_Days==0&&difference_In_Hours==0) {
			return new ForgotPasswordResponse(true);
		}
		else {
			return new ForgotPasswordResponse(false);
		}
		//return null;
		
		
		
	}
	
	
	
	@PostMapping("/forgotpassword")
	public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPasswordReq fpr) throws Exception, MessagingException {
		System.out.println("from /fp endpoint");
		System.out.println(fpr);
		String otp;
		//check if given user exist
		User user=userrepo.findByusernameStringAndemail(fpr.getUsername(), fpr.getEmail());
		if(user!=null) {
		System.out.println(user);
		Random rnd=new Random();
		otp=""+rnd.nextInt(999999);
		System.out.println(otp);
		user.setOtp(otp);
		userrepo.save(user);
		}
		else {
			
			throw new UserNotFoundException("no such user exist plz check your email again...");
		}
		
		MimeMessage msg=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(msg);
		helper.setFrom("fromshahnawaz@gmail.com","Todo_support_team");
		helper.setTo(fpr.getEmail());
		String subject="Here is your One time Password for password reset!!";
		String contentString="this is your OTP "+ otp +"  and this will expire in 5 minutes .please ignore this email if you remember your password or password reset request is not initiated by you!!";
		helper.setSubject(subject);
		helper.setText(contentString);
		try {
		javaMailSender.send(msg);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception occured in mail sending");
			e.printStackTrace();
			throw new UserNotFoundException("error in sending mail...");
		}
		
		return new ForgotPasswordResponse(true);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
//	@PostMapping("/signup")
//	public User signup(@RequestBody Newuser user ) throws Exception{
//		System.out.println("from signup point");
//		//int count=(int)userrepo.count();
//		System.out.println(user.toString());
//		try {
//			
//		
//		if( userrepo.findByName(user.getUsername())==null) {
//			System.out.println("sign up success");
//			return userrepo.save(new User( user.getUsername(),passwordEncoder.encode(user.getPassword())));
//			//return ResponseEntity.created(null).build();
//		}
//		else {
//			throw new UserNotFoundException("username already taken...");
//			//return null;//ResponseEntity.badRequest().build();
//		}
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new UserNotFoundException("username already taken...");
//		}
//		//String hashpswd=bcrypt.encode(user.getPassword());
//		 	
//		
//	}
	
	
	@PostMapping("/signup")
	public Object signup(@RequestBody Newuser user) {
		User u=userrepo.findByName(user.getUsername());
		try {
			//Thread.sleep(3000l, 0);
			if(u==null) {
				User user2=new User();
				user2.setEmail(user.getEmail());
				user2.setPasswordString(passwordEncoder.encode(user.getPassword()));
				user2.setusername(user.getUsername());
				user2.setTodos(null);
				return userrepo.save(user2);
				 //System.out.println("saved user is "+saveduser);
				 //return new ForgotPasswordResponse(true);
			}
			throw new LoginException("user taken");
		}catch (Exception e) {
			throw new LoginException("username not available");
			
		}
		
		 //throw new LoginException("username taken");
		 
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
	
	public Object login(@RequestBody Newuser user) {
//		User u=new User();
//		System.out.println(user);
//		u.setPasswordString(user.getPassword());
//		u.setusername(user.getUsername());
//		Optional<User> user2=userrepo.findbyusernameStringandpasswordString(u.getusername(),u.getPasswordString()); 
//		if(user2.isPresent()&& user2.get().getusername().equals(u.getusername())) {
//			System.out.println("returning success result");
//			return user2;
//		}
//		else
//			
//		throw new LoginException("user doesnt exist or bad credentials");
		System.out.println("from /login end point");
		try {		
		
		authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}catch(Exception e) {
			throw new LoginException("user doesn't exist or bad credentials");
		}
		System.out.println();
		User u1=userrepo.findByName(user.getUsername());
		JwtResponse jResponse= new JwtResponse( jwtUtil.generateToken(myuserdetailservice.loadUserByUsername(user.getUsername())),u1.getusername(),u1.getUserid());
		System.out.println(jResponse);
		return jResponse;
	}
	
	@GetMapping(value = "User/{id}/Todos")
	public List<Todo> GetTodo(@PathVariable String id) {
		System.out.println("from get todos get method");
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
		System.out.println(userid+" "+todoid);
		
		System.out.println(todorepo.findById(Integer.parseInt(todoid)));
		Optional<Todo> todo=todorepo.findById(Integer.parseInt(todoid));
		Optional<User> user=userrepo.findById(Integer.parseInt(userid));
		System.out.println("##############");
		
		System.out.println("todo list of this user is "+user.get().getTodos());
		System.out.println(todo.get()+"");
		System.out.println("##############");
		if(todo.isPresent()) {
			//user.get().removeTodo(Integer.parseInt(todoid));
			User u5=user.get();
			System.out.println("todo found "+u5);
			
			 Todo todo2=todo.get(); //// 
			todo.get().setUser(user.get()); ////
			System.out.println("user.todos  "+user.get().getTodos());
			 System.out.println("##############"); ////
			  System.out.println(todo.toString()); ////
			  System.out.println("##############"); ////
			  todorepo.deleteById(Integer.parseInt(todoid)); //
			  //return {msg:"deleted"};
			 			 //todorepo.flush();
			  return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
		return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@PutMapping("/users/{userid}")
	public Todo updateTodo(@RequestBody Todo todo,@PathVariable String userid) {
		//int todoid=todo.getTodoid();
		todo.setUser(userrepo.findById(Integer.parseInt(userid)).get());
		return todorepo.save(todo);
		
	}

}
