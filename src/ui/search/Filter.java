package ui.search;

import db.op.BookDAO;
import db.op.Genre;
import ui.Background;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Filter {

    private JPanel filterPanel;
    private JLabel filterTitle;
    private JRadioButton titleRadioButton;
    private JRadioButton authorRadioButton;
    private JRadioButton genreRadioButton;
    private JTextField textField;
    private JTextField textField1;
    private JLabel title;
    private JLabel author;
    private JButton searchButton1;
    private JButton searchButton2;
    private JButton searchButton3;
    private JComboBox comboBox1;
    private JLabel genre;


    public Filter(){
        comboBox1.setModel(new DefaultComboBoxModel<>(Genre.values()));

        filterPanel = new Background.imgMainPanel("/images/backgroundSearch.jpg");
        filterPanel.setLayout(new BorderLayout());

        filterTitle.setHorizontalAlignment(SwingConstants.CENTER);
        filterTitle.setForeground(Color.WHITE);
        filterTitle.setOpaque(true);
        filterTitle.setBackground(new Color(0, 0, 0, 128));

        filterPanel.add(filterTitle, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1,2));
        centerPanel.setOpaque(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setOpaque(true);
        optionsPanel.setBackground(new Color(239, 90, 237, 140));

        titleRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        genreRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        group.add(titleRadioButton);
        group.add(authorRadioButton);
        group.add(genreRadioButton);

        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(titleRadioButton);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(authorRadioButton);
        optionsPanel.add(Box.createVerticalStrut(20));
        optionsPanel.add(genreRadioButton);
        optionsPanel.add(Box.createVerticalGlue());

        centerPanel.add(optionsPanel);

        JPanel fieldsPanel = new JPanel(new CardLayout());
        fieldsPanel.setOpaque(false);

        //BY TITLE

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(90, 115, 239, 140));

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setMaximumSize(new Dimension(300, 30));
        searchButton1.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalGlue());
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(20));
        titlePanel.add(textField);
        titlePanel.add(Box.createVerticalStrut(20));
        titlePanel.add(searchButton1);
        titlePanel.add(Box.createVerticalGlue());

        //BY AUTHOR

        JPanel authorPanel = new JPanel();
        authorPanel.setOpaque(true);
        authorPanel.setBackground(new Color(90, 239, 177, 140));

        authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.Y_AXIS));
        author.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField1.setMaximumSize(new Dimension(300, 30));
        searchButton2.setAlignmentX(Component.CENTER_ALIGNMENT);

        authorPanel.add(Box.createVerticalGlue());
        authorPanel.add(author);
        authorPanel.add(Box.createVerticalStrut(20));
        authorPanel.add(textField1);
        authorPanel.add(Box.createVerticalStrut(20));
        authorPanel.add(searchButton2);
        authorPanel.add(Box.createVerticalGlue());

        //BY GENRE

        JPanel genrePanel = new JPanel();
        genrePanel.setOpaque(true);
        genrePanel.setBackground(new Color(140, 90, 239, 140));

        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.Y_AXIS));
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboBox1.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox1.setMaximumSize(new Dimension(300, 30));
        searchButton3.setAlignmentX(Component.CENTER_ALIGNMENT);

        genrePanel.add(Box.createVerticalGlue());
        genrePanel.add(genre);
        genrePanel.add(Box.createVerticalStrut(20));
        genrePanel.add(comboBox1);
        genrePanel.add(Box.createVerticalStrut(20));
        genrePanel.add(searchButton3);
        genrePanel.add(Box.createVerticalGlue());


        fieldsPanel.add(titlePanel, "TITLE");
        fieldsPanel.add(authorPanel, "AUTHOR");
        fieldsPanel.add(genrePanel, "GENRE");

        centerPanel.add(fieldsPanel);
        filterPanel.add(centerPanel, BorderLayout.CENTER);

        CardLayout cl = (CardLayout) fieldsPanel.getLayout();

        titleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(fieldsPanel, "TITLE");
            }
        });
        authorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(fieldsPanel, "AUTHOR");
            }
        });

        genreRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(fieldsPanel, "GENRE");
            }
        });

        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textField.getText();

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(filterPanel, "Please write a title");
                    return;
                }

                JTable table = BookDAO.getByTitle(title);

                JFrame resultsFrame = new JFrame("RESULTS");
                resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultsFrame.setSize(700, 400);
                resultsFrame.setLocationRelativeTo(null);

                JScrollPane scrollPane = new JScrollPane(table);
                resultsFrame.add(scrollPane, BorderLayout.CENTER);

                JButton exportButton = new JButton("Export TXT");

                exportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try (FileWriter fw = new FileWriter("BTTABLE.txt")) {
                            for (int i = 0; i < table.getRowCount(); i++) {
                                for (int j = 0; j < table.getColumnCount(); j++) {
                                    fw.write(table.getValueAt(i, j).toString() + "\t");
                                }
                                fw.write("\n");
                            }
                            JOptionPane.showMessageDialog(resultsFrame, "Table successfully exported.");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                        }
                    }
                });

                resultsFrame.add(exportButton, BorderLayout.SOUTH);

                resultsFrame.setVisible(true);
            }
        });
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = textField1.getText();

                if (author.isEmpty()) {
                    JOptionPane.showMessageDialog(filterPanel, "Please write an author");
                }

                JTable table = BookDAO.getByAuthor(author);

                JFrame resultsFrame = new JFrame("RESULTS");
                resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultsFrame.setSize(700, 400);
                resultsFrame.setLocationRelativeTo(null);

                JScrollPane scrollPane = new JScrollPane(table);
                resultsFrame.add(scrollPane, BorderLayout.CENTER);

                JButton exportButton = new JButton("Export TXT");
                exportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try (FileWriter fw = new FileWriter("BATABLE.txt")) {
                            for (int i = 0; i < table.getRowCount(); i++) {
                                for (int j = 0; j < table.getColumnCount(); j++) {
                                    fw.write(table.getValueAt(i, j).toString() + "\t");
                                }
                                fw.write("\n");
                            }
                            JOptionPane.showMessageDialog(resultsFrame, "Table successfully exported.");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                        }
                    }
                });

                resultsFrame.add(exportButton, BorderLayout.SOUTH);

                resultsFrame.setVisible(true);
            }
        });
        searchButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genre = comboBox1.getSelectedItem().toString();

                JTable table = BookDAO.getByGenre(genre);

                JFrame resultsFrame = new JFrame("RESULTS");
                resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                resultsFrame.setSize(700, 400);
                resultsFrame.setLocationRelativeTo(null);

                JScrollPane scrollPane = new JScrollPane(table);
                resultsFrame.add(scrollPane, BorderLayout.CENTER);

                JButton exportButton = new JButton("Export TXT");
                resultsFrame.add(exportButton, BorderLayout.SOUTH);
                exportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        try (FileWriter fw = new FileWriter("BGTABLE.txt")) {
                            for (int i = 0; i < table.getRowCount(); i++) {
                                for (int j = 0; j < table.getColumnCount(); j++) {
                                    fw.write(table.getValueAt(i, j).toString() + "\t");
                                }
                                fw.write("\n");
                            }
                            JOptionPane.showMessageDialog(resultsFrame, "Table successfully exported.");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
                        }
                    }
                });

                resultsFrame.setVisible(true);
            }
        });
    }

    public JPanel getFilter() {
        return filterPanel;
    }
}
