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

    public void GerarGraficoNoPDF(List<Valorindicador> listaValorindicador, String tipo) {
        try {
            BufferedImage imagem = new BufferedImage(0, 0, 0);
            InputStream inputStream = getClass().getResourceAsStream("/GraficoTeste.jasper");
            
            if ("Pizza".equals(tipo)) {
                imagem = GraficoPDF.geraGraficoPizzaEmPNG(listaValorindicador);
            } else if ("Barra".equals(tipo)) {
                imagem = GraficoPDF.geraGraficoBarraEmPNG(listaValorindicador);
            } else {
                imagem = GraficoPDF.geraGraficoLinhaEmPNG(listaValorindicador);
            }

            HashMap parametros = new HashMap();
            parametros.put("imagem", imagem);
        } catch (IOException ex) {
            Logger.getLogger(ConexaoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
