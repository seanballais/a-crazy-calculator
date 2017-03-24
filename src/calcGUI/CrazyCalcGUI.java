package calcGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CrazyCalcGUI extends JFrame {

	private JLabel equals;
	private JLabel plus;
	private JLabel minus;
	private JLabel asterisk;
	private JLabel frwrdslash;
	private JLabel openP;
	private JLabel closeP;
	private JLabel clear;

	private JTextField output;

	private JLabel[] numberButtons;

	private JPanel numPanel;
	private JPanel operationPanel;
	private JPanel mainPanel;
	private JPanel outPanel;

	public CrazyCalcGUI () {

		numberButtons= new JLabel[10];

		for(int i=9; i>=0;i--){
			numberButtons[i] = new JLabel(Integer.toString(i));
		}

		output= new JTextField();
		output.setPreferredSize(new Dimension(160,20));
		outPanel = new JPanel();
		outPanel.setPreferredSize(new Dimension(160,20));
		outPanel.add(output);

		mainPanel = new JPanel();
		mainPanel.add(outPanel);

		numPanel = new JPanel();
		numPanel.setLayout(new GridLayout(3,3));
		for(int p=9;p>=0;p--){
			numPanel.add(numberButtons[p]);
		}

		add(mainPanel);
		add(outPanel);
		add(numPanel);


	}
}
