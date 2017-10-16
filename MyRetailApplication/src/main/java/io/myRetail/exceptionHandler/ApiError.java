package io.myRetail.exceptionHandler;
//This is the error object that is returned when an exception is thrown
public class ApiError {

	private String message;
	private String details;
	

	public ApiError(String message, String details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
