package DB.OP;
import  DB.DataConnection;
import java.sql.*;
import Code.Book;
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
            PreparedStatement di = conn.prepareStatement(search_title);
        ){
            di.setString(1,s);
            ResultSet rs = di.executeQuery();
            if(!rs.next()){
                System.out.println("!! Title Not Found !!");
            }else{
                while(rs.next()){
                    Book b = new Book(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("genre"),rs.getInt("units_available"));
                    title.add(b);
                }
            }
        }
        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
        return title;
    }

    public static void ReadAuthor(String s){
        String search_author = "SELECT * FROM library WHERE author ILIKE ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_author);
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            if(!rs.next()){
                System.out.println("!! Author Not Found !!");
                return;
            }

            System.out.println("|   ID  |   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                int id = rs.getInt("id");
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|  "+id + "  |  " + book + "   |   " + author + "  |  " + genre + "  |  " + u_a +"  |");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void ReadGenre(String s){
        String search_genre = "SELECT * FROM library WHERE genre = ? ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_genre);
        ){
            di.setString(1,s);
            ResultSet rs = di.executeQuery();

            System.out.println("|   ID  |   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                int id = rs.getInt("id");
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|  "+id + "  |  " + book + "   |   " + author + "  |  " + genre + "  |  " + u_a +"  |");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void LendBook(int i,String name){
        String search_id = "SELECT * FROM library WHERE id = ?";
        String lend = "UPDATE library set units_available = ? WHERE id = ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_id);
             PreparedStatement ld = conn.prepareStatement(lend);
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
            LentDAO.Insert_person(book,name);
            System.out.println("The action was performed successfully. Units available of "+book+": "+ua);
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void UpdateQuantity(String book,int q){
        String search_book = "SELECT * FROM library WHERE book = ?";
        String update = "UPDATE library SET units_available = ? WHERE book = ?";

        try(Connection conn=DataConnection.getConnection();
            PreparedStatement sb = conn.prepareStatement(search_book);
            PreparedStatement rb = conn.prepareStatement(update);
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
    public static void InsertBook(Book b){
        String add_Book="INSERT INTO Library (Book,Author,Genre,Units_Available) values(?,?,?,?)";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(add_Book);
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


}
