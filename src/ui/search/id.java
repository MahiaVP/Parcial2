package ui.search;

import db.op.BookDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class id {
    private JPanel idPanel;
    private JTextField textField1;
    private JLabel idTitle;
    private JButton searchButton;


    public id(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                int idInt = Integer.parseInt(id);

                JTable table = BookDAO.getById(idInt);

                JFrame resultsFrame = new JFrame("RESULTS");
                resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultsFrame.setSize(700, 400);
                resultsFrame.setLocationRelativeTo(null);

                JScrollPane scrollPane = new JScrollPane(table);
                resultsFrame.add(scrollPane, BorderLayout.CENTER);

                JButton exportButton = new JButton("Export TXT");
                exportButton.addActionListener(ev -> {
                    try (FileWriter fw = new FileWriter("IDTABLE.txt")) {
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                fw.write(table.getValueAt(i, j).toString() + "\t");
                            }
                            fw.write("\n");
                        }
                        JOptionPane.showMessageDialog(resultsFrame, "Table exported successfully.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                    }
                });

                resultsFrame.add(exportButton, BorderLayout.SOUTH);
                resultsFrame.setVisible(true);
            }
        });
    }

    public JPanel getIdPanel() {
        return idPanel;
    }
}
