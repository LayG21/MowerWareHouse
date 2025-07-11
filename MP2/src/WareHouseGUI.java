import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WareHouseGUI {
	
	public MowerWareHouse wareHouse;
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
		//addMower.addActionListener(e -> addMowerAction());
		saveFile.addActionListener(e -> saveWareHouseAction());
		loadFile.addActionListener(e-> loadWareHouseAction());
		//viewWareHouse.addActionListener(e -> viewWareHouseAction());
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
	
	//Add mower to warehouse
	public void addMowerAction() {
		// TODO Auto-generated method stub
		// will have to ask what type of mower is added and then given fields of input for those 
		// may have to check if warehouse even has a name, so they will have to add after loading or i just leave the warehouse as None
		// if no warehouse name, ask for one, otherwise ask what type of mower is being added, then provide input fields for that mower type
		
	}
	
	//Save warehouse data to a file
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
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		JPanel info = new JPanel();											//Panel for page information
		JPanel conPanel = new JPanel();										//Panel for saving confirmation
		JPanel inputPanel = new JPanel();									//Panel for file input
		JLabel instructions = new JLabel();
		saveFrame.add(mainPanel);
		saveFrame.pack();
		saveFrame.setVisible(true);

	}

	//Load mower data from file.
	// type in input file exactly how it is named
	// tell user if it exists or not
	// it is assumed it will follow the input format as specified
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
		
		
		JLabel instruction = new JLabel("Please type in your file: ");
		instruction.setFont(new Font("SansSerif",Font.PLAIN,13));
		instruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel note1 = new JLabel("Note: Please type in file name the same way it is labeled.");
		note1.setFont(new Font("SansSerif",Font.BOLD,13));
		note1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel note2 = new JLabel("Note: Make sure file is written as expected.");
		note2.setFont(new Font("SansSerif",Font.BOLD,13));
		note2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		labelPanel.add(intro);
		labelPanel.add(instruction);
		labelPanel.add(note1);  
		labelPanel.add(note2); 
		labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.PAGE_AXIS));

				
		// where input field is located
		JTextField input = new JTextField();
		JButton loadButton = new JButton("Load");
		input.setPreferredSize(new Dimension(200, 20));
		input.setEditable(true);
		
		
		// where the confirmation that it was loaded will be
		JLabel confirmation = new JLabel("");

		confirmation.setAlignmentX(Component.CENTER_ALIGNMENT);
		// if load button is pressed, call readMowerData
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(input.getText().isEmpty() || input.getText().isBlank()) {
					confirmation.setText("Please type in a valid file name.");
				}
				else {
					wareHouse.readMowerData(input.getText());
					confirmation.setText(wareHouse.getOutString());
				}

			}
			
			
		});

		
		inputPanel.add(input);
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
	}
	
	
	//Exit out of application
	public void exitWareHouseAction() {
		System.exit(0);
	}

}
