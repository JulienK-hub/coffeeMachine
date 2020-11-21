package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigestSpi;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import drinks.*;

import fr.univcotedazur.polytech.si4.fsm.project.coffeemachine.CoffeeMachineStatemachine;
import preparationSteps.Step;

public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private Thread t;
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private int millis/*, secs, mins*/;
	private Timer msTimer;
	private CoffeeMachineStatemachine theFSM;
	private double coinsEntered;
	private Step[][] steps;
	private Drink actualDrink; 
	private int actualStepNumber;
	//private int msVariable = 0;
	private JLabel messagesToUser;
	private JLabel labelForPictures;
	/**
	 * @wbp.nonvisual location=311,475
	 */
	private final ImageIcon imageIcon = new ImageIcon();

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkFactoryMachine frame = new DrinkFactoryMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DrinkFactoryMachine() {
		theFSM = new CoffeeMachineStatemachine();
		TimerService timer = new TimerService();
		theFSM.setTimer(timer);
		theFSM.init();
		theFSM.enter();
		theFSM.getSCInterface().getListeners().add(new DrinkFactoryMachineInterfaceImplementation(this));
		Drink coffee = new Coffee();
		Drink expresso = new Expresso();
		Drink tea = new Tea();
		millis = 0;
		/*secs = 0;
		mins = 0;*/
		actualStepNumber = 1;
		
		
		 Runnable r = new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						theFSM.runCycle();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			};
			t = new Thread(r);
			t.start();
	
		setForeground(Color.WHITE);
		setFont(new Font("Cantarell", Font.BOLD, 22));
		setBackground(Color.DARK_GRAY);
		setTitle("Drinking Factory Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>Hello <br>you can choose <br> your drink !");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 175);
		contentPane.add(messagesToUser);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		JButton coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.BLUE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);

		JButton expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.BLUE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		JButton teaButton = new JButton("Tea");
		teaButton.setForeground(Color.BLUE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		JButton soupButton = new JButton("Soup");
		soupButton.setForeground(Color.BLUE);
		soupButton.setBackground(Color.DARK_GRAY);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPane.add(progressBar);

		JSlider sugarSlider = new JSlider();
		sugarSlider.setValue(1);
		sugarSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sugarSlider.setBackground(Color.DARK_GRAY);
		sugarSlider.setForeground(Color.WHITE);
		sugarSlider.setPaintTicks(true);
		sugarSlider.setMinorTickSpacing(1);
		sugarSlider.setMajorTickSpacing(1);
		sugarSlider.setMaximum(4);
		sugarSlider.setBounds(301, 51, 200, 36);
		contentPane.add(sugarSlider);

		JSlider sizeSlider = new JSlider();
		sizeSlider.setPaintTicks(true);
		sizeSlider.setValue(1);
		sizeSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sizeSlider.setBackground(Color.DARK_GRAY);
		sizeSlider.setForeground(Color.WHITE);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMaximum(2);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setBounds(301, 125, 200, 36);
		contentPane.add(sizeSlider);

		JSlider temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);

		Hashtable<Integer, JLabel> temperatureTable = new Hashtable<Integer, JLabel>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

		contentPane.add(temperatureSlider);

		JButton icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.BLUE);
		icedTeaButton.setBackground(Color.DARK_GRAY);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);

		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
		contentPane.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPane.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPane.add(lblTemperature);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(panel);
		panel.setBounds(538, 25, 96, 97);
		contentPane.add(panel);

		JButton money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.BLUE);
		money50centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money50centsButton);

		JButton money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.BLUE);
		money25centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money25centsButton);

		JButton money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.BLUE);
		money10centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money10centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 96, 40);
		contentPane.add(panel_1);

		JButton nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.BLUE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		JButton addCupButton = new JButton("Remove cup");
		addCupButton.setForeground(Color.BLUE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures = new JLabel(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPane.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.BLUE);
		cancelButton.setBackground(Color.DARK_GRAY);
		panel_2.add(cancelButton);

		// listeners
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theFSM.raiseRemoveCup();
			}
		});
		
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseNFC();
				System.out.println("NFC");
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseCancel();
			}
		});
		
		// Coins buttons
		
		money10centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered+=0.1;
			}
		});
		money25centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered+=0.25;
			}
		});
		money50centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered+=0.5;
			}
		});
		
		// Drinks buttons
		
		coffeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualDrink = coffee;
				//System.out.println("a2");
				theFSM.raiseDrinkSelectionDone();
			}
		});
		expressoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualDrink = expresso;
				theFSM.raiseDrinkSelectionDone();
				
			}
		});
		teaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualDrink = tea;
				theFSM.raiseDrinkSelectionDone();
				
			}
		});
		
		
		
		
	
		// init a msTimer which is ready to do an action every 7 ms
		ActionListener doCountEvery7 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count(7);

				updateProgressBar(); // pour la barre de chargement
			}
		};
		msTimer = new Timer(7, doCountEvery7);
		

	}
	
	protected void updateProgressBar() {
		int  msFor1percent = (int) (actualDrink.getTimeToMake() * 1000 / 100);
		if (millis >= msFor1percent) {
			progressBar.setValue(progressBar.getValue()+ 1);
			millis = 0;
		}
		
	}

	public void doWaitForRecuperation() {
		msTimer.stop();
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/ownCup.jpg"));
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		labelForPictures.setIcon(new ImageIcon(myPicture));
		double leftToPay = coinsEntered- actualDrink.getPrice();
		messagesToUser.setText("<html> Récupérez votre goblet svp<br> et n'oubliez pas vos <br> " + leftToPay + " pièces");
		
	}
	
	protected void count(int nbMillisec) {
		millis += nbMillisec;
		/*if (millis >= 1000) {
			secs++;
			millis = 1000 - millis;
		}
		if (secs >= 60) {
			mins++;
			secs = 60 - secs;
		}*/
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		t.stop();
	}

	void doCheckPayment() { // methode appelée dès que l'on met des pieces ou selectionne une boisson
		if(actualDrink != null) { // si on a choisi la boisson et que l'on met des pieces
			double leftToPay = actualDrink.getPrice()-coinsEntered;
			if(leftToPay > 0) {
				messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> il manque " + leftToPay + "<html> €." );	
			
			} else if(leftToPay < 0) {
				leftToPay=-leftToPay;
				messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> veuillez récuperer les " + leftToPay + "<html> € de trop." );	
				theFSM.setPaymentChecked(true);
				actualDrink = actualDrink.getCopy(); //donne une copie afin de pouvoir y modifier les données sans crainte pour les commandes suivantes
			
			} else {
				messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> Le compte est bon." );	
				theFSM.setPaymentChecked(true);
				actualDrink = actualDrink.getCopy(); //donne une copie afin de pouvoir y modifier les données sans crainte pour les commandes suivantes
			}
		} else { // si la boisson n'est pas encore choisie, mais que l'on met d'abord des pièces
			messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €.");
		}
			
		
		
	}

	void doNextPreparationStep() {
		
		if(actualStepNumber > actualDrink.getStepsList().length) { // cas où il n'y a plus d'étape à faire
			theFSM.setReadyToDeliver(true);
			System.out.println("pret à etre livré");
			
		}
		else {
				
			String currentSteps = "";
			for(Step step :actualDrink.getStepsList()[actualStepNumber-1]) { //permet de savoir quelles étapes effectuer pour les afficher
				currentSteps += step.getName() + " ";
			}
			messagesToUser.setText("<html> Préparation étape "+ currentSteps);	
			
			setFSMTimers(); // met les temps des timers des taches de la FSM
			
			sayRdyForNextStep(); // dit à la FSM qu'on est prêt pour la prochaine étape
			
			actualStepNumber++;
			
		}
		
		
	}

	private void setFSMTimers() {
		for(Step step :actualDrink.getStepsList()[actualStepNumber-1]) {
			switch(step.getName()) {
			case "CupPositionning":
				theFSM.setCpTime(step.getTimeToMake());
				break;
			case "PodPositionning":
				theFSM.setPpTime(step.getTimeToMake());
				break;
			case "PooringWaterForSize":
				theFSM.setPwfsTime(step.getTimeToMake());
				break;
			case "PooringWaterForTime":
				theFSM.setPwftTime(step.getTimeToMake());
				break;
			case "SugarTheDrink":
				theFSM.setStdTime(step.getTimeToMake());
				break;
			case "WaitingForTemperature":
				theFSM.setWftTime(step.getTimeToMake());
				break;
			case "WaterHeating":
				theFSM.setWhTime(step.getTimeToMake());
				break;
			case "WaitingForInfusion":
				theFSM.setWfiTime(step.getTimeToMake());
				break;
			case "SachetWithDrawal":
				theFSM.setSwdTime(step.getTimeToMake());
				break;
			case "GrainMashing":
				theFSM.setGmTime(step.getTimeToMake());
				break;
			case "SachetPositionning":
				theFSM.setSpTime(step.getTimeToMake());
				break;
			case "GrainTamping":
				theFSM.setGtTime(step.getTimeToMake());
				break;
			}

		}
		
	}
	
	private void sayRdyForNextStep() {
		switch(actualDrink.getName()) {
		case "coffee":
			switch(actualStepNumber) {
				case 1:
					msTimer.start();
					theFSM.setOkForCoffeeStep1(true);
					System.out.println("ok for coffee step 1");
					break;
				case 2:
					theFSM.setOkForCoffeeStep2(true);
					System.out.println("ok for coffee step 2");
					break;
				case 3:
					theFSM.setOkForCoffeeStep3(true);
					System.out.println("ok for coffee step 3");
					break;
				default:
					break;
			}
			break;
			
		case "expresso":
			switch(actualStepNumber) {
				case 1:
					msTimer.start();
					theFSM.setOkForExpressoStep1(true);
					System.out.println("ok for expresso step 1");
					break;
				case 2:
					theFSM.setOkForExpressoStep2(true);
					System.out.println("ok for expresso step 2");
					break;
				case 3:
					theFSM.setOkForExpressoStep3(true);
					System.out.println("ok for expresso step 3");
					break;
				default:
					break;
			}
			break;
		
		case "tea":
			switch(actualStepNumber) {
				case 1:
					msTimer.start();
					theFSM.setOkForTeaStep1(true);
					System.out.println("ok for tea step 1");
					break;
				case 2:
					theFSM.setOkForTeaStep2(true);
					System.out.println("ok for tea step 2");
					break;
				case 3:
					theFSM.setOkForTeaStep3(true);
					System.out.println("ok for tea step 3");
					break;
				case 4:
					theFSM.setOkForTeaStep4(true);
					System.out.println("ok for tea step 4");
					break;
				case 5:
					theFSM.setOkForTeaStep5(true);
					System.out.println("ok for tea step 5");
					break;
				default:
					break;
			}
			break;
			
		default:
			break;
		}
	}

	public void doPrepareForNextOrderRaised() {
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		labelForPictures.setIcon(new ImageIcon(myPicture));
		messagesToUser.setText("<html>Hello <br>you can choose <br> your drink !");
		
		progressBar.setValue(0);
		millis= 0;
		actualStepNumber = 1;
		
		resetFSMbooleans();
		doResetDrinkSelected();
		doResetPayment();
		doResetSliders();
	}

	private void resetFSMbooleans() {
		switch(actualDrink.getName()) {
		case "coffee":
			theFSM.setOkForCoffeeStep1(false);
			theFSM.setOkForCoffeeStep2(false);
			theFSM.setOkForCoffeeStep3(false);
			break;
		case "expresso":
			theFSM.setOkForExpressoStep1(false);
			theFSM.setOkForExpressoStep2(false);
			theFSM.setOkForExpressoStep3(false);
			break;
		case "tea":
			theFSM.setOkForTeaStep1(false);
			theFSM.setOkForTeaStep2(false);
			theFSM.setOkForTeaStep3(false);
			theFSM.setOkForTeaStep4(false);
			theFSM.setOkForTeaStep5(false);
			break;
		}
		theFSM.setPaymentChecked(false);
		theFSM.setReadyToDeliver(false);
	}

	public void doResetDrinkSelected() {
		actualDrink = null;
	}

	public void doResetPayment() {
		coinsEntered = 0;
	}

	public void doResetSliders() {
		// TODO Auto-generated method stub
		
	}
	
}
