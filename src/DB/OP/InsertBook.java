package DB.OP;
import  DB.DataConnection;
import java.sql.*;
public class InsertBook {
    public static void main(String[] args) throws SQLException {
        String add_Book="INSERT INTO Book (Book,Author,Genre,Units Available) values(?,?,?,?,?,?)";

        try (Connection conn = DataConnection.getConnection();
            PreparedStatement di = conn.prepareStatement(add_Book);
        ){
            di.setString(1,"");
            di.setString(2,"");
            di.setString(3,"");
            di.setInt(4,0);

            System.out.println("Added correctly");
        }

        catch (SQLException e)
        {
            System.err.println("Error: "+e.getMessage());
        }
    }
}
