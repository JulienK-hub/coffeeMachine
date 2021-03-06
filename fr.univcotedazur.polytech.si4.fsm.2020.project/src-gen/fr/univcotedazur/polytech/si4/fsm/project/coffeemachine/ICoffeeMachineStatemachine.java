/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.coffeemachine;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import java.util.List;

public interface ICoffeeMachineStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseOrderDelivered();
		
		public void raiseSliderModified();
		
		public void raiseRemoveCup();
		
		public void raiseDrinkSelectionDone();
		
		public void raiseOptionSelection();
		
		public void raiseAddCoin();
		
		public void raiseNotEnough();
		
		public void raiseCancel();
		
		public void raiseAddCup();
		
		public void raiseNFC();
		
		public boolean isRaisedDoPrepareForNextOrder();
		
		public boolean isRaisedDoResetOperation();
		
		public boolean isRaisedDoResetDrinkSelected();
		
		public boolean isRaisedDoResetPayment();
		
		public boolean isRaisedDoCheckPayment();
		
		public boolean isRaisedDoResetSliders();
		
		public boolean isRaisedDoWaitForRecuperation();
		
		public boolean isRaisedNoActionFor45sec();
		
		public boolean isRaisedDoNextPreparationStep();
		
		public boolean isRaisedDoPrintNextStep();
		
		public boolean getOkForExpressoStep1();
		
		public void setOkForExpressoStep1(boolean value);
		
		public boolean getOkForExpressoStep2();
		
		public void setOkForExpressoStep2(boolean value);
		
		public boolean getOkForExpressoStep3();
		
		public void setOkForExpressoStep3(boolean value);
		
		public boolean getOkForTeaStep1();
		
		public void setOkForTeaStep1(boolean value);
		
		public boolean getOkForTeaStep2();
		
		public void setOkForTeaStep2(boolean value);
		
		public boolean getOkForTeaStep3();
		
		public void setOkForTeaStep3(boolean value);
		
		public boolean getOkForTeaStep4();
		
		public void setOkForTeaStep4(boolean value);
		
		public boolean getOkForTeaStep5();
		
		public void setOkForTeaStep5(boolean value);
		
		public boolean getOkForCoffeeStep1();
		
		public void setOkForCoffeeStep1(boolean value);
		
		public boolean getOkForCoffeeStep2();
		
		public void setOkForCoffeeStep2(boolean value);
		
		public boolean getOkForCoffeeStep3();
		
		public void setOkForCoffeeStep3(boolean value);
		
		public boolean getOkForSoupStep1();
		
		public void setOkForSoupStep1(boolean value);
		
		public boolean getOkForSoupStep2();
		
		public void setOkForSoupStep2(boolean value);
		
		public boolean getOkForSoupStep3();
		
		public void setOkForSoupStep3(boolean value);
		
		public boolean getReadyToDeliver();
		
		public void setReadyToDeliver(boolean value);
		
		public long getWftTime();
		
		public void setWftTime(long value);
		
		public long getCpTime();
		
		public void setCpTime(long value);
		
		public long getStdTime();
		
		public void setStdTime(long value);
		
		public long getPwfsTime();
		
		public void setPwfsTime(long value);
		
		public long getPwftTime();
		
		public void setPwftTime(long value);
		
		public long getPpTime();
		
		public void setPpTime(long value);
		
		public long getWhTime();
		
		public void setWhTime(long value);
		
		public long getGmTime();
		
		public void setGmTime(long value);
		
		public long getSpTime();
		
		public void setSpTime(long value);
		
		public long getGtTime();
		
		public void setGtTime(long value);
		
		public long getWfiTime();
		
		public void setWfiTime(long value);
		
		public long getSwdTime();
		
		public void setSwdTime(long value);
		
		public long getSppTime();
		
		public void setSppTime(long value);
		
		public long getSpicingTime();
		
		public void setSpicingTime(long value);
		
		public long getIcTime();
		
		public void setIcTime(long value);
		
		public long getMTime();
		
		public void setMTime(long value);
		
		public long getAcTime();
		
		public void setAcTime(long value);
		
		public boolean getPaymentChecked();
		
		public void setPaymentChecked(boolean value);
		
		public boolean getMilk();
		
		public void setMilk(boolean value);
		
		public boolean getIceCream();
		
		public void setIceCream(boolean value);
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onDoPrepareForNextOrderRaised();
		public void onDoResetOperationRaised();
		public void onDoResetDrinkSelectedRaised();
		public void onDoResetPaymentRaised();
		public void onDoCheckPaymentRaised();
		public void onDoResetSlidersRaised();
		public void onDoWaitForRecuperationRaised();
		public void onNoActionFor45secRaised();
		public void onDoNextPreparationStepRaised();
		public void onDoPrintNextStepRaised();
		}
	
	public SCInterface getSCInterface();
	
}
