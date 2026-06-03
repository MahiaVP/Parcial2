package ui.location;

import ui.Background;

import javax.swing.*;
import java.awt.*;

public class Loc {
    private JPanel locationPanel;
    private JLabel title;

    public Loc() {
        locationPanel = new Background.imgMainPanel("/images/locationBG.jpg");
        locationPanel.setLayout(new BorderLayout());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(0, 0, 0, 200));

        locationPanel.add(title, BorderLayout.NORTH);
    }

    public JPanel getLocationPanel() {
        return locationPanel;
    }
}
