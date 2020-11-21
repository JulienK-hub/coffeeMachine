package drinks;

import preparationSteps.*;


public class Tea extends Drink {

	public Tea() {
		super("tea", 4000, 0.4, null);
		Step[][] steps = {
				{new SachetPositionning(),new WaterHeating()},
				{new CupPositionning(),new WaitingForTemperature()},
				{new PooringWaterForSize(), new SugarTheDrink()},
				{new WaitingForInfusion()},
				{new SachetWithDrawal()}
		};
		this.setStepsList(steps);
	}
}
