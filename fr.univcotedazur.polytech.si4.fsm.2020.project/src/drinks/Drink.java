package drinks;

public class Drink {
	
	private String name;
	private int timeToMake;
	private double price;
	
	public Drink(String name, int timeToMake, double price) {
		this.name = name;
		this.timeToMake = timeToMake;
		this.price = price;
	}

	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}
	
	
}
