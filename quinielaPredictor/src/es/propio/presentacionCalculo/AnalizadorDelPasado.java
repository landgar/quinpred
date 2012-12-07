package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.graficos.aciertosjornada.test.GraficoAciertosJornadaTest;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Analiza los resultados del pasado.
 * 
 * @author carlos.andres
 * 
 */
public class AnalizadorDelPasado {

	static List<AbstractAlgoritmo> algoritmos;

	public AnalizadorDelPasado() {
	}

	/**
	 * Analiza y compara las jornadas pasadas. Lo muestra en gráficos para que
	 * el usuario decida qué algoritmo es el mejor.
	 * 
	 * @throws Exception
	 */
	public static void estudiarJornadasPasadas(
			final List<AbstractAlgoritmo> algoritmosUsados,
			final Temporada temporadaPrimera, final Temporada temporadaSegunda)
			throws Exception {
		System.out
				.println("Pintando GRAFICOS para comparar los algoritmos ...");
		System.out
				.println("ENTRADA: Temporada, resultados reales y resultados pronosticados.");
		System.out
				.println("Aplicación del algoritmo a todos los ficheros de predicción:");
		algoritmos = algoritmosUsados;
		graficosDivision(temporadaPrimera, Division.PRIMERA);
		graficosDivision(temporadaSegunda, Division.SEGUNDA);
	}

	private static void graficosDivision(Temporada temporada, Division division)
			throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();
		for (Jornada jornada : temporada.getJornadasPasadas()) {
			pronosticosJornadas.addAll(pronosticarJornada(jornada, division));
		}

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadas, obtenerResultadosReales(division));
		graficoNumAciertosVsJornada(inDto, division);
	}

	/**
	 * Aplicando todos los algoritmos, pronostica la jornada entera.
	 * 
	 * @param jornada
	 * @return Lista de pronosticos de jornada, uno por cada algoritmo.
	 * @throws Exception
	 */
	private static List<PronosticoJornada> pronosticarJornada(Jornada jornada,
			Division division) throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();

		// EXTRAIGO INFO DE CADA PARTIDO PASADO
		List<PronosticoPartido> partidos = new ArrayList<PronosticoPartido>();
		for (Partido partido : jornada.getPartidos()) {
			PronosticoPartido pronostico = new PronosticoPartido();
			pronostico.setPartido(partido);
			partidos.add(pronostico);
		}

		// TODOS LOS ALGORITMOS
		for (AbstractAlgoritmo algor : algoritmos) {

			if (division.equals(Division.PRIMERA)) {
				algor.setEstimacionJornadaPrimera(new PronosticoJornada(
						partidos, jornada.getNumeroJornada(), algor.getId()));
				algor.calcularPronosticoPrimera();
				pronosticosJornadas.add(algor.getEstimacionJornadaPrimera());

			} else if (division.equals(Division.SEGUNDA)) {
				algor.setEstimacionJornadaSegunda(new PronosticoJornada(
						partidos, jornada.getNumeroJornada(), algor.getId()));
				algor.calcularPronosticoSegunda();
				pronosticosJornadas.add(algor.getEstimacionJornadaSegunda());
			}
		}

		return pronosticosJornadas;
	}

	private static List<PronosticoJornada> obtenerResultadosReales(
			Division division) {

		// TODO Quitar MOCK. Rellenar los resultados reales!!!!!!
		List<PronosticoJornada> resultadosReales = GraficoAciertosJornadaTest
				.generarPronosticosJornadaAlgoritmoDivisionMock(
						IdAlgoritmoEnum.REAL, division);
		return resultadosReales;

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

	/**
	 * @return the algoritmos
	 */
	public static List<AbstractAlgoritmo> getAlgoritmos() {
		return algoritmos;
	}

	/**
	 * @param algoritmos
	 *            the algoritmos to set
	 */
	public static void setAlgoritmos(List<AbstractAlgoritmo> algoritmos) {
		AnalizadorDelPasado.algoritmos = algoritmos;
	}

}
