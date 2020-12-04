package drinks;

import preparationSteps.*;


public class Tea extends Drink {

	public Tea() {
		super("tea", 47000, 0.4, null);
		Step[][] steps = {
				{new SachetPositionning(), new WaterHeating()}, // 15 000 ms
				{new CupPositionning(), new WaitingForTemperature()}, // 10 000 ms
				{new PouringWaterForSize(), new SugarTheDrink()}, // 5 000 ms
				{new WaitingForInfusion()}, // 15 000 ms
				{new SachetWithDrawal()} // 2 000 ms
		};
		this.setStepsList(steps);
	}
}
