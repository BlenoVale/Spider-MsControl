package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
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
 * @author BlenoVale
 */
public class Grafico {

    private Dados dados;
    private List<Dados> listaDados = new ArrayList<>();

    public Grafico() {
        listaDados = geraListaDados();
    }

    public List<Dados> geraListaDados() {
        List<Dados> listaDados = new ArrayList<>();
        dados = new Dados();
        dados.setNome("Sprint 1");
        dados.setValor(74);
        dados.setTipo("Sprints");
        listaDados.add(dados);

        dados = new Dados();
        dados.setNome("Sprint 2");
        dados.setValor(90);
        dados.setTipo("Sprints");
        listaDados.add(dados);

        dados = new Dados();
        dados.setNome("Sprint 3");
        dados.setValor(51);
        dados.setTipo("Sprints");
        listaDados.add(dados);
        
        dados = new Dados();
        dados.setNome("Sprint 4");
        dados.setValor(38);
        dados.setTipo("Sprints");
        listaDados.add(dados);
        
        dados = new Dados();
        dados.setNome("Sprint 5");
        dados.setValor(80);
        dados.setTipo("Sprints");
        listaDados.add(dados);
        
        return listaDados;
    }

    public ChartPanel geraGraficoPizza(String titulo) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Dados listaDado : listaDados) {
            dataset.setValue(listaDado.getNome(), listaDado.getValor());
        }

        JFreeChart jFreeChart = ChartFactory.createPieChart3D(
                titulo,
                dataset,
                true, // inclue legenda
                true,
                false);

        PiePlot3D plot = (PiePlot3D) jFreeChart.getPlot();
        plot.setStartAngle(200);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setCircular(true);

        ChartPanel chartPanel = new ChartPanel(jFreeChart);

        return chartPanel;
    }
    
    public ChartPanel geraGraficoBarra(String titulo, String x, String y) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < listaDados.size(); i++) {
            dataset.setValue(listaDados.get(i).getValor(), listaDados.get(i).getNome(), listaDados.get(i).getNome());
        }

        JFreeChart barChart = ChartFactory.createBarChart3D(
                titulo,
                x,
                y,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        BarRenderer3D barRenderer3D = (BarRenderer3D) barChart.getCategoryPlot().getRenderer();
        barRenderer3D.setSeriesPaint(0, Color.BLUE);
        barRenderer3D.setSeriesPaint(1, Color.YELLOW);
        barRenderer3D.setSeriesPaint(2, Color.GREEN);
        ChartPanel chartPanel = new ChartPanel(barChart);

        return chartPanel;
    }

    public ChartPanel geraGraficoLinha(String titulo, String x, String y) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < listaDados.size(); i++) {
            dataset.setValue(listaDados.get(i).getValor(), listaDados.get(i).getTipo(), listaDados.get(i).getNome());
        }

        JFreeChart lineChart = ChartFactory.createLineChart3D(
                titulo,
                x,
                y,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        //lineChart.setBackgroundPaint(Color.cyan);
        CategoryPlot plot = lineChart.getCategoryPlot();
        ChartPanel chartPanel = new ChartPanel(lineChart);

        return chartPanel;
    }
}
