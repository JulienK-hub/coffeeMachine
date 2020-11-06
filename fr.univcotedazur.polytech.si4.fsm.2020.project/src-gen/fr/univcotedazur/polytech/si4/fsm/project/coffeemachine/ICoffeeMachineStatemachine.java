/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.coffeemachine;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import java.util.List;

public interface ICoffeeMachineStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raisePaymentChecked();
		
		public void raiseOrderDelivered();
		
		public void raiseSliderModified();
		
		public void raiseRemoveCup();
		
		public void raiseDrinkSelectionDone();
		
		public void raiseAddCoin();
		
		public void raiseNotEnough();
		
		public void raiseCancel();
		
		public void raiseNFC();
		
		public void raiseCoffee();
		
		public void raiseExpresso();
		
		public void raiseTea();
		
		public void raiseOkForCoffeeStep2();
		
		public void raiseOkForCoffeeStep3();
		
		public void raiseOkForExpressoStep2();
		
		public void raiseOkForExpressoStep3();
		
		public void raiseOkForTeaStep2();
		
		public void raiseOkForTeaStep3();
		
		public void raiseOkForTeaStep4();
		
		public void raiseOkForTeaStep5();
		
		public void raiseReadyToDeliver();
		
		public boolean isRaisedDoPrepareForNextOrder();
		
		public boolean isRaisedDoResetDrinkSelected();
		
		public boolean isRaisedDoResetPayment();
		
		public boolean isRaisedDoCheckPayment();
		
		public boolean isRaisedDoStartPreparation();
		
		public boolean isRaisedDoResetSliders();
		
		public boolean isRaisedDoWaitForRecuperation();
		
		public boolean isRaisedNoActionFor45sec();
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onDoPrepareForNextOrderRaised();
		public void onDoResetDrinkSelectedRaised();
		public void onDoResetPaymentRaised();
		public void onDoCheckPaymentRaised();
		public void onDoStartPreparationRaised();
		public void onDoResetSlidersRaised();
		public void onDoWaitForRecuperationRaised();
		public void onNoActionFor45secRaised();
		}
	
	public SCInterface getSCInterface();
	
}