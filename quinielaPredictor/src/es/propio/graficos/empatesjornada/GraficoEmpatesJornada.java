package es.propio.graficos.empatesjornada;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.plot.CombinedCategoryPlot;
import org.jfree.ui.ApplicationFrame;

/**
 * Comparación gráfica de algoritmos según nº aciertos vs. jornada. ENTRADA:
 * EntradaAciertosJornadaDto. COMO TENEMOS LOS RESULTADOS REALES, OBVIAMENTE
 * todo LO QUE PINTAMOS SON DATOS PASADOS, NADA DEL FUTURO. SALIDA: grafico nº
 * aciertos vs. jornada
 * 
 * @author carlos.andres
 * 
 */
public class GraficoEmpatesJornada extends ApplicationFrame {

	public GraficoEmpatesJornada(String title) {
		super(title);
	}

	private static final long serialVersionUID = 1L;

	private static final Dimension DIMENSION_GRAFICO = new java.awt.Dimension(
			620, 340);
	private static final Font LETRA = new Font("SansSerif", Font.BOLD, 12);

	public static HashMap<Integer, Double> empates;

	public GraficoEmpatesJornada(String tituloVentana,
			HashMap<Integer, Double> empates) {
		super(tituloVentana);
		this.empates = empates;

		String tituloGraficos = "Numero de empates vs. jornadas";

		JPanel chartPanel = createDemoPanel(tituloGraficos);
		chartPanel.setPreferredSize(DIMENSION_GRAFICO);
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

		CategoryAxis domainAxis = new CategoryAxis("Num jornada");
		CombinedCategoryPlot plot = new CombinedCategoryPlot(domainAxis,
				new NumberAxis("Num empates"));
		plot.add(crearSubplotLineas(), 1);

		JFreeChart tabla = new JFreeChart(tituloGraficos, LETRA, plot, true);
		return tabla;
	}

	private static CategoryPlot crearSubplotLineas() {
		CategoryDataset dataset = createDataset();
		NumberAxis rangeAxis = new NumberAxis("Value");
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
		CategoryPlot subplot = new CategoryPlot(dataset, null, rangeAxis,
				renderer);
		subplot.setDomainGridlinesVisible(true);
		return subplot;
	}

	/**
	 * Creates a dataset.
	 * 
	 * @return A dataset.
	 */
	public static CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		rellenarLinea(dataset, "empates_vs_jornadas", ordenarMapa(empates));

		return dataset;
	}

	public static HashMap<Integer, Double> ordenarMapa(
			HashMap<Integer, Double> in) {

		List<Integer> keys = new ArrayList<Integer>();
		keys.addAll(in.keySet());
		Collections.sort(keys);

		HashMap<Integer, Double> out = new HashMap<Integer, Double>(keys.size());
		for (Integer key : keys) {
			out.put(key, in.get(key));
		}

		return out;
	}

	public static void rellenarLinea(DefaultCategoryDataset dataset,
			String nombreLinea, HashMap<Integer, Double> mapaxy) {
		Set<Integer> keys = mapaxy.keySet();
		List<Integer> keysOrdenadas = new ArrayList<Integer>(keys);
		Collections.sort(keysOrdenadas);

		for (Integer key : keysOrdenadas) {
			dataset.addValue(mapaxy.get(key), nombreLinea, key);
		}
	}

}
