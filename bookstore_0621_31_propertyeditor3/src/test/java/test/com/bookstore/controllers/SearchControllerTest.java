package test.com.bookstore.controllers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.config.RootContextConfiguration;
import com.bookstore.config.ServletContextConfiguration;
import com.bookstore.entities.SearchLevel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ServletContextConfiguration.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true)
public class SearchControllerTest {

	private static Log log = LogFactory.getLog(BookControllerTest.class);
	
	private MockMvc mvc;	
	@Autowired WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.mvc = webAppContextSetup(this.context).build();		
	}

	@Test
	public void testSearchLevel() throws Exception {
		this.mvc.perform(get("/searchlevel").param("level", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/searchlevel")))
		.andExpect(model().attribute("level", SearchLevel.MEDIUM));
	}

	@Test
	public void testSearchLevel2() throws Exception {
		this.mvc.perform(get("/searchlevel2").param("level", "2"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/searchlevel2")))
		.andExpect(model().attribute("level", SearchLevel.DETAIL));
	}	
}
