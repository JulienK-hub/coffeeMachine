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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigestSpi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import drinks.*;
import fr.univcotedazur.polytech.si4.fsm.project.NFC.Nfc;
import fr.univcotedazur.polytech.si4.fsm.project.coffeemachine.CoffeeMachineStatemachine;
import preparationSteps.Step;
import stock.Ingredient;
import stock.IngredientsStock;

public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private Thread t;
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel messagesToUser, labelForPictures, lblSugar;
	private final ImageIcon imageIcon = new ImageIcon();
	private JTextField nfcTextField;
	private JSlider temperatureSlider,sizeSlider,sugarSlider;
	BufferedImage myPicture;
	private ArrayList<JButton> buttons;
	private ArrayList<JCheckBox> optionCheckBoxes;
	private ArrayList<JSlider> sliders;
	private boolean owncupAdded;
	JButton coffeeButton, expressoButton, teaButton,soupButton, money50centsButton, money25centsButton, money10centsButton, cancelButton, nfcBiiiipButton, addCupButton;
	JCheckBox milkBox, mapleSyrupBox, vanillaIceCreamBox, croutonsBox;
	

	private int millis;
	private Timer msTimer;
	private CoffeeMachineStatemachine theFSM;
	private double coinsEntered;
	private Drink actualDrink; 
	private int actualStepNumber;
	private double leftToPay;
	private Nfc nfcData;
	private int numberForFreeDrink;
	private IngredientsStock stock;
	private String previousChoice;

	
	/**
	 * @wbp.nonvisual location=311,475
	 */

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
		Drink soup = new Soup();
		previousChoice = "";
		stock = new IngredientsStock();
		millis = 0;
		/*secs = 0;
		mins = 0;*/
		nfcData = new Nfc();
		actualStepNumber = 0;
		numberForFreeDrink = 11;
		
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

		messagesToUser = new JLabel("<html>Bonjour <br>veuillez choisir <br>une boisson !");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 150, 100);
		contentPane.add(messagesToUser);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.WHITE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		if(stock.getIngredients().get(Ingredient.COOFFEEPOD) == 0) {
			coffeeButton.setEnabled(false);
		}
		contentPane.add(coffeeButton);

		expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.WHITE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		if(stock.getIngredients().get(Ingredient.EXPRESSOGRAINDOSE) == 0) {
			expressoButton.setEnabled(false);
		}
		contentPane.add(expressoButton);

		teaButton = new JButton("Tea");
		teaButton.setForeground(Color.WHITE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		if(stock.getIngredients().get(Ingredient.TEASACHET) == 0) {
			teaButton.setEnabled(false);
		}
		contentPane.add(teaButton);

		soupButton = new JButton("Soup");
		soupButton.setForeground(Color.WHITE);
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
		
		

		sugarSlider = new JSlider();
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

		sizeSlider = new JSlider();
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

		temperatureSlider = new JSlider();
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

		
		
		milkBox = new JCheckBox("Milk");
		milkBox.setForeground(Color.WHITE);
		milkBox.setBackground(Color.DARK_GRAY);
		milkBox.setBounds(126, 145, 50, 25);
		milkBox.setEnabled(false);
		contentPane.add(milkBox);
		
		mapleSyrupBox = new JCheckBox("Maple Syrup");
		mapleSyrupBox.setForeground(Color.WHITE);
		mapleSyrupBox.setBackground(Color.DARK_GRAY);
		mapleSyrupBox.setBounds(126, 170, 100, 25);
		mapleSyrupBox.setEnabled(false);
		contentPane.add(mapleSyrupBox);
		
		vanillaIceCreamBox = new JCheckBox("Vanilla Ice Cream");
		vanillaIceCreamBox.setForeground(Color.WHITE);
		vanillaIceCreamBox.setBackground(Color.DARK_GRAY);
		vanillaIceCreamBox.setBounds(126, 195, 150, 25);
		vanillaIceCreamBox.setEnabled(false);
		contentPane.add(vanillaIceCreamBox);
		
		croutonsBox = new JCheckBox("Croutons");
		croutonsBox.setForeground(Color.WHITE);
		croutonsBox.setBackground(Color.DARK_GRAY);
		croutonsBox.setBounds(126, 220, 150, 25);
		croutonsBox.setEnabled(false);
		contentPane.add(croutonsBox);
		

		lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(340, 34, 90, 15);
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

		money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.WHITE);
		money50centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money50centsButton);

		money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.WHITE);
		money25centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money25centsButton);

		money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.WHITE);
		money10centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money10centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 96, 60);
		contentPane.add(panel_1);

		nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);
		
		nfcTextField = new JTextField(9);
		panel_1.add(nfcTextField);
		
		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.WHITE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 130, 25);
		contentPane.add(addCupButton);

		myPicture = null;
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

		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.DARK_GRAY);
		panel_2.add(cancelButton);
		
		
		buttons = new ArrayList<>();
		buttons.add(coffeeButton);
		buttons.add(expressoButton);
		buttons.add(teaButton);
		buttons.add(soupButton);
		buttons.add(money10centsButton);
		buttons.add(money25centsButton);
		buttons.add(money50centsButton);
		buttons.add(nfcBiiiipButton);
		buttons.add(cancelButton);
		buttons.add(addCupButton);
		
		
		optionCheckBoxes = new ArrayList<>();
		optionCheckBoxes.add(milkBox);
		optionCheckBoxes.add(mapleSyrupBox);
		optionCheckBoxes.add(vanillaIceCreamBox);
		optionCheckBoxes.add(croutonsBox);
		
		sliders = new ArrayList<>();
		sliders.add(sugarSlider);
		sliders.add(sizeSlider);
		sliders.add(temperatureSlider);
		


		
	
		// listeners 
		
		
		addCupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCup();
				if(addCupButton.getText().equals("Add cup")) {
					try {
						myPicture = ImageIO.read(new File("./picts/ownCup.jpg"));
						labelForPictures.setIcon(new ImageIcon(myPicture));
						addCupButton.setText("Remove cup");
						if(actualDrink != null) {
							actualDrink.setPrice(actualDrink.getPrice() - 0.1);
							doCheckPayment();
						}
					} catch (IOException excep) {
						excep.printStackTrace();
					}
				} else {
					try {
						myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
						labelForPictures.setIcon(new ImageIcon(myPicture));
						addCupButton.setText("Add cup");
						if(actualDrink != null) {
							actualDrink.setPrice(actualDrink.getPrice() + 0.1);
							doCheckPayment();

						}
						theFSM.raiseRemoveCup();
					} catch (IOException excep) {
						excep.printStackTrace();
					}
				}
			}
		});
		
		// sliders listeners 
		
		sugarSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				theFSM.raiseSliderModified();
				
			}
		});
		
		sizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				theFSM.raiseSliderModified();
				
			}
		});
		
		sugarSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				theFSM.raiseSliderModified();
				
			}
		});
		
		
		// NFC button listener
		
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(actualDrink == null) {
					messagesToUser.setText("<html> Sélectionnez d'abord<br> votre boisson <br> ");
				}
				else if (nfcTextField.getText().length()==0){
					messagesToUser.setText("<html> Vous n'avez pas <br> présenté votre carte <br> ");
				}
					
				else {
					int nfcId = Integer.parseInt(nfcTextField.getText());
					nfcData.add1(nfcId, actualDrink.getPrice());
					System.out.println("Données nfc: "+ nfcData.toString());
					if(nfcData.getScanNumber(nfcId) >= numberForFreeDrink && actualDrink.getPrice() <= nfcData.getAveragePurchase(nfcId)) {
						messagesToUser.setText("<html> Vous avez fait plus de 10 achats ! " + "<html> <br> celui-ci vous est offert ! <br> et n'oubliez pas vos pièces <br> si vous en avez rentré" );
						nfcData.resetCount(nfcId);
						theFSM.setPaymentChecked(true);
						actualDrink = actualDrink.getCopy();
					}
					else {
						coinsEntered += actualDrink.getPrice();
						doCheckPayment();
					}
					theFSM.raiseNFC();
					nfcTextField.setText("");
				}
				
			}
		});
		
		// Cancel button listener
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseCancel();
				messagesToUser.setText("<html> Paiment annulé, reprennez vos <br> "+ coinsEntered + "€");
				doResetPayment();
				
				
			}
		});
		
		// Coins buttons
		
		money10centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered = round(coinsEntered + 0.1, 2);
			}
		});
		money25centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered = round(coinsEntered + 0.25, 2);
			}
		});
		money50centsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseAddCoin();
				coinsEntered = round(coinsEntered + 0.5, 2);
			}
		});
		
		// Drink buttons listeners
		
		coffeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// on ne veut pas redefinir l'objet actualDrink si on vient de cliquer sur le bouton coffee, 
				//autrement on a un nouvel objet avec un prix de base alors qu'on a possiblement selectionné une ou plusieurs options

				if (actualDrink != coffee) { 
					actualDrink = coffee; 
					updateDrinkButtonscolor();
					coffeeButton.setBackground(Color.GRAY);
					previousChoice = "coffee";
					
					croutonsBox.setSelected(false);
					milkBox.setEnabled(true);
					mapleSyrupBox.setEnabled(true);            // actualiser les options disponibles pour cette boisson
					vanillaIceCreamBox.setEnabled(true);
					croutonsBox.setEnabled(false);
					lblSugar.setText("Sugar");


					calculatePrice();                        // pour recalculer le prix de cette boisson avec les potentielles options choisies lors de la derniere sélection
					// Exemple : on avait selectionné un café avec l'option glace vanille, puis on clique sur expresso, il faut penser à actualiser le prix total de la boisson avec cette option toujours selectionné
					
					if (stock.isIngredientInStock(Ingredient.COOFFEEPOD) && enoughIngredientForSugarSlider()) {
						theFSM.raiseDrinkSelectionDone();
					}
				}
			}
		});
		expressoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actualDrink != expresso) { 
					actualDrink = expresso;
					updateDrinkButtonscolor();
					expressoButton.setBackground(Color.GRAY);
					previousChoice = "expresso";
					
					croutonsBox.setSelected(false);
					milkBox.setEnabled(true);
					mapleSyrupBox.setEnabled(true); 
					vanillaIceCreamBox.setEnabled(true);
					croutonsBox.setEnabled(false);
					lblSugar.setText("Sugar");

					calculatePrice();

					if (stock.isIngredientInStock(Ingredient.EXPRESSOGRAINDOSE) && enoughIngredientForSugarSlider()) {
						theFSM.raiseDrinkSelectionDone();
					}
				
				}
			}
		});
		teaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actualDrink != tea) { 
					actualDrink = tea;
					updateDrinkButtonscolor();
					teaButton.setBackground(Color.GRAY);
					previousChoice = "tea";

					vanillaIceCreamBox.setSelected(false);
					croutonsBox.setSelected(false);
					milkBox.setEnabled(true);
					mapleSyrupBox.setEnabled(true);
					vanillaIceCreamBox.setEnabled(false);
					croutonsBox.setEnabled(false);
					lblSugar.setText("Sugar");

					calculatePrice();
				
					if (stock.isIngredientInStock(Ingredient.TEASACHET) && enoughIngredientForSugarSlider()) {
						theFSM.raiseDrinkSelectionDone();
					}
				}
			}
		});
		soupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actualDrink != soup) { 
					actualDrink = soup;
					updateDrinkButtonscolor();
					soupButton.setBackground(Color.GRAY);
					previousChoice = "soup";
					
					milkBox.setSelected(false);
					mapleSyrupBox.setSelected(false);
					vanillaIceCreamBox.setSelected(false);
					milkBox.setEnabled(false);
					mapleSyrupBox.setEnabled(false);
					vanillaIceCreamBox.setEnabled(false);
					croutonsBox.setEnabled(true);
					lblSugar.setText("Spices");
					
					calculatePrice();

					if (stock.isIngredientInStock(Ingredient.SOUPPOD) && enoughIngredientForSugarSlider()) {
						theFSM.raiseDrinkSelectionDone();
					}
				}
			}
		});
		
		
		// Option checkboxes listeners
		
		milkBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseOptionSelection();
				
				if(milkBox.isSelected()) {
					actualDrink.setPrice(actualDrink.getPrice() + 0.1);
					theFSM.setMilk(true);
				} else {
					actualDrink.setPrice(actualDrink.getPrice() - 0.1);
					theFSM.setMilk(false);
				}
				doCheckPayment();
			}
		});
		
		mapleSyrupBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseOptionSelection();
				if(mapleSyrupBox.isSelected()) {
					actualDrink.setPrice(actualDrink.getPrice() + 0.1);
					lblSugar.setText("Maple Syrup");
				} else {
					actualDrink.setPrice(actualDrink.getPrice() - 0.1);
					lblSugar.setText("Sugar");
				}
				doCheckPayment();
			}
		});
		
		vanillaIceCreamBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseOptionSelection();
				if(vanillaIceCreamBox.isSelected()) {
					actualDrink.setPrice(actualDrink.getPrice() + 0.6);
					theFSM.setIceCream(true);
				} else {
					actualDrink.setPrice(actualDrink.getPrice() - 0.6);
					theFSM.setIceCream(false);
				}
				doCheckPayment();
			}
		});
		
		croutonsBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				theFSM.raiseOptionSelection();
				if(croutonsBox.isSelected()) {
					actualDrink.setPrice(actualDrink.getPrice() + 0.3);
				} else {
					actualDrink.setPrice(actualDrink.getPrice() - 0.3);
				}
				doCheckPayment();
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
		int  msFor1percent = (int) (actualDrink.getTimeToMake() / 100);
		if (millis >= msFor1percent) {
			progressBar.setValue(progressBar.getValue()+ 1);
			millis = 0;
		}
	}
	
	protected void calculatePrice() {
		actualDrink = actualDrink.getCopy();
		if(milkBox.isSelected()) 
			actualDrink.setPrice(actualDrink.getPrice() + 0.1);
		if(mapleSyrupBox.isSelected()) 
			actualDrink.setPrice(actualDrink.getPrice() + 0.1);
		if(vanillaIceCreamBox.isSelected()) 
			actualDrink.setPrice(actualDrink.getPrice() + 0.6);
		if(addCupButton.getText().equals("Remove cup")) {
			actualDrink.setPrice(actualDrink.getPrice() - 0.1);
		}
	}
	
	protected void enableUserToTakeHisDrink() {
		addCupButton.setEnabled(true);
	}
	
	protected void updateDrinkButtonscolor() {
		switch(previousChoice) {
		case "coffee":
			coffeeButton.setBackground(Color.DARK_GRAY);
			break;
		case "expresso":
			expressoButton.setBackground(Color.DARK_GRAY); 
			break;
		case "tea":
			teaButton.setBackground(Color.DARK_GRAY); 
			break; 
		case "soup":
			soupButton.setBackground(Color.DARK_GRAY);  
			break;
		case "":
			break;
		}
	}
	
	protected void blockTheUI() {
		for(JButton button : buttons) {
			button.setEnabled(false);
		}
		for(JCheckBox optionBox : optionCheckBoxes) {
			optionBox.setEnabled(false);
		}
		for(JSlider slider : sliders) {
			slider.setEnabled(false);
		}
		nfcTextField.setEnabled(false);
	}
	

	protected void doWaitForRecuperation() {
		msTimer.stop();
		messagesToUser.setText("<html> Récupérez votre boisson svp<br> et n'oubliez pas vos <br> " + leftToPay + " €");
		addCupButton.setEnabled(true);
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

	protected void doCheckPayment() { // methode appelée dès que l'on met des pieces ou selectionne une boisson
		if(actualDrink != null) { // si on a choisi la boisson et que l'on met des pieces
			leftToPay = round(actualDrink.getPrice()-coinsEntered, 2);
			if(leftToPay > 0) { // le paiement n'est pas validé
				messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> il manque " + leftToPay + "<html> €." );	
			} else { // la transaction est validée, la préparation commence
				if (leftToPay < 0 ) {
					leftToPay=-leftToPay;
					messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> veuillez récuperer les " + leftToPay + "<html> € de trop." );	
				} else {
					messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €, <br> Le compte est bon." );	
				}
				theFSM.setPaymentChecked(true);
				if(addCupButton.getText().equals("Remove cup")) {
					actualDrink.getStep("CupPositionning").setTimeToMake(0);
				}
				actualDrink = actualDrink.getCopy(); //donne une copie afin de pouvoir y modifier les données sans crainte pour les commandes suivantes
				adaptDrinkToSliders(); // on modifie les temps de préparation en fonction des valeurs des sliders 
				consumeIngredientsFromStock(); // on met à jour le stock d'ingrédients
				consumeOptionIngredientsFromStock();
				blockTheUI(); // on rend impossible toute interaction avec la coffee machine pendant le temps de la préparation de la boisson
			}
		} else { // si la boisson n'est pas encore choisie, mais que l'on met d'abord des pièces
			messagesToUser.setText("<html> Vous avez mis un total de : "+ coinsEntered + "<html> €.");
		}
	}

	protected boolean enoughIngredientForSugarSlider() {
		Ingredient sliderIngredient = Ingredient.SUGAR;
		if(mapleSyrupBox.isSelected()) {
			sliderIngredient = Ingredient.MAPLESYRUPDOSE;
		}
		if(soupButton.isSelected()) {
			sliderIngredient = Ingredient.SPICEDOSE;
		}
		updatePossibleValuesForSugarSlider(sliderIngredient);
		return (stock.getIngredients().get(sliderIngredient) >= sugarSlider.getValue());
	}
	
	protected void consumeIngredientsFromStock() {
		switch(actualDrink.getName()) {
			case "coffee": 
				stock.consumeIngredient(Ingredient.COOFFEEPOD, 1);
				break;
			case "expresso":
				stock.consumeIngredient(Ingredient.EXPRESSOGRAINDOSE, 1);
				break;
			case "tea":
				stock.consumeIngredient(Ingredient.TEASACHET, 1);
				break;
			case "soup":
				stock.consumeIngredient(Ingredient.SOUPPOD, 1);
				break;
		}
		if(mapleSyrupBox.isSelected()) {
			stock.consumeIngredient(Ingredient.MAPLESYRUPDOSE, sugarSlider.getValue());
		}
		else if(soupButton.isSelected()) {
			stock.consumeIngredient(Ingredient.SPICEDOSE, sugarSlider.getValue());
		} else {
			stock.consumeIngredient(Ingredient.SUGAR, sugarSlider.getValue());
		}
		
	}
	
	protected void consumeOptionIngredientsFromStock() {
		if(milkBox.isSelected()) {
			stock.consumeIngredient(Ingredient.MILKDOSE, 1);
		}
		if(vanillaIceCreamBox.isSelected()) {
			stock.consumeIngredient(Ingredient.VANILLAICECREAMDOSE, 1);
		}
		if(croutonsBox.isSelected()) {
			stock.consumeIngredient(Ingredient.CROUTONDOSE, 1);
		}
	}
	
	protected void disableButtonsForUndoableDrinks() {
		if(stock.getIngredients().get(Ingredient.COOFFEEPOD) == 0) 
			coffeeButton.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.EXPRESSOGRAINDOSE) == 0) 
			expressoButton.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.TEASACHET) == 0) 
			teaButton.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.SOUPPOD) == 0) 
			soupButton.setEnabled(false);
	}
	
	protected void disableCheckBoxesForUndoableOptions() {
		if(stock.getIngredients().get(Ingredient.MILKDOSE) == 0) 
			milkBox.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.MAPLESYRUPDOSE) == 0) 
			mapleSyrupBox.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.VANILLAICECREAMDOSE) == 0) 
			vanillaIceCreamBox.setEnabled(false);
		if(stock.getIngredients().get(Ingredient.CROUTONDOSE) == 0) 
			croutonsBox.setEnabled(false);
	}
	
	protected void updatePossibleValuesForSugarSlider(Ingredient ingredient) {
		switch(stock.getIngredients().get(ingredient)) {
			case 0 : 
				sugarSlider.setValue(0);
				sugarSlider.setMaximum(0);
				break;
			case 1 :
				sugarSlider.setMaximum(1);
				break;
			case 2 :
				sugarSlider.setMaximum(2);
				break;
			case 3: 
				sugarSlider.setMaximum(3);
				break;
		}
	}
	

	protected void adaptDrinkToSliders() {
		Step waterHeating = actualDrink.getStep("WaterHeating");
		Step waitingForTemperature = actualDrink.getStep("WaitingForTemperature");
		Step grainMashing = actualDrink.getStep("GrainMashing");
		Step grainTamping = actualDrink.getStep("GrainTamping");
		Step pouringWaterForSize = actualDrink.getStep("PouringWaterForSize");
		Step sugarTheDrink = actualDrink.getStep("SugarTheDrink");
		Step waitingForInfusion = actualDrink.getStep("WaitingForInfusion");
		Step spicingTheDrink = actualDrink.getStep("SpicingTheDrink");
		
		if (waterHeating != null) {
			waterHeating.addTimeToMake((temperatureSlider.getValue() - 2)*5 *1000);
			waterHeating.addTimeToMake((int) ((sizeSlider.getValue() - 1)*0.5*waterHeating.getTimeToMake())); 
			// si le slider size est sur 0 (= la boisson est deux fois plus petite que celle de base médium), le temps de chauffage de l'eau est aussi deux fois plus petit
			// si le slider size est sur 2 (= la boisson est deux fois plus grande que celle de base médium), le temps de chauffage de l'eau est aussi deux fois plus grand
		}
		if (waitingForTemperature != null) {
			waitingForTemperature.addTimeToMake((temperatureSlider.getValue() - 2)*2 *1000); 
			
			// si la valeur du slider vaut 2 c'est qu'on est au temps par défaut,
			// si elle faut moins il faut retirer du temps, si elle vaut plus il faut en ajouter d'où le -2 dans le calcul
		
		}if (grainMashing != null) {
			grainMashing.addTimeToMake((int) ((sizeSlider.getValue() - 1)*0.5*grainMashing.getTimeToMake()));
		}
		if (grainTamping != null) {
			grainTamping.addTimeToMake((int) ((sizeSlider.getValue() - 1)*0.5*grainTamping.getTimeToMake()));
		}
		if (pouringWaterForSize != null) {
			pouringWaterForSize.addTimeToMake((int) ((sizeSlider.getValue() - 1)*0.5*pouringWaterForSize.getTimeToMake()));
		}
		if (sugarTheDrink != null) {
			if(sugarSlider.getValue() == 0) {
				sugarTheDrink.setTimeToMake(0);
			} else {
				sugarTheDrink.addTimeToMake((int) ((sugarSlider.getValue() - 1)*0.5*sugarTheDrink.getTimeToMake()));
				
			}
		}
		if (waitingForInfusion != null){
			waitingForInfusion.addTimeToMake((int) ((sizeSlider.getValue() - 1)*0.5*waitingForInfusion.getTimeToMake()));
		}
		
		if (spicingTheDrink != null) {
			if(sugarSlider.getValue() == 0) {
				spicingTheDrink.setTimeToMake(0);
			} else {
				spicingTheDrink.addTimeToMake((int) ((sugarSlider.getValue() - 1)*0.5*spicingTheDrink.getTimeToMake()));
			}
		}
	}

	protected void doNextPreparationStep() {
		actualStepNumber++;
		if(actualStepNumber > actualDrink.getStepsList().length) { // cas où il n'y a plus d'étape à faire
			theFSM.setReadyToDeliver(true);
			if(theFSM.getIceCream() && theFSM.getMilk())				
				messagesToUser.setText("<html>Préparation, étape : <br> glace vanille <br> nuage de lait");
			if(theFSM.getIceCream() && !theFSM.getMilk())
				messagesToUser.setText("<html>Préparation, étape : <br> glace vanille");
			if(theFSM.getMilk() && !theFSM.getIceCream())
				messagesToUser.setText("<html>Préparation, étape : <br> nuage de lait");
			if(croutonsBox.isSelected())
				messagesToUser.setText("<html>Préparation, étape : <br> croutons");

			System.out.println("Pret à etre livré");
		}
		else {
			setFSMTimers(); // met les temps des timers des taches de la FSM
			sayRdyForNextStep(); // dit à la FSM qu'on est prêt pour la prochaine étape
		}
	}
	
	protected void doPrintNextStep() {
		String currentSteps = "";
		for(Step step :actualDrink.getStepsList()[actualStepNumber-1]) { //permet de savoir quelles étapes effectuer pour les afficher
			if(step.getName().equals("CupPositionning") && addCupButton.getText().equals("Add cup")) {
				try {
					currentSteps += step.getName() + " ";
					myPicture = ImageIO.read(new File("./picts/gobeletPolluant.jpg"));
					labelForPictures.setIcon(new ImageIcon(myPicture));
					addCupButton.setText("Remove cup");
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			
			else if (!(step.getName().equals("CupPositionning")) || !(addCupButton.getText().equals("Remove cup"))){
				currentSteps += step.getName() + " ";
				
			}
	
		}
		messagesToUser.setText("<html>Préparation, étapes : "+ currentSteps);	
		//System.out.println("step " + actualStepNumber + " done");
	}

	protected void setFSMTimers() {
		for(Step step :actualDrink.getStepsList()[actualStepNumber-1]) {
			switch(step.getName()) {
			case "CupPositionning":
				theFSM.setCpTime(step.getTimeToMake());
				break;
			case "PodPositionning":
				theFSM.setPpTime(step.getTimeToMake());
				break;
			case "PouringWaterForSize":
				theFSM.setPwfsTime(step.getTimeToMake());
				break;
			case "PouringWaterForTime":
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
			case "SoupPodPouring":
				theFSM.setSppTime(step.getTimeToMake());
				break;
			case "SpicingTheDrink":
				theFSM.setSpicingTime(step.getTimeToMake());
				break;
			case "AddingCroutons":
				if (croutonsBox.isSelected()){
					theFSM.setAcTime(step.getTimeToMake() + 3000);
				} else {
					theFSM.setAcTime(0);
				}
				break;
			}

		}
		
	}
	
	protected void sayRdyForNextStep() {
		switch(actualDrink.getName()) {
		case "coffee":
			switch(actualStepNumber) {
				case 1:					
					actualDrink.updateTotalTimeTomake();
					checkOptions();
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
					actualDrink.updateTotalTimeTomake();
					checkOptions();
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
					actualDrink.updateTotalTimeTomake();
					checkOptions();
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
			
		case "soup":
			switch(actualStepNumber) {
				case 1:
					actualDrink.updateTotalTimeTomake();
					if(croutonsBox.isSelected()) {
						actualDrink.addTimeToMake(3000);
					}
					msTimer.start();
					theFSM.setOkForSoupStep1(true);
					System.out.println("ok for soup step 1");
					break;
				case 2:
					theFSM.setOkForSoupStep2(true);
					System.out.println("ok for soup step 2");
					break;
				case 3:
					theFSM.setOkForSoupStep3(true);
					System.out.println("ok for soup step 3");
					break;
			}
			break;
			
		default:
			break;
		}
	}

	protected void checkOptions() {
		if(milkBox.isSelected()) {
			actualDrink.addTimeToMake(2000);
		}
		if(vanillaIceCreamBox.isSelected()) {
			actualDrink.addTimeToMake(4000);
		}
		
	}
	

	protected void doPrepareForNextOrderRaised() {
		messagesToUser.setText("<html>Bonjour <br>veuillez choisir <br> une boisson!");
		
		progressBar.setValue(0);
		millis= 0;
		actualStepNumber = 0;
		
		resetFSMbooleans();
		doResetDrinkSelected();
		doResetPayment();
		doResetSliders();
		
		unblockTheUI();
		disableButtonsForUndoableDrinks();
		disableCheckBoxesForUndoableOptions();
	}

	protected void resetFSMbooleans() {
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
		case "soup":
			theFSM.setOkForSoupStep1(false);
			theFSM.setOkForSoupStep2(false);
			theFSM.setOkForSoupStep3(false);
			break;
		}
		theFSM.setPaymentChecked(false);
		theFSM.setReadyToDeliver(false);
		theFSM.setMilk(false);
		theFSM.setIceCream(false);
	}

	protected void doResetDrinkSelected() {
		coffeeButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBackground(Color.DARK_GRAY);
		teaButton.setBackground(Color.DARK_GRAY);
		soupButton.setBackground(Color.DARK_GRAY);
		milkBox.setSelected(false);
		mapleSyrupBox.setSelected(false);
		vanillaIceCreamBox.setSelected(false);
		croutonsBox.setSelected(false);
		milkBox.setEnabled(false);
		mapleSyrupBox.setEnabled(false);
		vanillaIceCreamBox.setEnabled(false);
		croutonsBox.setEnabled(false);
		actualDrink = null;
	}

	protected void doResetPayment() {
		coinsEntered = 0;
		leftToPay = 0;
		nfcTextField.setText("");
	}

	protected void doResetSliders() {
		lblSugar.setText("Sugar");
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
	}
	
	protected void unblockTheUI() {
		coffeeButton.setEnabled(true);
		expressoButton.setEnabled(true);
		teaButton.setEnabled(true);
		soupButton.setEnabled(true);
		sugarSlider.setEnabled(true);
		sizeSlider.setEnabled(true);
		temperatureSlider.setEnabled(true);
		money50centsButton.setEnabled(true);
		money25centsButton.setEnabled(true);
		money10centsButton.setEnabled(true);
		nfcBiiiipButton.setEnabled(true);
		cancelButton.setEnabled(true);
		nfcTextField.setEnabled(true);
		addCupButton.setEnabled(true);
	}

	
	protected static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	protected void doResetOperation() {
		coffeeButton.setBackground(Color.DARK_GRAY); // on réinitialise la couleur de fond du bouton selectionné
		expressoButton.setBackground(Color.DARK_GRAY);
		teaButton.setBackground(Color.DARK_GRAY);
		soupButton.setBackground(Color.DARK_GRAY);
		milkBox.setSelected(false); // on réinitialise les options
		mapleSyrupBox.setSelected(false);
		vanillaIceCreamBox.setSelected(false);
		croutonsBox.setSelected(false);
		milkBox.setEnabled(false); // on rend les options incochables (tant que pas de boisson selectionnée)
		mapleSyrupBox.setEnabled(false);
		vanillaIceCreamBox.setEnabled(false);
		croutonsBox.setEnabled(false);
		doResetSliders();
		if(coinsEntered > 0) {
			messagesToUser.setText("<html>Abandon, <br> recuperez vos <br> " + coinsEntered + " €. <br> Veuillez choisir <br>une boisson !");
		}
		else {
			messagesToUser.setText("<html>Abandon, veuillez choisir <br>une boisson !");
		}
		actualDrink = null;
		doResetPayment();
		doResetSliders();
	}
	
}
