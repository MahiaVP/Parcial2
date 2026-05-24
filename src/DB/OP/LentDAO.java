package DB.OP;
import Code.Lent_Book;
import  DB.DataConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LentDAO {
    public static void insert_person(Lent_Book lb){
        String add_person="INSERT INTO lent_books (book,person) VALUES (?,?)";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement ip = conn.prepareStatement(add_person)){
            ip.setString(1,lb.getBook());
            ip.setString(2, lb.getPerson());
            ip.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }

    }

    public static List<Lent_Book> returnBook(String name){
        List<Lent_Book> lentb = new ArrayList<>();
        String search_person = "SELECT * FROM lent_books WHERE person ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement sp = conn.prepareStatement(search_person);
        ){
            sp.setString(1,"%"+name+"%");
            ResultSet rs = sp.executeQuery();
            System.out.println("|   ID  |   NAME    |   BOOK    |   DATE    |");
            while(rs.next()){
                Lent_Book lb = new Lent_Book(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

        return lentb;
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

    public static List<Lent_Book> readAll(){
        List <Lent_Book> lentb = new ArrayList<>();

        String search_book = "SELECT * FROM lent_books";
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rl = conn.prepareStatement(search_book) ;
            ResultSet rs = rl.executeQuery()
        ){
            while(rs.next()){
                Lent_Book lb = new Lent_Book(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return lentb;

    }
}
