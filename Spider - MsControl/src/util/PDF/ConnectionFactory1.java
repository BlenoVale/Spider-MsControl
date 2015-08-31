package util.PDF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Géssica
 */
public class ConnectionFactory1 {
    static {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException exc ) {

            exc.printStackTrace();

        }
    }

    public static Connection getConnection(
            String url,
            String usuario,
            String senha ) throws SQLException {
        return DriverManager.getConnection( url, usuario, senha );
    }

    public static Connection getSpiderConnection() throws SQLException {

        return getConnection(
                "jdbc:mysql://localhost:3306/spidermscontrol",
                "root",
                "spider" );
    }
}
