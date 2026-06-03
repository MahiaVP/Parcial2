package ui.location;

import ui.Background;
import ui.lend.SeeAllLe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                SeeAllLo ui = new SeeAllLo();
                JFrame frame = new JFrame("SEE_ALL");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(ui.getSalo());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getLocationPanel() {
        return locationPanel;
    }
}
