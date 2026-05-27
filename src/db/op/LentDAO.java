package db.op;
import code.LentBook;
import  db.DataConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LentDAO {
    public static void insertPerson(LentBook lb){
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

    public static List<LentBook> returnBook(String name){
        List<LentBook> lentb = new ArrayList<>();
        String search_person = "SELECT * FROM lent_books WHERE person ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement sp = conn.prepareStatement(search_person)
        ){
            sp.setString(1,"%"+name+"%");
            ResultSet rs = sp.executeQuery();
            System.out.println("|   ID  |   NAME    |   BOOK    |   DATE    |");
            while(rs.next()){
                LentBook lb = new LentBook(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

        return lentb;
    }

    public static void deletePerson(int id){
        String search_book = "SELECT * FROM lent_books WHERE id = ?";
        String delete_person="DELETE FROM lent_books WHERE id=?";

        try(Connection conn=DataConnection.getConnection();
            PreparedStatement sp = conn.prepareStatement(search_book);
            PreparedStatement dp = conn.prepareStatement(delete_person)

        ){
            sp.setInt(1,id);
            ResultSet rs = sp.executeQuery();
            if(rs.next()){
                String book = rs.getString("book");
                int q=1;
                BookDAO.updateQuantity(book,q);
            }

            dp.setInt(1,id);
            dp.executeUpdate();
            System.out.println("Returned successfully");
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }

    }

    public static List<LentBook> readAll(){
        List <LentBook> lentb = new ArrayList<>();

        String search_book = "SELECT * FROM lent_books";
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rl = conn.prepareStatement(search_book) ;
            ResultSet rs = rl.executeQuery()
        ){
            while(rs.next()){
                LentBook lb = new LentBook(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return lentb;
    }

    public static List<LentBook> getByTitle(String title){
        List<LentBook> lentb = new ArrayList<>();

        String search_book = "SELECT * FROM lent_books WHERE book ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gbt = conn.prepareStatement(search_book)
        ){
            gbt.setString(1, "%"+title+"%");
            ResultSet rs = gbt.executeQuery();
            while(rs.next()){
                LentBook lb = new LentBook(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return lentb;
    }

    public static List<LentBook> getByPerson(String author){
        List<LentBook> lentb = new ArrayList<>();
        String search_book = "SELECT * FROM lent_books WHERE person ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gba = conn.prepareStatement(search_book)
        ){
            gba.setString(1, "%"+author+"%");
            ResultSet rs = gba.executeQuery();
            while(rs.next()){
                LentBook lb = new LentBook(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
                lentb.add(lb);
            }
        }
        catch (SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return lentb;
    }

    public static LentBook getbyId(int id){
        String search_book = "SELECT * FROM lent_books WHERE id = ?";

        LentBook lb = null;
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gbi = conn.prepareStatement(search_book)
        ){
            gbi.setInt(1,id);
            ResultSet rs =  gbi.executeQuery();
            if(rs.next()){
                lb = new LentBook(rs.getInt("id"),rs.getString("person"),rs.getString("book"),rs.getTimestamp("date"));
            }
        }
        catch (SQLException e) {
            System.err.println("Error: "+e.getMessage());
        }
        return lb;
    }
}
