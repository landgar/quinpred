package es.propio.cargadorInfoWeb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

/**
 * Parser desde un String (html) hacia estructura de clases Java.
 * 
 */
public class HandlerHtmlResultados {

	static final Logger logger = Logger.getLogger(HandlerHtmlResultados.class);

	/**
	 * @param paginaHtml
	 *            Pagina de EL PAIS con los resultados de todas las jornadas
	 *            hasta la actual. Es la pagina de una Division. Incluye
	 *            partidos de ida y vuelta.
	 * @return
	 */
	public static Temporada extraerDatos(final String paginaHtml) {

		Document documento = Jsoup.parse(paginaHtml);

		Temporada temporada = new Temporada();
		temporada.setDivision(extraerDivision(documento));

		// Resultados con enlace a otra web
		Integer totalJornadas;
		List<Jornada> jornadas;

		if (temporada.getDivision().equals(Division.PRIMERA)) {
			totalJornadas = (Temporada.NUM_EQUIPOS_PRIMERA - 1) * 2;
		} else if (temporada.getDivision().equals(Division.SEGUNDA)) {
			totalJornadas = (Temporada.NUM_EQUIPOS_SEGUNDA - 1) * 2;
		} else {
			logger.error("No se pueden crear jornadas para la division: "
					+ temporada.getDivision().getCodigo());
			return temporada;
		}
		// Inicializacion de las jornadas
		jornadas = new ArrayList<Jornada>(totalJornadas);
		for (int i = 1; i <= totalJornadas; i++) {
			Jornada jornada = new Jornada(new HashSet<Partido>(), i);
			jornadas.add(jornada);
		}

		temporada.setJornadas(jornadas);

		Elements divTablasDosJornadas = documento
				.getElementsByClass("jornada-container");
		Elements tablasDosJornadas = divTablasDosJornadas.select("table");

		int numTablasDosJornadas = tablasDosJornadas.size();
		for (int i = 0; i < numTablasDosJornadas; i++) {
			gestionarTablaDosJornadas(tablasDosJornadas.get(i), temporada);
		}

		return temporada;

	}

	private static Division extraerDivision(Document documento) {
		Division division = null;

		Elements cabeceraDeportes = documento
				.getElementsByClass("cab-deportes");
		Elements titulos = cabeceraDeportes.select("h2");
		Elements elementsDivision = titulos.select("strong");
		Elements divisionStrElements = elementsDivision.select("strong");
		String divisionStr = divisionStrElements.get(0).text();

		if (divisionStr.trim().equalsIgnoreCase("Primera")) {
			division = Division.PRIMERA;
		} else if (divisionStr.trim().equalsIgnoreCase("Segunda")) {
			division = Division.SEGUNDA;
		} else {
			logger.error("La DIVISI�N de la temporada no se ha encontrado");
		}

		return division;
	}

	private static void gestionarTablaDosJornadas(Element tablaDosJornadas,
			Temporada temporada) {

		Elements cabecera = tablaDosJornadas.getElementsByClass("cab");
		String cabStr = cabecera.get(0).text();
		cabStr = cabStr.split("Jornada")[1];
		String[] dosJornadas = cabStr.split("y");
		Integer jornada1 = Integer.valueOf(dosJornadas[0].trim());
		Integer jornada2 = Integer.valueOf(dosJornadas[1].trim());

		Elements cuerpo = tablaDosJornadas.select("tbody");
		Elements cuerpoFilas = cuerpo.get(0).getElementsByClass("linea");

		for (int j = 0; j < cuerpoFilas.size(); j++) {

			Element fila = cuerpoFilas.get(j);

			gestionarFilaEnTablaDosJornadas(fila, temporada, jornada1, jornada2);

		}
	}

	private static void gestionarFilaEnTablaDosJornadas(Element fila,
			Temporada temporada, Integer jornada1, Integer jornada2) {

		Elements equiposFila = fila.getElementsByClass("eq");

		String equipo1NombrePropio = equiposFila.get(0).text();
		String equipo2NombrePropio = equiposFila.get(1).text();

		equipo1NombrePropio = ConversorElPais
				.conversionNombreEquipos(equipo1NombrePropio);
		equipo2NombrePropio = ConversorElPais
				.conversionNombreEquipos(equipo2NombrePropio);

		// TODO rellenar
		Elements resultadoIdaVuelta = fila.getElementsByClass("res");

		// Jornada1: IDA
		Element ida = resultadoIdaVuelta.get(0);
		procesarResultadoPartido(ida, temporada, jornada1, equipo1NombrePropio,
				equipo2NombrePropio, true);

		// Jornada2: vuelta
		Element vuelta = resultadoIdaVuelta.get(1);
		procesarResultadoPartido(vuelta, temporada, jornada2,
				equipo1NombrePropio, equipo2NombrePropio, false);

	}

