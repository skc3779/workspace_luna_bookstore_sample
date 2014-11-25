package com.bookstore.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.config.ContextServletConfiguration;
import com.bookstore.config.RootContextConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ContextServletConfiguration.class})
@WebAppConfiguration
public class MultiUploadControllerTest {


	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	
	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(this.context).build();
	}

	@Test
	public void testUploadCompleted() throws Exception {
		MvcResult result = this.mvc.perform(
					post("/upload/multiuploadresult")
					.param("name1", "value1")
					.param("name2", "value2")
					.param("name3", "value3"))
				.andExpect(status().isOk()).andReturn();		
	}

}
