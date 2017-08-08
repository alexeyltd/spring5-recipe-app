package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import org.junit.Before;
import org.junit.Test;
import guru.springframework.domain.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

	private static final Long ID_CATEGORY = 1L;
	private static final String DESCRIPTION_CATEGORY = "description";
	CategoryToCategoryCommand categoryToCategoryCommand;

	@Before
	public void setUp() throws Exception {
		categoryToCategoryCommand = new CategoryToCategoryCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(categoryToCategoryCommand.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(categoryToCategoryCommand.convert(new Category()));
	}

	@Test
	public void convert() throws Exception {
//		given
		Category category = new Category();
		category.setId(ID_CATEGORY);
		category.setDescription(DESCRIPTION_CATEGORY);

//		when
		CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

//		then
		assertEquals(ID_CATEGORY, categoryCommand.getId());
		assertEquals(DESCRIPTION_CATEGORY, categoryCommand.getDescription());
	}

}