package es.propio.graficos;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.plot.CombinedCategoryPlot;
import org.jfree.ui.ApplicationFrame;

public class GraficosManager extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	public GraficosManager(String tituloVentana, String tituloGraficos) {
		super(tituloVentana);
		JPanel chartPanel = createDemoPanel(tituloGraficos);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	/**
	 * Panel principal
	 * 
	 * @return A panel.
	 */
	public static JPanel createDemoPanel(String tituloGraficos) {
		JFreeChart chart = createChart(tituloGraficos);
		return new ChartPanel(chart);
	}

	/**
	 * Tabla de graficos
	 * 
	 * @return A chart.
	 */
	private static JFreeChart createChart(String tituloGraficos) {

		CategoryAxis domainAxis = new CategoryAxis("Titulo eje X");
		CombinedCategoryPlot plot = new CombinedCategoryPlot(domainAxis,
				new NumberAxis("Titulo eje Y"));
		plot.add(crearSubplotLineas(), 2);
		plot.add(crearSubplot2(), 1);

		JFreeChart tabla = new JFreeChart(tituloGraficos, new Font("SansSerif",
				Font.BOLD, 12), plot, true);
		return tabla;

	}

	private static CategoryPlot crearSubplotLineas() {
		CategoryDataset dataset1 = createDataset1();
		NumberAxis rangeAxis1 = new NumberAxis("Value");
		rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
		renderer1
				.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1,
				renderer1);
		subplot1.setDomainGridlinesVisible(true);
		return subplot1;
	}

	private static CategoryPlot crearSubplot2() {
		CategoryDataset dataset2 = createDataset2();
		NumberAxis rangeAxis2 = new NumberAxis("Value");
		rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer renderer2 = new BarRenderer();
		renderer2
				.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2,
				renderer2);
		subplot2.setDomainGridlinesVisible(true);
		return subplot2;
	}

	/**
	 * Creates a dataset.
	 * 
	 * @return A dataset.
	 */
	public static CategoryDataset createDataset1() {
		DefaultCategoryDataset result = new DefaultCategoryDataset();
		String series1 = "colcap";
		String series2 = "icbc";
		String type1 = "Type 1";
		String type2 = "Type 2";
		String type3 = "Type 3";
		String type4 = "Type 4";
		String type5 = "Type 5";
		String type6 = "Type 6";
		String type7 = "Type 7";
		String type8 = "Type 8";

		result.addValue(2.0, series1, type1);
		result.addValue(4.0, series1, type2);
		result.addValue(3.0, series1, type3);
		result.addValue(5.0, series1, type4);
		result.addValue(5.0, series1, type5);
		result.addValue(7.0, series1, type6);
		result.addValue(7.0, series1, type7);
		result.addValue(80.0, series1, type8);

		result.addValue(5.0, series2, type1);
		result.addValue(7.0, series2, type2);
		result.addValue(6.0, series2, type3);
		result.addValue(8.0, series2, type4);
		result.addValue(4.0, series2, type5);
		result.addValue(4.0, series2, type6);
		result.addValue(2.0, series2, type7);
		result.addValue(1.0, series2, type8);

		return result;
	}

	/**
	 * Creates a dataset.
	 * 
	 * @return A dataset.
	 */
	public static CategoryDataset createDataset2() {

		DefaultCategoryDataset result = new DefaultCategoryDataset();

		String series1 = "Third";
		String series2 = "Fourth";

		String type1 = "Type 1";
		String type2 = "Type 2";
		String type3 = "Type 3";
		String type4 = "Type 4";
		String type5 = "Type 5";
		String type6 = "Type 6";
		String type7 = "Type 7";
		String type8 = "Type 8";

		result.addValue(11.0, series1, type1);
		result.addValue(14.0, series1, type2);
		result.addValue(13.0, series1, type3);
		result.addValue(15.0, series1, type4);
		result.addValue(15.0, series1, type5);
		result.addValue(17.0, series1, type6);
		result.addValue(17.0, series1, type7);
		result.addValue(18.0, series1, type8);

		result.addValue(15.0, series2, type1);
		result.addValue(17.0, series2, type2);
		result.addValue(16.0, series2, type3);
		result.addValue(18.0, series2, type4);
		result.addValue(14.0, series2, type5);
		result.addValue(14.0, series2, type6);
		result.addValue(12.0, series2, type7);
		result.addValue(11.0, series2, type8);

		return result;

	}

}
