
package util.PDF;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Valorindicador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
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
    
    public static BufferedImage geraGraficoBarraEmPNG(List<Valorindicador> listaValorindicador) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BufferedImage buf = null;
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaValorindicador.size(); i++) {
            String data = simpleDateFormat.format(listaValorindicador.get(i).getData());

            dataset.setValue(listaValorindicador.get(i).getValor(),
                    data, "id" + String.valueOf(i + 1));
        }

        JFreeChart barChart = ChartFactory.createBarChart3D(
                listaValorindicador.get(0).getIndicadorid().getNome(),
                listaValorindicador.get(0).getIndicadorid().getMnemonico(),
                "Valor indicador",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        BarRenderer3D barRenderer3D = (BarRenderer3D) barChart.getCategoryPlot().getRenderer();
        barRenderer3D.setSeriesPaint(0, Color.BLUE);
        barRenderer3D.setSeriesPaint(1, Color.YELLOW);
        barRenderer3D.setSeriesPaint(2, Color.GREEN);

        return buf = barChart.createBufferedImage(400, 250);
    }
    
     public static BufferedImage geraGraficoLinhaEmPNG(List<Valorindicador> listaValorindicador) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BufferedImage buf = null;
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < listaValorindicador.size(); i++) {
            String data = simpleDateFormat.format(listaValorindicador.get(i).getData());
            dataset.setValue(listaValorindicador.get(i).getValor(),
                    data, "id" + String.valueOf(i + 1));
        }

        JFreeChart lineChart = ChartFactory.createLineChart3D(
                listaValorindicador.get(0).getIndicadorid().getNome(),
                listaValorindicador.get(0).getIndicadorid().getMnemonico(),
                "Valor indicador",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        //lineChart.setBackgroundPaint(Color.cyan);
        CategoryPlot plot = lineChart.getCategoryPlot();
        
        return buf = lineChart.createBufferedImage(400, 250);
    }
}