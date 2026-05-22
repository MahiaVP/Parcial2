package DB.OP;
import  DB.DataConnection;
import java.sql.*;
import Code.Book;

public class LentDAO {
    public static void Insert_person(String book,String name){
        String add_person="INSERT INTO lent_books (book,person) VALUES (?,?)";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement ip = conn.prepareStatement(add_person)){
            ip.setString(1,book);
            ip.setString(2,name);
            ip.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void ReturnBook(String name){
        String search_person = "SELECT * FROM lent_books WHERE person ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement sp = conn.prepareStatement(search_person);
        ){
            sp.setString(1,"%"+name+"%");
            ResultSet rs = sp.executeQuery();
            System.out.println("|   ID  |   NAME    |   BOOK    |   DATE    |");
            while(rs.next()){
                int id = rs.getInt("id");
                String pname = rs.getString("person");
                String book = rs.getString("book");
                String date = rs.getString("date");
                System.out.println("|   "+id+"  |   "+pname+"   |   "+book+"    |   "+date+"    |   ");
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void Delete_person(int id){
        String delete_person="DELETE FROM lent_books WHERE id=?";

        try(Connection conn=DataConnection.getConnection();
            PreparedStatement dp = conn.prepareStatement(delete_person);
        ){
            dp.setInt(1,id);
            dp.executeUpdate();
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

    }
}
