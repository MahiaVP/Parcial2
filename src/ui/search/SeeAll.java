package ui.search;

import ui.Background;
import javax.swing.*;
import db.op.*;
import ui.TextFileWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SeeAll {
    private JPanel seeAll;
    private JTable table;
    private JScrollPane scroll;
    private JButton extraerTXTButton;

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

        extraerTXTButton.setPreferredSize(new Dimension(200,100));
        seeAll.add(Box.createHorizontalStrut(20));
        seeAll.add(extraerTXTButton);
        extraerTXTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    TextFileWriter writer = new TextFileWriter("tabla_exportada.txt");
                    writer.openFile();

                    writer.writeToFile("|ID|    BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE    |\n");

                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < table.getColumnCount(); j++) {
                            writer.writeToFile(table.getValueAt(i, j).toString() + " |\t");
                        }
                        writer.writeToFile("\n");
                    }

                    writer.closeFile();
                    JOptionPane.showMessageDialog(seeAll, "Archivo exportado exitosamente.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(seeAll, "Error al exportar: " + ex.getMessage());
                }
            }
        });
    }

    public JPanel getSeeAll() {
        return seeAll;
    }
}
