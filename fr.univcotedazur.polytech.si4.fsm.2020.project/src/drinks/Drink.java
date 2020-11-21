package drinks;

import java.util.ArrayList;
import java.util.HashMap;

import preparationSteps.Step;

public class Drink {
	
	private String name;
	private int timeToMake;
	private double price;
	private Step[][] stepsList;
	
	public Drink(String name, int timeToMake, double price,Step[][] stepsList) {
		this.name = name;
		this.timeToMake = timeToMake;
		this.price = price;
		this.stepsList = stepsList;
	}

	public Step[][] getStepsList() {
		return stepsList;
	}
	
	public void setStepsList(Step[][] stepsList) {
		this.stepsList = stepsList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Drink getCopy() {
		return new Drink(name,timeToMake,price,stepsList);
	}
}