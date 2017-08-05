package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(final CategoryRepository categoryRepository, final RecipeRepository recipeRepository, final UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
		recipeRepository.saveAll(getRecipes());
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>(1);

		Optional<Category> american = categoryRepository.findByDescription("American");

		if (!american.isPresent()) {
			throw new RuntimeException("american not found");
		}

		Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

		if (!teaspoonOptional.isPresent()) {
			throw new RuntimeException("teaspoon not found");
		}

//		get
		UnitOfMeasure teaspoon = teaspoonOptional.get();

		Recipe recipe = new Recipe();
		recipe.setDescription("some");
		recipe.setPrepTime(10);
		recipe.setCookTime(10);
		recipe.setServings(10);
		recipe.setSource("www.html");
		recipe.setDifficulty(Difficulty.EASY);
		recipe.setDescription("dome dicr");
		recipe.setDirections("dome dicr");

		Notes notes = new Notes();

		notes.setRecipeNotes("some notes");
		notes.setRecipe(recipe);

		recipe.setNotes(notes);

		recipe.getIngredients().add(new Ingredient("ripe", new BigDecimal(2), teaspoon));

		recipe.getCategories().add(american.get());

		recipes.add(recipe);


		return recipes;
	}
}
