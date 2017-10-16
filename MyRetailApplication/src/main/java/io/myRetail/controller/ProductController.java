package io.myRetail.controller;


import java.io.IOException;

import javax.naming.ServiceUnavailableException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.myRetail.model.Price;
import io.myRetail.model.PriceRepository;
import io.myRetail.service.DataService;

@RestController
public class ProductController{
	
	private DataService service;
	
	@Autowired
	public ProductController(DataService service){
		this.service = service;
	}

	//This handler maps to the the incoming GET request and returns a service
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
	public String findById(@PathVariable("id") long id) throws JSONException,HttpClientErrorException, MethodArgumentNotValidException {
		return service.getProduct(id);
	}
	
	//This handler maps to the the incoming PUT request and returns a service

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
	public Price update(@PathVariable long id, @RequestBody String obj) throws JSONException,HttpMessageNotReadableException {
		return service.update(id, obj);
	}
	
	
}