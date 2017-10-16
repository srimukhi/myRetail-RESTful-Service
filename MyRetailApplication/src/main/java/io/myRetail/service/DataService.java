package io.myRetail.service;
//This interface provides us with the services getProduct() and update()
//These services are returned at the time of request Mapping in the Controller
import org.json.JSONException;

import io.myRetail.model.Price;

public interface DataService {

	public String getProduct(long id) throws JSONException;
	
	public Price update(long id, String str) throws JSONException;

	
}
