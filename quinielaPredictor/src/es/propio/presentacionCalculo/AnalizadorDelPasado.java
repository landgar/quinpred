package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.graficos.aciertosjornada.test.GraficoAciertosJornadaTest;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Analiza los resultados del pasado.
 * 
 * @author carlos.andres
 * 
 */
public class AnalizadorDelPasado {

	static Algoritmo1 algo1;

	public AnalizadorDelPasado() {
	}

	/**
	 * Analiza y compara las jornadas pasadas. Lo muestra en gráficos para que
	 * el usuario decida qué algoritmo es el mejor.
	 * 
	 * @throws Exception
	 */
	public static void estudiarJornadasPasadas(int numeroUltimoBoleto)
			throws Exception {
		System.out
				.println("Pintando GRAFICOS para comparar los algoritmos ...");
		System.out
				.println("ENTRADA: Temporada, resultados reales y resultados pronosticados.");
		System.out
				.println("Aplicación del algoritmo a todos los ficheros de predicción:");

		// ALGORITMOS factory
		if (algo1 == null) {
			algo1 = new Algoritmo1();
		}

		// Boletos analizados
		List<String> numBoletos = numerosBoletoAnalizados(numeroUltimoBoleto);

		graficosPrimeraDivision(numBoletos);
		graficosSegundaDivision(numBoletos);
	}

	private static List<String> numerosBoletoAnalizados(int numeroUltimoBoleto) {
		List<String> numerosBoleto = new ArrayList<String>();

		// Analizo todos los boletos pasados
		for (int i = 1; i <= numeroUltimoBoleto; i++) {
			numerosBoleto.add(String.valueOf(i));
		}
		return numerosBoleto;
	}

	private static void graficosPrimeraDivision(List<String> numBoletos)
			throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();

		// TODO numBoleto es numero de jornada??

		for (String numBoleto : numBoletos) {

			List<PronosticoPartido> partidos = Principal.obtenerPartidos(
					Division.PRIMERA, numBoleto);

			// ALGORITMO 1
			algo1.setEstimacionJornadaPrimera(new PronosticoJornada(partidos,
					Integer.valueOf(numBoleto), IdAlgoritmoEnum.ALGORITMO1));
			algo1.calcularPronosticoPrimera();
			pronosticosJornadas.add(algo1.getEstimacionJornadaPrimera());

			// TODO ALGORITMO 2
			// rellenar con todos los algoritmos, para PRIMERA
		}

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadas, obtenerResultadosRealesPrimera());
		graficoNumAciertosVsJornada(inDto, Division.PRIMERA);
	}

	private static List<PronosticoJornada> obtenerResultadosRealesPrimera() {
		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosRealesPrimera = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, Division.PRIMERA);
		return resultadosRealesPrimera;
	}

	private static void graficosSegundaDivision(List<String> numBoletos)
			throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();
		// TODO numBoleto es numero de jornada??
		for (String numBoleto : numBoletos) {

			List<PronosticoPartido> partidos = Principal.obtenerPartidos(
					Division.SEGUNDA, numBoleto);

			// ALGORITMO 1
			algo1.setEstimacionJornadaSegunda(new PronosticoJornada(partidos,
					Integer.valueOf(numBoleto), IdAlgoritmoEnum.ALGORITMO1));
			algo1.calcularPronosticoSegunda();
			pronosticosJornadas.add(algo1.getEstimacionJornadaSegunda());

			// TODO ALGORITMO 2
			// rellenar con todos los algoritmos, para SEGUNDA
		}

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadas, obtenerResultadosRealesSegunda());
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
