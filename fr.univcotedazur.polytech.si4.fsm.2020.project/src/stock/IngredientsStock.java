package stock;

public class IngredientsStock {
	private int coffeePods;
	private int expressoGrainDoses;
	private int teaSachets;
	
	private int sugars;
	
	private int milkDoses;
	private int mapleSyrupDoses;
	private int vanillaIceCreamDoses;
	
	public IngredientsStock(){
		this.coffeePods = 100;
		this.expressoGrainDoses = 100;
		this.teaSachets = 100;
		this.sugars = 100;
		this.milkDoses = 100;
		this.mapleSyrupDoses = 100;
		this.vanillaIceCreamDoses = 100;
	}

	public int getCoffeePods() {
		return coffeePods;
	}

	public void setCoffeePods(int coffeePods) {
		this.coffeePods = coffeePods;
	}

	public int getExpressoGrainDoses() {
		return expressoGrainDoses;
	}

	public void setExpressoGrainDoses(int expressoGrainDoses) {
		this.expressoGrainDoses = expressoGrainDoses;
	}

	public int getTeaSachets() {
		return teaSachets;
	}

	public void setTeaSachets(int teaSachets) {
		this.teaSachets = teaSachets;
	}

	public int getSugars() {
		return sugars;
	}

	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	public int getMilkDoses() {
		return milkDoses;
	}

	public void setMilkDoses(int milkDoses) {
		this.milkDoses = milkDoses;
	}

	public int getMapleSyrupDoses() {
		return mapleSyrupDoses;
	}

	public void setMapleSyrupDoses(int mapleSyrupDoses) {
		this.mapleSyrupDoses = mapleSyrupDoses;
	}

	public int getVanillaIceCreamDoses() {
		return vanillaIceCreamDoses;
	}

	public void setVanillaIceCreamDoses(int vanillaIceCreamDoses) {
		this.vanillaIceCreamDoses = vanillaIceCreamDoses;
	}
	
	
	
}
