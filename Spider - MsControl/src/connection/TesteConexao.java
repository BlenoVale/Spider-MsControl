/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Souza
 */
public class TesteConexao {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        JOptionPane.showMessageDialog(null, "Conexão Funcionando", null, JOptionPane.INFORMATION_MESSAGE);
        connection.close();
    }
    
    
}
