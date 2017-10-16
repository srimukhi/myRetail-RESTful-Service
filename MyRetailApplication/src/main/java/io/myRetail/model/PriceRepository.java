package io.myRetail.model;

//This is an interface that helps us to interact with the data source
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<Price, String> {

	public Price findById(long id);

}
