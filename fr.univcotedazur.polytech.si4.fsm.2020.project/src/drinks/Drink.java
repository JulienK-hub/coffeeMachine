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
	
	public Step getStep(String stepName) {
		for(int i =0; i < stepsList.length; i++) {
			for (int j = 0; j < stepsList[i].length; j++) {
				if(stepName.equals(stepsList[i][j].getName())) {
					return stepsList[i][j];
				}
			}
		}
		return null;
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

	public void addTimeToMake(int i) {
		timeToMake+= i;
		
	}

	public void updateTotalTimeTomake() {
		int timeToMake =0;
		for(int i =0; i < stepsList.length; i++) {
			for (int j = 0; j < stepsList[i].length; j++) {
				timeToMake += stepsList[i][j].getTimeToMake();
			}
		}
		this.timeToMake =timeToMake;
	}
}