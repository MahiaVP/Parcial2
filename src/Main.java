import code.Book;
import code.LentBook;
import code.Location;
import db.op.BookDAO;
import db.op.Genre;
import db.op.LentDAO;
import db.op.LocationDAO;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import ui.MainUI;
import ui.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ui = new MainUI();
            JFrame frame = new JFrame("LIBRARY_SYSTEM");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(ui.getMainPanel());
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
