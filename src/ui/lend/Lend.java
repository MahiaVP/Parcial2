package ui.lend;

import ui.Background;

import javax.swing.*;
import java.awt.*;

public class Lend {
    private JPanel lendPanel;
    private JLabel title;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public Lend() {
        lendPanel = new Background.imgMainPanel("/images/lendBG.jpg");
        lendPanel.setLayout(new BorderLayout());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 200));

        lendPanel.add(title, BorderLayout.NORTH);
    }

    public JPanel getLend() {
        return lendPanel;
    }
}
