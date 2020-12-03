package stock;

import java.util.EnumMap;
import java.util.Map;

public class IngredientsStock {
	/*
	private int coffeePods;
	private int expressoGrainDoses;
	private int teaSachets;
	private int sugars;
	private int milkDoses;
	private int mapleSyrupDoses;
	private int vanillaIceCreamDoses;
	*/
	
	private Map<Ingredient, Integer> ingredients = new EnumMap<>(Ingredient.class);
	
	public IngredientsStock(){
		this.ingredients.put(Ingredient.COOFFEEPOD, 1) ;
		this.ingredients.put(Ingredient.EXPRESSOGRAINDOSE, 0);
		this.ingredients.put(Ingredient.TEASACHET, 100);
		this.ingredients.put(Ingredient.SUGAR, 100);
		this.ingredients.put(Ingredient.MILKDOSE, 100);
		this.ingredients.put(Ingredient.MAPLESYRUPDOSE, 100);
		this.ingredients.put(Ingredient.VANILLAICECREAMDOSE, 100);
		/*
		this.coffeePods = 1;
		this.expressoGrainDoses = 0;
		this.teaSachets = 100;
		this.sugars = 100;
		this.milkDoses = 100;
		this.mapleSyrupDoses = 100;
		this.vanillaIceCreamDoses = 100;
		*/
	}
	
	public Map<Ingredient, Integer> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(Map<Ingredient, Integer> ingredients) {
		this.ingredients = ingredients;
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