	/**
	 * @param resultadoOfecha
	 * @param temporada
	 * @param jornada
	 * @param nombreEquipo1
	 * @param nombreEquipo2
	 * @param esResultadoIda
	 *            Indica si es un resultado del partido de ida (true) o de
	 *            vuelta (false). Si es de vuelta, el resultado que muestra la
	 *            pagina de El Pais es golesVisitante-golesLocal, asi que le
	 *            damos la vuelta.
	 */
	private static void procesarResultadoPartido(Element resultadoOfecha,
			Temporada temporada, Integer jornada, String nombreEquipo1,
			String nombreEquipo2, boolean esResultadoIda) {

		Integer localGoles = -1;
		Integer visitanteGoles = -1;

		// Jugado, con link
		Elements jugadoConLink = resultadoOfecha.select("a");
		if (!jugadoConLink.isEmpty()) {
			String ambosStr = jugadoConLink.get(0).text();
			if (ambosStr.contains("-")) {
				String[] ambos = ambosStr.split("-");
				localGoles = esResultadoIda ? Integer.valueOf(ambos[0])
						: Integer.valueOf(ambos[1]);
				visitanteGoles = esResultadoIda ? Integer.valueOf(ambos[1])
						: Integer.valueOf(ambos[0]);
			}

		} else {

			Elements jugadoSinLink = resultadoOfecha.select("div");
			if (!jugadoSinLink.isEmpty()) {
				String ambosStr = jugadoSinLink.get(0).text();
				if (ambosStr.contains("-")) {
					String[] ambos = ambosStr.split("-");
					localGoles = esResultadoIda ? Integer.valueOf(ambos[0])
							: Integer.valueOf(ambos[1]);
					visitanteGoles = esResultadoIda ? Integer.valueOf(ambos[1])
							: Integer.valueOf(ambos[0]);
				}

			} else {
				// el resto son no jugados todavia
				// System.out.println(resultadoOfecha.text());
			}

		}

		Boolean partidoJugado = false;

		if (localGoles.intValue() != -1 && visitanteGoles.intValue() != -1) {
			// JUGADO
			partidoJugado = true;
		}

		List<String> lista = new ArrayList<String>();
		lista.add(nombreEquipo1);
		Equipo local = new Equipo(temporada.getDivision(), lista);

		if (local.getNombre().equals("invalido")) {
			System.out.println("Invalido=" + lista.get(0));
		}

		lista = new ArrayList<String>();
		lista.add(nombreEquipo2);
		Equipo visitante = new Equipo(temporada.getDivision(), lista);

		if (visitante.getNombre().equals("invalido")) {
			System.out.println("Invalido=" + lista.get(0));
		}

		Partido partido = new Partido(partidoJugado);
		partido.setEquipoLocal(local);
		partido.setEquipoVisitante(visitante);
		partido.setGolesLocal(localGoles);
		partido.setGolesVisitante(visitanteGoles);

		if (localGoles != -1 || visitanteGoles != -1) {
			// Solo para Log (es util para ver si leemos bien la VUELTA)
			String idaVueltaStr = esResultadoIda ? "IDA" : "VUELTA";
//			System.out
//					.println("HandlerHtmlResultados.procesarResultadoPartido() PARTIDO JUGADO -> "
//							+ idaVueltaStr
//							+ " local="
//							+ nombreEquipo1
//							+ " vis="
//							+ nombreEquipo2
//							+ " -->"
//							+ localGoles
//							+ "-" + visitanteGoles);
		}

		meterPartidoEnJornada(temporada.getJornadas(), jornada, partido);

	}

	public static void meterPartidoEnJornada(List<Jornada> jornadas,
			final Integer numeroJornada, final Partido partido) {
		for (Jornada jornada : jornadas) {
			if (jornada.getNumeroJornada().equals(numeroJornada)) {
				jornada.getPartidos().add(partido);
			}
		}
	}
}
