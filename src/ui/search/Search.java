package ui.search;

import db.op.BookDAO;
import ui.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Search {
    private JPanel searchPanel;
    private JLabel title;
    private JButton button2;
    private JButton button1;
    private JButton button3;
    private JButton button4;

    public Search() {
        searchPanel = new Background.imgMainPanel("/images/backgroundSearch.jpg");
        searchPanel.setLayout(new BorderLayout());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 128));

        searchPanel.add(title, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 200));
        botonesPanel.setOpaque(false);

        Dimension size = new Dimension(200, 100);
        button1.setPreferredSize(size);
        button2.setPreferredSize(size);
        button3.setPreferredSize(size);
        button4.setPreferredSize(size);

        botonesPanel.add(button1);
        botonesPanel.add(button2);
        botonesPanel.add(button3);
        botonesPanel.add(button4);

        searchPanel.add(botonesPanel, BorderLayout.CENTER);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = BookDAO.getAllBooks();

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
                        JOptionPane.showMessageDialog(resultsFrame, "Table exported successfully.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                    }
                });

                resultsFrame.add(exportButton, BorderLayout.SOUTH);
                resultsFrame.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filter ui = new Filter();
                JFrame frame = new JFrame("FILTER");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(ui.getFilter());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

    public JPanel getPanel() {
        return searchPanel;
    }
}
