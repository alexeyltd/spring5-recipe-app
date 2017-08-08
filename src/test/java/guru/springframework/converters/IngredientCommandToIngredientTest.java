package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

	private static final Long ID_INGREDIENT = 1L;
	private static final String DESCRIPTION_INGREDIENT = "descript";
	private static final BigDecimal DECIMAL_INGREDIENT = new BigDecimal("1");
	private static final Long ID_UNITOFMEASURE = 2L;
	private static final String DESCRIPTION_UNITOFMEASURE = "descripto";

	IngredientCommandToIngredient ingredientCommandToIngredient;

	@Before
	public void setUp() throws Exception {
		ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testNullObject() {
		assertNull(ingredientCommandToIngredient.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
	}


	@Test
	public void convert() throws Exception {
//		given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_INGREDIENT);
		ingredientCommand.setDescription(DESCRIPTION_INGREDIENT);
		ingredientCommand.setAmount(DECIMAL_INGREDIENT);
		UnitOfMeasureCommand unitOfMeasure = new UnitOfMeasureCommand();
		unitOfMeasure.setId(ID_UNITOFMEASURE);
		unitOfMeasure.setDescription(DESCRIPTION_UNITOFMEASURE);
		ingredientCommand.setUnitOfMeasure(unitOfMeasure);

//		when
		Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

//		then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getUnitOfMeasure());
		assertEquals(ID_INGREDIENT, ingredient.getId());
		assertEquals(ID_UNITOFMEASURE, ingredient.getUnitOfMeasure().getId());
		assertEquals(DESCRIPTION_INGREDIENT, ingredient.getDescription());
	}


}