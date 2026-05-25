package DB;

import com.github.shyiko.dotenv.DotEnv;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Map;

public class DataConnection {
    private static final Map<String, String> dotenv = DotEnv.load();

    public static Connection getConnection() throws SQLException{
        String URL = dotenv.get("DB_URL");
        String USER = dotenv.get("DB_USER");
        String KEY = dotenv.get("DB_KEY");
        return DriverManager.getConnection(URL,USER,KEY);
    }
}