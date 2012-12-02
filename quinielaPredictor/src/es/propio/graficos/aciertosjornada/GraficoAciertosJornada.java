package es.propio.graficos.aciertosjornada;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Comparaci�n gr�fica de algoritmos seg�n n� aciertos vs. jornada. ENTRADA:
 * EntradaAciertosJornadaDto. COMO TENEMOS LOS RESULTADOS REALES, OBVIAMENTE
 * TODO LO QUE PINTAMOS SON DATOS PASADOS, NADA DEL FUTURO. SALIDA: grafico n�
 * aciertos vs. jornada
 * 
 * @author carlos.andres
 * 
 */
public class GraficoAciertosJornada extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	private static final Dimension DIMENSION_GRAFICO = new java.awt.Dimension(
			500, 270);
	private static final Font LETRA = new Font("SansSerif", Font.BOLD, 12);

	public static EntradaAciertosJornadaDto inDto;

	public GraficoAciertosJornada(String tituloVentana, String tituloGraficos,
			EntradaAciertosJornadaDto inDto) {
		super(tituloVentana);
		this.inDto = inDto;
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
				new NumberAxis("Num aciertos"));
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

		Map<IdAlgoritmoEnum, List<PronosticoJornada>> mapa = inDto
				.organizarPorAlgoritmo();

		for (IdAlgoritmoEnum idAlgoritmo : mapa.keySet()) {

			List<PronosticoJornada> pronosticosJornadasDeUnAlgoritmo = mapa
					.get(idAlgoritmo);
			Collections.sort(pronosticosJornadasDeUnAlgoritmo);

			HashMap<String, Double> mapaxyAlgoritmo = new HashMap<String, Double>();
			for (PronosticoJornada pjornadaEjex : pronosticosJornadasDeUnAlgoritmo) {

				// TODO Rellenar los aciertos comparando pjornadaEjex con el
				// PronosticoJornada real.
				Double aciertos = 5.0D;
				mapaxyAlgoritmo.put(pjornadaEjex.getNumeroJornada().toString(),
						aciertos);
			}

			rellenarLinea(dataset, idAlgoritmo.toString(), mapaxyAlgoritmo);
		}

		return dataset;
	}

	public static void rellenarLinea(DefaultCategoryDataset dataset,
			String nombreLinea, HashMap<String, Double> mapaxy) {
		Set<String> keys = mapaxy.keySet();
		for (String key : keys) {
			dataset.addValue(mapaxy.get(key), nombreLinea, key);
		}
	}

	public EntradaAciertosJornadaDto getInDto() {
		return inDto;
	}

	public void setInDto(EntradaAciertosJornadaDto inDto) {
		this.inDto = inDto;
	}

}
