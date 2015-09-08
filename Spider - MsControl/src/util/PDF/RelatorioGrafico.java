package util.PDF;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Valorindicador;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author FACOMP
 */
public class RelatorioGrafico {

    public void GerarGraficoTeste(List<Valorindicador> listaValorindicador) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/GraficoTeste.jasper");
            BufferedImage imagem = GraficoPDF.geraGraficoPizzaEmPNG(listaValorindicador);

            HashMap parametros = new HashMap();
            parametros.put("imagem", imagem);
            try {

                ReportUtils.openReport("Grafico_" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()), inputStream, parametros,
                        ConnectionFactory1.getSpiderConnection());

            } catch (SQLException exc) {
                exc.printStackTrace();
            } catch (JRException exc) {
                exc.printStackTrace();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
