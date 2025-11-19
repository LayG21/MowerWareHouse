import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class UpdatedGUI {
	public static JLabel greeting = new JLabel("Welcome to Moni's WareHouse!"); // filler for now
	public static void main(String[] args) {
		JFrame homeFrame = new JFrame();
		homeFrame.setSize(new Dimension(3000,700));
		
		
		//Create page for Push Reel Mower
		JDialog addFields = new JDialog(homeFrame, "Add Push Reel");
		addFields.setSize(new Dimension(1100,700));
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
		
		JLabel con = new JLabel("Place Holder");
		con.setFont(new Font("SansSerif",Font.PLAIN,16));
		con.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		conPanel.add(con);
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
		JLabel label1 = new JLabel("Mower Manufacturer:");
		label1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input1 = new JTextField();
		input1.setPreferredSize(new Dimension(200, 35));
		input1.setMaximumSize(input1.getPreferredSize());
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
		JLabel label2 = new JLabel("Mower Year(Number):");
		label2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input2 = new JTextField();
		input2.setPreferredSize(new Dimension(200, 35));
		input2.setMaximumSize(input2.getPreferredSize());
		input2.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		inputPanel.add(input2, gbc);

		// Section for third input
		JLabel label3 = new JLabel("Mower Serial Number:");
		label3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input3 = new JTextField();
		input3.setPreferredSize(new Dimension(200, 35));
		input3.setMaximumSize(input3.getPreferredSize());
		input3.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		inputPanel.add(input3, gbc);

		// Section for fourth input
		JLabel label4 = new JLabel("Walk Behind Mower Cut Width(Number):");
		label4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input4 = new JTextField();
		input4.setPreferredSize(new Dimension(200, 35));
		input4.setMaximumSize(input4.getPreferredSize());
		input4.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		inputPanel.add(input4, gbc);

		// Section for fifth input
		JLabel label5 = new JLabel("Walk Behind Mower Wheel Diameter(Number):");
		label5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input5 = new JTextField();
		input5.setPreferredSize(new Dimension(200, 35));
		input5.setMaximumSize(input5.getPreferredSize());
		input5.setEditable(true);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		inputPanel.add(label5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		inputPanel.add(input5, gbc);

		// Section for sixth input
		JLabel label6 = new JLabel("Push Reel Mower Wheels(Number):");
		label6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		JTextField input6 = new JTextField();
		input6.setPreferredSize(new Dimension(200, 35));
		input6.setMaximumSize(input6.getPreferredSize());
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

}
