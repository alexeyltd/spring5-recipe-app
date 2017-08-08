package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasure;

	public IngredientCommandToIngredient(final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(final IngredientCommand source) {

		if (source == null) {
			return null;
		}

		final Ingredient ingredientCommand = new Ingredient();
		ingredientCommand.setId(source.getId());
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setUnitOfMeasure(unitOfMeasure.convert(source.getUnitOfMeasure()));

		return ingredientCommand;
	}
}
