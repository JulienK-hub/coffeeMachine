package drinks;

public class Coffee extends Drink{
	private int timeForStep1;
	private int timeForStep2;
	private int timeForStep3;
	public Coffee() {
		super("coffee", 15, 0.35);
		timeForStep1 = 1;
		timeForStep2 = 2;
		timeForStep3 = 3;
	}
	public int getTimeForStep1() {
		return timeForStep1;
	}
	public void setTimeForStep1(int timeForStep1) {
		this.timeForStep1 = timeForStep1;
	}
	public int getTimeForStep2() {
		return timeForStep2;
	}
	public void setTimeForStep2(int timeForStep2) {
		this.timeForStep2 = timeForStep2;
	}
	public int getTimeForStep3() {
		return timeForStep3;
	}
	public void setTimeForStep3(int timeForStep3) {
		this.timeForStep3 = timeForStep3;
	}

}
