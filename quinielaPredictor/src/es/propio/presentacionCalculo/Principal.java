/**
 * 
 */
package es.propio.presentacionCalculo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.jfree.ui.RefineryUtilities;

import es.propio.cargadorInfoWeb.CargadorWebyXMLPronosticoQuinielista;
import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.Algoritmo1;

/**
 * @author i3casa
 * 
 */
public class Principal {

	static final String LOG_PROPERTIES_FILE = "logging/log4j.properties";

	public Principal(String title) {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
		PropertyConfigurator.configure(logProperties);

		System.out.println("COMIENZO");
		estudiarJornadasPasadas();
		analizarJornadaActual();
		System.out.println("FIN");
	}

	/**
	 * Analiza y compara las jornadas pasadas. Lo muestra en gráficos para que
	 * el usuario decida qué algoritmo es el mejor.
	 * 
	 * @throws Exception
	 */
	private static void estudiarJornadasPasadas() throws Exception {
		System.out
				.println("Aplicación del algoritmo a todos los ficheros de predicción:");
		Set<PronosticoJornada> pronosticosAlgoritmo1 = Algoritmo1
				.calcularPronosticos();

		System.out
				.println("Pintando GRAFICOS para comparar los algoritmos ...");
		System.out
				.println("ENTRADA: Temporada, resultados reales y resultados pronosticados.");
		String title = "Comparación de algoritmos según resultados pasados";

		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.addAll(pronosticosAlgoritmo1);

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk);

		GraficoAciertosJornada demo = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", title, inDto);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

	/**
	 * En base a las jornadas pasadas (y el algoritmo ya elegido), pinta la
	 * quiniela que tendríamos que rellenar a papel en la JORNADA ACTUAL, para
	 * cada algoritmo y pronosticos que leemos en webs externas.
	 * 
	 * @throws Exception
	 */
	private static void analizarJornadaActual() throws Exception {

		System.out
				.println("Pintando quinielas previstas para jornada actual, segun varios algoritmos y webs");
		// TODO Modulo para que nos ayude a rellenar la quiniela en papel, en
		// función del algortimo que queramos usar.
		Algoritmo1.calcularPronosticos();

		// System.out
		// .println("Pintando GRAFICOS para comparar los algoritmos ...");
		// String title = "-- pruebas de graficos --";
		// GraficosManager demo = new GraficosManager(title, title);
		// demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		// demo.setVisible(true);

		System.out
				.println("Obteniendo pronosticos de WEB QUINIELISTA.COM para Jornada actual (tarda unos 10 segundos)...");
		CargadorWebyXMLPronosticoQuinielista cargador = new CargadorWebyXMLPronosticoQuinielista();
		PronosticoJornada pronosticoJornada = cargador.ejecutar();
		pronosticoJornada.pintarme();

	}

}
