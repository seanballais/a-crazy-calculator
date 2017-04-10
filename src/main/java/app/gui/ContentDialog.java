package app.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Queue;

public class ContentDialog extends JDialog
{
    private Queue<DefaultTableModel> tableModels;
    private JTable dsContents;
    private Timer updateTimer;

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
        this.updateTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tableModels.isEmpty()) {
                    dsContents.setModel(tableModels.remove());
                }
            }
        });

        this.updateTimer.start();
    }

    public void addContents(DefaultTableModel contents)
    {
        this.tableModels.add(contents);
    }
}
