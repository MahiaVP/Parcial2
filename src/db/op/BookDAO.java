package db.op;
import code.LentBook;
import db.DataConnection;
import java.sql.*;
import code.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static JTable getAllBooks(){
        String read_books = "SELECT * FROM library ORDER BY id ASC";

        DefaultTableModel book = new DefaultTableModel();
        book.addColumn("ID");
        book.addColumn("Book");
        book.addColumn("Author");
        book.addColumn("Genre");
        book.addColumn("Units Available");

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rb = conn.prepareStatement(read_books);
            ResultSet rs = rb.executeQuery()
        ){
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("units_available")
                };
                book.addRow(row);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return new JTable(book);
    }

    public static JTable getByTitle(String s){
        String search_title = "SELECT * FROM library WHERE book ILIKE ? ORDER BY id ASC";
        DefaultTableModel book = new DefaultTableModel();
        book.addColumn("ID");
        book.addColumn("Book");
        book.addColumn("Author");
        book.addColumn("Genre");
        book.addColumn("Units Available");

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(search_title)
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("units_available")
                };
                book.addRow(row);
            }
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return new JTable(book);
    }

    public static JTable getByAuthor(String s){
        String search_author = "SELECT * FROM library WHERE author ILIKE ?";

        DefaultTableModel author= new DefaultTableModel();
        author.addColumn("ID");
        author.addColumn("Book");
        author.addColumn("Author");
        author.addColumn("Genre");
        author.addColumn("Units Available");

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_author)
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("units_available")
                };
                author.addRow(row);
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }

        return new JTable(author);
    }

    public static JTable getByGenre(String s){
        String search_genre = "SELECT * FROM library WHERE genre = ? ORDER BY id ASC";

        DefaultTableModel genre = new DefaultTableModel();
        genre.addColumn("ID");
        genre.addColumn("Book");
        genre.addColumn("Author");
        genre.addColumn("Genre");
        genre.addColumn("Units Available");

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_genre)
        ){
            di.setString(1,s);
            ResultSet rs = di.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("units_available")
                };
                genre.addRow(row);
            }
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return new JTable (genre);
    }

    public static void insertBook(Book b){
        String add_Book="INSERT INTO Library (Book,Author,Genre,Units_Available) values(?,?,?,?)";

        JFrame resultsFrame = new JFrame("RESULTS");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.setSize(700, 400);
        resultsFrame.setLocationRelativeTo(null);

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(add_Book)
        ){

            di.setString(1, b.getTitle());
            di.setString(2, b.getAuthor());
            di.setString(3,b.getGenre());
            di.setInt(4,b.getAvailability());
            di.executeUpdate();


            JOptionPane.showMessageDialog(resultsFrame, "Book added correctly.");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(resultsFrame, "Error: " + e.getMessage());
            resultsFrame.setVisible(true);
        }
    }

    public static JTable getById(int id){
        String add_Book="SELECT * FROM Library WHERE id=?";
        DefaultTableModel idT = new DefaultTableModel();
        idT.addColumn("ID");
        idT.addColumn("Book");
        idT.addColumn("Author");
        idT.addColumn("Genre");
        idT.addColumn("Units Available");

        Book b =null;
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gi = conn.prepareStatement(add_Book)
        ) {
            gi.setInt(1, id);
            ResultSet rs = gi.executeQuery();
            if (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("book"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("units_available")
                };
                idT.addRow(row);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return new  JTable(idT);
    }

    public static void lendBook(int i,String name){
        String search_id = "SELECT * FROM library WHERE id = ?";
        String lend = "UPDATE library set units_available = ? WHERE id = ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_id);
             PreparedStatement ld = conn.prepareStatement(lend)
        ){
            di.setInt(1,i);
            ResultSet rs = di.executeQuery();
            if (!rs.next()) {
                System.out.println("There is no book with that id");
                return;
            }

            int ua = rs.getInt("units_available");

            if(ua==0){
                System.out.println("There are no units available");
                return;
            }
            ua--;

            ld.setInt(1,ua);
            ld.setInt(2,i);
            ld.executeUpdate();
            String book=rs.getString("book");
            LentBook lb = new LentBook(book,name);
            LentDAO.insertPerson(lb);
            System.out.println("The action was performed successfully. Units available of "+book+": "+ua);
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void updateQuantity(String book,int q){
        String search_book = "SELECT * FROM library WHERE book = ?";
        String update = "UPDATE library SET units_available = ? WHERE book = ?";

        try(Connection conn=DataConnection.getConnection();
            PreparedStatement sb = conn.prepareStatement(search_book);
            PreparedStatement rb = conn.prepareStatement(update)
        ){
            sb.setString(1,book);
            ResultSet rs = sb.executeQuery();
            int ua=0;
            if(rs.next()){
                ua = rs.getInt("units_available")+q;
                rb.setInt(1,ua);
                rb.setString(2,book);
                rb.executeUpdate();
            }
            System.out.println("The action was performed successfully. Units of "+book+" available: "+ua);

        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
