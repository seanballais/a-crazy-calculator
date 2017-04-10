package app;

import app.gui.ContentDialog;
import app.gui.GUI;
import app.processor.Evaluator;
import app.utils.patterns.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends Observer
{
    private Evaluator evaluator;
    private GUI gui;
    private boolean isExpressionValid;
    private ContentDialog contentDialog;

    public App()
    {
        super();

        this.contentDialog = new ContentDialog();
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
        String[] columnNames = {
            "Stack", "Queue 1", "Queue 2", "Pseudo Array 1", "PseudoArray 2", "Linked List 1", "Linked List 2"
        };
        DefaultTableModel dsTableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.populateTable(dsTableModel, stackContents);
        this.contentDialog.addContents(dsTableModel);
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
            this.contentDialog.setVisible(true);
        }

        this.isExpressionValid = true;
    }

    private void populateTable(DefaultTableModel dsTableModel, HashMap<String, Object[]> stackContents)
    {
        Object[] stackItems = stackContents.get("stack");
        Object[] queue1Items = stackContents.get("queue1");
        Object[] queue2Items = stackContents.get("queue2");
        Object[] pseudoarray1Items = stackContents.get("pseudoarray1");
        Object[] pseudoarray2Items = stackContents.get("pseudoarray2");
        Object[] linkedlist1Items = stackContents.get("linkedlist1");
        Object[] linkedlist2Items = stackContents.get("linkedlist2");

        int numRows = Math.max(
            Math.max(
                stackItems.length,
                Math.max(
                    queue1Items.length,
                    queue2Items.length
                )
            ),
            Math.max(
                Math.max(
                    pseudoarray1Items.length,
                    pseudoarray2Items.length
                ),
                Math.max(
                    linkedlist1Items.length,
                    linkedlist2Items.length
                )
            )
        );

        ArrayList<Object> rowData = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i < stackItems.length) {
                rowData.add(stackItems[i]);
            } else {
                rowData.add("");
            }

            if (i < queue1Items.length) {
                rowData.add(queue1Items[i]);
            } else {
                rowData.add("");
            }

            if (i < queue2Items.length) {
                rowData.add(queue2Items[i]);
            } else {
                rowData.add("");
            }

            if (i < pseudoarray1Items.length) {
                rowData.add(pseudoarray1Items[i]);
            } else {
                rowData.add("");
            }

            if (i < pseudoarray2Items.length) {
                rowData.add(pseudoarray2Items[i]);
            } else {
                rowData.add("");
            }

            if (i < linkedlist1Items.length) {
                rowData.add(linkedlist1Items[i]);
            } else {
                rowData.add("");
            }

            if (i < linkedlist2Items.length) {
                rowData.add(linkedlist2Items[i]);
            } else {
                rowData.add("");
            }

            dsTableModel.addRow(rowData.toArray());
            rowData.clear();
        }
    }

    public static void main(String[] args)
    {
        App app = new App();
        app.getGUI().setVisible(true);
        app.getGUI().setSize(300,400);
        app.getGUI().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.getGUI().setResizable(false);
        app.getGUI().setLocationRelativeTo(null);
    }
}
