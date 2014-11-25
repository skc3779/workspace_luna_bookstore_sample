package test.com.bookstore.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.config.ServletContextConfiguration;
import com.bookstore.config.RootContextConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ServletContextConfiguration.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true)
public class Book2ControllerTest {

	private static Log log = LogFactory.getLog(Book2ControllerTest.class);
	
	private MockMvc mvc;	
	@Autowired WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.mvc = webAppContextSetup(this.context).build();		
	}
	
	@Test
	public void testBook2Add() throws Exception {
		MvcResult result = this.mvc.perform(post("/book2/add")
				.param("name", "name test01")
				.param("author", "author test01")
				.param("publishDate", "2012-02-01")
				.param("comment","comment test01")
				.param("bookStatus","0")
				.param("rentUser", "1"))
				.andExpect(status().isMovedTemporarily())
				.andReturn();
		
		log.info("test out:" + result.getResponse().getContentAsString());
	}

}