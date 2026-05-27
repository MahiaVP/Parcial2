package db.op;
import code.LentBook;
import db.DataConnection;
import java.sql.*;
import code.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();

        String read_books = "SELECT * FROM library ORDER BY id ASC";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rb = conn.prepareStatement(read_books);
            ResultSet rs = rb.executeQuery()
        ){
            while(rs.next()){
                Book b = new Book(rs.getInt("id"),rs.getString("book"),rs.getString("author"), rs.getString("genre"), rs.getInt("units_available"));
                books.add(b);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return books;
    }

    public static List<Book> getByTitle(String s){
        List<Book> title = new ArrayList<>();

        String search_title = "SELECT * FROM library WHERE book ILIKE ? ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(search_title)
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();
            while(rs.next()){
                Book b = new Book(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("genre"),rs.getInt("units_available"));
                title.add(b);
            }
            if(title.isEmpty()){
                System.out.println("!! TITLE NOT FOUND !!");
            }
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return title;
    }

    public static List <Book> getByAuthor(String s){
        List<Book> author = new ArrayList<>();

        String search_author = "SELECT * FROM library WHERE author ILIKE ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_author)
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            while(rs.next()){
                Book b = new Book(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("genre"),rs.getInt("units_available"));
                author.add(b);
            }
            if(author.isEmpty()){
                System.out.println("!! AUTHOR NOT FOUND !!");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }

        return author;
    }

    public static List<Book> getByGenre(String s){
        List<Book> genre = new ArrayList<>();

        String search_genre = "SELECT * FROM library WHERE genre = ? ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_genre)
        ){
            di.setString(1,s);
            ResultSet rs = di.executeQuery();

            while(rs.next()){
                Book b = new Book(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("genre"),rs.getInt("units_available"));
                genre.add(b);
            }
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return genre;
    }

    public static void insertBook(Book b){
        String add_Book="INSERT INTO Library (Book,Author,Genre,Units_Available) values(?,?,?,?)";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(add_Book)
        ){
            di.setString(1, b.getTitle());
            di.setString(2, b.getAuthor());
            di.setString(3,b.getGenre());
            di.setInt(4,b.getAvailability());
            di.executeUpdate();

            System.out.println("Added correctly");
        }
        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static Book getById(int id){
        String add_Book="SELECT * FROM Library WHERE id=?";

        Book b =null;
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gi = conn.prepareStatement(add_Book)
        ) {
            gi.setInt(1, id);
            ResultSet rs = gi.executeQuery();
            if (rs.next()) {
                b = new Book(rs.getInt("id"), rs.getString("book"), rs.getString("author"), rs.getString("genre"), rs.getInt("units_available"));
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return b;
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
