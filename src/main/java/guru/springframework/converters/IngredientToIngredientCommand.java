package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand unit;

	public IngredientToIngredientCommand(final UnitOfMeasureToUnitOfMeasureCommand unit) {
		this.unit = unit;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(final Ingredient source) {



		if (source == null) {
			return null;
		}

		final IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(source.getId());
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setUnitOfMeasure(unit.convert(source.getUnitOfMeasure()));

		return ingredientCommand;
	}
}
