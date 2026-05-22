package DB.OP;
import  DB.DataConnection;
import java.sql.*;
import Code.Book;

public class BookDAO {
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

    public static void ReadBook(){
        String rd = "SELECT * FROM library ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(rd);
        ){
            System.out.println("|   ID  |   BOOK    |   AUTHOR  |   GENRE   |   UNITS AVAILABLE |");
            while(rs.next()){
                int id = rs.getInt("id");
                String book = rs.getString("book");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                int u_a = rs.getInt("units_available");

                System.out.println("|  "+id + "  |  " + book + "   |   " + author + "  |  " + genre + "  |  " + u_a +"  |");
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void ReadTitle(String s){
        String search_title = "SELECT * FROM library WHERE book ILIKE ? ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(search_title);
        ){
            di.setString(1,"%"+s+"%");
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

    public static void ReadAuthor(String s){
        String search_author = "SELECT * FROM library WHERE author ILIKE ? ORDER BY id ASC";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement di = conn.prepareStatement(search_author);
        ){
            di.setString(1,"%"+s+"%");
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

            PreparedStatement ld = conn.prepareStatement(lend);
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


}
