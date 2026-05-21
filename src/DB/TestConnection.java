package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class TestConnection {
    public static void main(String[] args) throws SQLException {

        String connection_key= "jdbc:postgresql://ep-dawn-feather-aphaq3sf-pooler.c-7.us-east-1.aws.neon.tech/neondb?user=neondb_owner&password=npg_Pi15oQsUerOB&sslmode=require&channelBinding=require";

        Connection conn = DriverManager.getConnection(connection_key);
        System.out.println("Exitos "+ conn.getMetaData().getDatabaseProductVersion());

    }
}
