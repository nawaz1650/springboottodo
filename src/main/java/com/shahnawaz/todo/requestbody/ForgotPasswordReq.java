package com.shahnawaz.todo.requestbody;

public class ForgotPasswordReq {
	private String email;
	private String username;
	private String otp;
	private String newpswd;
	public String getNewpswd() {
		return newpswd;
	}
	public void setNewpswd(String newpswd) {
		this.newpswd = newpswd;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public ForgotPasswordReq(String email,String username) {
		super();
		this.email = email;
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public ForgotPasswordReq() {
	}
	@Override
	public String toString() {
		return "ForgotPasswordReq [email=" + email + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
