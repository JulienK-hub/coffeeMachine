package drinks;



import preparationSteps.*;

public class Coffee extends Drink{
	
	public Coffee() {
		super("coffee", 30000, 0.35, null);
		Step[][] steps = {
				{new PodPositionning(),new WaterHeating()}, // 15 000 ms
				{new CupPositionning(),new WaitingForTemperature()}, // 10 000 ms
				{new PooringWaterForSize(), new SugarTheDrink()} // 5 000 ms
		};
		this.setStepsList(steps);
	}

}
