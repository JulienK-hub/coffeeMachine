package drinks;



import preparationSteps.*;

public class Coffee extends Drink{
	
	public Coffee() {
		super("coffee", 20, 0.35, null);
		Step[][] steps = {
				{new PodPositionning(),new WaterHeating()},
				{new CupPositionning(),new WaitingForTemperature()},
				{new PooringWaterForSize(), new SugarTheDrink()}
		};
		this.setStepsList(steps);
	}

}
