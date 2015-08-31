package util.PDF;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Géssica
 */
public class ReportUtils {
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map<String, Object> parametros,
            Connection conexao ) throws JRException {

        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );

        viewReportFrame( titulo, print );

    }

    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map<String, Object> parametros,
            JRDataSource dataSource ) throws JRException {

        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource );

        viewReportFrame( titulo, print );

    }

    private static void viewReportFrame( String titulo, JasperPrint print ) {

        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer( print );

        // cria o JFrame
        JFrame frameRelatorio = new JFrame( titulo );

        // adiciona o JRViewer no JFrame
        frameRelatorio.add( viewer, BorderLayout.CENTER );

        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize( 500, 500 );

        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );

        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // exibe o JFrame
        frameRelatorio.setVisible( true );
    }
}
