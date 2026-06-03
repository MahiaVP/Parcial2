package ui;

import javax.swing.*;

import ui.lend.Lend;
import ui.search.Search;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {

    private JPanel mainPanel;
    private JLabel Title;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public MainUI() {
        mainPanel = new Background.imgMainPanel("/images/animeBookshelf.jpg");
        mainPanel.setLayout(new BorderLayout());

        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setForeground(Color.WHITE);
        Title.setOpaque(true);
        Title.setBackground(new Color(0, 0, 0, 128));

        mainPanel.add(Title, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 200));
        botonesPanel.setOpaque(false);

        Dimension size = new Dimension(150, 100);
        button1.setPreferredSize(size);
        button2.setPreferredSize(size);
        button3.setPreferredSize(size);

        botonesPanel.add(button1);
        botonesPanel.add(button2);
        botonesPanel.add(button3);

        mainPanel.add(botonesPanel, BorderLayout.CENTER);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search ui = new Search();
                JFrame frame = new JFrame("LIBRARY_SYSTEM_SEARCH");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(ui.getPanel());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lend ui = new Lend();
                JFrame frame = new JFrame("LIBRARY_SYSTEM_SEARCH");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(ui.getLend());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
