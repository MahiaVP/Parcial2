package ui.lend;

import ui.Background;
import db.op.*;
import javax.swing.*;
import java.awt.*;

public class SeeAllLe {
    private JPanel seeAllLe;
    private JTable table;
    private JScrollPane scroll;

    public SeeAllLe() {
        seeAllLe = new Background.imgMainPanel("/images/lendBG.jpg");
        table=LentDAO.readAll();

        seeAllLe.setLayout(new GridBagLayout());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFont(new Font("Consolas", Font.PLAIN, 24));
        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        table.getColumnModel().getColumn(1).setPreferredWidth(700); //BOOK
        table.getColumnModel().getColumn(2).setPreferredWidth(300); //PERSON
        table.getColumnModel().getColumn(3).setPreferredWidth(300); //DATE

        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1400, 800));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        seeAllLe.add(scroll, gbc);

    }

    public JPanel getSeeAllLe() {
        return seeAllLe;
    }
}
