import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

public class WareHouseGUI {

	public MowerWareHouse wareHouse;
	public JFrame homePage;
	//public JFrame addPage;
	public JFrame viewFrame;
	public String storeName = "";
	public JLabel greeting = new JLabel("No Warehouse Available");
	public boolean updateSaved = true;
	public PushReelMower pr;
	public LawnTractor lt;
	public Engine le;
	public GasPoweredMower gp;
	public Engine ge;
	public CommercialMower cw;
	public Engine ce;
	public boolean exitCalled = false;
	public int mowerNumber;
	
	// Launch the Application
	public static void main(String[] args) {
		new WareHouseGUI();
	}

	
	public WareHouseGUI() {
		wareHouse = new MowerWareHouse();
		createHomePage();
	}

	
	// Check if there is a warehouse name
	public boolean checkWareHouse() {
		boolean nameAvailable = true;
		if (wareHouse.getStoreName() == null) {
			nameAvailable = false;
		}

		return nameAvailable;
	}
	
	
	// Allow user to name the warehouse
	public void changeWareHouseName() {
		
		JDialog addFields = new JDialog(homePage, "Add Mower Warehouse Name");
		addFields.setSize(new Dimension(500, 400));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(120, 20, 20, 20));
		
		// Main Panel Components
		
		// Information Panel components
		// Panel for page information
		JPanel infoPanel = new JPanel(); 
		JLabel info = new JLabel("Welcome to the name warehouse page.");
		info.setFont(new Font("SansSerif", Font.BOLD, 21));
		info.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Please enter your warehouse name.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 17));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(info);
		infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		// Input Panel components and its components
		// Panel for store name input
		JPanel inputPanel = new JPanel(); 
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(250, 35));
		input.setMaximumSize(new Dimension(250, 35));
		input.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		input.setFont(new Font("SansSerif",Font.PLAIN,15));
		input.setEditable(true);
		
		
		JButton saveButton = new JButton("Create Store");
		saveButton.setPreferredSize(new Dimension(100,35));
		saveButton.setMaximumSize(new Dimension(100,35));
		saveButton.setFont(new Font("SansSerif",Font.PLAIN,14));


		inputPanel.add(input);
		inputPanel.add(Box.createRigidArea(new Dimension(8,0)));
		inputPanel.add(saveButton);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		// Confirmation Panel and its components
		// Panel for confirmation
		JPanel conPanel = new JPanel(); 
		JLabel confirmation = new JLabel();
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
		mainPanel.add(inputPanel);

		addFields.add(mainPanel);
		addFields.setLocationRelativeTo(homePage);
		addFields.setVisible(true);

		// Action to save warehouse name and update menu with warehouse name
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid name.");
					confirmation.setForeground(Color.RED);
					confirmation.setFont(new Font(null,Font.PLAIN,15));
				} else {
					wareHouse.setStoreName(input.getText().strip());
					storeName = wareHouse.getStoreName();
					greeting.setText("Welcome to " + storeName + "!");
					updateSaved = false;
					addFields.dispose();
				}

			}

		});

	}
	
	
	// Creates Home Page
	public void createHomePage() {
		
		//Create the home page
		homePage = new JFrame("Home Page");
		homePage.setSize(new Dimension(1200, 500));
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePage.setResizable(false);
		
		// Panels for the home page
		
		//Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(140,33,33,33));

		//Introduction Panel and its components
		JPanel introPanel = new JPanel();
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
		
		
		//Inner introPanel to help place infoButton and greeting side by side
		JPanel innerIntroPanel = new JPanel();
		innerIntroPanel.setLayout(new BoxLayout(innerIntroPanel,BoxLayout.X_AXIS));
		
		JButton infoButton = new JButton();
		infoButton.setPreferredSize(new Dimension(45,45));
		infoButton.setMaximumSize(new Dimension(45,45));

		ImageIcon infoIcon = new ImageIcon("info-icon.png");
		Image scaledIcon = infoIcon.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH);
		infoButton.setIcon(new ImageIcon(scaledIcon));
		infoButton.setOpaque(true);
		infoButton.setBorderPainted(false);
		
		// Greeting Label
		greeting.setFont(new Font("SansSerif", Font.BOLD, 26));
		greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Add to innerIntroPanel
		innerIntroPanel.add(greeting);
		innerIntroPanel.add(Box.createRigidArea(new Dimension(20,0)));
		innerIntroPanel.add(infoButton);

		// Instructions Label
		JLabel instructions = new JLabel("Select An Operation:");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 16));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Add introPanel components
		introPanel.add(innerIntroPanel);
		introPanel.add(Box.createRigidArea(new Dimension(0,30)));
		introPanel.add(instructions);
		
		// Options Panel and its components
		// optionsPanel components where operation buttons are located
		JPanel optionsPanel = new JPanel();
	
		JButton addMower = new JButton("Add Mower");
		addMower.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton viewWareHouse = new JButton("View Warehouse");
		viewWareHouse.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton loadFile = new JButton("Load File");
		loadFile.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton saveFile = new JButton("Save File");
		saveFile.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton rename = new JButton("Rename Warehouse");
		rename.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton deleteMower = new JButton("Delete Mower");
		deleteMower.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		JButton exit = new JButton("Exit");
		exit.setFont(new Font("SansSerif",Font.PLAIN,16));
		

		// Add action listeners
		addMower.addActionListener(e -> addMowerAction());
		viewWareHouse.addActionListener(e -> viewWareHouseAction());
		loadFile.addActionListener(e -> loadWareHouseAction());
		saveFile.addActionListener(e -> saveWareHouseAction());
		rename.addActionListener(e->changeWareHouseName());
		deleteMower.addActionListener(e->deleteMowerAction());
		exit.addActionListener(e -> exitWareHouseAction());

		// Add buttons to be displayed
		optionsPanel.add(addMower);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(viewWareHouse);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(loadFile);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(saveFile);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(rename);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(deleteMower);
		optionsPanel.add(Box.createRigidArea(new Dimension(15,0)));
		optionsPanel.add(exit);

		optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		
		
		mainPanel.add(introPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
		mainPanel.add(optionsPanel);
		
		//Design for jOptionPane (Warehouse Information Pop-up)
		JPanel panePanel = new JPanel();
		panePanel.setBackground(new Color(240,240,255));
		panePanel.setLayout(new BoxLayout(panePanel,BoxLayout.Y_AXIS));
		
		// create a raised panel with padding
		panePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				new EmptyBorder(20, 20, 20, 20)
		));
		
		
		JLabel title = new JLabel("Warehouse Information");
		title.setFont(new Font("SansSerif",Font.BOLD,19));
		
		JLabel line1 = new JLabel("Home page shows:\n");
		line1.setFont(new Font("SansSerif",Font.BOLD,15));
		
		// create underline under line1
		Font  lineFont = line1.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(lineFont.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		line1.setFont(lineFont.deriveFont(attributes));
		
		
		JLabel line2 = new JLabel("- 'Welcome to <Store Name>!' if warehouse is named\n");
		line2.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		JLabel line3 = new JLabel("- 'No Warehouse Available' if there is no warehouse\n");
		line3.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		JLabel line4 = new JLabel("You can rename the warehouse by loading a file or using the 'Rename Warehouse' button\n");
		line4.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		JLabel line5 = new JLabel("If there is no warehouse name, saving it will use a default name");
		line5.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		panePanel.add(title);
		panePanel.add(Box.createRigidArea(new Dimension(0,15)));
		panePanel.add(line1);
		panePanel.add(Box.createRigidArea(new Dimension(0,8)));
		panePanel.add(line2);
		panePanel.add(Box.createRigidArea(new Dimension(0,4)));
		panePanel.add(line3);
		panePanel.add(Box.createRigidArea(new Dimension(0,4)));
		panePanel.add(line4);
		panePanel.add(Box.createRigidArea(new Dimension(0,4)));
		panePanel.add(line5);
		
		//custom icon to display in Information pop-up 
		ImageIcon paneIcon = new ImageIcon (infoIcon.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH));
		
		//call for store name information pop-up
		infoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						homePage,
						panePanel,
						"Information",
						JOptionPane.INFORMATION_MESSAGE,
						paneIcon
						);
			}

		});

		// Add panels to window
		homePage.add(mainPanel);
		homePage.setLocationRelativeTo(null);
		homePage.setVisible(true);


		updateSaved = true;
	}
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Delete Mower Section
	
	//Staring page to delete mower
	public void  deleteMowerAction() {
		JFrame delPage = new JFrame("Delete Mower Data");
		delPage.setSize(new Dimension(1100, 700));
		delPage.setResizable(false);
		
		// Panels for Delete Mower Page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(90, 10, 30, 10));
		
		
		// Panel for Page information and greetings
		JPanel infoPanel = new JPanel();
		
		JLabel instruc1 = new JLabel("Enter the number of the mower you wish to delete.");
		instruc1.setFont(new Font("SansSerif",Font.BOLD,18));
		instruc1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc2 = new JLabel("The right side will show submission details for confirmation.");
		instruc2.setFont(new Font("SansSerif",Font.PLAIN,16));
		instruc2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc3 = new JLabel("Press confirm if you are satisfied.");
		instruc3.setFont(new Font("SansSerif",Font.BOLD,16));
		instruc3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(instruc1);
		infoPanel.add(instruc2);
		infoPanel.add(instruc3);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setMaximumSize(infoPanel.getPreferredSize());
		
		// Confirmation Panel and its components
		// Panel for confirmation
		JPanel conPanel = new JPanel();
		
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif",Font.PLAIN,16));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setPreferredSize(new Dimension(500,50));
		conPanel.setMaximumSize(conPanel.getPreferredSize());
		
		JPanel numPanel = new JPanel();
		
		JLabel numMowers = new JLabel("Current Number of Mowers: 0");
		numMowers.setFont(new Font("SansSerif",Font.PLAIN,16));
		numMowers.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		numPanel.add(numMowers);
		numPanel.setPreferredSize(new Dimension(400,50));
		numPanel.setMaximumSize(numPanel.getPreferredSize());
		
		// Field Panel and its components
		// Panel for input
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.X_AXIS));
		fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Inner panels for fieldPanel
		// Panel for input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(800, 350));
		inputPanel.setMaximumSize(new Dimension(800,350));
		TitledBorder inputTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Mower List",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		inputPanel.setBorder(inputTitle);
		inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.Y_AXIS));
		
		// Displays what mower the user is deleting for confirmation
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(500, 350));
		displayPanel.setMaximumSize(new Dimension(500,350));
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));
		TitledBorder displayTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), 
				"Mower Deletion Confirmation",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		displayPanel.setBorder(displayTitle);
	
		
		JTextArea displayText = new JTextArea();
		displayText.setFont(new Font("SansSerif",Font.PLAIN,15));
		displayText.setEditable(false);
		
		JScrollPane confirmScroll = new JScrollPane(displayText);
		confirmScroll.setPreferredSize(new Dimension(300,300));
		
		displayPanel.add(confirmScroll);
		
		DefaultListModel<String> displayMowers = new DefaultListModel<String>();
		JList<String> mList = new JList<String>(displayMowers);
		mList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		JList<String> pList = new JList<String>(displayMowers);
		pList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		JScrollPane displayScroll = new JScrollPane(pList);
		displayScroll.setPreferredSize(new Dimension(300,300));
		
		// Panel for user input
		JPanel inputFieldPanel = new JPanel();
		inputFieldPanel.setLayout(new BoxLayout(inputFieldPanel,BoxLayout.X_AXIS));
		
		JTextField mowerNum = new JTextField();
		JButton mowerDel = new JButton("Delete Mower");
		
		mowerDel.setPreferredSize(new Dimension(150,40));
		mowerDel.setMaximumSize(mowerDel.getPreferredSize());
		mowerDel.setFont(new Font("SansSerif",Font.PLAIN,15));
		//confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		inputFieldPanel.add(mowerNum);
		inputFieldPanel.add(mowerDel);
		
		inputPanel.add(displayScroll,BorderLayout.CENTER);
		inputPanel.add(inputFieldPanel);
		// Panel for confirmation buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JButton confirmButton = new JButton("Confirm Input");
		confirmButton.setPreferredSize(new Dimension(150,40));
		confirmButton.setMaximumSize(confirmButton.getPreferredSize());
		confirmButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(150,40));
		cancelButton.setMaximumSize(cancelButton.getPreferredSize());
		cancelButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		/*Stop user from pressing button until they press Add Mower*/
		confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonPanel.add(cancelButton);
		
		
		displayPanel.add(buttonPanel);
		
		//add panels to fieldPanel
		fieldPanel.add(inputPanel);
		fieldPanel.add(displayPanel);
		
		//boolean mowersFound = false;

		if (wareHouse.getNumMowers() == 0) {
			displayMowers.addElement("No mowers available to delete.");
		}

		else {
			numMowers.setText("Current Number of Mowers: " + wareHouse.getNumMowers()); 
			
			int countMowers = wareHouse.getNumMowers();

			for (int count = 0; count < countMowers; count++) {
				Mower m = wareHouse.getMowersList().get(count);
				
				
				displayMowers.addElement("[ Mower #: " + (count + 1) + " ]");
				displayMowers.addElement(m.listDisplayString());
				displayMowers.addElement("-----------------------------------------");
			}
			
			
			mowerDel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String userInput = mowerNum.getText().strip();
					
					// check if the number is greater than 0 but less than or equal to size
					if (userInput.isEmpty() == false) {

						try {
							int input = Integer.parseInt(userInput);
							if(input > 0 && input <= wareHouse.getNumMowers()) {
								Mower temp = wareHouse.getMower(input - 1);
								displayText.setText(temp.confirmString());
								mowerNumber = input;
								confirmation.setText("Please confirm your input.");
								confirmation.setForeground(Color.BLACK);
								/* Enable user to perform action for after pressing add */ 
								confirmButton.setEnabled(true);
								cancelButton.setEnabled(true);
							}
							else {
								confirmation.setText("Enter a number between 1 and the number of mowers.");
								confirmation.setForeground(Color.RED);
								confirmButton.setEnabled(false);
								cancelButton.setEnabled(false);		
							}
						} catch (NumberFormatException nfe) {
							confirmation.setText("Please enter a valid number.");
							confirmation.setForeground(Color.RED);
							confirmButton.setEnabled(false);
							cancelButton.setEnabled(false);		
							return;
						}
					}
				}
			});
			
			confirmButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						wareHouse.removeMower(mowerNumber - 1);
						confirmation.setText("Mower Number " + mowerNumber + " Removed!");
						confirmation.setForeground(new Color(18, 112, 32));
						displayText.setText("");
						numMowers.setText("Current Number of Mowers: " + wareHouse.getNumMowers());
						updateSaved = false;
						
						// show the updated list after deleting
						displayMowers.clear();
						if(wareHouse.getNumMowers() == 0) {
							displayMowers.addElement("No mowers available to delete.");
						}
						else {
							for (int count = 0; count < wareHouse.getNumMowers(); count++) {
								Mower m = wareHouse.getMowersList().get(count);
								displayMowers.addElement("[ Mower #: " + (count + 1) + " ]");
								displayMowers.addElement(m.listDisplayString());
								displayMowers.addElement("-----------------------------------------");
							}
						}

						
						/*stop user from pressing button after confirmation or 
						cancellation until the make a change or press the add button */ 
						confirmButton.setEnabled(false);
						cancelButton.setEnabled(false);

				}
				
			});
			
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					confirmation.setText("Mower Deletion Canceled.");
					confirmation.setForeground(Color.BLACK);
					displayText.setText("");
					
					updateSaved = true;
					
					/*stop user from pressing button after confirmation or 
					cancellation until the make a change or press the add button */ 
					confirmButton.setEnabled(false);
					cancelButton.setEnabled(false);
				}
				
			});
		}

		// add panels to mainPanel
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		mainPanel.add(numPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		mainPanel.add(fieldPanel);
		
		
		// Add panels to window
		delPage.add(mainPanel);		
		delPage.setLocationRelativeTo(null);
		delPage.setVisible(true);
	}

	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Add Mower Section
	
 // Starting page to add different mower types
	
	public void addMowerAction() {

		JFrame addPage = new JFrame("Add Mower Data");
		addPage.setSize(new Dimension(900, 400));
		addPage.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(100, 20, 20, 20));

		// Panel for page information
		JPanel infoPanel = new JPanel();

		// infoPanel components
		JLabel greetings = new JLabel("Welcome to the Add Page!");
		greetings.setFont(new Font("SansSerif", Font.BOLD, 26));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Select the type of mower you would like to add to the warehouse:");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 16));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(greetings);
		infoPanel.add(Box.createRigidArea(new Dimension(0,30)));
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		// Panel for displaying different mower buttons
		JPanel mowersPanel = new JPanel();

		JButton lawnTractorMower = new JButton("Lawn Tractor Mower");
		lawnTractorMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton commercialMower = new JButton("Commercial Mower");
		commercialMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton gasPoweredMower = new JButton("Gas Powered Mower");
		gasPoweredMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton pushReelMower = new JButton("Push Reel Mower");
		pushReelMower.setFont(new Font("SansSerif",Font.PLAIN,15));

		// Add action listeners
		lawnTractorMower.addActionListener(e -> addLawnTractorMowerAction(addPage));
		commercialMower.addActionListener(e -> addCommercialMowerAction(addPage));
		gasPoweredMower.addActionListener(e -> addGasPoweredMowerAction(addPage));
		pushReelMower.addActionListener(e -> addPushReelMowerAction(addPage));

		mowersPanel.add(lawnTractorMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(commercialMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(gasPoweredMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(pushReelMower);
		
		mowersPanel.setLayout(new BoxLayout(mowersPanel,BoxLayout.X_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
		mainPanel.add(mowersPanel);

		addPage.add(mainPanel);
		addPage.setLocationRelativeTo(null);
		addPage.setVisible(true);
		
	}
	
	// Adding mower type Push Reel to list of mowers in warehouse
	public void addPushReelMowerAction(JFrame parentFrame) {
		//Create page for Push Reel Mower
		JDialog addFields = new JDialog(parentFrame, "Add Push Reel");
		addFields.setSize(new Dimension(1300,700));
		addFields.setResizable(false);
		
		
		// Panels for Push Reel Page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(100, 10, 30, 10));
		
		
		// Panel for Page information and greetings
		JPanel infoPanel = new JPanel();
		
		JLabel instruc1 = new JLabel("Enter all input for the new Push Reel Mower or predetermined input will be used.");
		instruc1.setFont(new Font("SansSerif",Font.BOLD,18));
		instruc1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc2 = new JLabel("The right side will show submission details for confirmation.");
		instruc2.setFont(new Font("SansSerif",Font.PLAIN,16));
		instruc2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc3 = new JLabel("Press confirm if you are satisfied.");
		instruc3.setFont(new Font("SansSerif",Font.BOLD,16));
		instruc3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(instruc1);
		infoPanel.add(instruc2);
		infoPanel.add(instruc3);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setMaximumSize(infoPanel.getPreferredSize());
		
		// Confirmation Panel and its components
		// Panel for confirmation
		JPanel conPanel = new JPanel();
		
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif",Font.PLAIN,16));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		conPanel.add(confirmation);
		conPanel.setPreferredSize(new Dimension(400,50));
		conPanel.setMaximumSize(conPanel.getPreferredSize());
		
		
		// Field Panel and its components
		// Panel for input
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.X_AXIS));
		fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Inner panels for fieldPanel
		// Panel for input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(800, 350));
		inputPanel.setMaximumSize(new Dimension(800,350));
		TitledBorder inputTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Input",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		inputPanel.setBorder(inputTitle);
		
		
		// Displays what the user set as input to confirm if the input was correct
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(500, 350));
		displayPanel.setMaximumSize(new Dimension(500,350));
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));
		TitledBorder displayTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), 
				"Input Confirmation",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		displayPanel.setBorder(displayTitle);
	
		
		JTextArea displayText = new JTextArea();
		displayText.setMaximumSize(displayText.getPreferredSize());
		displayText.setFont(new Font("SansSerif",Font.PLAIN,15));
		displayText.setEditable(false);
		
		JScrollPane displayScroll = new JScrollPane(displayText);
		displayScroll.setPreferredSize(new Dimension(300,300));
		
		// Panel for confirmation buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JButton confirmButton = new JButton("Confirm Input");
		confirmButton.setPreferredSize(new Dimension(150,40));
		confirmButton.setMaximumSize(confirmButton.getPreferredSize());
		confirmButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(150,40));
		cancelButton.setMaximumSize(cancelButton.getPreferredSize());
		cancelButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		/*Stop user from pressing button until they press Add Mower*/
		confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonPanel.add(cancelButton);
		
		displayPanel.add(displayScroll);
		displayPanel.add(buttonPanel);
		
		
		//Styling for inputPanel input fields
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Section for first input
		JLabel label1 = new JLabel("Mower Manufacturer (Text):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 35));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setFont(new Font("SansSerif",Font.PLAIN,15));
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		inputPanel.add(input1, gbc);

		// Section for second input
		JLabel label2 = new JLabel("Mower Year (Whole Number):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 35));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setFont(new Font("SansSerif",Font.PLAIN,15));
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(input2, gbc);

		// Section for third input
		JLabel label3 = new JLabel("Mower Serial Number (Text):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 35));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setFont(new Font("SansSerif",Font.PLAIN,15));
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		inputPanel.add(input3, gbc);

		// Section for fourth input
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width (Whole or Decimal Number):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 35));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setFont(new Font("SansSerif",Font.PLAIN,15));
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		inputPanel.add(input4, gbc);

		// Section for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter (Whole or Decimal Number):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 35));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setFont(new Font("SansSerif",Font.PLAIN,15));
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		inputPanel.add(input5, gbc);

		// Section for sixth input
		JLabel label6 = new JLabel("Push Reel Mower Wheels (Whole Number):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 35));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setFont(new Font("SansSerif",Font.PLAIN,15));
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		inputPanel.add(input6, gbc);

		JButton addMower = new JButton("Add Push Reel Mower");
		addMower.setFont(new Font("SansSerif", Font.PLAIN,15));
		addMower.setPreferredSize(new Dimension(250, 35));
		addMower.setMaximumSize(addMower.getPreferredSize());
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		inputPanel.add(addMower, gbc);
		
		//add panels to fieldPanel
		fieldPanel.add(inputPanel);
		fieldPanel.add(displayPanel);
		
		
		// Action for adding to warehouse
		addMower.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pr = new PushReelMower();
				confirmation.setForeground(Color.BLACK);
				
				// if user types in something, set the value.
				// assume that empty input or white space will use default values
				String in1Trim = input1.getText().strip();
				if (in1Trim.isEmpty() == false) {
					pr.setManufacturer(in1Trim);
				}

				// if user types in something, parse and set the value.
				// assume that empty input or white space will use default values.
				String in2Trim = input2.getText().strip();
				if (in2Trim.isEmpty() == false) {
					// if the input is not 4 characters long
					if (in2Trim.length() != 4) {
						confirmation.setText("Please enter a 4 digit year.");
						confirmation.setForeground(Color.RED);
						return;
					}
					// if input is 4 characters long but those characters are not whole numbers
					else {
						try {
							int i = Integer.parseInt(in2Trim);
							pr.setYear(i);
						} catch (NumberFormatException nfe) {
							confirmation.setText("Please enter a valid number for mower year.");
							confirmation.setForeground(Color.RED);
							return;
						}
					}
				}

				// if user types in something,set the value.
				// assume that empty input or white space will result in the use of default values.
				String in3Trim = input3.getText().strip();
				if (in3Trim.isEmpty() == false) {
					pr.setSerialNumber(in3Trim);
				}

				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in4Trim = input4.getText().strip();
				if (in4Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in4Trim);
						pr.setCutWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cut width.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}

				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in5Trim = input5.getText().strip();
				if (in5Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in5Trim);
						pr.setWheelDiameter(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for wheel diameter.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}

				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in6Trim = input6.getText().strip();
				if (in6Trim.isEmpty() == false) {
					try {
						int i = Integer.parseInt(in6Trim);
						pr.setNumWheels(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid whole number for wheels.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}

				displayText.setText(pr.confirmString());
				confirmation.setText("Please confirm your input.");
				
				
				/* Enable user to perform action after pressing add */ 
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(true);		
					
			}

		});

		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					wareHouse.addMower(pr);
					confirmation.setText("Mower Added!");
					confirmation.setForeground(new Color(18, 112, 32));
					displayText.setText("");
					updateSaved = false;
					
					/*stop user from pressing button after confirmation or 
					cancellation until they make a change or press the add button */ 
					confirmButton.setEnabled(false);
					cancelButton.setEnabled(false);

					pr = null;
			    

			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation.setText("Mower Addition Canceled.");
				confirmation.setForeground(Color.BLACK);
				displayText.setText("");
				
				updateSaved = true;
				
				/*stop user from pressing button after confirmation or 
				cancellation until they make a change or press the add button */ 
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);

				pr = null;
			}
			
		});
		
		// add panels to mainPanel
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 0))); 
		mainPanel.add(fieldPanel);
		
		
		// Add panels to window
		addFields.add(mainPanel);
		addFields.setLocationRelativeTo(null);
		addFields.setVisible(true);
	}

	// Adding mower type Gas Powered to list of mowers in warehouse
	
	// Adding mower type Gas Powered to list of mowers in warehouse
	public void addGasPoweredMowerAction(JFrame parentFrame) {

		JDialog addFields = new JDialog(parentFrame, "Add Gas Powered Mower");
		addFields.setSize(new Dimension(1250, 800));
		addFields.setResizable(false);

		// Panels for Gas Powered Mower Page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(90, 10, 20, 10));

		// Information Panel and its components
		// Panel for page information and greetings
		JPanel infoPanel = new JPanel();

		JLabel instruc1 = new JLabel("Enter all input for the new Gas Powered Mower or predetermined input will be used.");
		instruc1.setFont(new Font("SansSerif",Font.BOLD,18));
		instruc1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc2 = new JLabel("The right side will show submission details for confirmation.");
		instruc2.setFont(new Font("SansSerif",Font.PLAIN,16));
		instruc2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc3 = new JLabel("Press confirm if you are satisfied.");
		instruc3.setFont(new Font("SansSerif",Font.BOLD,16));
		instruc3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(instruc1);
		infoPanel.add(instruc2);
		infoPanel.add(instruc3);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setMaximumSize(infoPanel.getPreferredSize());

		
		// Confirmation Panel and its components
		// Panel for confirmation of adding mower
		JPanel conPanel = new JPanel();
		
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setPreferredSize(new Dimension(400,50));
		conPanel.setMaximumSize(conPanel.getPreferredSize());

		// Field Panel and its components
		// Panel for input 
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.X_AXIS));
		fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Inner Panels for fieldPanel
		// Panel for input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(800,550));
		inputPanel.setMaximumSize(inputPanel.getPreferredSize());
		TitledBorder inputTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Input",
				TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP,
				new Font("SansSerif",Font.PLAIN,15),
				Color.BLACK
				);
		inputPanel.setBorder(inputTitle);
		
		// Panel for Display
		// Displays what the user set as input to confirm if the input was correct
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(500,350));
		displayPanel.setMaximumSize(displayPanel.getPreferredSize());
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));
		TitledBorder displayTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), 
				"Input Confirmation",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		displayPanel.setBorder(displayTitle);
		
		JTextArea displayText = new JTextArea();
		displayText.setMaximumSize(displayText.getPreferredSize());
		displayText.setFont(new Font("SansSerif",Font.PLAIN,15));
		displayText.setEditable(false);
		
		JScrollPane displayScroll = new JScrollPane(displayText);
		displayScroll.setPreferredSize(new Dimension(300,300));
		
		// Panel for confirmation buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JButton confirmButton = new JButton("Confirm Input");
		confirmButton.setPreferredSize(new Dimension(150,40));
		confirmButton.setMaximumSize(confirmButton.getPreferredSize());
		confirmButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(150,40));
		cancelButton.setMaximumSize(cancelButton.getPreferredSize());
		cancelButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		/*Stop user from pressing button until they press Add Mower*/
		confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonPanel.add(cancelButton);
		
		displayPanel.add(displayScroll);
		displayPanel.add(buttonPanel);
		
		
		//Styling for inputPanel input fields
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Section for first input
		JLabel label1 = new JLabel("Mower Manufacturer (Text):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 40));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setFont(new Font("SansSerif",Font.PLAIN,15));
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		inputPanel.add(input1, gbc);

		// Section for second input
		JLabel label2 = new JLabel("Mower Year (Whole Number):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 40));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setFont(new Font("SansSerif",Font.PLAIN,15));
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(input2, gbc);

		// Section for third input
		JLabel label3 = new JLabel("Mower Serial Number (Text):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 40));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setFont(new Font("SansSerif",Font.PLAIN,15));
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		inputPanel.add(input3, gbc);

		// Section for fourth input
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width (Whole or Decimal Number):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 40));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setFont(new Font("SansSerif",Font.PLAIN,15));
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		inputPanel.add(input4, gbc);

		// Section for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter (Whole or Decimal Number):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 40));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setFont(new Font("SansSerif",Font.PLAIN,15));
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		inputPanel.add(input5, gbc);

		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Manufacturer (Text):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 40));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setFont(new Font("SansSerif",Font.PLAIN,15));
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		inputPanel.add(input6, gbc);

		// Panel for seventh input
		JLabel label7 = new JLabel("Engine Horsepower (Whole or Decimal Number):");
		label7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200, 40));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setFont(new Font("SansSerif",Font.PLAIN,15));
		input7.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label7, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		inputPanel.add(input7, gbc);

		// Panel for eighth input
		JLabel label8 = new JLabel("Engine Cylinders (Whole Number):");
		label8.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200, 40));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setFont(new Font("SansSerif",Font.PLAIN,15));
		input8.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label8, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		inputPanel.add(input8, gbc);
		
		// Panel for ninth input
		JLabel label9 = new JLabel("Is It Self Propelled?:");
		label9.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		String[] options = { "Select", "True", "False" };
		JComboBox<?> input9 = new JComboBox<Object>(options);
		input9.setSelectedIndex(0);
		input9.setPreferredSize(new Dimension(200, 40));
		input9.setMaximumSize(input9.getPreferredSize());
		input9.setFont(new Font("SansSerif",Font.PLAIN,15));
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label9, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		inputPanel.add(input9, gbc);

		JButton addMower = new JButton("Add Gas Powered Mower");
		addMower.setFont(new Font("SansSerif", Font.PLAIN,15));
		addMower.setPreferredSize(new Dimension(250, 40));
		addMower.setMaximumSize(addMower.getPreferredSize());
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 9;
		inputPanel.add(addMower, gbc);
		
		// add panels to fieldPanel
		fieldPanel.add(inputPanel);
		fieldPanel.add(displayPanel);

		// Action to save Gas Powered mower
		addMower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gp = new GasPoweredMower();
				ge = new Engine();
				
				confirmation.setForeground(Color.BLACK);
				
				// if user types in something, set the value.
				// assume that empty input or white space will use default values.
				String in1Trim = input1.getText().strip();
				if (in1Trim.isEmpty() == false) {
					gp.setManufacturer(in1Trim);
				}
				
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will use default values.
				String in2Trim = input2.getText().strip();
				if (in2Trim.isEmpty() == false) {
					// if the input is not 4 characters long
					if (in2Trim.length() != 4) {
						confirmation.setText("Please enter a 4 digit year.");
						confirmation.setForeground(Color.RED);
						return;
					}
					// if input is 4 characters long but those characters are not numbers
					else {
						try {
							int i = Integer.parseInt(in2Trim);
							gp.setYear(i);
						} catch (NumberFormatException nfe) {
							confirmation.setText("Please enter a valid number for mower year.");
							confirmation.setForeground(Color.RED);
							return;
						}
					}
				}

				
				// if user types in something, set the value.
				// assume that empty input or white space will result in the use of default values.
				String in3Trim = input3.getText().strip();
				if (in3Trim.isEmpty() == false) {
					gp.setSerialNumber(in3Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in4Trim = input4.getText().strip();
				if (in4Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in4Trim);
						gp.setCutWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cut width.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in5Trim = input5.getText().strip();
				if (in5Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in5Trim);
						gp.setWheelDiameter(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for wheel diameter.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, set the value.
				// assume that empty input or white space will use default values.
				String in6Trim = input6.getText().strip();
				if (in6Trim.isEmpty() == false) {
					ge.setManufacturer(in6Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in7Trim = input7.getText().strip();
				if (in7Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in7Trim);
						ge.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for horse power.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in8Trim = input8.getText().strip();
				if (in8Trim.isEmpty() == false) {
					try {
						int i = Integer.parseInt(in8Trim);
						ge.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid whole number for cylinders.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user selects something, save it.
				// assume that not selecting will result in use of default values.
				String selectedItem = (String) input9.getSelectedItem();
				if ("True".equals(selectedItem)) {
					gp.setSelfPropelled(true);
				} else if ("False".equals(selectedItem)) {
					gp.setSelfPropelled(false);
				}
				
				gp.setEngine(ge);
				
				displayText.setText(gp.confirmString());
				confirmation.setText("Please confirm your input.");
				
				/* Enable user to perform action after pressing add */ 
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(true);	

			}
		});
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					wareHouse.addMower(gp);
					confirmation.setText("Mower Added!");
					confirmation.setForeground(new Color(18, 112, 32));
					displayText.setText("");
					updateSaved = false;
					
					/*stop user from pressing button after confirmation or 
					cancellation until they make a change or press the add button */ 
					confirmButton.setEnabled(false);
					cancelButton.setEnabled(false);

					gp = null;
					ge = null;
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation.setText("Mower Addition Canceled.");
				confirmation.setForeground(Color.BLACK);
				displayText.setText("");
				
				updateSaved = true;
				
				/*stop user from pressing button after confirmation or 
				cancellation until they make a change or press the add button */ 
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);

				gp = null;
				ge = null;
			}	
		});

		// add panels to mainPanel
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 0))); 
		mainPanel.add(fieldPanel);
		
		// Add panels to window
		addFields.add(mainPanel);
		addFields.setLocationRelativeTo(null);
		addFields.setVisible(true);
	}
	
	
	// Adding mower type Commercial to list of mowers in warehouse
	// Adding mower type Commercial to list of mowers in warehouse
	public void addCommercialMowerAction(JFrame parentFrame) {

		JDialog addFields = new JDialog(parentFrame, "Add Commercial Mower");
		addFields.setSize(new Dimension(1300, 800));
		addFields.setResizable(false);
		
		// Panels for Commercial Mower
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(80, 10, 10, 10));

		// Information Panel and its components
		// Panel for page information and greetings
		JPanel infoPanel = new JPanel();

		JLabel instruc1 = new JLabel("Enter all input for the new Commercial Mower or predetermined input will be used.");
		instruc1.setFont(new Font("SansSerif",Font.BOLD,18));
		instruc1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instruc2 = new JLabel("The right side will show submission details for confirmation.");
		instruc2.setFont(new Font("SansSerif",Font.PLAIN,16));
		instruc2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc3 = new JLabel("Press confirm if you are satisfied.");
		instruc3.setFont(new Font("SansSerif",Font.BOLD,16));
		instruc3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(instruc1);
		infoPanel.add(instruc2);
		infoPanel.add(instruc3);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setMaximumSize(infoPanel.getPreferredSize());

		// Confirmation Panel and its components
		// Panel for confirmation of adding mower
		JPanel conPanel = new JPanel();

		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setPreferredSize(new Dimension(400,50));
		conPanel.setMaximumSize(conPanel.getPreferredSize());
		
		// Field Panel and its components
				// Panel for input 
				JPanel fieldPanel = new JPanel();
				fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.X_AXIS));
				fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				// Inner Panels for fieldPanel
				// Panel for input fields
				JPanel inputPanel = new JPanel();
				inputPanel.setPreferredSize(new Dimension(800,600));
				inputPanel.setMaximumSize(inputPanel.getPreferredSize());
				TitledBorder inputTitle = BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
						"Input",
						TitledBorder.LEFT,
						TitledBorder.ABOVE_TOP,
						new Font("SansSerif",Font.PLAIN,15),
						Color.BLACK
						);
				inputPanel.setBorder(inputTitle);
				
				// Panel for Display
				// Displays what the user set as input to confirm if the input was correct
				JPanel displayPanel = new JPanel();
				displayPanel.setPreferredSize(new Dimension(500,350));
				displayPanel.setMaximumSize(displayPanel.getPreferredSize());
				displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));
				TitledBorder displayTitle = BorderFactory.createTitledBorder(
						BorderFactory.createEmptyBorder(), 
						"Input Confirmation",
						TitledBorder.LEFT, 
						TitledBorder.ABOVE_TOP,
						new Font("SanSerif",Font.PLAIN,15),
						Color.BLACK
						);
				displayPanel.setBorder(displayTitle);
				
				JTextArea displayText = new JTextArea();
				displayText.setMaximumSize(displayText.getPreferredSize());
				displayText.setFont(new Font("SansSerif",Font.PLAIN,15));
				displayText.setEditable(false);
				
				JScrollPane displayScroll = new JScrollPane(displayText);
				displayScroll.setPreferredSize(new Dimension(300,300));
				
				// Panel for confirmation buttons
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
				
				JButton confirmButton = new JButton("Confirm Input");
				confirmButton.setPreferredSize(new Dimension(150,40));
				confirmButton.setMaximumSize(confirmButton.getPreferredSize());
				confirmButton.setFont(new Font("SansSerif",Font.PLAIN,15));
				confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setPreferredSize(new Dimension(150,40));
				cancelButton.setMaximumSize(cancelButton.getPreferredSize());
				cancelButton.setFont(new Font("SansSerif",Font.PLAIN,15));
				cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
				
				/*Stop user from pressing button until they press Add Mower*/
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				buttonPanel.add(confirmButton);
				buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
				buttonPanel.add(cancelButton);
				
				displayPanel.add(displayScroll);
				displayPanel.add(buttonPanel);

				//Styling for inputPanel input fields
				inputPanel.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();

				gbc.insets = new Insets(4, 4, 4, 4);
				gbc.anchor = GridBagConstraints.EAST;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				
				// Section for first input
				JLabel label1 = new JLabel("Mower Manufacturer (Text):");
				label1.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input1 = new JTextField();
				input1.setPreferredSize(new Dimension(200, 40));
				input1.setMaximumSize(input1.getPreferredSize());
				input1.setFont(new Font("SansSerif",Font.PLAIN,15));
				input1.setEditable(true);
				
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label1, gbc);

				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.weightx = 1;
				inputPanel.add(input1, gbc);


				// Section for second input
				JLabel label2 = new JLabel("Mower Year (Whole Number):");
				label2.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input2 = new JTextField();
				input2.setPreferredSize(new Dimension(200, 40));
				input2.setMaximumSize(input2.getPreferredSize());
				input2.setFont(new Font("SansSerif",Font.PLAIN,15));
				input2.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label2, gbc);

				gbc.gridx = 1;
				gbc.gridy = 1;
				inputPanel.add(input2, gbc);

				// Section for third input
				JLabel label3 = new JLabel("Mower Serial Number (Text):");
				label3.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input3 = new JTextField();
				input3.setPreferredSize(new Dimension(200, 40));
				input3.setMaximumSize(input3.getPreferredSize());
				input3.setFont(new Font("SansSerif",Font.PLAIN,15));
				input3.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 2;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label3, gbc);

				gbc.gridx = 1;
				gbc.gridy = 2;
				inputPanel.add(input3, gbc);
				
				// Section for fourth input
				JLabel label4 = new JLabel("Engine Manufacturer (Text):");
				label4.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input4 = new JTextField();
				input4.setPreferredSize(new Dimension(200, 40));
				input4.setMaximumSize(input4.getPreferredSize());
				input4.setFont(new Font("SansSerif",Font.PLAIN,15));
				input4.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label4, gbc);

				gbc.gridx = 1;
				gbc.gridy = 3;
				inputPanel.add(input4, gbc);
				
				// Section for fifth input
				JLabel label5 = new JLabel("Engine Horse Power (Whole or Decimal Number):");
				label5.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input5 = new JTextField();
				input5.setPreferredSize(new Dimension(200, 40));
				input5.setMaximumSize(input5.getPreferredSize());
				input5.setFont(new Font("SansSerif",Font.PLAIN,15));
				input5.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 4;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label5, gbc);

				gbc.gridx = 1;
				gbc.gridy = 4;
				inputPanel.add(input5, gbc);
				
				// Panel for sixth input
				JLabel label6 = new JLabel("Engine Cylinders (Whole Number):");
				label6.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input6 = new JTextField();
				input6.setPreferredSize(new Dimension(200, 40));
				input6.setMaximumSize(input6.getPreferredSize());
				input6.setFont(new Font("SansSerif",Font.PLAIN,15));
				input6.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 5;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label6, gbc);

				gbc.gridx = 1;
				gbc.gridy = 5;
				inputPanel.add(input6, gbc);
				
				// Panel for seventh input
				JLabel label7 = new JLabel("Lawn Tractor Model (Text):");
				label7.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input7 = new JTextField();
				input7.setPreferredSize(new Dimension(200, 40));
				input7.setMaximumSize(input7.getPreferredSize());
				input7.setFont(new Font("SansSerif",Font.PLAIN,15));
				input7.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 6;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label7, gbc);

				gbc.gridx = 1;
				gbc.gridy = 6;
				inputPanel.add(input7, gbc);
				
				// Panel for eighth input
				JLabel label8 = new JLabel("Lawn Tractor Deck Width (Whole or Decimal Number):");
				label8.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input8 = new JTextField();
				input8.setPreferredSize(new Dimension(200, 40));
				input8.setMaximumSize(input8.getPreferredSize());
				input8.setFont(new Font("SansSerif",Font.PLAIN,15));
				input8.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 7;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label8, gbc);

				gbc.gridx = 1;
				gbc.gridy = 7;
				inputPanel.add(input8, gbc);
				
				// Panel for ninth input
				JLabel label9 = new JLabel("Operating Hours (Whole or Decimal Number):");
				label9.setFont(new Font("SansSerif", Font.PLAIN, 15));
				JTextField input9 = new JTextField();
				input9.setPreferredSize(new Dimension(200, 40));
				input9.setMaximumSize(input9.getPreferredSize());
				input9.setFont(new Font("SansSerif",Font.PLAIN,15));
				input9.setEditable(true);

				gbc.gridx = 0;
				gbc.gridy = 8;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label9, gbc);

				gbc.gridx = 1;
				gbc.gridy = 8;
				inputPanel.add(input9, gbc);
				
				// Panel for tenth input
				JLabel label10 = new JLabel("Is Zero Turn Radius?:");
				label10.setFont(new Font("SansSerif", Font.PLAIN, 15));
				
				String[] options = { "Select", "True", "False" };
				JComboBox<?> input10 = new JComboBox<Object>(options);
				input10.setSelectedIndex(0);
				input10.setPreferredSize(new Dimension(200, 40));
				input10.setMaximumSize(input10.getPreferredSize());
				input10.setFont(new Font("SansSerif",Font.PLAIN,15));
				
				gbc.gridx = 0;
				gbc.gridy = 9;
				gbc.anchor = GridBagConstraints.WEST;
				inputPanel.add(label10, gbc);

				gbc.gridx = 1;
				gbc.gridy = 9;
				inputPanel.add(input10, gbc);
				
				JButton addMower = new JButton("Add Commercial Mower");
				addMower.setFont(new Font("SansSerif", Font.PLAIN,15));
				addMower.setPreferredSize(new Dimension(250, 40));
				addMower.setMaximumSize(addMower.getPreferredSize());
				
				gbc.fill = GridBagConstraints.NONE;
				gbc.anchor = GridBagConstraints.WEST;
				
				gbc.gridx = 0;
				gbc.gridy = 10;
				inputPanel.add(addMower, gbc);
				
				// add panels to fieldPanel
				fieldPanel.add(inputPanel);
				fieldPanel.add(displayPanel);
				

		// action to save commercial mower
		addMower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cw = new CommercialMower();
				ce = new Engine();
				
				confirmation.setForeground(Color.BLACK);
				
				// if user types in something, set the value.
				// assume that empty input or white space will use default values.
				String in1Trim = input1.getText().strip();
				if (in1Trim.isEmpty() == false) {
					cw.setManufacturer(in1Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will use default values.
				String in2Trim = input2.getText().strip();
				if (in2Trim.isEmpty() == false) {
					// if the input is not 4 characters long
					if (in2Trim.length() != 4) {
						confirmation.setText("Please enter a 4 digit year.");
						confirmation.setForeground(Color.RED);
						return;
					}
					// if input is 4 characters long but those characters are not numbers
					else {
						try {
							int i = Integer.parseInt(in2Trim);
							cw.setYear(i);
						} catch (NumberFormatException nfe) {
							confirmation.setText("Please enter a valid number for mower year.");
							confirmation.setForeground(Color.RED);
							return;
						}
					}
				}

				// if user types in something, set the value.
				// assume that empty input or white space will result in the use of default values.
				String in3Trim = input3.getText().strip();
				if (in3Trim.isEmpty() == false) {
					cw.setSerialNumber(in3Trim);
				}
				
				// if user types in something, set the value.
				// assume that empty input or white space will result in the use of default values.
				String in4Trim = input4.getText().strip();
				if (in4Trim.isEmpty() == false) {
					ce.setManufacturer(in4Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in5Trim = input5.getText().strip();
				if (in5Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in5Trim);
						ce.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for horse power.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in6Trim = input6.getText().strip();
				if (in6Trim.isEmpty() == false) {
					try {
						int i = Integer.parseInt(in6Trim);
						ce.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid whole number for cylinders.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, set the value.
				// assume that empty input or white space will result in the use of default values.
				String in7Trim = input7.getText().strip();
				if (in7Trim.isEmpty() == false) {
					cw.setModel(in7Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in8Trim = input8.getText().strip();
				if (in8Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in8Trim);
						cw.setDeckWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for deck width.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in9Trim = input9.getText().strip();
				if (in9Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in9Trim);
						cw.setOperatingHours(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for operating hours.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}

				// if user selects something, save it.
				// assume that not selecting will result in use of default values.
				String selectedItem = (String) input10.getSelectedItem();
				if ("True".equals(selectedItem)) {
					cw.setZeroTurnRadius(true);
				} else if ("False".equals(selectedItem)) {
					cw.setZeroTurnRadius(false);
				}
				
				cw.setEngine(ce);
				
				displayText.setText(cw.confirmString());
				confirmation.setText("Please confirm your input.");
				
				/* Enable user to perform action after pressing add */ 
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(true);	
			}

		});
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					wareHouse.addMower(cw);
					confirmation.setText("Mower Added!");
					confirmation.setForeground(new Color(18, 112, 32));
					displayText.setText("");
					updateSaved = false;
					
					/*stop user from pressing button after confirmation or 
					cancellation until they make a change or press the add button */ 
					confirmButton.setEnabled(false);
					cancelButton.setEnabled(false);

					cw = null;
					ce = null;
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation.setText("Mower Addition Canceled.");
				confirmation.setForeground(Color.BLACK);
				displayText.setText("");
				
				updateSaved = true;
				
				/*stop user from pressing button after confirmation or 
				cancellation until they make a change or press the add button */ 
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);

				cw = null;
				ce = null;
			}	
		});
		
		// add panels to mainPanel
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 0))); 
		mainPanel.add(fieldPanel);
		
		// Add panels to window
		addFields.add(mainPanel);
		addFields.setLocationRelativeTo(null);
		addFields.setVisible(true);
	}
	
	// Adding mower type Lawn Tractor to list of mowers in warehouse

	// Adding mower type Lawn Tractor to list of mowers in warehouse
	public void addLawnTractorMowerAction(JFrame parentFrame) {
		
		// Create Page for Lawn Tractor Mower
		JDialog addFields = new JDialog(parentFrame, "Add Lawn Tractor Mower");
		addFields.setSize(new Dimension(1100, 700));
		addFields.setResizable(false);
		
		//Panels for Lawn tractor Page
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(100, 10, 30, 10));
		
		//Information Panel and its components
		// Panel for page information and greetings
		JPanel infoPanel = new JPanel();
		
		JLabel instruc1 = new JLabel("Enter all input for the new Lawn Tractor Mower or predetermined input will be used.");
		instruc1.setFont(new Font("SansSerif",Font.BOLD,18));
		instruc1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc2 = new JLabel("The right side will show submission details for confirmation.");
		instruc2.setFont(new Font("SansSerif",Font.PLAIN,16));
		instruc2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel instruc3 = new JLabel("Press confirm if you are satisfied.");
		instruc3.setFont(new Font("SansSerif",Font.BOLD,16));
		instruc3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(instruc1);
		infoPanel.add(instruc2);
		infoPanel.add(instruc3);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		infoPanel.setMaximumSize(infoPanel.getPreferredSize());

		// Confirmation Panel and its components
		// Panel for confirmation of adding mower
		JPanel conPanel = new JPanel();
		
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setPreferredSize(new Dimension(400,50));
		conPanel.setMaximumSize(conPanel.getPreferredSize());
		
		// Field Panel and its components
		// Panel for input 
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel,BoxLayout.X_AXIS));
		fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Inner Panels for fieldPanel
		// Panel for input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(800,450));
		inputPanel.setMaximumSize(inputPanel.getPreferredSize());
		TitledBorder inputTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Input",
				TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP,
				new Font("SansSerif",Font.PLAIN,15),
				Color.BLACK
				);
		inputPanel.setBorder(inputTitle);
		
		// Panel for Display
		// Displays what the user set as input to confirm if the input was correct
		JPanel displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(500,350));
		displayPanel.setMaximumSize(displayPanel.getPreferredSize());
		displayPanel.setLayout(new BoxLayout(displayPanel,BoxLayout.Y_AXIS));
		TitledBorder displayTitle = BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), 
				"Input Confirmation",
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP,
				new Font("SanSerif",Font.PLAIN,15),
				Color.BLACK
				);
		displayPanel.setBorder(displayTitle);
		
		JTextArea displayText = new JTextArea();
		displayText.setMaximumSize(displayText.getPreferredSize());
		displayText.setFont(new Font("SansSerif",Font.PLAIN,15));
		displayText.setEditable(false);
		
		JScrollPane displayScroll = new JScrollPane(displayText);
		displayScroll.setPreferredSize(new Dimension(300,300));
		
		// Panel for confirmation buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		
		JButton confirmButton = new JButton("Confirm Input");
		confirmButton.setPreferredSize(new Dimension(150,40));
		confirmButton.setMaximumSize(confirmButton.getPreferredSize());
		confirmButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(150,40));
		cancelButton.setMaximumSize(cancelButton.getPreferredSize());
		cancelButton.setFont(new Font("SansSerif",Font.PLAIN,15));
		cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		/*Stop user from pressing button until they press Add Mower*/
		confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		buttonPanel.add(cancelButton);
		
		displayPanel.add(displayScroll);
		displayPanel.add(buttonPanel);
		
		//Styling for inputPanel input fields
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Section for first input
		JLabel label1 = new JLabel("Mower Manufacturer (Text):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 35));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setFont(new Font("SansSerif",Font.PLAIN,15));
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		inputPanel.add(input1, gbc);

		// Section for second input
		JLabel label2 = new JLabel("Mower Year (Whole Number):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 35));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setFont(new Font("SansSerif",Font.PLAIN,15));
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(input2, gbc);

		// Section for third input
		JLabel label3 = new JLabel("Mower Serial Number (Text):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 35));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setFont(new Font("SansSerif",Font.PLAIN,15));
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		inputPanel.add(input3, gbc);

		// Section for fourth input
		JLabel label4 = new JLabel("Engine Manufacturer (Text):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 35));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setFont(new Font("SansSerif",Font.PLAIN,15));
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		inputPanel.add(input4, gbc);

		// Section for fifth input
		JLabel label5 = new JLabel("Engine Horse Power (Whole or Decimal Number):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 35));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setFont(new Font("SansSerif",Font.PLAIN,15));
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		inputPanel.add(input5, gbc);

		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Cylinders (Whole Number):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 35));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setFont(new Font("SansSerif",Font.PLAIN,15));
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		inputPanel.add(input6, gbc);

		// Panel for seventh input
		JLabel label7 = new JLabel("Lawn Tractor Model (Text):");
		label7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200, 35));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setFont(new Font("SansSerif",Font.PLAIN,15));
		input7.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label7, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		inputPanel.add(input7, gbc);

		// Panel for eighth input
		JLabel label8 = new JLabel("Lawn Tractor Deck Width (Whole or Decimal Number):");
		label8.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200, 35));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setFont(new Font("SansSerif",Font.PLAIN,15));
		input8.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label8, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		inputPanel.add(input8, gbc);

		JButton addMower = new JButton("Add Lawn Tractor Mower");
		addMower.setFont(new Font("SansSerif", Font.PLAIN,15));
		addMower.setPreferredSize(new Dimension(250, 35));
		addMower.setMaximumSize(addMower.getPreferredSize());
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		inputPanel.add(addMower, gbc);
		
		// add panels to fieldPanel
		fieldPanel.add(inputPanel);
		fieldPanel.add(displayPanel);
		
		// Action for adding to warehouse
		addMower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lt = new LawnTractor();
				le = new Engine();
				confirmation.setForeground(Color.BLACK);
				
				// if user types in something, set the value.
				// assume that empty input or white space will use default values.
				String in1Trim = input1.getText().strip();
				if (in1Trim.isEmpty() == false) {
					lt.setManufacturer(in1Trim);
				}

				// if user types in something, parse and set the value.
				// assume that empty input or white space will use default values.
				String in2Trim = input2.getText().strip();
				if (in2Trim.isEmpty() == false) {
					// if the input is not 4 characters long
					if (in2Trim.length() != 4) {
						confirmation.setText("Please enter a 4 digit year.");
						confirmation.setForeground(Color.RED);
						return;
					}
					// if input is 4 characters long but those characters are not numbers
					else {
						try {
							int i = Integer.parseInt(in2Trim);
							lt.setYear(i);
						} catch (NumberFormatException nfe) {
							confirmation.setText("Please enter a valid number for mower year.");
							confirmation.setForeground(Color.RED);
							return;
						}
					}
				}
				
				// if user types in something,set the value.
				// assume that empty input or white space will result in the use of default values.
				String in3Trim = input3.getText().strip();
				if (in3Trim.isEmpty() == false) {
					lt.setSerialNumber(in3Trim);
				}
				
				// if user types in something,set the value.
				// assume that empty input or white space will result in the use of default values.
				String in4Trim = input4.getText().strip();
				if (in4Trim.isEmpty() == false) {
					le.setManufacturer(in4Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in5Trim = input5.getText().strip();
				if (in5Trim.isEmpty() == false) {
					try {
						int i = Integer.parseInt(in5Trim);
						le.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid whole number for horse power.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default  values.
				String in6Trim = input6.getText().strip();
				if (in6Trim.isEmpty() == false) {
					try {
						int i = Integer.parseInt(in6Trim);
						le.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid whole number for cylinders.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				// if user types in something,set the value.
				// assume that empty input or white space will result in the use of default values.
				String in7Trim = input7.getText().strip();
				if (in7Trim.isEmpty() == false) {
					lt.setModel(in7Trim);
				}
				
				// if user types in something, parse and set the value.
				// assume that empty input or white space will result in the use of default values.
				String in8Trim = input8.getText().strip();
				if (in8Trim.isEmpty() == false) {
					try {
						double i = Double.parseDouble(in8Trim);
						lt.setDeckWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for deck width.");
						confirmation.setForeground(Color.RED);
						return;
					}
				}
				
				lt.setEngine(le);
				
				displayText.setText(lt.confirmString());
				confirmation.setText("Please confirm your input.");
				
				/* Enable user to perform action after pressing add */ 
				confirmButton.setEnabled(true);
				cancelButton.setEnabled(true);
			}

		});
		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				wareHouse.addMower(lt);
				confirmation.setText("Mower Added!");
				confirmation.setForeground(new Color(18, 112, 32));
				displayText.setText("");
				
				updateSaved = false;
				
				/*stop user from pressing button after confirmation or 
				cancellation until they make a change or press the add button */ 
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				le = null;
				lt = null;
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confirmation.setText("Mower Addition Canceled.");
				confirmation.setForeground(Color.BLACK);
				displayText.setText("");
				
				updateSaved = true;
				
				/*stop user from pressing button after confirmation or 
				cancellation until they make a change or press the add button */ 
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				le = null;
				lt = null;
			}
			
		});	
		// add panels to mainPanel
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 0))); 
		mainPanel.add(fieldPanel);
		
		// Add panels to window
		addFields.add(mainPanel);
		addFields.setLocationRelativeTo(null);
		addFields.setVisible(true);

	}
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Save Mower Section
	
	// Saves user data to a .txt file
	
	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// Save Mower Section

	// Save warehouse data to a file
	// Allows user to provide an output file name and save the in - memory warehouse to that file
	public void saveWareHouseAction() {
		// Create save page
		JFrame savePage = new JFrame("Save WareHouse Data");
		savePage.setSize(new Dimension(590, 400));
		savePage.setResizable(false);
		
		// Panels for the save page
		
		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(100, 33, 33, 33));

		// Information Panel and its components
		
		// Panel for page information
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
		
		//Inner infoPanel to help place infoButton and greeting side by side
		JPanel innerInfoPanel = new JPanel();
		innerInfoPanel.setLayout(new BoxLayout(innerInfoPanel,BoxLayout.X_AXIS));
		
		// Button to get page information
		JButton infoButton = new JButton();
		infoButton.setPreferredSize(new Dimension(45,45));
		infoButton.setMaximumSize(new Dimension(45,45));

		ImageIcon infoIcon = new ImageIcon("info-icon.png");
		Image scaledIcon = infoIcon.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH);
		infoButton.setIcon(new ImageIcon(scaledIcon));
		infoButton.setOpaque(true);
		infoButton.setBorderPainted(false);
		
		// Label to greet user
		JLabel saveGreeting = new JLabel("Welcome to the Save Page!");
		saveGreeting.setFont(new Font("SansSerif", Font.BOLD, 26));
		saveGreeting.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Label for page instructions
		JLabel saveInstructions = new JLabel("Input name of file where you want to save warehouse data.");
		saveInstructions.setFont(new Font("SansSerif", Font.PLAIN, 16));
		saveInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Add to innerIntroPanel
		innerInfoPanel.add(saveGreeting);
		innerInfoPanel.add(Box.createRigidArea(new Dimension(20,0)));
		innerInfoPanel.add(infoButton);
		
		//Add introPanel components
		infoPanel.add(innerInfoPanel);
		infoPanel.add(Box.createRigidArea(new Dimension(0,30)));
		infoPanel.add(saveInstructions);

		
		// Confirmation Panel and its components
		
		// Panel for displaying save confirmation
		JPanel conPanel = new JPanel(); 
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.Y_AXIS));
		
		// Where the confirmation appears
		JLabel confirmation = new JLabel();
		confirmation.setFont(new Font("SansSerif",Font.PLAIN,14));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		conPanel.add(confirmation);
		
		
		// Input Panel and its  components
		
		// Panel for input
		JPanel inputPanel = new JPanel(); 
		
		
		JTextField input = new JTextField();
		input.setMaximumSize(new Dimension(450, 40));
		input.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		input.setFont(new Font(null,Font.PLAIN,15));
		input.setEditable(true);
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("SansSerif",Font.PLAIN,16));
		saveButton.setMaximumSize(new Dimension(70, 40));
		
		inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.X_AXIS));
		inputPanel.add(input);
		inputPanel.add(Box.createRigidArea(new Dimension(30,0)));
		inputPanel.add(saveButton);
		
		
		// action to save file
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid file name.");
					confirmation.setForeground(Color.RED);
				}
				else {
					if (storeName == null || wareHouse.getStoreName() == null) {
						boolean isSaved = saveNameDialog(savePage);
						if(isSaved == false) {
							confirmation.setText("You can not save without a warehouse name.");
							confirmation.setForeground(Color.RED);
						}
					}
					else {
						// Ensure data is saved as a .txt file
						String fileName = input.getText().strip();
						if (fileName.toLowerCase().endsWith(".txt") == false) {
							fileName += ".txt";
						}
						
						wareHouse.saveMowerData(fileName);
						confirmation.setText(wareHouse.getOutString());
						confirmation.setForeground(new Color(18, 112, 32));
						input.setText("");
						updateSaved = true;
						
						// Exit program when exit is called and data is saved
						if(exitCalled == true) {
							exitCalled = false;
							System.exit(0);
						}
					}

				}

			}
		});
		
		
		// For displaying page information
		
		//Custom icon to display in Information pop-up 
		//Design for JOptionPane
		JPanel panePanel = new JPanel();
		panePanel.setBackground(new Color(240,240,255));
		panePanel.setLayout(new BoxLayout(panePanel,BoxLayout.Y_AXIS));
		
		// create a raised panel with padding
		panePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				new EmptyBorder(20, 20, 20, 20)
		));
		
		JLabel note1 = new JLabel("Save Page Information:");
		note1.setFont(new Font("SansSerif", Font.BOLD, 17));
		note1.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel note2 = new JLabel("- This saved file has the same format as the input file.");
		note2.setFont(new Font("SansSerif", Font.BOLD, 14));
		note2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		panePanel.add(note1);
		panePanel.add(Box.createRigidArea(new Dimension(0,8)));
		panePanel.add(note2);
		
		ImageIcon paneIcon = new ImageIcon (infoIcon.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH));
		
		//call for store name information pop-up
		infoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						savePage,
						panePanel,
						"Information",
						JOptionPane.INFORMATION_MESSAGE,
						paneIcon
						);
			}

		});
		
		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
		mainPanel.add(inputPanel);

		savePage.add(mainPanel);
		savePage.setLocationRelativeTo(null);
		savePage.setVisible(true);

	}
	
	
	// Pop-up modal to set warehouse name when trying to save
	boolean wareHouseNamed = false;
	public boolean saveNameDialog(JFrame parentFrame) {
		// pop-up for trying to save without a warehouse name
		JDialog nameDialog = new JDialog(parentFrame,"Warehouse Name Required",true);
		nameDialog.setSize(new Dimension(600,250));
		nameDialog.setResizable(false);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.Y_AXIS));
		
		// create a raised panel with padding
		namePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createRaisedBevelBorder(),
				new EmptyBorder(20, 20, 20, 20)
		));
		
		JLabel nameWarning = new JLabel("You can not save without naming your warehouse.");
		nameWarning.setFont(new Font("SansSerif", Font.BOLD, 17));
		nameWarning.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel nameInstruction = new JLabel("Please name your warehouse:");
		nameInstruction.setFont(new Font("SansSerif", Font.BOLD, 17));
		nameInstruction.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		// Panel for displaying save confirmation
		JPanel nameConPanel = new JPanel(); 
		nameConPanel.setLayout(new BoxLayout(nameConPanel, BoxLayout.Y_AXIS));
		
		// Where the confirmation appears
		JLabel con = new JLabel();
		con.setFont(new Font("SansSerif",Font.PLAIN,14));
		con.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		nameConPanel.add(con);
		
		
		JPanel inputNamePanel = new JPanel(); 

		JTextField nameInput = new JTextField();
		nameInput.setMaximumSize(new Dimension(400, 45));
		nameInput.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		nameInput.setFont(new Font(null,Font.PLAIN,15));
		nameInput.setEditable(true);
		
		JButton saveName = new JButton("Save");
		saveName.setFont(new Font("SansSerif",Font.PLAIN,16));
		saveName.setMaximumSize(new Dimension(90, 55));
		
		inputNamePanel.setLayout(new BoxLayout(inputNamePanel,BoxLayout.X_AXIS));
		inputNamePanel.add(nameInput);
		inputNamePanel.add(Box.createRigidArea(new Dimension(30,0)));
		inputNamePanel.add(saveName);
		inputNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		saveName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameInput.getText().isBlank() || nameInput.getText().isEmpty()) {
					con.setText("Please type in a valid name.");
					con.setForeground(Color.RED);
				}
				else {
					wareHouse.setStoreName(nameInput.getText().strip());
					storeName = wareHouse.getStoreName();
					greeting.setText("Welcome to " + storeName + "!");
					wareHouseNamed = true;
					updateSaved = false;
					nameDialog.dispose();
				}
				
				
			}
			
		});
		namePanel.add(nameWarning);
		namePanel.add(Box.createRigidArea(new Dimension(0,30)));
		namePanel.add(nameInstruction);
		namePanel.add(Box.createRigidArea(new Dimension(0,30)));
		namePanel.add(nameConPanel);
		namePanel.add(Box.createRigidArea(new Dimension(0,10)));
		namePanel.add(inputNamePanel);
		
		nameDialog.add(namePanel);
		nameDialog.setLocationRelativeTo(parentFrame);
		nameDialog.setVisible(true);
		
		return wareHouseNamed;
	}
	
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Load Mower Section
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Load Mower Section

	// Allows user to load mower data from input file
	public void loadWareHouseAction() {

		JFrame loadFrame = new JFrame("Load WareHouse Data");
		loadFrame.setSize(new Dimension(700, 400));
		loadFrame.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(90, 20, 20, 20));
		
		//IntroPanel and its components
		JPanel introPanel = new JPanel(); // Panel for information about page

		JLabel intro = new JLabel("Welcome to the Load Page!");
		intro.setFont(new Font("SansSerif", Font.BOLD, 20));
		intro.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instruction = new JLabel("Please select the file you wish to load. ");
		instruction.setFont(new Font("SansSerif", Font.PLAIN, 18));
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note1 = new JLabel("Note: Attempting to load in a file with the wrong format will cause an error.");
		note1.setFont(new Font("SansSerif", Font.BOLD, 16));
		note1.setAlignmentX(Component.CENTER_ALIGNMENT);

		introPanel.add(intro);
		introPanel.add(Box.createRigidArea(new Dimension(0,15)));
		introPanel.add(instruction);
		introPanel.add(Box.createRigidArea(new Dimension(0,15)));
		introPanel.add(note1);
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.PAGE_AXIS));
		
		// InputPanel and its components 
		JPanel inputPanel = new JPanel(); // Panel for file input
		JButton loadButton = new JButton("Load");
		loadButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		loadButton.setPreferredSize(new Dimension(100, 35));
		loadButton.setMaximumSize(loadButton.getPreferredSize());
		
		JButton formatButton = new JButton("Format Information");
		formatButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		formatButton.setPreferredSize(new Dimension(170, 35));
		formatButton.setMaximumSize(formatButton.getPreferredSize());
		
		inputPanel.add(loadButton);
		inputPanel.add(Box.createRigidArea(new Dimension(15,0)));
		inputPanel.add(formatButton);

		// Where the confirmation that it was loaded will be
		JPanel conPanel = new JPanel(); // Panel for loading confirmation
		
		JLabel confirmation = new JLabel("");
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmation.setFont(new Font("SansSerif",Font.PLAIN,16));
		
		
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));
		conPanel.add(confirmation);

		// If load button is pressed, call readMowerData
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					System.out.println("You chose to open this file: " + selectedFile.getAbsolutePath());

					String filepath = selectedFile.getPath();
					wareHouse.readMowerData(filepath);
					
					if(wareHouse.getLoadError() == true) {
						confirmation.setText("Error opening file.");
						confirmation.setForeground(Color.RED);
					}
					else {
						confirmation.setText(wareHouse.getOutString());
						confirmation.setForeground(new Color(18, 112, 32));
						storeName = wareHouse.getStoreName();
						greeting.setText("Welcome to " + storeName + "!");
						updateSaved = true;
					}
				}

			}
		});
		
		formatButton.addActionListener(e -> fileInfoPopUp(loadFrame));

		mainPanel.add(introPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		mainPanel.add(conPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		mainPanel.add(inputPanel);
		

		loadFrame.add(mainPanel);
		loadFrame.setLocationRelativeTo(null);
		loadFrame.setVisible(true);

	}
	
	// For user to see the input file format
	
	// Pop up informing the user about file formatting as well as examples
	public void fileInfoPopUp(JFrame parentPage) {
		

				JDialog formatWindow = new JDialog(parentPage, "Expected File Format");
				formatWindow.setSize(new Dimension(700,500));
				formatWindow.setResizable(false);
				
				// Main Panel for dialog
				JPanel filePanel = new JPanel(); // Panel to show the user the expected file format
				filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
				filePanel.setBorder(new EmptyBorder(15,15,15,15));
				
				// Label
				JLabel formatExample = new JLabel("File Format");
				formatExample.setFont(new Font("Sans Serif", Font.BOLD, 16));
				formatExample.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				JTextArea formatText = new JTextArea();
				
				JLabel formatNote1 = new JLabel("Each Property will be on a separate line in the same order listed above.");
				JButton viewExampleButton = new JButton("View Example");
				JButton labeledExampleButton = new JButton("View Labeled Example");
				JLabel formatNote2 = new JLabel("Mower Year is saved and displayed as a 4 digit number.");
				
				formatNote1.setFont(new Font("Sans Serif", Font.BOLD, 15));
				formatNote1.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				viewExampleButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
				viewExampleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				labeledExampleButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
				labeledExampleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				formatNote2.setFont(new Font("Sans Serif", Font.BOLD, 15));
				formatNote2.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				String fileFormat = "Store Name (Text)\n"
						+ "Mower Class Properties:\n	"
						+ "Mower Manufacturer (Text)\n	"
						+ "Mower Year (Whole Number)\n	"
						+ "Mower Serial Number(Text)\n"
						+ "Mower Type (L, C, G, or P) followed by its properties\n"
						+ "L (LawnTractor) Properties:\n"
						+ "	Engine Manufacturer (Text)\n"
						+ "	Engine Horsepower (Decimal Number)\n"
						+ "	Engine Cylinders (Whole Number)\n"
						+ "	LawnTractor Model (Text)\n"
						+ "	LawnTractor Deck Width (Decimal Number)\n"
						+ "C (Commercial Mower) Properties:\n"
						+ "	Engine Manufacturer (Text)\n"
						+ "	Engine Horsepower (Decimal Number)\n"
						+ "	Engine Cylinders (Whole Number)\n"
						+ "	LawnTractor Model (Text)\n"
						+ "	LawnTractor Deck Width (Decimal Number)\n"
						+ "	Commercial Mower Operating Hours (Decimal Number)\n"
						+ "	Commercial Mower Zero Turn Radius? (true or false)\n"
						+ "G (Gaspowered Mower) Properties:\n"
						+ "	Walk Behind Mower Cut Width (Decimal Number)\n"
						+ "	Walk Behind Mower Wheel Diameter (Decimal Number)\n"
						+ "	Engine Manufacturer (Text)\n"
						+ "	Engine Horsepower (Decimal Number)\n"
						+ "	Engine Cylinders (Whole Number)\n"
						+ "	Gas Powered Mower Self Propelled? (true or false)\n"
						+ "P (Push Reel Mower) Properties:\n"
						+ "	Walk Behind Mower Cut Width (Decimal Number)\n"
						+ "	Walk Behind Mower Wheel Diameter (Decimal Number)\n"
						+ "	Push Reel Mower Number of Wheels (Whole Number)";
				
				
				viewExampleButton.addActionListener(e -> examplePopUp(formatWindow));
				labeledExampleButton.addActionListener(e -> labeledExamplePopUp(formatWindow));
				
				formatText.setText(fileFormat);
				formatText.setFont(new Font("Sana Serif", Font.PLAIN, 16));
				formatText.setEditable(false);
				formatText.setLineWrap(false);
				formatText.setWrapStyleWord(false);
				
				JScrollPane formatScroll = new JScrollPane(formatText);
				formatScroll.setPreferredSize(new Dimension(570,250));
				formatScroll.setMaximumSize(new Dimension(570,250));
				formatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				formatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				filePanel.add(formatExample);
				filePanel.add(formatNote1);
				filePanel.add(formatNote2);
				filePanel.add(formatScroll);

				filePanel.add(viewExampleButton);
				filePanel.add(labeledExampleButton);
				
				
				
				formatWindow.add(filePanel);
				formatWindow.pack();
				formatWindow.setLocationRelativeTo(null);
				formatWindow.setVisible(true);


	}
	
	

	// Shows user an example of input with labels
	// Pop up showing a labeled example to the user
	public void labeledExamplePopUp(JDialog parentWindow) {
		JDialog labeledWindow = new JDialog(parentWindow, "Labeled Example File");
		labeledWindow.setSize(new Dimension(400,500));
		labeledWindow.setResizable(false);
		
		// Main Panel for dialog
		JPanel filePanel = new JPanel(); // Panel to show the user the example labeled
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
		filePanel.setBorder(new EmptyBorder(15,15,15,15));
		
		// Label
		JLabel formatExample = new JLabel("Labeled Example File");
		formatExample.setFont(new Font("Sans Serif", Font.BOLD, 16));
		formatExample.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextArea formatText = new JTextArea();
		
		JLabel formatNote1 = new JLabel("Each Property will be on a separate line in the same order listed above.");
		JLabel formatNote2 = new JLabel("Each propery will have a description and space between them.");


		
		formatNote1.setFont(new Font("Sans Serif", Font.BOLD, 15));
		formatNote1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		formatNote2.setFont(new Font("Sans Serif", Font.BOLD, 15));
		formatNote2.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		String exampleText = "Mower Warehouse Store Name:\n"
				+ "Monstrous Mower Warehouse\n\n"
				+ "Mower Manufacturer:\n"
				+ "Briggs and Stratton\n\n"
				+ "Mower Year:\n"
				+ "2015\n\n"
				+ "Mower Serial Number:\n"
				+ "AFXH1\n\n"
				+ "Mower Subclass:\n"
				+ "L\n\n"
				+ "Engine Manufacturer:\n"
				+ "Gravely Parts\n\n"
				+ "Engine Horse Power:\n"
				+ "10.8\n\n"
				+ "Engine Cylinders:\n"
				+ "2\n\n"
				+ "Lawn Tractor Model:\n"
				+ "Model1\n\n"
				+ "Lawn Tractor Deck Width:\n"
				+ "45.5\n\n"
				+ "Mower Manufacturer:\n"
				+ "B&H Manufacturing\n\n"
				+ "Mower Year:\n"
				+ "2011\n\n"
				+ "Mower Serial Number:\n"
				+ "AFXH2\n\n"
				+ "Mower Subclass:\n"
				+ "C\n\n"
				+ "Engine Manufacturer:\n"
				+ "Poulan Parts\n\n"
				+ "Engine Horse Power:\n"
				+ "10.5\n\n"
				+ "Engine Cylinders:\n"
				+ "2\n\n"
				+ "Lawn Tractor Model:\n"
				+ "Model2\n\n"
				+ "Lawn Tractor Deck Width:\n"
				+ "5.8\n\n"
				+ "Commercial Mower Operating Hours:\n"
				+ "35.5\n\n"
				+ "Commercial Mower Zero Turn Radius?:\n"
				+ "false\n\n"
				+ "Mower Manufacturer:\n"
				+ "Bush Hog Manufaturing\n\n"
				+ "Mower Year:\n"
				+ "2013\n\n"
				+ "Mower Serial Number:\n"
				+ "AFXH3\n\n"
				+ "Mower Subclass:\n"
				+ "G\n\n"
				+ "Walk Behind Mower Cut Width:\n"
				+ "20.0\n\n"
				+ "Walk Behind Mower Wheel Diameter:\n"
				+ "16.0\n\n"
				+ "Engine Manufacturer:\n"
				+ "Homelite Parts\n\n"
				+ "Engine Horse Power:\n"
				+ "10.5\n\n"
				+ "Engine Cylinders:\n"
				+ "2\n\n"
				+ "Gas Powered Mower selfPropelled?:\n"
				+ "true\n\n"
				+ "Mower Manufacturer:\n"
				+ "Ferris Industries\n\n"
				+ "Mower Year:\n"
				+ "2003\n\n"
				+ "Mower Serial Number:\n"
				+ "AFXH4\n\n"
				+ "Mower Subclass:\n"
				+ "P\n\n"
				+ "Walk Behind Mower Cut Width:\n"
				+ "18.0\n\n"
				+ "Walk Behind Mower Wheel Diameter:\n"
				+ "25.1\n\n"
				+ "Push Reel Mower Number of Wheels:\n"
				+ "4\n\n"
				+ "Mower Manufacturer:\n"
				+ "Briggs and Stratton II\n\n"
				+ "Mower Year:\n"
				+ "2015\n\n"
				+ "Mower Serial Number:\n"
				+ "AFXH1vv\n\n"
				+ "Mower Subclass:\n"
				+ "L\n\n"
				+ "Engine Manufacturer:\n"
				+ "Gravely Parts\n\n"
				+ "Engine Horse Power:\n"
				+ "13.3\n\n"
				+ "Engine Cylinders:\n"
				+ "2\n\n"
				+ "Lawn Tractor Model:\n"
				+ "Model1\n\n"
				+ "Lawn Tractor Deck Width:\n"
				+ "45.5";
		
		

		
		formatText.setText(exampleText);
		formatText.setFont(new Font("Sana Serif", Font.PLAIN, 16));
		formatText.setEditable(false);
		formatText.setLineWrap(false);
		formatText.setWrapStyleWord(false);
		
		JScrollPane formatScroll = new JScrollPane(formatText);
		formatScroll.setPreferredSize(new Dimension(350,250));
		formatScroll.setMaximumSize(new Dimension(350,250));
		formatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		filePanel.add(formatExample);
		filePanel.add(formatNote1);
		filePanel.add(formatNote2);
		filePanel.add(formatScroll);
		
		
		
		labeledWindow.add(filePanel);
		labeledWindow.pack();
		labeledWindow.setLocationRelativeTo(null);
		labeledWindow.setVisible(true);
		
	}
	
	
	// Shows user an example file
	//Pop up showing a example to the user
	public void examplePopUp(JDialog parentWindow) {
		JDialog exampleWindow = new JDialog(parentWindow, "Example File");
		exampleWindow.setSize(new Dimension(400,500));
		exampleWindow.setResizable(false);
		
		// Main Panel for dialog
		JPanel filePanel = new JPanel(); // Panel to show the user the example
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
		filePanel.setBorder(new EmptyBorder(15,15,15,15));
		
		// Label
		JLabel formatExample = new JLabel("Example File");
		formatExample.setFont(new Font("Sans Serif", Font.BOLD, 16));
		formatExample.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextArea formatText = new JTextArea();
		
		JLabel formatNote1 = new JLabel("Each Property will be on a separate line in the same order listed above.");

		
		formatNote1.setFont(new Font("Sans Serif", Font.BOLD, 15));
		formatNote1.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		String exampleText = "Monstrous Mower Warehouse\n"
				+ "Briggs and Stratton\n"
				+ "2015\n"
				+ "AFXH1\n"
				+ "L\n"
				+ "Gravely Parts\n"
				+ "10.8\n"
				+ "2\n"
				+ "Model1\n"
				+ "45.5\n"
				+ "B&H Manufacturing\n"
				+ "2011\n"
				+ "AFXH2\n"
				+ "C\n"
				+ "Poulan Parts\n"
				+ "10.5\n"
				+ "2\n"
				+ "Model2\n"
				+ "5.8\n"
				+ "35.5\n"
				+ "false\n"
				+ "Bush Hog Manufaturing\n"
				+ "2013\n"
				+ "AFXH3\n"
				+ "G\n"
				+ "20.0\n"
				+ "16.0\n"
				+ "Homelite Parts\n"
				+ "10.5\n"
				+ "2\n"
				+ "true\n"
				+ "Ferris Industries\n"
				+ "2003\n"
				+ "AFXH4\n"
				+ "P\n"
				+ "18.0\n"
				+ "25.1\n"
				+ "4\n"
				+ "Briggs and Stratton II\n"
				+ "2015\n"
				+ "AFXH1vv\n"
				+ "L\n"
				+ "Gravely Parts\n"
				+ "13.3\n"
				+ "2\n"
				+ "Model1\n"
				+ "45.5";
		
		

		
		formatText.setText(exampleText);
		formatText.setFont(new Font("Sana Serif", Font.PLAIN, 16));
		formatText.setEditable(false);
		formatText.setLineWrap(false);
		formatText.setWrapStyleWord(false);
		
		JScrollPane formatScroll = new JScrollPane(formatText);
		formatScroll.setPreferredSize(new Dimension(350,250));
		formatScroll.setMaximumSize(new Dimension(350,250));
		formatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		filePanel.add(formatExample);
		filePanel.add(formatNote1);
		filePanel.add(formatScroll);
		
		
		
		exampleWindow.add(filePanel);
		exampleWindow.pack();
		exampleWindow.setLocationRelativeTo(null);
		exampleWindow.setVisible(true);
	}
	
	
	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// View Mowers Section
	
	// Start page to view different mower types 

	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// View Mower Section

	// Allows user to view mowers in the warehouse
	public void viewWareHouseAction() {		
		JFrame viewPage = new JFrame("View Warehouse Data");
		viewPage.setSize(new Dimension(900, 400));
		viewPage.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(100, 20, 20, 20));

		// Panel for page information
		JPanel infoPanel = new JPanel();

		// infoPanel components
		JLabel greetings = new JLabel("Welcome to the View Page!");
		greetings.setFont(new Font("SansSerif", Font.BOLD, 26));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Select the type of mower list you would like to view:");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 16));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(greetings);
		infoPanel.add(Box.createRigidArea(new Dimension(0,30)));
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		// Panel for displaying different mower buttons
		JPanel mowersPanel = new JPanel();
		
		
		JButton lawnTractorMower = new JButton("Lawn Tractor Mower");
		lawnTractorMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton commercialMower = new JButton("Commercial Mower");
		commercialMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton gasPoweredMower = new JButton("Gas Powered Mower");
		gasPoweredMower.setFont(new Font("SansSerif",Font.PLAIN,15));
		JButton pushReelMower = new JButton("Push Reel Mower");
		pushReelMower.setFont(new Font("SansSerif",Font.PLAIN,15));

		// Add action listeners		
		lawnTractorMower.addActionListener(e -> viewLawnTractor(viewPage));
		commercialMower.addActionListener(e -> viewCommercialMower(viewPage));
		gasPoweredMower.addActionListener(e -> viewGasPoweredMower(viewPage));
		pushReelMower.addActionListener(e -> viewPushReelMower(viewPage));

		mowersPanel.add(lawnTractorMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(commercialMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(gasPoweredMower);
		mowersPanel.add(Box.createRigidArea(new Dimension(15,0)));
		mowersPanel.add(pushReelMower);
		
		mowersPanel.setLayout(new BoxLayout(mowersPanel,BoxLayout.X_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
		mainPanel.add(mowersPanel);

		viewPage.add(mainPanel);
		viewPage.setLocationRelativeTo(null);
		viewPage.setVisible(true);
	}
	
	// Shows user the list of Push Reel mowers

	// View properties of all Push Reel mowers
	public void viewPushReelMower(JFrame parentFrame) {
		JDialog pushWindow = new JDialog(parentFrame, "View Push Reel Mowers");
		pushWindow.setSize(new Dimension(490, 300));
		pushWindow.setResizable(false);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		DefaultListModel<String> prMowers = new DefaultListModel<String>();

		boolean mowersFound = false;

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof PushReelMower) {
				mowersFound = true;

				// split to print all properties stacked on top

				PushReelMower pm = (PushReelMower) m;
				
				prMowers.addElement(pm.listDisplayString());
				prMowers.addElement("-----------------------------------------");
			}
		}

		if (mowersFound == false) {
			prMowers.addElement("No Push Reel Mowers in warehouse.");

		}
		JList<String> pList = new JList<String>(prMowers);
		pList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(pList);

		mainPanel.add(scrollPane,BorderLayout.CENTER);

		pushWindow.add(mainPanel);
		pushWindow.setLocationRelativeTo(null);
		pushWindow.setVisible(true);

	}
	
	// Shows user the list of Gas Powered mowers

	// View properties of all Gas Powered mowers
	public void viewGasPoweredMower(JFrame parentFrame) {

		JDialog gasWindow = new JDialog(parentFrame, "View Gas Powered Mowers");
		gasWindow.setSize(new Dimension(490, 300));
		gasWindow.setResizable(false);

		boolean mowersFound = false;

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		DefaultListModel<String> gpMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof GasPoweredMower) {
				mowersFound = true;

				// split toString to print all properties stacked on top
				GasPoweredMower gm = (GasPoweredMower)m;
				gpMowers.addElement(gm.listDisplayString());
				
				gpMowers.addElement("-----------------------------------------");
			}
		}

		if (mowersFound == false) {
			gpMowers.addElement("No Gas Powered Mowers in warehouse.");

		}

		JList<String> gList = new JList<String>(gpMowers);
		gList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(gList);

		mainPanel.add(scrollPane,BorderLayout.CENTER);

		gasWindow.add(mainPanel);
		gasWindow.setLocationRelativeTo(null);
		gasWindow.setVisible(true);

	}
	
	// Shows user the list of Commercial mowers
	// View properties of all Commercial Mowers
	public void viewCommercialMower(JFrame parentFrame) {

		JDialog comWindow = new JDialog(parentFrame, "View Commercial Mowers");
		comWindow.setSize(new Dimension(490, 300));
		comWindow.setResizable(false);

		boolean mowersFound = false;

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		DefaultListModel<String> cmMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof CommercialMower) {
				mowersFound = true;

				// split toString to print all properties stacked on top
				CommercialMower cm = (CommercialMower)m;
				
				cmMowers.addElement(cm.listDisplayString());
				cmMowers.addElement("-----------------------------------------");
			}
		}

		if (mowersFound == false) {
			cmMowers.addElement("No Commercial Mowers in warehouse.");

		}

		JList<String> cList = new JList<String>(cmMowers);
		cList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(cList);

		mainPanel.add(scrollPane,BorderLayout.CENTER);

		comWindow.add(mainPanel);
		comWindow.setLocationRelativeTo(null);
		comWindow.setVisible(true);

	}
	
	// Shows user the list of Lawn Tractor mowers

	// View properties of all Lawn Tractor mowers
	public void viewLawnTractor(JFrame parentFrame) {

		JDialog lawnWindow = new JDialog(parentFrame, "View Lawn Tractor Mowers");
		lawnWindow.setSize(new Dimension(490, 300));
		lawnWindow.setResizable(false);

		boolean mowersFound = false;

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

		DefaultListModel<String> ltMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if ((m instanceof LawnTractor) && !(m instanceof CommercialMower)) {
				mowersFound = true;

				// split to print all properties stacked on top
				LawnTractor lm = (LawnTractor) m;

				ltMowers.addElement(lm.listDisplayString());
				ltMowers.addElement("-----------------------------------------");
			}
		}

		if (mowersFound == false) {
			ltMowers.addElement("No Lawn Tractor Mowers in warehouse.");

		}

		JList<String> lList = new JList<String>(ltMowers);
		lList.setFont(new Font("SansSerif", Font.PLAIN, 17));
		JScrollPane scrollPane = new JScrollPane(lList);
		
		
		mainPanel.add(scrollPane,BorderLayout.CENTER);

		lawnWindow.add(mainPanel);
		lawnWindow.setLocationRelativeTo(null);
		lawnWindow.setVisible(true);
	}
	

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// Exit Program Section
	/*
	 * TODO Change to ask if the user would like to save the update or not 
	 * Cases: 
	 * - when you add a mower 
	 * - when you load in data 
	 * - when you change the warehouse name
	 * - when you do not update anything and just want to exit
	 * - when you delete a mower
	 */
	

	// Exit out of application
	public void exitWareHouseAction() {

		// if no updates were made, just exit
		if (updateSaved == true) {
			System.exit(0);
		}
		
		

		JDialog exitWindow = new JDialog(homePage, "Exit Confirmation");
		exitWindow.setSize(new Dimension(700, 150));
		exitWindow.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel instruc = new JPanel();
		JLabel instruction = new JLabel("Would you like to save your unsaved data before exiting?");
		instruction.setFont(new Font("SansSerif", Font.BOLD, 14));
		instruc.add(instruction);
		instruc.setLayout(new BoxLayout(instruc, BoxLayout.LINE_AXIS));
		mainPanel.add(instruc);

		JPanel buttons = new JPanel();
		JButton yesButton = new JButton("Yes");
		JButton noButton = new JButton("No");
		buttons.add(yesButton);
		buttons.add(noButton);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		mainPanel.add(buttons);

		exitWindow.add(mainPanel);

		// If there were unsaved changes, ask the user what action to take
		// After saving, exit the program
		// Current Problem: the program is exiting before saving
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitCalled = true;
				saveWareHouseAction();
				exitWindow.dispose();


			}

		});
		


		// call to exit program if user says no
		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				exitWindow.dispose();

			}

		});
		
		exitWindow.setLocationRelativeTo(homePage);
		exitWindow.setVisible(true);

	}

}
