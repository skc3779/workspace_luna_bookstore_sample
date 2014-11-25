package test.com.bookstore.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.config.ContextServletConfiguration;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ ContextServletConfiguration.class})
@WebAppConfiguration
public class HelloControllerTestClass {

	private MockMvc mvc;
	@Autowired
	WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(this.context).build();
	}

	@Test
	public void getHello() throws Exception {
		this.mvc.perform(get("/index?name=seo")).andExpect(status().isOk())
				.andExpect(view().name(containsString("hello")))
				.andExpect(model().attribute("helloSpring", "Hello seo"))
				.andExpect(model().size(2));
		return;
	}

}
