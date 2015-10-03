package instalador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bleno Vale
 */
public class ExecutaBanco {

    private static final String drive = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        criaBancoDeDados();
    }

    static {
        try {
            Class.forName(drive).newInstance();
            System.out.println(">> Drive rodou!!");
        } catch (Exception error) {
            System.out.println(">> Erro:" + error + "\n");
            error.printStackTrace();
        }

    }

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "spider";
    private static final String INSTRUCTIONS = new String();

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void criaBancoDeDados() {
        String linha = new String();
        StringBuffer stringBuffer = new StringBuffer();

        try {
            FileReader fileReader = new FileReader(new File("Banco.sql").getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((linha = bufferedReader.readLine()) != null) {
                stringBuffer.append(linha);
            }
            bufferedReader.close();

            String[] comando = stringBuffer.toString().split(";");

            Connection connection = getConexao();
            Statement statement = connection.createStatement();

            for (int i = 0; i < comando.length; i++) {
                if (!comando[i].trim().equals("")) {
                    statement.executeUpdate(comando[i]);
                    System.out.println(">>" + comando[i]);
                }
            }
        } catch (Exception error) {
            System.out.println("error:" + error + "\n");
            error.printStackTrace();
        }
    }
}
