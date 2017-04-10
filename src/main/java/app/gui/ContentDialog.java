package app.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class ContentDialog extends JDialog
{
    private Queue<DefaultTableModel> tableModels;
    private JTable dsContents;

    public ContentDialog()
    {
        this.dsContents = new JTable();
        this.tableModels = new LinkedList<>();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(dsContents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.pack();
        this.setTitle("Data Structure Contents");
    }

    public void addContents(DefaultTableModel contents)
    {
        this.tableModels.add(contents);
    }

    public void runAnimation()
    {
        // Run animation.
    }
}
