package DB.OP;
import DB.DataConnection;
import java.sql.*;

public class ReadBook {
    public static void main(String[] args) {
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
}
