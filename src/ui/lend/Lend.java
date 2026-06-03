package ui.lend;

import ui.Background;
import ui.search.SeeAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                SeeAllLe ui = new SeeAllLe();
                JFrame frame = new JFrame("SEE_ALL");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(ui.getSeeAllLe());
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getLend() {
        return lendPanel;
    }
}
