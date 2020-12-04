package drinks;

import preparationSteps.AddingCroutons;
import preparationSteps.CupPositionning;
import preparationSteps.PodPositionning;
import preparationSteps.PouringWaterForSize;
import preparationSteps.SoupPodPooring;
import preparationSteps.SpicingTheDrink;
import preparationSteps.Step;
import preparationSteps.WaitingForTemperature;
import preparationSteps.WaterHeating;

public class Soup extends Drink {
	public Soup() {
		super("soup", 30000, 0.75, null);
		Step[][] steps = {
				{new CupPositionning(), new WaterHeating()}, // 15 000 ms
				{new SoupPodPooring(), new WaitingForTemperature(), new SpicingTheDrink()}, // 10 000 ms
				{new PouringWaterForSize(), new AddingCroutons()} // 5 000 ms
		};
		this.setStepsList(steps);
	}

}
