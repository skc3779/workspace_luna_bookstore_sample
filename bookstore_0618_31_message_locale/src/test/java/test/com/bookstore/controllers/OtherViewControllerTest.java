package test.com.bookstore.controllers;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class OtherViewControllerTest {

	final static String LIST = "/tiles/list";

	
	//private static Log log = LogFactory.getLog(OtherViewControllerTest.class);
	
	private MockMvc mvc;
	@Autowired WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mvc = webAppContextSetup(this.context).build();
	}
	@Test
	public void testGetListJson1() throws Exception {
		MvcResult result = this.mvc.perform(get(LIST))
				.andExpect(status().isOk()).andReturn();
		// 출력
		//log.info(result.getResponse().getContentAsString());
		System.out.println(result.getResponse().getContentAsString());
	}

}
