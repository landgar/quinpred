package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.graficos.aciertosjornada.test.GraficoAciertosJornadaTest;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;
import es.propio.procesadoInfo.TipoDivisionEnum;

/**
 * Analiza los resultados del pasado.
 * 
 * @author carlos.andres
 * 
 */
public class AnalizadorDelPasado {

	public AnalizadorDelPasado() {
	}

	/**
	 * Analiza y compara las jornadas pasadas. Lo muestra en gráficos para que
	 * el usuario decida qué algoritmo es el mejor.
	 * 
	 * @throws Exception
	 */
	public static void estudiarJornadasPasadas() throws Exception {
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
		String titulo = "Comparación de algoritmos en jornadas pasadas: ";
		titulo += division.equals(TipoDivisionEnum.PRIMERA) ? "PRIMERA"
				: "SEGUNDA";
		titulo += " DIVISION";

		GraficoAciertosJornada grafico = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", titulo, inDto);
		grafico.pack();
		RefineryUtilities.centerFrameOnScreen(grafico);
		grafico.setVisible(true);
	}

}
