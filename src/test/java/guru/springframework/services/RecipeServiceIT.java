package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

	private static final String SOME_DESCRIPTION = "New Description";

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeCommandToRecipe recipeCommandToRecipe;

	@Autowired
	private RecipeToRecipeCommand recipeToRecipeCommand;


	@Test
	@Transactional
	public void testSaveOfDescription() {
//		given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

//		when
		testRecipeCommand.setDescription(SOME_DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

//		then
		assertEquals(SOME_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipeCommand.getId(), savedRecipeCommand.getId());
		assertEquals(testRecipeCommand.getCategories().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipeCommand.getIngredients().size(), savedRecipeCommand.getIngredients().size());
	}

}