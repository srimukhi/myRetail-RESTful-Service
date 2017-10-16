package io.myRetail.DbSetup;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface PriceRepository extends MongoRepository<Price, String> {

	public Price findById(long id);

}
