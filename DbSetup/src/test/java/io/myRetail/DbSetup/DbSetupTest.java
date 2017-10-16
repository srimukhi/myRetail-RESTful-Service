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
		assertEquals(priceRepository.findById(123456).getId(), price.getId());
		assertEquals(priceRepository.findById(123456).getValue(), price.getValue(), 0);
	
		assertEquals(priceRepository.findById(123456).getCode(), price.getCode());
	}
	
}
	
