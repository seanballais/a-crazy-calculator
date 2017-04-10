package app;

import app.gui.GUI;
import app.processor.Evaluator;
import app.processor.StringValidator;
import app.utils.patterns.Observer;

import javax.swing.*;
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
        this.isExpressionValid = true;
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
