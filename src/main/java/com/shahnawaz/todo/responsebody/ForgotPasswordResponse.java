package com.shahnawaz.todo.responsebody;

public  class ForgotPasswordResponse {
	private boolean success;

	public ForgotPasswordResponse(boolean success) {
		//super();
		this.success = success;
	}
	
	public ForgotPasswordResponse() {
		
		
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
