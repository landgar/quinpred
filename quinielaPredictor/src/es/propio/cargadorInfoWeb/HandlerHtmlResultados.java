package es.propio.cargadorInfoWeb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

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

	public static Temporada extraerDatos(final String paginaHtml) {
		Temporada temporada = new Temporada();
		// División
		if (paginaHtml.lastIndexOf("<strong>Primera</strong>") != -1) {
			temporada.setDivision(Division.PRIMERA);
		} else if (paginaHtml.lastIndexOf("<strong>Segunda</strong>") != -1) {
			temporada.setDivision(Division.SEGUNDA);
		} else {
			logger.error("La DIVISIÓN de la temporada no se ha encontrado");
		}

		// Resultados con enlace a otra web
		String cadenaABuscar = "/deportes/futbol/partido/";
		int lastIndex = 0;
		String cadenaAParsear = paginaHtml;
		Equipo local, visitante;
		Integer golesLocal, golesVisitante, totalJornadas, numeroJornada = 0;
		Partido partido;
		List<Jornada> jornadas;

		if (temporada.getDivision().equals(Division.PRIMERA)) {
			totalJornadas = (Temporada.NUM_EQUIPOS_PRIMERA - 1) * 2;
		} else if (temporada.getDivision().equals(Division.SEGUNDA)) {
			totalJornadas = (Temporada.NUM_EQUIPOS_SEGUNDA - 1) * 2;
		} else {
			logger.error("No se pueden crear jornadas para la división: "
					+ temporada.getDivision().getCodigo());
			return temporada;
		}
		// Inicialización de las jornadas
		jornadas = new ArrayList<Jornada>(totalJornadas);
		for (int i = 1; i <= totalJornadas; i++) {
			Jornada jornada = new Jornada(new HashSet<Partido>(), i);
			jornadas.add(jornada);
		}

		temporada.setJornadas(jornadas);

		// Resultados sin enlace a otra web
		lastIndex = 0;
		cadenaABuscar = "\"><a name=\"Jornada";
		Integer jornada1, jornada2;
		String aux, equipo1NombrePropio, equipo2NombrePropio, resultados;
		while (lastIndex != -1) {
			lastIndex = paginaHtml.indexOf(cadenaABuscar, lastIndex);
			if (lastIndex != -1) {
				lastIndex += cadenaABuscar.length();

				// Tomaré los bloques de tabla, precedidos por un nombre de
				// jornada:
				// <a name="Jornada1"></a>
				// <a name="Jornada22"></a>
				// <table cellspacing="0">
				// ...
				// </table>
				// Para ello, buscaré las jornadas
				cadenaAParsear = paginaHtml.substring(lastIndex,
						paginaHtml.indexOf("</table>", lastIndex));

				// Se guardan las dos jornadas de la tabla
				String cadena = "<a name=\"Jornada";
				aux = cadenaAParsear.substring(0,
						cadenaAParsear.indexOf("\"></a>"));
				jornada1 = Integer.valueOf(aux);
				aux = cadenaAParsear.substring(5);
				aux = aux.substring(aux.indexOf(cadena) + cadena.length(),
						aux.indexOf("\"></a>"));
				jornada2 = Integer.valueOf(aux);

				// Se toman los nombres de los equipos de dentro del id
				// de la etiqueta html, y luego se hace una conversión al equipo
				// del sistema.

				cadena = "<td class=\"eq\" id=\"";
				String cadena2;
				while (cadenaAParsear.indexOf(cadena) != -1) {
					cadena2 = "<div>";
					cadenaAParsear = cadenaAParsear.substring(cadenaAParsear
							.indexOf(cadena) + cadena.length());
					equipo1NombrePropio = cadenaAParsear.substring(0,
							cadenaAParsear.indexOf("\""));
					cadenaAParsear = cadenaAParsear.substring(cadenaAParsear
							.indexOf(cadena) + cadena.length());
					equipo2NombrePropio = cadenaAParsear.substring(0,
							cadenaAParsear.indexOf("\""));

					equipo1NombrePropio = ConversorElPais
							.conversionNombreEquipos(equipo1NombrePropio);
					equipo2NombrePropio = ConversorElPais
							.conversionNombreEquipos(equipo2NombrePropio);

					cadenaAParsear = cadenaAParsear.substring(cadenaAParsear
							.indexOf(cadena2) + cadena2.length());
					Pattern p, p2;
					Matcher m, m2;
					p = Pattern.compile("^([0-9])");
					m = p.matcher(cadenaAParsear);
					if (!m.find()) {
						cadena2 = ">";
						cadenaAParsear = cadenaAParsear
								.substring(cadenaAParsear.indexOf(cadena2)
										+ cadena2.length());
					}
					// Resultados del primer y segundo bloque de jornadas
					// encontrados
					for (int i = 0; i < 2; i++) {
						numeroJornada = 0;
						if (i == 0) {
							numeroJornada = jornada1;
						} else if (i == 1) {
							numeroJornada = jornada2;
							cadena2 = "ult\"><p>";
							cadenaAParsear = cadenaAParsear
									.substring(cadenaAParsear.indexOf(cadena2)
											+ cadena2.length());
							p = Pattern
									.compile("([>]+)([/a-zA-Z0-9-]+)([</a>]*)([</div>]*)([</span>]*)");
							m = p.matcher(cadenaAParsear);
							if (m.find()) {
								// <span>D20</span> </p>
								resultados = m.group(2);
								if (resultados
										.contains("/deportes/futbol/partido")) {
									// <div><a
									// href="/deportes/futbol/partido/Real-Zaragoza-Valladolid-0841_00_01_0022_0021">0-1</a></div>
									p = Pattern
											.compile("([>]+)([/a-zA-Z0-9-]+)([</a>]*)([</div>]*)([</span>]*)");
									m = p.matcher(cadenaAParsear);
									resultados = m.group(2);
								}
							}
						}
						golesLocal = -1;
						golesVisitante = -1;
						Boolean partidoJugado = Boolean.FALSE;
						p = Pattern.compile("^([0-9]+)-([0-9]+)</span>");
						m = p.matcher(cadenaAParsear);
						p2 = Pattern
								.compile("^([0-9]+)-([0-9]+)([</a>]*)</div>");
						m2 = p2.matcher(cadenaAParsear);
						if (m.find()) {
							// Partido todavía no jugado
							partidoJugado = Boolean.FALSE;
						} else if (m2.find()) {
							resultados = cadenaAParsear.substring(0,
									cadenaAParsear.indexOf("<"));
							p = Pattern.compile("^([0-9]+)-([0-9]+)$");
							m = p.matcher(resultados);
							// El primer caracter es un número.
							// Tendremos un resultado:
							// 2-5
							if (m.find()) {
								golesLocal = Integer.parseInt(m.group(1));
								golesVisitante = Integer.parseInt(m.group(2));
								partidoJugado = Boolean.TRUE;
							}
						}
						List<String> lista = new ArrayList<String>();
						lista.add(equipo1NombrePropio);
						local = new Equipo(temporada.getDivision(), lista);
						lista = new ArrayList<String>();
						lista.add(equipo2NombrePropio);
						visitante = new Equipo(temporada.getDivision(), lista);
						partido = new Partido(partidoJugado);
						partido.setEquipoLocal(local);
						partido.setEquipoVisitante(visitante);
						partido.setGolesLocal(golesLocal);
						partido.setGolesVisitante(golesVisitante);

						meterPartidoEnJornada(temporada.getJornadas(),
								numeroJornada, partido);

					}
				}
			}
		}
		return temporada;
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
