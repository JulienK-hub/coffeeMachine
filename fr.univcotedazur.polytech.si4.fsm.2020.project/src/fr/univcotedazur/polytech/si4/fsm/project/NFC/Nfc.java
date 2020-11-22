package fr.univcotedazur.polytech.si4.fsm.project.NFC;

import java.util.HashMap;

public class Nfc {
	private HashMap<Integer, Integer> nfcData;
	
	public Nfc() {
		nfcData = new HashMap<Integer,Integer>();
	}

	public HashMap<Integer, Integer> getNfcData() {
		return nfcData;
	}

	public int getValue(int key) {
		return (int) nfcData.get(key);
	}
	
	public void add1(int nfcId) {
		int key = 0; 					// cryptage de la carte banquaire: 
		while(nfcId > 0) {				// on additionne les chiffres de celle-ci pour 
			key = key + nfcId % 10;		// avoir la clef à enregistrer
			nfcId = nfcId /10;			//
		}
		if(nfcData.get(key)!= null) {
			nfcData.replace(key, getValue(key) +1);
		}
		else {
			nfcData.put(key, 1);
		}
		
	}
	
	@Override
	public String toString() {
		
		return nfcData.toString();
	}
	
}
