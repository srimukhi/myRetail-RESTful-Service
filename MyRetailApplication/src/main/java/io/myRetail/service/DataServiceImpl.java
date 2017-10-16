package io.myRetail.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import io.myRetail.model.Price;
import io.myRetail.model.PriceRepository;
//This class is the implementation of the services defined in the interface
@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private PriceRepository repo;
	

	private RestTemplate restTemplate = new RestTemplate();
	
	
	@Override
	public String getProduct(long id) throws JSONException, HttpClientErrorException {
		String url="http://redsky.target.com/v2/pdp/tcin/"+id+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		String str=null;
		
		if(repo.findById(id)!=null) {
			try {
				str= restTemplate.getForObject(url,String.class, id);
			} catch (HttpClientErrorException ex)   {
				
		    	return "{\"id\":" + id + ",\"name\":"+null+ ","+repo.findById(id) +",\"message\":\"The name of this product doesn't exist in the external resource\"}";
			}
		}
		
			str= restTemplate.getForObject(url,String.class, id);//handled by exception controller if there is a bad request
			JSONObject root = new JSONObject(str);
			
			String name=root.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").getString("title");

			return "{\"id\":" + id + ",\"name\":\"" + name + "\"," + repo.findById(id) + "}";
		
	}

	@Override
	public Price update(long id, String str) throws JSONException, HttpMessageNotReadableException {

		JSONObject root = new JSONObject(str);
		
		JSONObject updatedPrice = root.getJSONObject("current_price");
		String name=root.getString("name");
		double cost=updatedPrice.getDouble("value");
		String currency=updatedPrice.getString("currency_code");
		return repo.save(new Price(id, cost,currency));
		
		
	}

}
