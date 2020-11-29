package fr.univcotedazur.polytech.si4.fsm.project.NFC;

import java.util.HashMap;

import javax.swing.JTextField;

public class Nfc {
	private HashMap<Integer, NfcInfos> nfcData;
	
	public Nfc() {
		nfcData = new HashMap<Integer,NfcInfos>();
	}

	public HashMap<Integer, NfcInfos> getNfcData() {
		return nfcData;
	}

	public int getValue(int key) {
		return nfcData.get(key).getScanNumber();
	}
	
	public void add1(int nfcId, double price) {
		int key = getKey(nfcId);
		if(nfcData.get(key)!= null) {
			nfcData.get(key).incrementScanNumber();
			nfcData.get(key).addAPurchase(price);
		}
		else {
			nfcData.put(key, new NfcInfos(1, price));
		}
		
	}
	
	public int getKey(int id) { // algorithme de cryptage de la carte banquaire
		int key = 0; 			// 
		while(id > 0) {			// 
			key = key + id % 10;// on additionne les chiffres de celle-ci pour
			id = id /10;		// avoir la clef à enregistrer exmple 123 donnera 6
		}
		return key;
	}
	

	public int getScanNumber(int nfcId) {
		return nfcData.get(getKey(nfcId)).getScanNumber();
	}

	public double getAveragePurchase(int nfcId) {
		return nfcData.get(getKey(nfcId)).getAveragePurchase() ;
	}
	
	@Override
	public String toString() {
		return nfcData.toString();
	}

	public void resetCount(int nfcId) {
		nfcData.get(getKey(nfcId)).resetCount();
		
	}
}
