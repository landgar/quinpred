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
import es.propio.graficos.aciertosjornada.test.GraficoAciertosJornadaTest;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;
import es.propio.procesadoInfo.TipoDivisionEnum;

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
		// analizarJornadaActual();
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
				.println("Pintando GRAFICOS para comparar los algoritmos ...");
		System.out
				.println("ENTRADA: Temporada, resultados reales y resultados pronosticados.");
		System.out
				.println("Aplicación del algoritmo a todos los ficheros de predicción:");

		graficosPrimeraDivision();
		graficosSegundaDivision();
	}

	private static void graficosPrimeraDivision() throws Exception {
		// PRIMERA DIVISION
		// TODO invocar a AbstractAlgoritmo.calcularPronosticoPrimera
		Set<PronosticoJornada> pronosticosPrimeraAlgoritmo1 = Algoritmo1
				.calcularPronosticos();
		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.addAll(pronosticosPrimeraAlgoritmo1);
		// pronosticosJornadaBulk.addAll(pronosticosPrimeraAlgoritmo2);
		// pronosticosJornadaBulk.addAll(pronosticosPrimeraAlgoritmo3);

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk, obtenerResultadosRealesPrimera());
		graficoNumAciertosVsJornada(inDto, TipoDivisionEnum.PRIMERA);

	}

	private static List<PronosticoJornada> obtenerResultadosRealesPrimera() {
		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosRealesPrimera = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, TipoDivisionEnum.PRIMERA);
		return resultadosRealesPrimera;
	}

	private static void graficosSegundaDivision() throws Exception {
		// SEGUNDA DIVISION
		// TODO invocar a AbstractAlgoritmo.calcularPronosticoSegunda
		Set<PronosticoJornada> pronosticosSegundaAlgoritmo1 = Algoritmo1
				.calcularPronosticos();

		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.addAll(pronosticosSegundaAlgoritmo1);
		// pronosticosJornadaBulk.addAll(pronosticosSegundaAlgoritmo2);
		// pronosticosJornadaBulk.addAll(pronosticosSegundaAlgoritmo3);

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk, obtenerResultadosRealesSegunda());
		graficoNumAciertosVsJornada(inDto, TipoDivisionEnum.SEGUNDA);

	}

	private static List<PronosticoJornada> obtenerResultadosRealesSegunda() {
		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosRealesSegunda = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, TipoDivisionEnum.SEGUNDA);
		return resultadosRealesSegunda;
	}

	private static void graficoNumAciertosVsJornada(
			EntradaAciertosJornadaDto inDto, TipoDivisionEnum division) {
		String title = "Comparación de algoritmos según resultados pasados";
		GraficoAciertosJornada grafico = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", title, inDto);
		grafico.pack();
		RefineryUtilities.centerFrameOnScreen(grafico);
		grafico.setVisible(true);
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
		// función del algoritmo que queramos usar.
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
