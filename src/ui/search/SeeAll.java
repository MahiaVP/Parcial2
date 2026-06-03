package ui.search;

import ui.Background;
import javax.swing.*;
import db.op.*;

import java.awt.*;

public class SeeAll {
    private JPanel seeAll;
    private JTable table;
    private JScrollPane scroll;

    public SeeAll() {
        seeAll = new Background.imgMainPanel("/images/backgroundSearch.jpg");
        table = BookDAO.getAllBooks();
        seeAll.setLayout(new GridBagLayout());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFont(new Font("Consolas", Font.PLAIN, 24));
        table.setRowHeight(30);

        table.getColumnModel().getColumn(0).setPreferredWidth(80); //ID
        table.getColumnModel().getColumn(1).setPreferredWidth(700); //BOOK
        table.getColumnModel().getColumn(2).setPreferredWidth(300); //AUTHOR
        table.getColumnModel().getColumn(3).setPreferredWidth(120); //GENDER
        table.getColumnModel().getColumn(4).setPreferredWidth(80); //UNITS

        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1300, 800));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        seeAll.add(scroll, gbc);
    }

    public JPanel getSeeAll() {
        return seeAll;
    }
}
