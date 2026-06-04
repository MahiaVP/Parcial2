package ui.location;

import db.op.LentDAO;
import db.op.LocationDAO;
import ui.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Loc {
    private JPanel locationPanel;
    private JLabel title;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public Loc() {
        locationPanel = new Background.imgMainPanel("/images/locationBG.jpg");
        locationPanel.setLayout(new BorderLayout());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 200));

        locationPanel.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,200));
        buttonPanel.setOpaque(false);

        Dimension size = new Dimension(200, 100);
        button1.setPreferredSize(size);
        button2.setPreferredSize(size);
        button3.setPreferredSize(size);
        button4.setPreferredSize(size);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        locationPanel.add(buttonPanel, BorderLayout.CENTER);


        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = LocationDAO.getAllLocation();

                JFrame resultsFrame = new JFrame("RESULTS");
                resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultsFrame.setSize(700, 400);
                resultsFrame.setLocationRelativeTo(null);

                JScrollPane scrollPane = new JScrollPane(table);
                resultsFrame.add(scrollPane, BorderLayout.CENTER);

                JButton exportButton = new JButton("Export TXT");
                exportButton.addActionListener(ev -> {
                    try (FileWriter fw = new FileWriter("TABLE.txt")) {
                        for (int i = 0; i < table.getRowCount(); i++) {
                            for (int j = 0; j < table.getColumnCount(); j++) {
                                fw.write(table.getValueAt(i, j).toString() + "\t");
                            }
                            fw.write("\n");
                        }
                        JOptionPane.showMessageDialog(resultsFrame, "Table successfully exported.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                    }
                });

                resultsFrame.add(exportButton, BorderLayout.SOUTH);
                resultsFrame.setVisible(true);
            }
        });
    }

    public JPanel getLocationPanel() {
        return locationPanel;
    }
}
