package stock;

import java.util.EnumMap;
import java.util.Map;

public class IngredientsStock {
	
	private Map<Ingredient, Integer> ingredients = new EnumMap<>(Ingredient.class);
	
	public IngredientsStock(){
		this.ingredients.put(Ingredient.COOFFEEPOD, 100);
		this.ingredients.put(Ingredient.EXPRESSOGRAINDOSE, 1);
		this.ingredients.put(Ingredient.TEASACHET, 100);
		this.ingredients.put(Ingredient.SOUPPOD, 100);
		this.ingredients.put(Ingredient.SUGAR, 100);
		this.ingredients.put(Ingredient.MILKDOSE, 100);
		this.ingredients.put(Ingredient.MAPLESYRUPDOSE, 100);
		this.ingredients.put(Ingredient.VANILLAICECREAMDOSE, 100);
		this.ingredients.put(Ingredient.SPICEDOSE, 100);
		this.ingredients.put(Ingredient.CROUTONDOSE, 100);

	}
	
	public Map<Ingredient, Integer> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(Map<Ingredient, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
	public boolean isIngredientInStock(Ingredient ingredient) {
		return (this.ingredients.get(ingredient) != 0);
	}

	public void consumeIngredient(Ingredient ingredientType, int amount) {
		if(amount >0) {
			int numberInStock = ingredients.get(ingredientType);
			if(numberInStock >= amount) {
				ingredients.put(ingredientType, numberInStock - amount);
			}
		}
	}
	
	
	
}