package util.PDF;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Valorindicador;
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
        InputStream inputStream = getClass().getResourceAsStream("/Ireport_ProcColeta.jasper");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("medida_id", "F%");

        try {

            ReportUtils.openReport("Plano de Coleta_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection());

        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void gerarPDF_ProcAnalise() {
        InputStream inputStream = getClass().getResourceAsStream("/Ireport_ProcAnalise.jasper");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("indicador_id", "F%");

        try {

            ReportUtils.openReport("Plano de Análise_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection());

        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
        }
    }

    public void gerarPDF_Geral(List<Valorindicador> listaValorindicador, String tipo) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/Relatorio_Geral.jasper");

            Map<String, Object> parametros = new HashMap<String, Object>();

            BufferedImage imagem = null;
            if ("Pizza".equals(tipo)) {
                imagem = GraficoPDF.geraGraficoPizzaEmPNG(listaValorindicador);
            } else if ("Barra".equals(tipo)) {
                imagem = GraficoPDF.geraGraficoBarraEmPNG(listaValorindicador);
            } else {
                imagem = GraficoPDF.geraGraficoLinhaEmPNG(listaValorindicador);
            }

            parametros.put("projeto_id", 1);
            parametros.put("idProjeto", 1);
            parametros.put("resultados_id", 1);
            parametros.put("imagem", imagem);

            ReportUtils.openReport("Plano de Medição_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                    ConnectionFactory1.getSpiderConnection());

        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
