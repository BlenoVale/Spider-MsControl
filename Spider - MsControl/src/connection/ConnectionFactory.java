/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Paulo Souza
 */
public class ConnectionFactory {
    public Connection getConnection (){
        try {
            
            return DriverManager.getConnection("jdbc:mysql://localhost/spidermscontrol2.1", "root", "");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Error conexão", null, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(erro);
        }
        
    }
    
}
