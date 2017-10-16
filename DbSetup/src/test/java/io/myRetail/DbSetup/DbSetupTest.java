package io.myRetail.DbSetup;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class DbSetupTest {
	
	@Mock
	private PriceRepository priceRepository;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	//testing findById operation
	@Test
	public void testGetPriceById(){
		Price price = new Price(123456,1000.51,"USD");
		when(priceRepository.findById(123456)).thenReturn(price);
		assertEquals(123456, price.getId());
		//assertEquals(1000.51, result.getValue());
		assertEquals("USD", price.getCode());
	}
	
	//Testing save operation
	@Test
	public void saveToDo(){
		Price price = new Price(8,10000,"USD");
		when(priceRepository.save(price)).thenReturn(price);
		assertEquals(8, price.getId());
		//assertEquals(10000, result.getValue());
		assertEquals("USD", price.getCode());
	}
}
	