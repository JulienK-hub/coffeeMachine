package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.coffeemachine.ICoffeeMachineStatemachine.SCInterfaceListener;

public class DrinkFactoryMachineInterfaceImplementation implements SCInterfaceListener{

	DrinkFactoryMachine theDFM;
	public DrinkFactoryMachineInterfaceImplementation(DrinkFactoryMachine df) {
		theDFM = df;
	}
	
	@Override
	public void onDoPrepareForNextOrderRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoResetDrinkSelectedRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoResetPaymentRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoCheckPaymentRaised() {
		// TODO Auto-generated method stub
		theDFM.doCheckPayment();
	}

	@Override
	public void onDoStartPreparationRaised() {
		theDFM.doStartPreparation();
		
	}

	@Override
	public void onDoResetSlidersRaised() {
		// TODO Auto-generated method stub
		
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

}
