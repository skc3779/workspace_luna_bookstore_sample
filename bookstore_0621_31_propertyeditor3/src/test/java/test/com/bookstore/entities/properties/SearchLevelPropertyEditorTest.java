package test.com.bookstore.entities.properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.WebDataBinder;

import com.bookstore.entities.SearchLevel;
import com.bookstore.entities.properties.SearchLevelPropertyEditor;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchLevelPropertyEditorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		SearchLevelPropertyEditor levelPropertyEditor = new SearchLevelPropertyEditor();
		levelPropertyEditor.setAsText("2");		
		assertThat((SearchLevel)levelPropertyEditor.getValue(), is(SearchLevel.DETAIL));
	}
	
	@Test
	public void test2() {
		WebDataBinder dataBinder = new WebDataBinder(null);
		dataBinder.registerCustomEditor(SearchLevel.class, new SearchLevelPropertyEditor());
		assertThat(dataBinder.convertIfNecessary("2", SearchLevel.class), is(SearchLevel.DETAIL));
	}	

}
