package ui.lend;

import db.op.BookDAO;
import db.op.LentDAO;
import ui.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Lend {
    private JPanel lendPanel;
    private JLabel title;
    private JButton returnButton;
    private JButton seeAllButton;
    private JButton lendButton;
    private JButton filterByCategoryButton;
    private JButton searchByIDButton;

    public Lend() {
        lendPanel = new Background.imgMainPanel("/images/lendBG.jpg");
        lendPanel.setLayout(new BorderLayout());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 200));

        lendPanel.add(title, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 200));
        botonesPanel.setOpaque(false);

        Dimension size = new Dimension(200, 100);
        lendButton.setPreferredSize(size);
        returnButton.setPreferredSize(size);
        seeAllButton.setPreferredSize(size);
        filterByCategoryButton.setPreferredSize(size);
        searchByIDButton.setPreferredSize(size);

        botonesPanel.add(lendButton);
        botonesPanel.add(returnButton);
        botonesPanel.add(seeAllButton);
        botonesPanel.add(filterByCategoryButton);
        botonesPanel.add(searchByIDButton);

        lendPanel.add(botonesPanel, BorderLayout.CENTER);

        seeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = LentDAO.readAll();

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

    public JPanel getLend() {
        return lendPanel;
    }
}
