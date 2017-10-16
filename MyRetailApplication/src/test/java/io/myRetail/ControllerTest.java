package io.myRetail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import io.myRetail.controller.ProductController;
import io.myRetail.model.PriceRepository;
import io.myRetail.service.DataService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyRetailApplication.class)

@SpringBootTest
public class ControllerTest {

	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private PriceRepository priceRepository;
	

	@Before
	public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	
	// to test GET request for the product in external API
	@Test
	public void retrieveProductDetailsbyId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/13860428").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(13860428))
		.andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
		.andExpect(jsonPath("$.current_price.value").value(priceRepository.findById(13860428).getValue()));
		
	}
	
	// to test GET request for the product not in external API
	@Test
	public void retrieveNoProductDetailsById() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",15117729).accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").doesNotExist())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(15117729))
		.andExpect(jsonPath("$.current_price.value").value(priceRepository.findById(15117729).getValue()));
		
	}
	
	//to test GET request of the product not in datasource
	@Test
	public void retrieveNonExistingProduct() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",123456).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError());
	}

	//to test PUT Request by performing GET on the product whose pricing info is changed
	@Test
	public void retrieveUpdateProductDetailsById() throws Exception {
	mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}",16696652).accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.current_price").exists())
		.andExpect(jsonPath("$.id").value(16696652))
		.andExpect(jsonPath("$.name").value("Beats Solo 2 Wireless - Black"))
		.andExpect(jsonPath("$.current_price.value").value(priceRepository.findById(16696652).getValue()));
		  
	}
	
	//to validate PUT request
	@Test
	public void testingPUTrequest() throws Exception{
		 mockMvc.perform(put("/products/123456")
		        .contentType(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	//this test fails if the requestbody is empty
/*	@Test
	public void testingPUTrequestbodyEmpty() throws Exception{
		 mockMvc.perform(put("/products/16696652")
		        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		 .andExpect(content().string(""));
	}*/

}