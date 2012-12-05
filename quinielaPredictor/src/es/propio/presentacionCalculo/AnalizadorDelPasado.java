package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.graficos.aciertosjornada.test.GraficoAciertosJornadaTest;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

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
		Algoritmo1 alg = new Algoritmo1();
		alg.calcularPronosticoPrimera();
		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.add(alg.getEstimacionJornadaPrimera());
		// pronosticosJornadaBulk.addAll(pronosticosPrimeraAlgoritmo2);
		// pronosticosJornadaBulk.addAll(pronosticosPrimeraAlgoritmo3);

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk, obtenerResultadosRealesPrimera());
		graficoNumAciertosVsJornada(inDto, Division.PRIMERA);

	}

	private static List<PronosticoJornada> obtenerResultadosRealesPrimera() {
		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosRealesPrimera = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, Division.PRIMERA);
		return resultadosRealesPrimera;
	}

	private static void graficosSegundaDivision() throws Exception {
		// SEGUNDA DIVISION
		// TODO invocar a AbstractAlgoritmo.calcularPronosticoSegunda
		Algoritmo1 alg = new Algoritmo1();
		alg.calcularPronosticoSegunda();
		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.add(alg.getEstimacionJornadaSegunda());
		// pronosticosJornadaBulk.addAll(pronosticosSegundaAlgoritmo2);
		// pronosticosJornadaBulk.addAll(pronosticosSegundaAlgoritmo3);

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk, obtenerResultadosRealesSegunda());
		graficoNumAciertosVsJornada(inDto, Division.SEGUNDA);

	}

	private static List<PronosticoJornada> obtenerResultadosRealesSegunda() {
		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosRealesSegunda = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, Division.SEGUNDA);
		return resultadosRealesSegunda;
	}

	private static void graficoNumAciertosVsJornada(
			EntradaAciertosJornadaDto inDto, Division division) {
		String titulo = "Comparación de algoritmos en jornadas pasadas: ";
		titulo += division.equals(Division.PRIMERA) ? "PRIMERA" : "SEGUNDA";
		titulo += " DIVISION";

		GraficoAciertosJornada grafico = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", titulo, inDto);
		grafico.pack();
		RefineryUtilities.centerFrameOnScreen(grafico);
		grafico.setVisible(true);
	}

}
