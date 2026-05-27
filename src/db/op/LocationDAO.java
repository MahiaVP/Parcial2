package db.op;
import code.Location;
import db.DataConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LocationDAO {
    public static Location findTitle(int id) {
        String find="SELECT * FROM library WHERE id = ?";
        String search="SELECT * FROM location WHERE book = ?";
        String Update="INSERT INTO location (book,author,section,row) VALUES (?,?,?,?)";
        String ait="SELECT * FROM location WHERE book = ?";
        Location loc1 = null;
        try(Connection conn= DataConnection.getConnection();
            PreparedStatement fi = conn.prepareStatement(find);
            PreparedStatement st=conn.prepareStatement(search);
            PreparedStatement il=conn.prepareStatement(Update);
            PreparedStatement paf=conn.prepareStatement(ait)
        ){
            fi.setInt(1, id);
            ResultSet rs=fi.executeQuery();
            String book="";
            String author="";
            if(rs.next()){
                book=rs.getString("book");
                author=rs.getString("author");
            }

            st.setString(1, book);
            String section="";
            ResultSet rs1=st.executeQuery();
            if(!rs1.next()){
                String genre=rs.getString("genre");
                switch(genre){
                    case "FANTASY":
                        section="A";
                        break;
                    case "ROMANCE":
                        section="B";
                        break;
                    case "SCI_FI":
                        section="C";
                        break;
                    case "MYSTERY":
                        section="D";
                        break;
                    case "HORROR":
                        section="E";
                        break;
                    case "POETRY":
                        section="F";
                        break;
                }
                int row=0;
                char in=Character.toUpperCase(book.charAt(0));
                if(in>='A' && in<='F'){
                    row=1;
                }else if(in>='G' && in<='L'){
                    row=2;
                }else if(in>='M' && in<='R'){
                    row=3;
                }else if(in>='S' && in<='Z'){
                    row=4;
                }
                il.setString(1, book);
                il.setString(2, author);
                il.setString(3, section);
                il.setInt(4, row);
                il.executeUpdate();
                Location loc = new Location(book,author,section,row);
                System.out.println("\n!! If the book was not previously registered the ID will appear as 0 even though it is not really 0, search it again to see the id !!\n");
                return loc;
            }else{
                paf.setString(1, book);
                ResultSet fb=paf.executeQuery();
                if(fb.next()){
                    loc1=new Location(fb.getInt("id"),fb.getString("book"),fb.getString("author"),fb.getString("section"),fb.getInt("row"));
                }
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return loc1;
    }

    public static List<Location> findByTitle(String title) {
        List<Location> loc=new ArrayList<>();
        String find="SELECT * FROM location WHERE book ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement fbt = conn.prepareStatement(find)){
            fbt.setString(1, "%"+title+"%");
            ResultSet rs=fbt.executeQuery();
            while(rs.next()){
                Location lc =  new Location(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
                loc.add(lc);
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return loc;
    }

    public static List<Location> findByAuthor(String author) {
        List<Location> au=new ArrayList<>();
        String find="SELECT * FROM location WHERE author ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement fbt = conn.prepareStatement(find)){
            fbt.setString(1, "%"+author+"%");
            ResultSet rs=fbt.executeQuery();
            while(rs.next()){
                Location aut =  new Location(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
                au.add(aut);
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return au;
    }

    public static List<Location> findBySection(String section) {
        List<Location> sec=new ArrayList<>();
        String find="SELECT * FROM location WHERE section ILIKE ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement fbs = conn.prepareStatement(find)
        ){
           fbs.setString(1, "%"+section+"%");
           ResultSet rs=fbs.executeQuery();
           while(rs.next()){
               Location sc= new Location(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
               sec.add(sc);
           }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return sec;
    }

    public static List<Location> findByRow(int row) {
        List<Location> rw=new ArrayList<>();

        String find="SELECT * FROM location WHERE row = ?";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement fbr = conn.prepareStatement(find)
        ){
            fbr.setInt(1,row);
            ResultSet rs=fbr.executeQuery();
            while(rs.next()){
                Location rOw = new Location(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
                rw.add(rOw);
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return rw;
    }

    public static List<Location> getAllLocation() {
        List<Location> all=new ArrayList<>();

        String find="SELECT * FROM location";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rl = conn.prepareStatement(find);
            ResultSet rs = rl.executeQuery()
        ){
            while(rs.next()){
                Location eve = new Location(rs.getInt("id"),rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
                all.add(eve);
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return all;
    }

    public static Location getById(int id) {
        String find="SELECT * FROM location WHERE id = ?";
        Location location=null;
        try(Connection conn = DataConnection.getConnection();
            PreparedStatement gbi= conn.prepareStatement(find)
        ){
            gbi.setInt(1,id);
            ResultSet rs=gbi.executeQuery();
            if(rs.next()){
                location = new Location(id,rs.getString("book"),rs.getString("author"),rs.getString("section"),rs.getInt("row"));
                return location;
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
        return location;
    }
}
