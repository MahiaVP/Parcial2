package ui.location;

import db.op.LocationDAO;
import ui.Background;

import javax.swing.*;
import java.awt.*;

public class SeeAllLo {
    private JPanel salo;
    private JTable table;
    private JScrollPane scroll;

    public SeeAllLo() {
        salo = new Background.imgMainPanel("/images/locationBG.jpg");
        table = LocationDAO.getAllLocation();
        salo.setLayout(new GridBagLayout());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFont(new Font("Consolas", Font.PLAIN, 24));
        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        table.getColumnModel().getColumn(1).setPreferredWidth(700); //BOOK
        table.getColumnModel().getColumn(2).setPreferredWidth(300); //AUTHOR
        table.getColumnModel().getColumn(3).setPreferredWidth(80); //SECTION
        table.getColumnModel().getColumn(4).setPreferredWidth(80); //ROW

        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1260, 800));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        salo.add(scroll, gbc);
    }

    public JPanel getSalo() {
        return salo;
    }
}
