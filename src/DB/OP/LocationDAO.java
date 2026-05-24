package DB.OP;
import DB.DataConnection;
import java.sql.*;


public class LocationDAO {
    public static void find_title(int id) {
        String find="SELECT * FROM library WHERE id = ?";
        String search="SELECT * FROM location WHERE book = ?";
        String Update="INSERT INTO location (book,author,section,row) VALUES (?,?,?,?)";
        String ait="SELECT * FROM location WHERE book = ?";

        try(Connection conn= DataConnection.getConnection();
            PreparedStatement fi = conn.prepareStatement(find);
            PreparedStatement st=conn.prepareStatement(search);
            PreparedStatement il=conn.prepareStatement(Update);
            PreparedStatement paf=conn.prepareStatement(ait);
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
                System.out.println(book+" by "+author+" is at section "+section+" row "+row);
            }else{
                paf.setString(1, book);
                ResultSet fb=paf.executeQuery();
                if(fb.next()){
                    String book1=fb.getString("book");
                    String author1=fb.getString("author");
                    String section1=fb.getString("section");
                    int row1=fb.getInt("row");

                    System.out.println(book+" by "+author1+" is at section "+section1+" row "+row1);
                }

            }

        }

        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }

    public static void Read_Location() {
        String find="SELECT * FROM location";

        try(Connection conn = DataConnection.getConnection();
            PreparedStatement rl = conn.prepareStatement(find);
            ResultSet rs = rl.executeQuery();
        ){
            System.out.println("|   BOOK    |   AUTHOR  |   SECTION   |     ROW     |");
            while(rs.next()){
                String book=rs.getString("book");
                String author=rs.getString("author");
                String section=rs.getString("section");
                int row=rs.getInt("row");

                System.out.println("| "+book+" | "+author+" | "+section+" | "+row);
            }
        }
        catch(SQLException e){
            System.err.println("Error: "+e.getMessage());
        }
    }
}
