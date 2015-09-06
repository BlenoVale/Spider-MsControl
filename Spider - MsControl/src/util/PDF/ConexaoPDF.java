package util.PDF;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Géssica
 */
public class ConexaoPDF {
     
    public static void main(String[] args) {
        new ConexaoPDF().gerarPDF_ProcColeta();
        new ConexaoPDF().gerarPDF_ProcAnalise();
    }
    
    public void gerarPDF_ProcColeta() {
        InputStream inputStream = getClass().getResourceAsStream( "/Ireport_ProcColeta.jasper" );

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put( "medida_id", "F%" );

        try {
            
            ReportUtils.openReport( "Plano de Coleta_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection() );

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } catch ( JRException exc ) {
            exc.printStackTrace();
        }
    }
    
    public void gerarPDF_ProcAnalise() {
        InputStream inputStream = getClass().getResourceAsStream( "/Ireport_ProcAnalise.jasper" );

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put( "indicador_id", "F%" );

        try {
            
            ReportUtils.openReport( "Plano de Análise_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection() );

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } catch ( JRException exc ) {
            exc.printStackTrace();
        }
    }
    
    public void gerarPDF_Geral() {
        InputStream inputStream = getClass().getResourceAsStream( "/Relatorio_Geral.jasper" );

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put( "projeto_id", "F%" );
        parametros.put( "idProjeto", "F%" );
        parametros.put( "resultados_id", "F%" );

        try {
            
            ReportUtils.openReport( "Plano de Medição_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection() );

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } catch ( JRException exc ) {
            exc.printStackTrace();
        }
    }
}
