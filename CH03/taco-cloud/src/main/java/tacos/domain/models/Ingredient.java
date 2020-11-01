package tacos.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import tacos.domain.models.util.IngredientType;

@Data
@RequiredArgsConstructor
public class Ingredient {

	private final String id;
	private final String name;
	private final IngredientType ingredientType;
	
}
