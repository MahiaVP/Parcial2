package DB.OP;
import  DB.DataConnection;
import java.sql.*;

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
        String search_book = "SELECT * FROM lent_books WHERE id = ?";
        String delete_person="DELETE FROM lent_books WHERE id=?";

        try(Connection conn=DataConnection.getConnection();
            PreparedStatement sp = conn.prepareStatement(search_book);
            PreparedStatement dp = conn.prepareStatement(delete_person);

        ){
            sp.setInt(1,id);
            ResultSet rs = sp.executeQuery();
            if(rs.next()){
                String book = rs.getString("book");
                int q=1;
                BookDAO.UpdateQuantity(book,q);
            }

            dp.setInt(1,id);
            dp.executeUpdate();
            System.out.println("Returned successfully");
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

    }

    public static void Read_lent(){
        String search_book = "SELECT * FROM lent_books";
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rl = conn.prepareStatement(search_book) ;
            ResultSet rs = rl.executeQuery()
        ){
            System.out.println("|       BOOK       |       PERSON       |       DATE        |");
            while(rs.next()){
                String book = rs.getString("book");
                String person = rs.getString("person");
                String date = rs.getString("date");
                System.out.println("|"+book+"   |"+person+"   |"+date+"   |");
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

    }
}
