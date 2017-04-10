package app;

import app.gui.GUI;
import app.processor.Evaluator;
import app.processor.StringValidator;
import app.utils.patterns.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class App extends Observer
{
    private Evaluator evaluator;
    private GUI gui;
    private boolean isExpressionValid;

    public App()
    {
        super();

        this.evaluator = new Evaluator();
        this.gui = new GUI(this);
        this.evaluator.registerObserver(this);
        this.isExpressionValid = true;
    }

    public GUI getGUI()
    {
        return this.gui;
    }

    public void update(HashMap<String, Object[]> stackContents)
    {
        System.out.println("===");
        System.out.print("Stack contents: ");
        this.printObjects(stackContents.get("stack"));
        System.out.print("Queue 1 contents: ");
        this.printObjects(stackContents.get("queue1"));
        System.out.print("Queue 2 contents: ");
        this.printObjects(stackContents.get("queue2"));
        System.out.print("Pseudo Array 1 contents: ");
        this.printObjects(stackContents.get("pseudoarray1"));
        System.out.print("Pseudo Array 2 contents: ");
        this.printObjects(stackContents.get("pseudoarray2"));
        System.out.print("Linked List 1 contents: ");
        this.printObjects(stackContents.get("linkedlist1"));
        System.out.print("Linked List 2 contents: ");
        this.printObjects(stackContents.get("linkedlist2"));

        String[] columnNames = {
            "Stack", "Queue 1", "Queue 2", "Pseudo Array 1", "PseudoArray 2", "Linked List 1", "Linked List 2"
        };
        DefaultTableModel dsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.populateTable(dsTableModel, stackContents);
    }

    public void alertParseError(String error)
    {
        this.isExpressionValid = false;
        JOptionPane.showMessageDialog(this.gui, error, "Calculator Error", JOptionPane.ERROR_MESSAGE);
    }

    public void startComputing(String expression)
    {
        this.evaluator.setExpression(expression);

        if (this.isExpressionValid) {
            this.gui.setOutputField(this.evaluator.compute());
        }
    }

    private void populateTable(DefaultTableModel dsTableModel, HashMap<String, Object[]> stackContents)
    {
        for ()
    }

    private void printObjects(Object[] contents)
    {
        for (int i = 0; i < contents.length; i++) {
            System.out.print(contents[i]);

            if (i < contents.length - 1) {
                System.out.print(' ');
            }
        }

        System.out.println("");
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.getGUI().setVisible(true);
        app.getGUI().setSize(500,400);
        app.getGUI().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
