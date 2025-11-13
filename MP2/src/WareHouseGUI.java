import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WareHouseGUI {

	public MowerWareHouse wareHouse;
	public JFrame start;
	public JFrame addFrame;
	public JFrame viewFrame;
	public String storeName = null;
	public JLabel greeting = new JLabel("No Warehouse Available");
	public boolean updateSaved = true;

	
	public static void main(String[] args) {
		WareHouseGUI wh = new WareHouseGUI();
	}

	
	public WareHouseGUI() {
		wareHouse = new MowerWareHouse();
		createGUI();
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
		
		
		JDialog addFields = new JDialog(start, "Add Mower Warehouse Name");
		addFields.setPreferredSize(new Dimension(500, 400));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(120, 20, 20, 20));

		JPanel infoPanel = new JPanel(); // Panel for page information
		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		JPanel inputPanel = new JPanel(); // Panel for store name input

		// infoPanel components
		JLabel info = new JLabel("Welcome to the name warehouse page.");
		info.setFont(new Font("SansSerif", Font.BOLD, 20));
		info.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Please enter your warehouse name.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(info);
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// inputPanel components
		JTextField input = new JTextField();
		JButton saveButton = new JButton("Create Store");
		input.setPreferredSize(new Dimension(200, 20));
		input.setEditable(true);

		inputPanel.add(input);
		inputPanel.add(saveButton);

		// conPanel components
		JLabel confirmation = new JLabel("");
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add(conPanel);
		mainPanel.add(inputPanel);

		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);

		// action to save store name and update menu with store name
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid name.");
				} else {
					wareHouse.setStoreName(input.getText());
					storeName = wareHouse.getStoreName();
					confirmation.setText("Store Name Saved!");

					greeting.setText("Welcome to " + storeName + "!");

					addFields.dispose();
				}

			}

		});

	}
	
	
	// Starting page of program
	public void createGUI() {

		start = new JFrame("Mower Warehouse");
		start.setPreferredSize(new Dimension(800, 500));
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setResizable(false);

		JPanel window = new JPanel();
		window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
		window.setBorder(new EmptyBorder(150, 20, 20, 20));

		// Opening Panel
		JPanel opening = new JPanel();
		opening.setLayout(new BoxLayout(opening, BoxLayout.PAGE_AXIS));
		opening.setAlignmentX(Component.CENTER_ALIGNMENT);
	
		
		// Checks if user wants to start with naming their warehouse
		if (checkWareHouse() == false) {
			changeWareHouseName();
		}

		
		// Greeting Label
		greeting.setFont(new Font("SansSerif", Font.BOLD, 20));
		greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Instructions Label
		JLabel instructions = new JLabel("Select An Operation:");
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 14));

		opening.add(greeting);
		opening.add(Box.createRigidArea(new Dimension(0, 20)));
		opening.add(instructions);

		window.add(opening);
		window.add(Box.createRigidArea(new Dimension(0, 20)));

		// Options Panel
		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options, BoxLayout.LINE_AXIS));
		options.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton addMower = new JButton("Add Mower");
		JButton viewWareHouse = new JButton("View Warehouse");
		JButton loadFile = new JButton("Load File");
		JButton saveFile = new JButton("Save File");
		JButton rename = new JButton("Rename Warehouse");
		JButton exit = new JButton("Exit");
		

		// Add action listeners
		addMower.addActionListener(e -> addMowerAction());
		viewWareHouse.addActionListener(e -> viewWareHouseAction());
		loadFile.addActionListener(e -> loadWareHouseAction());
		saveFile.addActionListener(e -> saveWareHouseAction());
		rename.addActionListener(e->changeWareHouseName());
		exit.addActionListener(e -> exitWareHouseAction());

		// Add buttons to be displayed
		options.add(addMower);
		options.add(viewWareHouse);
		options.add(loadFile);
		options.add(saveFile);
		options.add(rename);
		options.add(exit);

		window.add(options);
		start.add(window);
		start.pack();
		start.setVisible(true);

		updateSaved = true;
	}
	/*--------------------------------------------------------------------------------------------------------------------------------------------*/
	// Add Mower Section

	
	// Starting page to add different mower types
	public void addMowerAction() {
		// TODO 
		// may have to check if warehouse even has a name, so they will have to add
		// after loading or i just leave the warehouse as None
		// if no warehouse name, ask for one, otherwise ask what type of mower is being
		// added, then provide input fields for that mower type

		addFrame = new JFrame("Add Mower Data");
		addFrame.setPreferredSize(new Dimension(790, 400));
		addFrame.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		// Panel for page information
		JPanel infoPanel = new JPanel();

		// infoPanel components
		JLabel greetings = new JLabel("Welcome to the Add Page!");
		greetings.setFont(new Font("SansSerif", Font.BOLD, 20));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Please select the type of mower you would like to add to the warehouse.:");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(greetings);
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Panel for displaying different mower buttons
		JPanel mowersPanel = new JPanel();

		JButton lawnTractorMower = new JButton("LawnTractor Mower");
		JButton commercialMower = new JButton("Commercial Mower");
		JButton gasPoweredMower = new JButton("GasPowered Mower");
		JButton pushReelMower = new JButton("PushReel Mower");

		// Add action listeners
		lawnTractorMower.addActionListener(e -> addLawnTractorMowerAction());
		commercialMower.addActionListener(e -> addCommercialMowerAction());
		gasPoweredMower.addActionListener(e -> addGasPoweredMowerAction());
		pushReelMower.addActionListener(e -> addPushReelMowerAction());

		mowersPanel.add(lawnTractorMower);
		mowersPanel.add(commercialMower);
		mowersPanel.add(gasPoweredMower);
		mowersPanel.add(pushReelMower);
		// mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));

		mainPanel.add(infoPanel);
		mainPanel.add(mowersPanel);

		addFrame.add(mainPanel);
		addFrame.pack();
		addFrame.setVisible(true);
	}

	// Adding mower type Push Reel to list of mowers in warehouse
	public void addPushReelMowerAction() {

		JDialog addFields = new JDialog(addFrame, "Add Push Reel Mower");
		addFields.setPreferredSize(new Dimension(790, 500));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));
		
		// Section for page information
		JPanel infoPanel = new JPanel();

		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif", Font.BOLD, 14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Section for confirmation of adding mower
		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 15));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		// Section for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.EAST;

		// Section for first input
		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1, gbc);

		// Section for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2, gbc);

		// Section for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3, gbc);

		// Section for fourth input
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width(Double):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4, gbc);

		// Section for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter(Double):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5, gbc);

		// Section for sixth input
		JLabel label6 = new JLabel("Push Reel Mower Wheels(Integer):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6, gbc);

		JButton addMower = new JButton("Add Push Reel Mower");
		gbc.gridy = 6;
		fieldPanel.add(addMower, gbc);

		// Action to save push reel mower
		addMower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PushReelMower pr = new PushReelMower();
				if (input1.getText().isBlank() != true) {
					pr.setManufacturer(input1.getText());
				}
				if (input2.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input2.getText());
						pr.setYear(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for year.");
						return;
					}

				}
				if (input3.getText().isBlank() != true) {
					pr.setSerialNumber(input3.getText());
				}
				if (input4.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input4.getText());
						pr.setCutWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cut width.");
						return;
					}

				}
				if (input5.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input5.getText());
						pr.setWheelDiameter(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for wheel diameter.");
						return;
					}
				}
				if (input6.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input6.getText());
						pr.setNumWheels(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for number of wheels.");
						return;
					}
				}
				wareHouse.addMower(pr);
				confirmation.setText("Mower Added!");
				updateSaved = false;
			}

		});
		mainPanel.add(infoPanel);
		mainPanel.add(conPanel);
		mainPanel.add(fieldPanel);

		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);
	}

	// Adding mower type Gas Powered to list of mowers in warehouse
	public void addGasPoweredMowerAction() {

		JDialog addFields = new JDialog(addFrame, "Add Gas Powered Mower");
		addFields.setPreferredSize(new Dimension(790, 550));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		JPanel infoPanel = new JPanel();

		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif", Font.BOLD, 14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Panel for confirmation of adding mower

		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 15));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		// Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = GridBagConstraints.EAST;

		// Panel for first input

		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1, gbc);

		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2, gbc);

		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3, gbc);

		// Panel for fourth input

		JLabel label4 = new JLabel("Walk Behind Mower Cut Width(Double):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4, gbc);

		// Panel for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter(Double):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5, gbc);

		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Manufacturer(String):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6, gbc);

		// Panel for seventh input
		JLabel label7 = new JLabel("Engine HorsePower(Double):");
		label7.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200, 20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7, gbc);

		// Panel for eigth input
		JLabel label8 = new JLabel("Engine Cylinders(Integer):");
		label8.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200, 20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8, gbc);

		// Panel for seventh input
		JLabel label9 = new JLabel("Is it Self Propelled?:");
		label9.setFont(new Font("SansSerif", Font.PLAIN, 13));

		String[] options = { "Select", "True", "False" };

		JComboBox<?> input9 = new JComboBox<Object>(options);
		input9.setSelectedIndex(0);
		input9.setPreferredSize(new Dimension(200, 20));
		input9.setMaximumSize(input9.getPreferredSize());

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label9, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		fieldPanel.add(input9, gbc);

		JButton addMower = new JButton("Add Gas Powered Mower");
		gbc.gridy = 9;
		fieldPanel.add(addMower, gbc);

		// Action to save Gas Powered mower
		addMower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GasPoweredMower gp = new GasPoweredMower();
				Engine ge = new Engine();
				if (input1.getText().isBlank() != true) {
					gp.setManufacturer(input1.getText());
				}
				if (input2.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input2.getText());
						gp.setYear(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for year");
						return;
					}
				}
				if (input3.getText().isBlank() != true) {
					gp.setSerialNumber(input3.getText());
				}
				if (input4.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input4.getText());
						gp.setCutWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cut width");
						return;
					}
				}
				if (input5.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input5.getText());
						gp.setWheelDiameter(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for wheel diameter");
						return;
					}
				}
				if (input6.getText().isBlank() != true) {
					ge.setManufacturer(input6.getText());
				}
				if (input7.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input7.getText());
						ge.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for horse power");
						return;
					}
				}
				if (input8.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input8.getText());
						ge.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cylinders");
						return;
					}
				}

				String selectedItem = (String) input9.getSelectedItem();
				if ("True".equals(selectedItem)) {
					gp.setSelfPropelled(true);
				} else if ("False".equals(selectedItem)) {
					gp.setSelfPropelled(false);
				}

				gp.setEngine(ge);
				wareHouse.addMower(gp);
				confirmation.setText("Mower Added!");
				updateSaved = false;
			}

		});

		mainPanel.add(infoPanel);
		mainPanel.add(conPanel);
		mainPanel.add(fieldPanel);

		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);

	}

	// Adding mower type Commercial to list of mowers in warehouse
	public void addCommercialMowerAction() {

		JDialog addFields = new JDialog(addFrame, "Add Commercial Mower");
		addFields.setPreferredSize(new Dimension(790, 600));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		// Panel for page information
		JPanel infoPanel = new JPanel();

		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif", Font.BOLD, 14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Panel for confirmation of adding mower

		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 15));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		// Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = GridBagConstraints.EAST;

		// Panel for first input

		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1, gbc);

		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2, gbc);

		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3, gbc);

		// Panel for fourth input
		JLabel label4 = new JLabel("Engine Manufacturer(String):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4, gbc);

		// Panel for fifth input
		JLabel label5 = new JLabel("Engine HorsePower(Double):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5, gbc);

		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Cylinders(Integer):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6, gbc);

		// Panel for seventh input
		JLabel label7 = new JLabel("Lawn Tractor Model(String)");
		label7.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200, 20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7, gbc);

		// Panel for eigth input
		JLabel label8 = new JLabel("Lawn Tractor Deck Width(Double)");
		label8.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200, 20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8, gbc);

		// Panel for ninth input
		JLabel label9 = new JLabel("Operating Hours(Double)");
		label9.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input9 = new JTextField();
		input9.setPreferredSize(new Dimension(200, 20));
		input9.setMaximumSize(input9.getPreferredSize());
		input9.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label9, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		fieldPanel.add(input9, gbc);

		// Panel for tenth input
		JLabel label10 = new JLabel("Zero Turn Radius(Boolean)");
		label10.setFont(new Font("SansSerif", Font.PLAIN, 13));

		String[] options = { "Select", "True", "False" };

		JComboBox<?> input10 = new JComboBox<Object>(options);
		input10.setSelectedIndex(0);
		input10.setPreferredSize(new Dimension(200, 20));
		input10.setMaximumSize(input10.getPreferredSize());

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label10, gbc);

		gbc.gridx = 1;
		gbc.gridy = 9;
		fieldPanel.add(input10, gbc);

		JButton addMower = new JButton("Add Commercial Mower");
		gbc.gridy = 10;
		fieldPanel.add(addMower, gbc);

		// action to save commerical mower
		addMower.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {
				CommercialMower cw = new CommercialMower();
				Engine ce = new Engine();
				if (input1.getText().isBlank() != true) {
					cw.setManufacturer(input1.getText());
				}
				if (input2.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input2.getText());
						cw.setYear(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for year");
						return;
					}
				}
				if (input3.getText().isBlank() != true) {
					cw.setSerialNumber(input3.getText());
				}
				if (input4.getText().isBlank() != true) {
					ce.setManufacturer(input4.getText());
				}
				if (input5.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input5.getText());
						ce.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for horse power");
						return;
					}
				}
				if (input6.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input6.getText());
						ce.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cylinders");
						return;
					}
				}
				if (input7.getText().isBlank() != true) {
					cw.setModel(input7.getText());
				}
				if (input8.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input8.getText());
						cw.setDeckWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for deck width");
						return;
					}
				}
				if (input9.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input9.getText());
						cw.setOperatingHours(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for operating hours");
						return;
					}
				}
				String selectedItem = (String) input10.getSelectedItem();
				if ("True".equals(selectedItem)) {
					cw.setZeroTurnRadius(true);
				} else if ("False".equals(selectedItem)) {
					cw.setZeroTurnRadius(false);
				}
				cw.setEngine(ce);
				wareHouse.addMower(cw);

				confirmation.setText("Mower Added!");
				updateSaved = false;
			}

		});

		mainPanel.add(infoPanel);
		mainPanel.add(confirmation);
		mainPanel.add(fieldPanel);

		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);

	}

	// Adding mower type Lawn Tractor to list of mowers in warehouse
	public void addLawnTractorMowerAction() {

		JDialog addFields = new JDialog(addFrame, "Add Lawn Tractor Mower");
		addFields.setPreferredSize(new Dimension(790, 600));
		addFields.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		JPanel infoPanel = new JPanel();

		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif", Font.BOLD, 14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Panel for confirmation of adding mower

		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		JLabel confirmation = new JLabel("");
		confirmation.setFont(new Font("SansSerif", Font.PLAIN, 15));
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		// Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.anchor = GridBagConstraints.EAST;

		// Panel for first input

		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1, gbc);

		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2, gbc);

		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3, gbc);

		// Panel for fourth input
		JLabel label4 = new JLabel("Engine Manufacturer(String):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4, gbc);

		// Panel for fifth input
		JLabel label5 = new JLabel("Engine HorsePower(Double):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5, gbc);

		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Cylinders(Integer):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6, gbc);

		// Panel for seventh input
		JLabel label7 = new JLabel("Lawn Tractor Model(String)");
		label7.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200, 20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7, gbc);

		// Panel for eigth input
		JLabel label8 = new JLabel("Lawn Tractor Deck Width(Double)");
		label8.setFont(new Font("SansSerif", Font.PLAIN, 13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200, 20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8, gbc);

		JButton addMower = new JButton("Add Lawn Tractor Mower");
		gbc.gridy = 8;
		fieldPanel.add(addMower, gbc);

		// action to save lawn tractor mower
		addMower.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {
				LawnTractor lt = new LawnTractor();
				Engine le = new Engine();
				if (input1.getText().isBlank() != true) {
					lt.setManufacturer(input1.getText());
				}
				if (input2.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input2.getText());
						lt.setYear(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for year");
						return;
					}
				}
				if (input3.getText().isBlank() != true) {
					lt.setSerialNumber(input3.getText());
				}
				if (input4.getText().isBlank() != true) {
					le.setManufacturer(input4.getText());
				}
				if (input5.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input5.getText());
						le.setHorsePower(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for horse power");
						return;
					}
				}
				if (input6.getText().isBlank() != true) {
					try {
						int i = Integer.parseInt(input6.getText());
						le.setCylinders(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for cylinders");
						return;
					}
				}
				if (input7.getText().isBlank() != true) {
					lt.setModel(input7.getText());
				}
				if (input8.getText().isBlank() != true) {
					try {
						double i = Double.parseDouble(input8.getText());
						lt.setDeckWidth(i);
					} catch (NumberFormatException nfe) {
						confirmation.setText("Please enter a valid number for deck width");
						return;
					}
				}

				lt.setEngine(le);
				wareHouse.addMower(lt);
				confirmation.setText("Mower Added!");
				updateSaved = false;
			}

		});

		mainPanel.add(infoPanel);
		mainPanel.add(conPanel);
		mainPanel.add(fieldPanel);

		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);

	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// Save Mower Section

	// Save warehouse data to a file
	// Allows user to provide an output file name and save the in - memory warehouse to that file
	public void saveWareHouseAction() {

		JFrame saveFrame = new JFrame("Save WareHouse Data");

		saveFrame.setPreferredSize(new Dimension(590, 400));
		saveFrame.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		JPanel infoPanel = new JPanel(); // Panel for page information
		JPanel conPanel = new JPanel(); // Panel for saving confirmation
		JPanel inputPanel = new JPanel(); // Panel for file input

		// infoPanel components
		JLabel greetings = new JLabel("Welcome to the Save Page!");
		greetings.setFont(new Font("SansSerif", Font.BOLD, 20));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Input name of file you want to save warehouse data to.");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note1 = new JLabel("Note: This file has the same format as the input file.");
		note1.setFont(new Font("SansSerif", Font.BOLD, 13));
		note1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note2 = new JLabel("Note: If there is no warehouse name it will be left as null.");
		note2.setFont(new Font("SansSerif", Font.BOLD, 13));
		note2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(greetings);
		infoPanel.add(instructions);
		infoPanel.add(note1);
		infoPanel.add(note2);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// inputPanel components
		JTextField input = new JTextField();
		JButton saveButton = new JButton("Save");
		input.setPreferredSize(new Dimension(200, 20));
		input.setEditable(true);

		inputPanel.add(input);
		inputPanel.add(saveButton);

		// conPanel components
		JLabel confirmation = new JLabel("");
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));

		// action if the file is or is not saved
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid file name.");
				}
				else {
					// Ensure data is saved as a .txt file
					String fileName = input.getText().strip();
					if(fileName.toLowerCase().endsWith(".txt") == false) {
						fileName +=".txt";
					}
					wareHouse.saveMowerData(fileName);
					confirmation.setText(wareHouse.getOutString());
					updateSaved = true;
				}

			}

		});

		mainPanel.add(infoPanel);
		mainPanel.add(conPanel);
		mainPanel.add(inputPanel);

		saveFrame.add(mainPanel);
		saveFrame.pack();
		saveFrame.setVisible(true);

	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// Load Mower Section

	// Allows user to load mower data from input file

	public void loadWareHouseAction() {

		JFrame loadFrame = new JFrame("Load WareHouse Data");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		JPanel labelPanel = new JPanel(); // Panel for information
		JPanel conPanel = new JPanel(); // Panel for loading confirmation
		JPanel inputPanel = new JPanel(); // Panel for file input
		

		loadFrame.setPreferredSize(new Dimension(590, 400));
		loadFrame.setResizable(false);

		JLabel intro = new JLabel("Welcome to the Load Page!");
		intro.setFont(new Font("SansSerif", Font.BOLD, 20));
		intro.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instruction = new JLabel("Please select the file you wish to load: ");
		instruction.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel note1 = new JLabel("Note: Make sure file is written as expected.");
		note1.setFont(new Font("SansSerif", Font.BOLD, 13));
		note1.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelPanel.add(intro);
		labelPanel.add(instruction);

		labelPanel.add(note1);
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
		
		// inputPanel components field is located

		JButton loadButton = new JButton("Load");
		JButton fileButton = new JButton("View File Format");

		// Where the confirmation that it was loaded will be
		JLabel confirmation = new JLabel("");

		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

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
					try {
						wareHouse.readMowerData(filepath);
						storeName = wareHouse.getStoreName();
						greeting.setText("Welcome to " + storeName + "!");
						confirmation.setText(wareHouse.getOutString());
						updateSaved = false;
					}

					catch (Exception ex) {
						confirmation.setText("Error opening file.");
						ex.printStackTrace();
					}
				}

			}
		});
		
		// If user wants to see the input file format
		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog formatWindow = new JDialog(loadFrame, "Expected File Format");
				formatWindow.setPreferredSize(new Dimension(600,500));
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
				JLabel formatNote2 = new JLabel("View in.txt as an example input file.");
				JLabel formatNote3 = new JLabel("View label.txt if you want to see what each line represents.");
				
				formatNote1.setFont(new Font("Sans Serif", Font.BOLD, 14));
				formatNote1.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				formatNote2.setFont(new Font("Sans Serif", Font.BOLD, 14));
				formatNote2.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				formatNote3.setFont(new Font("Sans Serif", Font.BOLD, 14));
				formatNote3.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				String fileFormat = "Store Name\nMower Class Properties:\n	MowerManufacturer, Mower Year, Mower Serial Number\n"
						+ "Mower Subclass Type (L, C, G, P)\n"
						+ "	L (LawnTractor) Properties: Engine Manufacturer, Engine Horsepower, Engine Cylinders, LawnTractor Model, LawnTractor Deck Width\n"
						+ "	C (Commercial Mower) Properties: Engine Manufacturer, Engine Horsepower, Engine Cylinders, LawnTractor Model, LawnTractor Deck Width, Commercial Mower Operating Hours, Commercial Mower Zero Turn Radius\n"
						+ "	G (Gaspowered Mower) Properties: Walk Behind Mower Cut Width, Walk Behind Mower Wheel Diameter, Engine Manufacturer, Engine Horsepower, Engine Cylinders, Gas Powered Mower Self Propelled\n"
						+ "	P (Push Reel Mower) Properties: Walk Behind Mower Cut Width, Walk Behind Mower Wheel Diameter, Push Reel Mower Number of Wheels";
				
				
				formatText.setText(fileFormat);
				formatText.setEditable(false);
				formatText.setLineWrap(false);
				formatText.setWrapStyleWord(false);
				
				JScrollPane formatScroll = new JScrollPane(formatText);
				formatScroll.setPreferredSize(new Dimension(400,250));
				formatScroll.setMaximumSize(new Dimension(400,250));
				formatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				formatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				filePanel.add(formatExample);
				filePanel.add(formatScroll);
				filePanel.add(formatNote1);
				filePanel.add(formatNote2);
				filePanel.add(formatNote3);
				
				
				formatWindow.add(filePanel);
				formatWindow.pack();
				formatWindow.setVisible(true);
				
			}
			
		});
		
		
		inputPanel.add(loadButton);
		inputPanel.add(fileButton);
		conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.PAGE_AXIS));
		conPanel.add(confirmation);

		mainPanel.add(labelPanel);
		mainPanel.add(conPanel);
		mainPanel.add(inputPanel);
		

		loadFrame.add(mainPanel);
		loadFrame.pack();
		loadFrame.setVisible(true);

	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// View Mower Section

	// Allows user to view mowers in the warehouse
	public void viewWareHouseAction() {
		// if the ware house is empty, state that it is empty
		// otherwise call MowerWareHouse toString and display it.
		// do not allow to edit this text field
		// allow to scroll

		viewFrame = new JFrame("View WareHouse Data");
		viewFrame.setPreferredSize(new Dimension(700, 440));
		viewFrame.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150, 20, 20, 20));

		// Panel for panel information
		JPanel infoPanel = new JPanel();

		// infoPanel components
		JLabel greetings = new JLabel("Welcome to the View Page!");
		greetings.setFont(new Font("SansSerif", Font.BOLD, 20));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel instructions = new JLabel("Please select the type of mower you would like to view:");
		instructions.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(greetings);
		infoPanel.add(instructions);

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

		// Panel for displaying different mower buttons
		JPanel mowersPanel = new JPanel();

		JButton lawnTractorMower = new JButton("LawnTractor Mower");
		JButton commercialMower = new JButton("Commercial Mower");
		JButton gasPoweredMower = new JButton("GasPowered Mower");
		JButton pushReelMower = new JButton("PushReel Mower");

		// Add action listeners
		lawnTractorMower.addActionListener(e -> viewLawnTractor());
		commercialMower.addActionListener(e -> viewCommercialMower());
		gasPoweredMower.addActionListener(e -> viewGasPoweredMower());
		pushReelMower.addActionListener(e -> viewPushReelMower());

		mowersPanel.add(lawnTractorMower);
		mowersPanel.add(commercialMower);
		mowersPanel.add(gasPoweredMower);
		mowersPanel.add(pushReelMower);

		mainPanel.add(infoPanel);
		mainPanel.add(mowersPanel);

		viewFrame.add(mainPanel);
		viewFrame.pack();
		viewFrame.setVisible(true);
	}

	// View properties of all Push Reel mowers
	public void viewPushReelMower() {
		JDialog pushWindow = new JDialog(viewFrame, "View Push Reel Mowers");
		pushWindow.setPreferredSize(new Dimension(490, 300));
		pushWindow.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(400, 120));

		DefaultListModel<String> prMowers = new DefaultListModel<String>();

		boolean found = false;

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof PushReelMower) {
				found = true;

				// split toString to print all properties stacked on top
				String[] lines = m.toString().split("\n");
				for (String line : lines) {
					prMowers.addElement(line);
				}
				prMowers.addElement("------------------------------------");
			}
		}

		if (found == false) {
			prMowers.addElement("No Push Reel Mowers in warehouse.");

		}
		JList<String> pList = new JList<String>(prMowers);
		pList.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(pList);

		mainPanel.add(scrollPane);

		pushWindow.add(mainPanel);
		pushWindow.pack();
		pushWindow.setVisible(true);

	}

	// View properties of all Gas Powered mowers
	public void viewGasPoweredMower() {

		JDialog gasWindow = new JDialog(viewFrame, "View Gas Powered Mowers");
		gasWindow.setPreferredSize(new Dimension(490, 300));
		gasWindow.setResizable(false);

		boolean found = false;

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(400, 120));

		DefaultListModel<String> gpMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof GasPoweredMower) {
				found = true;

				// split toString to print all properties stacked on top
				String[] lines = m.toString().split("\n");
				for (String line : lines) {
					gpMowers.addElement(line);
				}
				gpMowers.addElement("------------------------------------");
			}
		}

		if (found == false) {
			gpMowers.addElement("No Gas Powered Mowers in warehouse.");

		}

		JList<String> gList = new JList<String>(gpMowers);
		gList.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(gList);

		mainPanel.add(scrollPane);

		gasWindow.add(mainPanel);
		gasWindow.pack();
		gasWindow.setVisible(true);

	}

	// View properties of all Commercial Mowers
	public void viewCommercialMower() {

		JDialog comWindow = new JDialog(viewFrame, "View Commercial Mowers");
		comWindow.setPreferredSize(new Dimension(490, 300));
		comWindow.setResizable(false);

		boolean found = false;

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(400, 120));

		DefaultListModel<String> cmMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if (m instanceof CommercialMower) {
				found = true;

				// split toString to print all properties stacked on top
				String[] lines = m.toString().split("\n");
				for (String line : lines) {
					cmMowers.addElement(line);
				}
				cmMowers.addElement("------------------------------------");
			}
		}

		if (found == false) {
			cmMowers.addElement("No Commercial Mowers in warehouse.");

		}

		JList<String> cList = new JList<String>(cmMowers);
		cList.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(cList);

		mainPanel.add(scrollPane);

		comWindow.add(mainPanel);
		comWindow.pack();
		comWindow.setVisible(true);

	}

	// View properties of all Lawn Tractor mowers
	public void viewLawnTractor() {

		JDialog lawnWindow = new JDialog(viewFrame, "View Lawn Tractor Mowers");
		lawnWindow.setPreferredSize(new Dimension(490, 300));
		lawnWindow.setResizable(false);

		boolean found = false;

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(400, 120));

		DefaultListModel<String> ltMowers = new DefaultListModel<String>();

		for (Mower m : wareHouse.getMowersList()) {
			if ((m instanceof LawnTractor) && !(m instanceof CommercialMower)) {
				found = true;

				// split toString to print all properties stacked on top
				String[] lines = m.toString().split("\n");
				for (String line : lines) {
					ltMowers.addElement(line);
				}
				ltMowers.addElement("------------------------------------");
			}
		}

		if (found == false) {
			ltMowers.addElement("No Lawn Tractor Mowers in warehouse.");

		}

		JList<String> lList = new JList<String>(ltMowers);
		lList.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(lList);

		mainPanel.add(scrollPane);

		lawnWindow.add(mainPanel);
		lawnWindow.pack();
		lawnWindow.setVisible(true);
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	// Exit Program Section
	/*
	 * TODO Change to ask if the user would like to save the update or not 
	 * Cases: 
	 * - when you add a mower 
	 * - when you load in ware house data 
	 * - when you do not update anything and just want to exit
	 */

	// Exit out of application
	public void exitWareHouseAction() {

		// if no updates were made just exit
		if (updateSaved == true) {
			System.exit(0);
		}

		JDialog exitWindow = new JDialog(start, "Exit Confirmation", true);
		exitWindow.setPreferredSize(new Dimension(700, 150));
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
		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog yesModal = new JDialog(exitWindow, "Save to File", true);
				yesModal.setPreferredSize(new Dimension(500, 400));
				yesModal.setResizable(false);

				JPanel mainP = new JPanel();
				mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
				mainP.setBorder(new EmptyBorder(145, 15, 15, 15));
				
				// Section for instructions
				JPanel infoPanel = new JPanel();
				JLabel info1 = new JLabel("Please enter the file you would like to save your data to.");
				infoPanel.add(info1);
				info1.setFont(new Font("SansSerif", Font.BOLD, 14));
				infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

				// Section to display errors or messages to users
				JPanel conPanel = new JPanel();
				JLabel feedBack = new JLabel("");
				conPanel.add(feedBack);
				conPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				conPanel.setLayout(new BoxLayout(conPanel, BoxLayout.Y_AXIS));

				// Section for user input
				JPanel inputPanel = new JPanel();
				JTextField input = new JTextField();
				JButton saveButton = new JButton("Save Data");

				input.setEditable(true);
				input.setPreferredSize(new Dimension(200, 20));
				input.setMaximumSize(new Dimension(200, 20));
				input.setMinimumSize(new Dimension(200, 20));

				inputPanel.add(input);
				inputPanel.add(saveButton);
				inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
				inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

				mainP.add(infoPanel);
				mainP.add(conPanel);
				mainP.add(inputPanel);

				yesModal.add(mainP);
				
				
				//Save changed data to file before exiting
				saveButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (input.getText().isEmpty() || input.getText().isBlank()) {
							feedBack.setText("Please enter a valid file name.");
						} 
						else {
							String fileName = input.getText().strip();
							
							// Ensure data is saved as a .txt file
							if(fileName.toLowerCase().endsWith(".txt") == false) {
								fileName +=".txt";
							}
							
							wareHouse.saveMowerData(fileName);
							updateSaved = true;
							System.exit(0);
							exitWindow.dispose();
						}

					}

				});

				yesModal.pack();
				yesModal.setVisible(true);

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

		exitWindow.pack();
		exitWindow.setVisible(true);

	}

}
