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

import com.bookstore.config.ContextServletConfiguration;
import com.bookstore.config.RootContextConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ContextServletConfiguration.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback=true)
public class BookControllerTest {

	private static Log log = LogFactory.getLog(BookControllerTest.class);
	
	private MockMvc mvc;
	
	@Autowired WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.mvc = webAppContextSetup(this.context).build();		
	}


	@Test
	//@Ignore
	public void testListHtml() throws Exception {
		this.mvc.perform(get("/books/list/html"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/list/html")))		
		.andExpect(model().size(1));
	}
	
	@Test
	public void testListAjax() throws Exception {
		this.mvc.perform(get("/books/list/ajax"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/list/ajax")));		
	}		
	
	@Test
	public void testAddAjax() throws Exception {
		MvcResult result = this.mvc.perform(post("/books/add/ajax").param("name", "name test01")
				.param("author", "")
				.param("publishDate", "2012-02-01")
				.param("comment","comment test01"))
				.andExpect(status().isOk())
				.andReturn();
		
		assertThat("{\"isOk\":false}", is(result.getResponse().getContentAsString()));		
		log.info("test out:" + result.getResponse().getContentAsString());
	}

	@Test
	public void testEditHtml() throws Exception {
		this.mvc.perform(get("/books/edit/html/42"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/edit/html")))
		.andExpect(model().size(1));
	}	
	
}
