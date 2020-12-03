package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.coffeemachine.ICoffeeMachineStatemachine.SCInterfaceListener;

public class DrinkFactoryMachineInterfaceImplementation implements SCInterfaceListener{

	DrinkFactoryMachine theDFM;
	public DrinkFactoryMachineInterfaceImplementation(DrinkFactoryMachine df) {
		theDFM = df;
	}
	
	@Override
	public void onDoPrepareForNextOrderRaised() {
		theDFM.doPrepareForNextOrderRaised();
		
	}

	@Override
	public void onDoResetDrinkSelectedRaised() {
		theDFM.doResetDrinkSelected();
		
	}

	@Override
	public void onDoResetPaymentRaised() {
		theDFM.doResetPayment();
		
	}

	@Override
	public void onDoCheckPaymentRaised() {
		theDFM.doCheckPayment();
	}

	@Override
	public void onDoResetSlidersRaised() {
		theDFM.doResetSliders();
		
	}

	@Override
	public void onDoWaitForRecuperationRaised() {
		theDFM.doWaitForRecuperation();
		
	}

	@Override
	public void onNoActionFor45secRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoNextPreparationStepRaised() {
		theDFM.doNextPreparationStep();
		
	}

	@Override
	public void onDoPrintNextStepRaised() {
		theDFM.doPrintNextStep();
		
	}

	@Override
	public void onDoResetOperationRaised() {
		theDFM.doResetOperation();
	}



}
