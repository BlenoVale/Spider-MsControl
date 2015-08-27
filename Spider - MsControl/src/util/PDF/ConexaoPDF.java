package util.PDF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sandro Bezerra
 */
public class ConexaoPDF {
     private static Connection conexao = null;

    public ConexaoPDF() {
    }

    public static Connection estabelecerConexao() {

        String url = "jdbc:mysql://localhost:3306/spidermscontrol";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, "root", "spider");

        } catch (Exception ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexao;
    }

    public static Connection getConexao() {
        if (conexao != null) {
            return conexao;
        }
        return null;
    }

    public static void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Boolean conexaoFechada(Connection conexao) throws SQLException {

        if (conexao.isClosed() == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public void gerarPDF_ProcColeta() {
         try {
            estabelecerConexao();
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream("\\Ireport_ProcColeta.jasper"), null, getConexao());
            JasperViewer jrViewer = new JasperViewer(jasperPrint, false);
            jrViewer.setTitle("Relatorio_ProcedimentoDeColeta" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            jrViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jrViewer.setVisible(Boolean.TRUE);
        } catch (JRException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarPDF_ProcAnalise() {
        try {
            estabelecerConexao();
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\Sandro Bezerra\\Documents\\NetBeansProjects\\Spider-MsControl\\Spider - MsControl\\src\\util\\PDF\\Ireport_ProcAnalise.jasper", null, getConexao());
            JasperViewer jrViewer = new JasperViewer(jasperPrint, false);
            jrViewer.setTitle("Relatorio_ProcedimentoDeAnalise" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            jrViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jrViewer.setVisible(Boolean.TRUE);
        } catch (JRException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
