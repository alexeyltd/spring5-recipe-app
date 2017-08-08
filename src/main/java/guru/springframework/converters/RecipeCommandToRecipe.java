package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final IngredientCommandToIngredient ingredientConverter;
	private final NotesCommandToNotes notes;
	private final CategoryCommandToCategory categoryConverter;

	public RecipeCommandToRecipe(final IngredientCommandToIngredient ingredient, final NotesCommandToNotes notes, final CategoryCommandToCategory category) {
		this.ingredientConverter = ingredient;
		this.notes = notes;
		this.categoryConverter = category;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(final RecipeCommand source) {

		if (source == null) {
			return null;
		}

		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDirections(source.getDirections());
		recipe.setSource(source.getSource());
		recipe.setServings(source.getServings());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setUrl(source.getUrl());
		recipe.setNotes(notes.convert(source.getNotes()));

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
		}

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		return recipe;
	}
}
