package DB.OP;
import  DB.DataConnection;
import java.sql.*;
import Code.Book;
public class BookDAO {
    public static void InsertBook(Book b) throws SQLException {
        String add_Book="INSERT INTO Library (Book,Author,Genre,Units_Available) values(?,?,?,?,?,?)";

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(add_Book);
        ){
            di.setString(1, b.getTitle());
            di.setString(2, b.getAuthor());
            di.setString(3,b.getGenre());
            di.setInt(4,b.getAvailability());

            System.out.println("Added correctly");
        }

        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void ReadBook(){
        String rd = "SELECT * FROM library";

        try (Connection conn = DataConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(rd);
        ){
            System.out.println("|   ID  |   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                int id = rs.getInt("id");
                String book = rs.getString("Book");
                String author = rs.getString("Author");
                String genre = rs.getString("Genre");
                int u_a = rs.getInt("Units_Available");

                System.out.println("|  "+id + "  |  " + book + "   |   " + author + "  |  " + genre + "  |  " + u_a +"  |");
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void ReadTitle(String s){
        String search_title = "SELECT * FROM library WHERE book ILIKE ?";

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(search_title);
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            System.out.println("|   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|"+book+"|"+author+"|"+genre+"|"+u_a+"|");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void ReadAuthor(String s){
        String search_author = "SELECT * FROM library WHERE author ILIKE ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_author);
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            System.out.println("|   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|"+book+"|"+author+"|"+genre+"|"+u_a+"|");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void ReadGenre(String s){
        String search_genre = "SELECT * FROM library WHERE genre ILIKE ?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_genre);
        ){
            di.setString(1,"%"+s+"%");
            ResultSet rs = di.executeQuery();

            System.out.println("|   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|"+book+"|"+author+"|"+genre+"|"+u_a+"|");
            }
        }

        catch(Exception e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
