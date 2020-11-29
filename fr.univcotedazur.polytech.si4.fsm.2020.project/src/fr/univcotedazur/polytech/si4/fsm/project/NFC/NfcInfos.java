package fr.univcotedazur.polytech.si4.fsm.project.NFC;

public class NfcInfos {
	private int scanNumber;
	private double sumOfPurchases;
	
	public NfcInfos(int scanNumber, double sumOfPurchases) {
		this.scanNumber = scanNumber;
		this.sumOfPurchases = sumOfPurchases;
	}
	public int getScanNumber() {
		return scanNumber;
	}
	public void setScanNumber(int scanNumber) {
		this.scanNumber = scanNumber;
	}
	public void incrementScanNumber() {
		scanNumber++;
	}
	public double getsumOfPurchases() {
		return sumOfPurchases;
	}
	public void setsumOfPurchases(double sumOfPurchases) {
		this.sumOfPurchases = sumOfPurchases;
	}
	public void addAPurchase(double price) {
		sumOfPurchases += price;
	}
	public double getAveragePurchase() {
		return sumOfPurchases/scanNumber;
	}
	
	@Override
	public String toString() {
		return scanNumber + " times scaned for " + sumOfPurchases + "€";
	}
	public void resetCount() {
		scanNumber = 0;
		sumOfPurchases = 0;		
	}
}
