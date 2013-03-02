package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.ui.RefineryUtilities;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.modeladoInfo.ValorResultado;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Analiza los resultados del pasado.
 * 
 * @author carlos.andres
 * 
 */
public class AnalizadorDelPasado {
	static final Logger logger = Logger.getLogger(AnalizadorDelPasado.class);
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
			final Temporada temporadaPrimera, final Temporada temporadaSegunda,
			boolean graficoVisible) throws Exception {
		// System.out
		// .println("Pintando GRAFICOS para comparar los algoritmos ...");
		// System.out
		// .println("ENTRADA: Temporada, resultados reales y resultados pronosticados.");
		// System.out
		// .println("Aplicación del algoritmo a todos los ficheros de predicción:");
		algoritmos = algoritmosUsados;
		graficosDivision(temporadaPrimera, Division.PRIMERA, graficoVisible);
		graficosDivision(temporadaSegunda, Division.SEGUNDA, graficoVisible);
	}

	private static void graficosDivision(Temporada temporada,
			Division division, boolean graficoVisible) throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();
		for (Jornada jornada : temporada.getJornadasPasadas()) {
			List<PronosticoJornada> pronosticosJornada = pronosticarJornada(
					jornada, division);
			pronosticosJornadas.addAll(pronosticosJornada);
		}

		List<PronosticoJornada> resultadosReales = obtenerResultadosReales(temporada);
		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadas, resultadosReales);

		graficoNumAciertosVsJornada(inDto, division, graficoVisible);
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

		// TODOS LOS ALGORITMOS
		List<PronosticoPartido> pronosticosPartidos;
		for (AbstractAlgoritmo algor : algoritmos) {
			if (division.equals(Division.PRIMERA)) {
				pronosticosPartidos = crearPronosticosPartidosVacios(jornada);
				algor.setEstimacionJornadaPrimera(new PronosticoJornada(
						pronosticosPartidos, jornada.getNumeroJornada(), algor
								.getId()));
				algor.calcularPronosticoPrimera();
				pronosticosJornadas.add(algor.getEstimacionJornadaPrimera());

			} else if (division.equals(Division.SEGUNDA)) {
				pronosticosPartidos = crearPronosticosPartidosVacios(jornada);
				algor.setEstimacionJornadaSegunda(new PronosticoJornada(
						pronosticosPartidos, jornada.getNumeroJornada(), algor
								.getId()));
				algor.calcularPronosticoSegunda();
				pronosticosJornadas.add(algor.getEstimacionJornadaSegunda());
			}
		}

		return pronosticosJornadas;
	}

	private static List<PronosticoPartido> crearPronosticosPartidosVacios(
			final Jornada jornada) {
		List<PronosticoPartido> pronosticoPartidos = new ArrayList<PronosticoPartido>();
		for (Partido partido : jornada.getPartidos()) {
			PronosticoPartido pronostico = new PronosticoPartido();
			pronostico.setPartido(partido);
			pronosticoPartidos.add(pronostico);
		}
		return pronosticoPartidos;
	}

	private static List<PronosticoJornada> obtenerResultadosReales(
			final Temporada temporada) throws Exception {

		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				Principal.MODO_MOCK);
		cargador.cargar();
		List<Jornada> jornadasPasadas = new ArrayList<Jornada>();
		if (temporada.getDivision().equals(Division.PRIMERA)) {
			jornadasPasadas = cargador.getTemporadaPrimera()
					.getJornadasPasadas();
		} else if (temporada.getDivision().equals(Division.SEGUNDA)) {
			jornadasPasadas = cargador.getTemporadaSegunda()
					.getJornadasPasadas();
		}

		List<PronosticoJornada> resultadosReales = new ArrayList<PronosticoJornada>();
		List<PronosticoPartido> pronosticosPartidosReales;
		PronosticoJornada pronosticoJornada;
		PronosticoPartido pronosticoPartido;
		for (Jornada jornada : jornadasPasadas) {
			pronosticoJornada = new PronosticoJornada(
					jornada.getNumeroJornada(), IdAlgoritmoEnum.REAL);
			pronosticosPartidosReales = new ArrayList<PronosticoPartido>();
			for (Partido partido : jornada.getPartidos()) {
				pronosticoPartido = new PronosticoPartido();
				pronosticoPartido.setPartido(partido);
				ValorResultado resultadoReal = partido.getResultadoQuiniela()
						.getValor();
				pronosticoPartido.setPorcentaje1(0F);
				pronosticoPartido.setPorcentajeX(0F);
				pronosticoPartido.setPorcentaje2(0F);
				if (resultadoReal.equals(ValorResultado.UNO)) {
					pronosticoPartido.setPorcentaje1(1F);
				} else if (resultadoReal.equals(ValorResultado.EQUIS)) {
					pronosticoPartido.setPorcentajeX(1F);
				} else if (resultadoReal.equals(ValorResultado.DOS)) {
					pronosticoPartido.setPorcentaje2(1F);
				} else {
					logger.error("El resultado real del partido siguiente no es correcto: "
							+ partido.getEquipoLocal().getNombre()
							+ " - "
							+ partido.getEquipoVisitante().getNombre());
				}
				pronosticosPartidosReales.add(pronosticoPartido);
			}
			pronosticoJornada.setPronosticoPartidos(pronosticosPartidosReales);
			resultadosReales.add(pronosticoJornada);
		}
		// List<PronosticoJornada> resultadosReales = GraficoAciertosJornadaTest
		// .generarPronosticosJornadaAlgoritmoDivisionMock(
		// IdAlgoritmoEnum.REAL, temporada.getDivision());
		return resultadosReales;

	}

	private static void graficoNumAciertosVsJornada(
			EntradaAciertosJornadaDto inDto, Division division,
			boolean graficoVisible) {

		GraficoAciertosJornada grafico = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", inDto, division);
		grafico.pack();
		RefineryUtilities.centerFrameOnScreen(grafico);
		grafico.setVisible(graficoVisible);
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
