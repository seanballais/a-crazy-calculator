package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calcGUI extends JFrame implements ActionListener {

	private JButton equals;
	private JButton plus;
	private JButton minus;
	private JButton asterisk;
	private JButton divide;
	private JButton clear;
	private JButton del;
	private JButton off;

	private JTextArea output;

	private JButton[] numberButtons;

	private JPanel numPanel;
	private JPanel operationPanel;
	private JPanel mainPanel;
	private JPanel outPanel;
	private JPanel table;

	public calcGUI () {

		numberButtons= new JButton[12];
		setLayout(new BorderLayout());
		//mainPanel.setLayout(new BorderLayout());

		for(int i=9; i>=0;i--){
			numberButtons[i] = new JButton(Integer.toString(i));
			numberButtons[i].setPreferredSize(new Dimension(40,40));
			numberButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			numberButtons[i].setForeground(Color.WHITE);
			numberButtons[i].setBackground(Color.BLUE);
			numberButtons[i].setOpaque(true);
			numberButtons[i].addActionListener(this);
		}
		numberButtons[10] = new JButton("(");
		numberButtons[10].setHorizontalAlignment(SwingConstants.CENTER);
		numberButtons[10].setForeground(Color.WHITE);
		numberButtons[10].setBackground(Color.BLUE);
		numberButtons[10].setOpaque(true);
	
		numberButtons[11] = new JButton(")");
		numberButtons[11].setHorizontalAlignment(SwingConstants.CENTER);
		numberButtons[11].setForeground(Color.WHITE);
		numberButtons[11].setBackground(Color.BLUE);
		numberButtons[11].setOpaque(true);
		
		clear = new JButton("AC");
		clear.setForeground(Color.WHITE);
		clear.setBackground(Color.BLUE);
		clear.setOpaque(true);
		clear.setHorizontalAlignment(SwingConstants.CENTER);
		del = new JButton("DEL");
		del.setForeground(Color.WHITE);
		del.setBackground(Color.BLUE);
		del.setOpaque(true);
		del.setHorizontalAlignment(SwingConstants.CENTER);
		off= new JButton("OFF");
		off.setForeground(Color.WHITE);
		off.setBackground(Color.BLUE);
		off.setOpaque(true);
		off.setHorizontalAlignment(SwingConstants.CENTER);
		
		output= new JTextArea(500,20);
		output.setEditable(false);
		output.setLayout(new FlowLayout());
		output.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//output.setPreferredSize(new Dimension(160,20));
		outPanel = new JPanel();
		outPanel.setPreferredSize(new Dimension(500,20));
		//outPanel.setBackground(Color.BLACK);
		outPanel.add(output);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(outPanel, BorderLayout.NORTH);
		
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(5,3));
		numPanel.setBackground(Color.BLACK);
		//numPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		numPanel.add(clear);
		numPanel.add(del);
		numPanel.add(off);
		numPanel.add(numberButtons[7]);
		numPanel.add(numberButtons[8]);
		numPanel.add(numberButtons[9]);
		numPanel.add(numberButtons[4]);
		numPanel.add(numberButtons[5]);
		numPanel.add(numberButtons[6]);
		numPanel.add(numberButtons[1]);
		numPanel.add(numberButtons[2]);
		numPanel.add(numberButtons[3]);
		numPanel.add(numberButtons[0]);
		mainPanel.add(numPanel);
		
		numPanel.add(numberButtons[10]);
		mainPanel.add(numPanel);
		numPanel.add(numberButtons[11]);
		mainPanel.add(numPanel);
		
		plus = new JButton("+");
		plus.setHorizontalAlignment(SwingConstants.CENTER);
		//plus.setForeground(Color.WHITE);
		//plus.setBackground(Color.BLUE);
		minus = new JButton("-");
		minus.setHorizontalAlignment(SwingConstants.CENTER);
		//minus.setForeground(Color.WHITE);
		//minus.setBackground(Color.BLUE);
		asterisk = new JButton("*");
		asterisk.setHorizontalAlignment(SwingConstants.CENTER);
		//asterisk.setForeground(Color.WHITE);
		//asterisk.setBackground(Color.BLUE);
		divide = new JButton("/");
		divide.setHorizontalAlignment(SwingConstants.CENTER);
		//divide.setForeground(Color.WHITE);
		//divide.setBackground(Color.BLUE);
		equals = new JButton("=");
		equals.setHorizontalAlignment(SwingConstants.CENTER);
		//equals.setForeground(Color.WHITE);
		//equals.setBackground(Color.BLUE);
		operationPanel = new JPanel();
		operationPanel.setLayout(new GridLayout (5,1));
		operationPanel.add(plus);
		operationPanel.add(minus);
		operationPanel.add(asterisk);
		operationPanel.add(divide);
		operationPanel.add(equals);
		//operationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//mainPanel.add(operationPanel, BorderLayout.EAST);
		
		add(operationPanel, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
		
		
		setVisible(true);
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args)
	{
		new calcGUI();
	}

	public void actionPerformed(ActionEvent e) 
	{
		for(int i=0; i<12; i++)
		{
			if(e.getSource() == numberButtons[i])
				output.setText(output.getText() + numberButtons[i].getText());
		}
		
		if(e.getSource() == plus)
			output.setText(output.getText() + plus.getText());
		if(e.getSource() == minus)
			output.setText(output.getText() + minus.getText());
		if(e.getSource() == asterisk)
			output.setText(output.getText() + asterisk.getText());
		if(e.getSource() == divide)
			output.setText(output.getText() + divide.getText());
		if(e.getSource() == clear)
			output.setText("");
	}
}
