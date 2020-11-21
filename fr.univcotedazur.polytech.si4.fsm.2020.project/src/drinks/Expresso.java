package drinks;

import preparationSteps.*;


public class Expresso extends Drink{

	public Expresso() {
		super("expresso", 3, 0.5, null);
		Step[][] steps = {
				{new GrainMashing(),new WaterHeating()},
				{new CupPositionning(),new WaitingForTemperature(), new GrainTamping()},
				{new PooringWaterForTime(), new SugarTheDrink()}
		};
		this.setStepsList(steps);
	}

	

}
