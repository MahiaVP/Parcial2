package ui.search;

import code.Book;
import db.op.BookDAO;
import db.op.Genre;
import ui.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBook {
    private JPanel addBookPanel;
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox comboBox1;
    private JTextField unitsField;
    private JButton confirmButton;
    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel units;

    public AddBook() {
        comboBox1.setModel(new DefaultComboBoxModel<>(Genre.values()));
        addBookPanel = new Background.imgMainPanel("/images/backgroundSearch.jpg");
        addBookPanel.setLayout(new FlowLayout());
        addBookPanel.setOpaque(true);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(true);
        optionsPanel.setBackground(new Color(239, 90, 237, 140));
        optionsPanel.setPreferredSize(new Dimension(600,500));

        titleField.setMaximumSize(new Dimension(400, 30));
        authorField.setMaximumSize(new Dimension(400, 30));
        comboBox1.setMaximumSize(new Dimension(400, 30));
        unitsField.setMaximumSize(new Dimension(400, 30));

        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        unitsField.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox1.setAlignmentX(Component.CENTER_ALIGNMENT);

        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(title);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(titleField);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(author);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(authorField);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(genre);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(comboBox1);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(units);
        optionsPanel.add(unitsField);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(confirmButton);
        optionsPanel.add(Box.createVerticalGlue());

        addBookPanel.add(optionsPanel,BorderLayout.CENTER);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = comboBox1.getSelectedItem().toString();
                String units = unitsField.getText();
                int unit = Integer.parseInt(units);

                Book b = new Book(title,author,genre,unit);
                BookDAO.insertBook(b);
            }
        });
    }

    public JPanel getPanel() {
        return addBookPanel;
    }
}
