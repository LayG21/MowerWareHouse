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
	public JFrame addFrame;
	public static void main(String[] args) {
		WareHouseGUI wh = new WareHouseGUI();
	}
	
	public WareHouseGUI() {
		wareHouse = new MowerWareHouse();
		createGUI();
	}
	
	
	public void createGUI() {
		
		
		//TO-DO:
		/*
		 * Move the information to the middle of the window
		 * Make the opening texts bold and bigger
		 * the greeting should be in the middle
		 * 
		 * */ 
		JFrame start = new JFrame("Mower WareHouse");
		start.setPreferredSize(new Dimension(620, 500));
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setResizable(false);
		
		JPanel window = new JPanel();
		window.setLayout(new BoxLayout(window,BoxLayout.PAGE_AXIS));
		window.setBorder(new EmptyBorder(150,20,20,20));
		
		
		//Opening Panel
		JPanel opening = new JPanel();
		opening.setLayout(new BoxLayout(opening,BoxLayout.PAGE_AXIS));
		opening.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Greeting Label
		JLabel greeting = new JLabel("Welcome to the Mower WareHouse!");
		greeting.setFont(new Font("SansSerif",Font.BOLD,20));
		greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Instructions Label
		JLabel instructions = new JLabel("Select An Operation:");
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		instructions.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		
		opening.add(greeting);
		opening.add(Box.createRigidArea(new Dimension(0,20)));
		opening.add(instructions);
		
		
		
		window.add(opening);
		window.add(Box.createRigidArea(new Dimension(0,20)));
		
		//Options Panel
		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options,BoxLayout.LINE_AXIS));
		options.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton saveFile = new JButton("Save File");
		JButton loadFile = new JButton("Load File");
		JButton addMower = new JButton("Add Mower");
		JButton viewWareHouse = new JButton("View WareHouse");
		JButton exit = new JButton("Exit");
		
		//Add action listeners
		addMower.addActionListener(e -> addMowerAction());
		saveFile.addActionListener(e -> saveWareHouseAction());
		loadFile.addActionListener(e-> loadWareHouseAction());
		viewWareHouse.addActionListener(e -> viewWareHouseAction());
		exit.addActionListener(e -> exitWareHouseAction());
		
		//Add buttons to be displayed
		options.add(addMower);
		options.add(viewWareHouse);
		options.add(loadFile);
		options.add(saveFile);
		options.add(exit);
		
		
		window.add(options);
		start.add(window);
		start.pack();
		start.setVisible(true);
	}
	
	//Check if there is a wareHouse name
	/*
	 * if there is no storename, you can not view the mowers since it is not stored anywhere*/
	public boolean checkWareHouse() {
		boolean nameAvailable = true;
		if(wareHouse.getStoreName() == null) {
			nameAvailable = false;
		}
		
		return nameAvailable;
	}
	
	
	//Add mower to warehouse
	public void addMowerAction() {
		// TODO Auto-generated method stub
		// will have to ask what type of mower is added and then given fields of input for those 
		// may have to check if warehouse even has a name, so they will have to add after loading or i just leave the warehouse as None
		// if no warehouse name, ask for one, otherwise ask what type of mower is being added, then provide input fields for that mower type
		
		addFrame = new JFrame("Add Mower Data");
		addFrame.setPreferredSize(new Dimension(790, 400));
		addFrame.setResizable(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		//Panel for panel information
		JPanel infoPanel = new JPanel();
		
		//infoPanel components
		JLabel greetings = new JLabel("Welcome to the Add Page!");
		greetings.setFont(new Font("SansSerif",Font.BOLD,20));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel instructions = new JLabel("Please select the type of mower you would like to add to the warehouse.:");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(greetings);
		infoPanel.add(instructions);
		
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		
		//Panel for displaying different mower buttons
		JPanel mowersPanel = new JPanel();
		
		JButton lawnTractorMower = new JButton("LawnTractor Mower");
		JButton commercialMower = new JButton("Commercial Mower");
		JButton gasPoweredMower = new JButton("GasPowered Mower");
		JButton pushReelMower = new JButton("PushReel Mower");
		
		
		//Add action listeners
		lawnTractorMower.addActionListener(e -> lawnTractorMowerAction());
		commercialMower.addActionListener(e -> commercialMowerAction());
		gasPoweredMower.addActionListener(e-> gasPoweredMowerAction());
		pushReelMower.addActionListener(e -> pushReelMowerAction());
		
		
		mowersPanel.add(lawnTractorMower);
		mowersPanel.add(commercialMower);
		mowersPanel.add(gasPoweredMower);
		mowersPanel.add(pushReelMower);
		//mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		mainPanel.add(infoPanel);
		mainPanel.add(mowersPanel);
		
		addFrame.add(mainPanel);
		addFrame.pack();
		addFrame.setVisible(true);
	}
	

	public void pushReelMowerAction() {
		// TODO Auto-generated method stub
		
		JDialog addFields = new JDialog(addFrame, "Add PushReel Mower");
		addFields.setPreferredSize(new Dimension(790, 500));
		addFields.setResizable(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		JPanel infoPanel = new JPanel();
		
		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif",Font.BOLD,14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		
		//Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.EAST;
		
		// Panel for first input
		
		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200,20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1,gbc);
		
		
		
		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200,20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2,gbc);
		
		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200,20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3,gbc);
		
		// Panel for fourth input
	
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width(Double):");
		label4.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200,20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4,gbc);
		
		
		// Panel for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter(Double):");
		label5.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200,20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5,gbc);
		
		
		// Panel for sixth input
		JLabel label6 = new JLabel("Push Reel Mower Wheels(Integer):");
		label6.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200,20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6,gbc);
		
		
		JButton addMower = new JButton("Add LawnTractor Mower");
		gbc.gridy = 6;
		fieldPanel.add(addMower,gbc);
		
		
		mainPanel.add(infoPanel);
		mainPanel.add(fieldPanel);
		
		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);
	}

	public void gasPoweredMowerAction() {
		// TODO Auto-generated method stub
		
		JDialog addFields = new JDialog(addFrame, "Add GasPowered Mower");
		addFields.setPreferredSize(new Dimension(790, 550));
		addFields.setResizable(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		JPanel infoPanel = new JPanel();
		
		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif",Font.BOLD,14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		
		//Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.anchor = GridBagConstraints.EAST;
		
		// Panel for first input
		
		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200,20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1,gbc);
		
		
		
		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200,20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2,gbc);
		
		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200,20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3,gbc);
		
		// Panel for fourth input
	
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width(Double):");
		label4.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200,20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4,gbc);
		
		
		// Panel for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter(Double):");
		label5.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200,20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5,gbc);
		
		
		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Manufacturer(String):");
		label6.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200,20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6,gbc);
		
		// Panel for seventh input
		JLabel label7 = new JLabel("Engine HorsePower(Double):");
		label7.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200,20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7,gbc);
		
		// Panel for eigth input
		JLabel label8 = new JLabel("Engine Cylinders(Integer):");
		label8.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200,20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8,gbc);
		
		
		// Panel for seventh input
		JLabel label9 = new JLabel("Is it Self Propelled?:");
		label9.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input9 = new JTextField();
		input9.setPreferredSize(new Dimension(200,20));
		input9.setMaximumSize(input9.getPreferredSize());
		input9.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label9,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		fieldPanel.add(input9,gbc);
		
		JButton addMower = new JButton("Add GasPowered Mower");
		gbc.gridy = 9;
		fieldPanel.add(addMower,gbc);
		
		
		mainPanel.add(infoPanel);
		mainPanel.add(fieldPanel);
		
		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);
		
	}

	public void commercialMowerAction() {
		// TODO Auto-generated method stub
		JDialog addFields = new JDialog(addFrame, "Add Commercial Mower");
		addFields.setPreferredSize(new Dimension(790, 600));
		addFields.setResizable(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		JPanel infoPanel = new JPanel();
		
		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif",Font.BOLD,14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		
		//Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.anchor = GridBagConstraints.EAST;
		
		// Panel for first input
		
		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200,20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1,gbc);
		
		
		
		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200,20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2,gbc);
		
		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200,20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3,gbc);
		
				
		
		// Panel for fourth input
		JLabel label4 = new JLabel("Engine Manufacturer(String):");
		label4.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200,20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4,gbc);
		
		// Panel for fifth input
		JLabel label5 = new JLabel("Engine HorsePower(Double):");
		label5.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200,20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5,gbc);
		
		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Cylinders(Integer):");
		label6.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200,20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6,gbc);
		
		
		// Panel for seventh input
		JLabel label7 = new JLabel("LawnTractor Model(String)");
		label7.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200,20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7,gbc);
		
		// Panel for eigth input
		JLabel label8 = new JLabel("LawnTractor Deck Width(Double)");
		label8.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200,20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8,gbc);
		
		// Panel for ninth input
		JLabel label9 = new JLabel("Operating Hours(Double)");
		label9.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input9 = new JTextField();
		input9.setPreferredSize(new Dimension(200,20));
		input9.setMaximumSize(input9.getPreferredSize());
		input9.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label9,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		fieldPanel.add(input9,gbc);
		
		// Panel for tenth input
		JLabel label10 = new JLabel("Zero Turn Radius(Boolean)");
		label10.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input10 = new JTextField();
		input10.setPreferredSize(new Dimension(200,20));
		input10.setMaximumSize(input10.getPreferredSize());
		input10.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label10,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 9;
		fieldPanel.add(input10,gbc);
		
		JButton addMower = new JButton("Add Commercial Mower");
		gbc.gridy = 10;
		fieldPanel.add(addMower,gbc);
		
		
		mainPanel.add(infoPanel);
		mainPanel.add(fieldPanel);
		
		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);
		
	}

	public void lawnTractorMowerAction() {
		// TODO Auto-generated method stub
		JDialog addFields = new JDialog(addFrame, "Add LawnTractor Mower");
		addFields.setPreferredSize(new Dimension(790, 600));
		addFields.setResizable(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		JPanel infoPanel = new JPanel();
		
		JLabel instructions = new JLabel("Fill in all fields and then press add when done.");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,15));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note = new JLabel("If all fields are not filled in, it will be filled with predetermined data.");
		note.setFont(new Font("SansSerif",Font.BOLD,14));
		note.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		infoPanel.add(instructions);
		infoPanel.add(note);
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		
		//Panel for input fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.anchor = GridBagConstraints.EAST;
		
		// Panel for first input
		
		JLabel label1 = new JLabel("Mower Manufacturer(String):");
		label1.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200,20));
		input1.setMaximumSize(input1.getPreferredSize());
		input1.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label1,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		fieldPanel.add(input1,gbc);
		
		
		
		// Panel for second input
		JLabel label2 = new JLabel("Mower Year(Integer):");
		label2.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200,20));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label2,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		fieldPanel.add(input2,gbc);
		
		// Panel for third input
		JLabel label3 = new JLabel("Mower Serial Number(String):");
		label3.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200,20));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label3,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		fieldPanel.add(input3,gbc);
		
				
		
		// Panel for fourth input
		JLabel label4 = new JLabel("Engine Manufacturer(String):");
		label4.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200,20));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label4,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		fieldPanel.add(input4,gbc);
		
		// Panel for fifth input
		JLabel label5 = new JLabel("Engine HorsePower(Double):");
		label5.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200,20));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label5,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		fieldPanel.add(input5,gbc);
		
		// Panel for sixth input
		JLabel label6 = new JLabel("Engine Cylinders(Integer):");
		label6.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200,20));
		input6.setMaximumSize(input6.getPreferredSize());
		input6.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label6,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		fieldPanel.add(input6,gbc);
		
		
		// Panel for seventh input
		JLabel label7 = new JLabel("LawnTractor Model(String)");
		label7.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input7 = new JTextField();
		input7.setPreferredSize(new Dimension(200,20));
		input7.setMaximumSize(input7.getPreferredSize());
		input7.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label7,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		fieldPanel.add(input7,gbc);
		
		// Panel for eigth input
		JLabel label8 = new JLabel("LawnTractor Deck Width(Double)");
		label8.setFont(new Font("SansSerif",Font.PLAIN,13));
		JTextField input8 = new JTextField();
		input8.setPreferredSize(new Dimension(200,20));
		input8.setMaximumSize(input8.getPreferredSize());
		input8.setEditable(true);

		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.WEST;
		fieldPanel.add(label8,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		fieldPanel.add(input8,gbc);
		
				
		JButton addMower = new JButton("Add LawnTractor Mower");
		gbc.gridy = 8;
		fieldPanel.add(addMower,gbc);
		
		
		mainPanel.add(infoPanel);
		mainPanel.add(fieldPanel);
		
		addFields.add(mainPanel);
		addFields.pack();
		addFields.setVisible(true);

		
	}

	//Save warehouse data to a file
	/*
	 * Should provide dialog to allow user to provide an output file name 
	 * and then save the in - memory warehouse to a file
	 * */
	public void saveWareHouseAction() {
		// TODO Auto-generated method stub
		// can not save with no name and no mowers so ask for that first
		// if there is a name and mowers, call the to string and print it out.
		
		JFrame saveFrame = new JFrame("Save WareHouse Data");
		
		saveFrame.setPreferredSize(new Dimension(590, 400));
		saveFrame.setResizable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		
		JPanel infoPanel = new JPanel();											//Panel for page information
		JPanel conPanel = new JPanel();										//Panel for saving confirmation
		JPanel inputPanel = new JPanel();									//Panel for file input
		
		
		//infoPanel components
		JLabel greetings = new JLabel("Welcome to the Save Page!");
		greetings.setFont(new Font("SansSerif",Font.BOLD,20));
		greetings.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel instructions = new JLabel("Input name of file you want to save WareHouse data to.");
		instructions.setFont(new Font("SansSerif",Font.PLAIN,13));
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.add(greetings);
		infoPanel.add(instructions);
		
		infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
		
		
		//inputPanel components
		JTextField input = new JTextField();
		JButton saveButton = new JButton("Save");
		input.setPreferredSize(new Dimension(200, 20));
		input.setEditable(true);
		
		inputPanel.add(input);
		inputPanel.add(saveButton);
		
		
		//conPanel components
		JLabel confirmation = new JLabel("");
		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		conPanel.add(confirmation);
		conPanel.setLayout(new BoxLayout(conPanel,BoxLayout.PAGE_AXIS));
		
		//action if the file is or is not saved
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid file name.");
				}
				else {
					wareHouse.saveMowerData(input.getText());
					confirmation.setText(wareHouse.getOutString());
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

	//Load mower data from file.
	// type in input file exactly how it is named
	// tell user if it exists or not
	// it is assumed it will follow the input format as specified
	//TODO
	/*
	 * change it to use jfilechooser as specified in the document
	 * */
	public void loadWareHouseAction() {
		
		JFrame loadFrame = new JFrame("Load WareHouse Data");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(new EmptyBorder(150,20,20,20));
		
		JPanel labelPanel = new JPanel();									// Panel for information
		JPanel conPanel = new JPanel();										// Panel for loading confirmation
		JPanel inputPanel = new JPanel();									// Panel for file input
		
		
		loadFrame.setPreferredSize(new Dimension(590, 400));
		loadFrame.setResizable(false);
		

		 
		JLabel intro = new JLabel("Welcome to the Load Page!");
		intro.setFont(new Font("SansSerif",Font.BOLD,20));
		intro.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel instruction = new JLabel("Please select the file you wish to load: ");
		instruction.setFont(new Font("SansSerif",Font.PLAIN,13));
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		JLabel note2 = new JLabel("Note: Make sure file is written as expected.");
		note2.setFont(new Font("SansSerif",Font.BOLD,13));
		note2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		labelPanel.add(intro);
		labelPanel.add(instruction);
		 
		labelPanel.add(note2); 
		labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.PAGE_AXIS));

				
		// where input field is located

	    
		JButton loadButton = new JButton("Load");

		
		
		// where the confirmation that it was loaded will be
		JLabel confirmation = new JLabel("");

		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// if load button is pressed, call readMowerData
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    //chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
			    chooser.setFileFilter(filter);
			    
			    int returnVal = chooser.showOpenDialog(new JFrame());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       File selectedFile = chooser.getSelectedFile();
			       System.out.println("You chose to open this file: " + selectedFile.getAbsolutePath());
			       
			       
			       String filepath = selectedFile.getPath();
					try {
						wareHouse.readMowerData(filepath);
					
						confirmation.setText(wareHouse.getOutString());
						}
						
					 catch (Exception ex) {
						 confirmation.setText("Error reading file.");
						ex.printStackTrace();
					}
			    }

			}	
		});

		
		inputPanel.add(loadButton);
		
		conPanel.setLayout(new BoxLayout(conPanel,BoxLayout.PAGE_AXIS));
		conPanel.add(confirmation);

		
		mainPanel.add(labelPanel);
		mainPanel.add(conPanel);
		mainPanel.add(inputPanel);
		//mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		
		loadFrame.add(mainPanel);
		loadFrame.pack();
		loadFrame.setVisible(true);
	
	}

	
	//View mowers in the warehouse
	public void viewWareHouseAction() {
		// if the ware house is empty, state that it is empty
		//otherwise call MowerWareHouse toString and display it.
		// do not allow to edit this text field
		//allow to scroll
		
		JFrame viewFrame = new JFrame("View WareHouse Data");
		viewFrame.setPreferredSize(new Dimension(590, 400));
		viewFrame.setResizable(false);
		
		viewFrame.pack();
		viewFrame.setVisible(true);
	}
	
	
	//Exit out of application
	public void exitWareHouseAction() {
		System.exit(0);
	}

}
