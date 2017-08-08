package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

	private static final Long ID_CATEGORY = 1L;
	private static final String DESCRIPTION_CATEGORY = "description";
	CategoryCommandToCategory categoryCommandToCategory;

	@Before
	public void setUp() throws Exception {
		categoryCommandToCategory = new CategoryCommandToCategory();
	}

	@Test
	public void testNullObject() {
		assertNull(categoryCommandToCategory.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
	}

	@Test
	public void convert() throws Exception {
//		given
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(ID_CATEGORY);
		categoryCommand.setDescription(DESCRIPTION_CATEGORY);

//		when
		Category category = categoryCommandToCategory.convert(categoryCommand);

//		then
		assertEquals(ID_CATEGORY, category.getId());
		assertEquals(DESCRIPTION_CATEGORY, category.getDescription());
	}

}