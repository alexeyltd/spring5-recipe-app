package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

	private static final Long ID_INGREDIENT = 1L;
	private static final String DESCRIPTION_INGREDIENT = "description";
	private static final BigDecimal AMOUNT_INGREDIENT = new BigDecimal("1");
	private static final Long ID_UNIT_OF_MEASURE = 2L;

	IngredientToIngredientCommand ingredientToIngredientCommand;

	@Before
	public void setUp() throws Exception {
		ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void testNullObject() {
		assertNull(ingredientToIngredientCommand.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
	}

	@Test
	public void convert() throws Exception {
//		given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_INGREDIENT);
		ingredient.setDescription(DESCRIPTION_INGREDIENT);
		ingredient.setAmount(AMOUNT_INGREDIENT);
		UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(ID_UNIT_OF_MEASURE);
		ingredient.setUnitOfMeasure(unitOfMeasure);

//		when
		IngredientCommand ingredientCommand	= ingredientToIngredientCommand.convert(ingredient);

//		then
		assertNotNull(ingredientCommand);
		assertNotNull(ingredient.getUnitOfMeasure());
		assertEquals(ID_INGREDIENT, ingredient.getId());
		assertEquals(ID_UNIT_OF_MEASURE, ingredient.getUnitOfMeasure().getId());
	}

}