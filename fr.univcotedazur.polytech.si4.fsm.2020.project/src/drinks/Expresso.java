package drinks;

import preparationSteps.*;


public class Expresso extends Drink{

	public Expresso() {
		super("expresso", 28000, 0.5, null);
		Step[][] steps = {
				{new GrainMashing(),new WaterHeating()}, // 15 000 ms
				{new CupPositionning(),new WaitingForTemperature(), new GrainTamping()}, // 10 000 ms
				{new PooringWaterForTime(), new SugarTheDrink()} // 3 000 ms
		};
		this.setStepsList(steps);
	}

	

}
