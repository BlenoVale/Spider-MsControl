
package util.PDF;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import model.Valorindicador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author FACOMP
 */
public class GraficoPDF {
     
    public static BufferedImage geraGraficoPizzaEmPNG(List<Valorindicador> listaValorindicador) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        BufferedImage buf = null;

        for (int i = 0; i < listaValorindicador.size(); i++) {
            dataset.setValue("id-" + String.valueOf(i + 1),
                    listaValorindicador.get(i).getValor());
        }

        JFreeChart jFreeChart = ChartFactory.createPieChart3D(
                listaValorindicador.get(0).getIndicadorid().getNome(),
                dataset,
                true, // inclue legenda
                true,
                false);

        PiePlot3D plot = (PiePlot3D) jFreeChart.getPlot();
        plot.setStartAngle(200);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setCircular(true);
        return buf = jFreeChart.createBufferedImage(400, 250);
    }
}
