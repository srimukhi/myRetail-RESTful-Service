package io.myRetail.DbSetup;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="price")
public class Price {
		
		@Id
		private long id;
	 	private double price;
	    private String code;

	    protected Price(){}

	    public Price(long id, double price, String code) {
	    	this.id = id;
	        this.price = price;
	        this.code = code;
	    }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public double getValue() {
			return price;
		}

		public void setValue(int price) {
			this.price = price;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	    public String toString(){
	    	return  "\"current_price\":{\"value\":" + price + ",\"currency_code\":\"" + code + "\"}";
	    }
}