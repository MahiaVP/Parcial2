package DB.OP;
import  DB.DataConnection;
import java.sql.*;
import Code.Book;
public class BookDAO {
    public static void InsertBook(Book b) throws SQLException {
        String add_Book="INSERT INTO Book (Book,Author,Genre,Units_Available) values(?,?,?,?,?,?)";

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
}
