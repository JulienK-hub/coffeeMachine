package preparationSteps;

public class Step {
	private String name;
	private int timeToMake; //en millisecondes
	
	public Step(String name, int timeToMake){
		this.name = name;
		this.timeToMake = timeToMake;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}
	
	public void addTimeToMake(int timeToAdd) {
		timeToMake += timeToAdd;
	}
	
	public void RemoveTimeToMake(int timeToRemove) {
		timeToMake += timeToRemove;
	}
	
}
