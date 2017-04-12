package app.gui;

import app.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

	private JButton equalsButton;
	private JButton plusButton;
	private JButton minusButton;
	private JButton multiplyButton;
	private JButton divideButton;
	private JButton clearButton;
	private JButton deleteButton;
	private JButton offButton;
	private JButton[] numberButtons;

	private JTextField outputField;
	private JTextField postfixField;

	private JLabel postfixLabel = new JLabel("POSTFIX EXPRESSION", SwingConstants.CENTER);
	
	private JPanel numPanel;
	private JPanel mainPanel;
	private JPanel outPanel;

	private App appRef;

	public GUI (App app) {
	    super("Crazy Calculator");
	    this.appRef = app;
		numberButtons= new JButton[12];
		setLayout(new BorderLayout());
		//mainPanel.setLayout(new BorderLayout());
		Font font = new Font("Verdana", Font.PLAIN, 14);
		
		for(int i=9; i>=0;i--){
			numberButtons[i] = new JButton(Integer.toString(i));
			numberButtons[i].setPreferredSize(new Dimension(40,40));
			numberButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			numberButtons[i].setForeground(Color.WHITE);
			numberButtons[i].setBackground(Color.BLACK);
			numberButtons[i].setOpaque(true);
			numberButtons[i].addActionListener(this);
		}
		numberButtons[10] = new JButton("(");
		numberButtons[10].setHorizontalAlignment(SwingConstants.CENTER);
		numberButtons[10].setForeground(Color.WHITE);
		numberButtons[10].setBackground(Color.BLACK);
		numberButtons[10].setOpaque(true);
        numberButtons[10].addActionListener(this);
	
		numberButtons[11] = new JButton(")");
		numberButtons[11].setHorizontalAlignment(SwingConstants.CENTER);
		numberButtons[11].setForeground(Color.WHITE);
		numberButtons[11].setBackground(Color.BLACK);
		numberButtons[11].setOpaque(true);
        numberButtons[11].addActionListener(this);
		
		clearButton = new JButton("AC");
		clearButton.setForeground(Color.WHITE);
		clearButton.setBackground(Color.BLACK);
		clearButton.setOpaque(true);
		clearButton.setHorizontalAlignment(SwingConstants.CENTER);
        clearButton.addActionListener(this);
		deleteButton = new JButton("DEL");
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.BLACK);
		deleteButton.setOpaque(true);
		deleteButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteButton.addActionListener(this);
		offButton = new JButton("OFF");
		offButton.setForeground(Color.WHITE);
		offButton.setBackground(Color.BLACK);
		offButton.setOpaque(true);
		offButton.setHorizontalAlignment(SwingConstants.CENTER);
        offButton.addActionListener(this);
        
        plusButton = new JButton("+");
		plusButton.setHorizontalAlignment(SwingConstants.CENTER);
        plusButton.addActionListener(this);
		plusButton.setForeground(Color.WHITE);
		plusButton.setBackground(Color.BLACK);
		minusButton = new JButton("-");
		minusButton.setHorizontalAlignment(SwingConstants.CENTER);
        minusButton.addActionListener(this);
		minusButton.setForeground(Color.WHITE);
		minusButton.setBackground(Color.BLACK);
		multiplyButton = new JButton("*");
		multiplyButton.setHorizontalAlignment(SwingConstants.CENTER);
        multiplyButton.addActionListener(this);
		multiplyButton.setForeground(Color.WHITE);
		multiplyButton.setBackground(Color.BLACK);
		divideButton = new JButton("/");
		divideButton.setHorizontalAlignment(SwingConstants.CENTER);
        divideButton.addActionListener(this);
		divideButton.setForeground(Color.WHITE);
		divideButton.setBackground(Color.BLACK);
		equalsButton = new JButton("=");
		equalsButton.setHorizontalAlignment(SwingConstants.CENTER);
        equalsButton.addActionListener(this);
		equalsButton.setForeground(Color.WHITE);
		equalsButton.setBackground(Color.BLACK);
		
		outputField = new JTextField();
		outputField.setHorizontalAlignment(JTextField.RIGHT);
		outputField.setPreferredSize(new Dimension(295,95));
		outputField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		outputField.setOpaque(false);
		outputField.setBackground(new Color(0,0,0,0));
		outputField.setEditable(false);
		outputField.setFont(font);
		outPanel = new JPanel();
		outPanel.setLayout(new GridLayout(3,1));
		outPanel.setPreferredSize(new Dimension(50,100));
		//outPanel.setBackground(Color.BLACK);
		
		postfixField = new JTextField("POSTFIX EXPRESSION HERE");
		//postfixField.setPreferredSize(new Dimension(290,95));
		postfixField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		postfixField.setHorizontalAlignment(JTextField.RIGHT);
		postfixField.setOpaque(false);
		postfixField.setBackground(new Color(0,0,0,0));
		postfixField.setEditable(false);
		postfixField.setFont(font);
		postfixField.setVisible(false);
		postfixLabel.setVisible(false);
		outPanel.add(outputField);
		outPanel.add(postfixLabel);
		outPanel.add(postfixField);



		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(outPanel, BorderLayout.NORTH);
		
		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(5,4));
		numPanel.setBackground(Color.BLACK);
		//numPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		numPanel.add(clearButton);
		numPanel.add(deleteButton);
		numPanel.add(offButton);
		numPanel.add(divideButton);
		numPanel.add(numberButtons[7]);
		numPanel.add(numberButtons[8]);
		numPanel.add(numberButtons[9]);
		numPanel.add(multiplyButton);
		numPanel.add(numberButtons[4]);
		numPanel.add(numberButtons[5]);
		numPanel.add(numberButtons[6]);
		numPanel.add(minusButton);
		numPanel.add(numberButtons[1]);
		numPanel.add(numberButtons[2]);
		numPanel.add(numberButtons[3]);
		numPanel.add(plusButton);
		numPanel.add(numberButtons[0]);
		numPanel.add(numberButtons[10]);
		numPanel.add(numberButtons[11]);
		numPanel.add(equalsButton);
		mainPanel.add(numPanel);

		add(mainPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) 
	{
		for (int i = 0; i < 12; i++)
		{
			if (e.getSource() == numberButtons[i]) {
				outputField.setText(outputField.getText() + numberButtons[i].getText());
			}
		}
		
		if (e.getSource() == plusButton) {
            outputField.setText(outputField.getText() + plusButton.getText());
        } else if (e.getSource() == minusButton) {
            outputField.setText(outputField.getText() + minusButton.getText());
        } else if (e.getSource() == multiplyButton) {
            outputField.setText(outputField.getText() + multiplyButton.getText());
        } else if (e.getSource() == divideButton) {
            outputField.setText(outputField.getText() + divideButton.getText());
        } else if (e.getSource() == equalsButton) {
		    if (!this.outputField.getText().equals("")) {
                this.appRef.startComputing(this.outputField.getText());
                postfixLabel.setVisible(true);
                postfixField.setVisible(true);
            }
        } else if (e.getSource() == clearButton) {
            outputField.setText("");
        } else if (e.getSource() == deleteButton) {
		    if (outputField.getText().length() > 0) {
                outputField.setText(outputField.getText().substring(0, outputField.getText().length() - 1));
            }
        } else if (e.getSource() == offButton) {
		    System.exit(0);
        }
	}

	public void setOutputField(String text)
    {
        this.outputField.setText(text);
    }
    public void setPostfixField(String text) { this.postfixField.setText(text); }
}
