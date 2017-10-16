package io.myRetail.exceptionHandler;

import javax.naming.ServiceUnavailableException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	 
	//handles unreadable requestbody exception
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	      ApiError apiError=new ApiError(ex.getMessage(), "Invalid JSON request body!!");
	       return new ResponseEntity<>(apiError, new HttpHeaders(),HttpStatus.BAD_REQUEST);
	   }

	//handles ClientError exceptions
	  @ExceptionHandler(HttpClientErrorException.class)//when invalid parameter is provided
		public final ResponseEntity<ApiError> bad_request(HttpClientErrorException ex){
			ApiError badreq=new ApiError(ex.getMessage(),"Its a bad request!! Please provide another id");
			return new ResponseEntity<ApiError>(badreq, new HttpHeaders(),HttpStatus.NOT_FOUND);

	  }
	  
	

}