package test.com.bookstore.controllers;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;

import com.bookstore.config.ContextServletConfiguration;
import com.bookstore.config.RootContextConfiguration;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ContextServletConfiguration.class})
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
