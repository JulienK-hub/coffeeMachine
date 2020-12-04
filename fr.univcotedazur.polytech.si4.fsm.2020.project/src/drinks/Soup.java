package drinks;

import preparationSteps.PodPositionning;
import preparationSteps.PooringWaterForSize;
import preparationSteps.SoupPodPooring;
import preparationSteps.SpicingTheDrink;
import preparationSteps.Step;
import preparationSteps.WaitingForTemperature;
import preparationSteps.WaterHeating;

public class Soup extends Drink {
	public Soup() {
		super("soup", 30000, 0.75, null);
		Step[][] steps = {
				{new PodPositionning(), new WaterHeating()}, // 15 000 ms
				{new SoupPodPooring(), new WaitingForTemperature(), new SpicingTheDrink()}, // 10 000 ms
				{new PooringWaterForSize()} // 5 000 ms
		};
		this.setStepsList(steps);
	}

}
