package app.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);

                while (!tableModels.isEmpty()) {
                    dsContents.setModel(tableModels.remove());
                }
            }
        });
    }

    public void addContents(DefaultTableModel contents)
    {
        for (int i = 0; i < 50; i++) {
            this.tableModels.add(contents);
        }
    }
}
