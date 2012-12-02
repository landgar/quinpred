package es.propio.cargadorInfoWeb;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

public class HandlerHtmlResultados {
	static final Logger logger = Logger.getLogger(HandlerHtmlResultados.class);

	public static void extraerDatos(final String paginaHtml, Temporada temporada) {

		// División
		if (paginaHtml.lastIndexOf("<strong>Primera</strong>") != -1) {
			temporada.setDivision(Division.PRIMERA);
		} else if (paginaHtml.lastIndexOf("<strong>Segunda</strong>") != -1) {
			temporada.setDivision(Division.SEGUNDA);
		} else {
			logger.error("La DIVISIÓN de la temporada no se ha encontrado");
			return;
		}

		// Resultados con enlace a otra web
		String cadenaABuscar = "/deportes/futbol/partido/";
		int lastIndex = 0;
		String cadenaAParsear = paginaHtml;
		Equipo local, visitante;
		Integer golesLocal, golesVisitante, numeroJornada = 0, numeroJornadaAnterior = 1;
		Partido partido;
		Jornada jornada;
		Set<Partido> partidos = new HashSet<Partido>();
		Set<Jornada> jornadas = new HashSet<Jornada>();
		while (lastIndex != -1) {
			lastIndex = paginaHtml.indexOf(cadenaABuscar, lastIndex);
			if (lastIndex != -1) {
				lastIndex += cadenaABuscar.length();

				// Tendré que parsear:
				// /deportes/futbol/partido/Real-Madrid-Real-Zaragoza-0841_00_10_0013_0022">4-0</a>
				// ó
				// /deportes/futbol/partido/Barcelona-B-Racing-0842_00_15_0842_0129">D25</a>
				cadenaAParsear = paginaHtml.substring(lastIndex,
						paginaHtml.indexOf("</a>", lastIndex));
				String nombresEquipos = cadenaAParsear.substring(0,
						cadenaAParsear.indexOf("08"));
				String numeros = cadenaAParsear.substring(cadenaAParsear
						.indexOf("08"));
				// Se asume que nunca se meterán más de 9 goles por un equipo.
				String resultado = cadenaAParsear.substring(cadenaAParsear
						.length() - 3);
				try {
					golesLocal = Integer.valueOf(resultado.substring(0, 1));

					golesVisitante = Integer.valueOf(resultado.substring(2, 3));
				} catch (NumberFormatException ex) {
					// Partidos sin resultado todavía. Les ponemos un -1
					golesLocal = -1;
					golesVisitante = -1;
				}
				List<String> listaNumeros = Arrays.asList(numeros.split("_"));
				numeroJornada = Integer.valueOf(listaNumeros.get(2));
				List<String> trozos = Arrays.asList(nombresEquipos.split("-"));
				List<String> posiblesNombresLocal = new ArrayList<>();
				List<String> posiblesNombresVisitante = new ArrayList<>();
				if (trozos.size() == 2) {
					posiblesNombresLocal.add(trozos.get(0));
					posiblesNombresVisitante.add(trozos.get(1));
				} else if (trozos.size() == 3) {
					posiblesNombresLocal.add(trozos.get(0));
					posiblesNombresLocal.add(trozos.get(0) + "-"
							+ trozos.get(1));
					posiblesNombresVisitante.add(trozos.get(1) + "-"
							+ trozos.get(2));
					posiblesNombresVisitante.add(trozos.get(2));
				} else if (trozos.size() == 4) {
					posiblesNombresLocal.add(trozos.get(0));
					posiblesNombresLocal.add(trozos.get(0) + "-"
							+ trozos.get(1));
					posiblesNombresVisitante.add(trozos.get(2) + "-"
							+ trozos.get(3));
					posiblesNombresVisitante.add(trozos.get(3));
				} else {
					logger.error("Se han obtenido unos nombres de equipos inesperados usando esta cadena: "
							+ nombresEquipos);
				}
				local = new Equipo(temporada.getDivision(),
						posiblesNombresLocal);
				visitante = new Equipo(temporada.getDivision(),
						posiblesNombresVisitante);
				partido = new Partido();
				partido.setEquipoLocal(local);
				partido.setEquipoVisitante(visitante);
				partido.setGolesLocal(golesLocal);
				partido.setGolesVisitante(golesVisitante);

				if (numeroJornada != numeroJornadaAnterior
						&& partidos.size() > 0) {
					// Jornada completada con sus partidos
					jornada = new Jornada(partidos, numeroJornadaAnterior);
					jornadas.add(jornada);
					partidos = new HashSet<Partido>();
				}
				partidos.add(partido);

				numeroJornadaAnterior = numeroJornada;
			}
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
				String cadena2 = "<div>";
				if (temporada.getDivision().equals(Division.SEGUNDA)) {
					while (cadenaAParsear.indexOf(cadena) != -1) {

						cadenaAParsear = cadenaAParsear
								.substring(cadenaAParsear.indexOf(cadena)
										+ cadena.length());
						equipo1NombrePropio = cadenaAParsear.substring(0,
								cadenaAParsear.indexOf("\""));
						cadenaAParsear = cadenaAParsear
								.substring(cadenaAParsear.indexOf(cadena)
										+ cadena.length());
						equipo2NombrePropio = cadenaAParsear.substring(0,
								cadenaAParsear.indexOf("\""));

						equipo1NombrePropio = conversionNombreEquipos(equipo1NombrePropio);
						equipo2NombrePropio = conversionNombreEquipos(equipo2NombrePropio);

						// Se toman los resultados, sólo si son numéricos y no
						// tienen enlaces.

						cadenaAParsear = cadenaAParsear
								.substring(cadenaAParsear.indexOf(cadena2)
										+ cadena2.length());
						// Resultados del primer y segundo bloque de jornadas
						// encontrados
						for (int i = 0; i < 2; i++) {
							numeroJornada = 0;
							if (i == 0) {
								numeroJornada = jornada1;
							} else if (i == 1) {
								numeroJornada = jornada2;
							}

							if (cadenaAParsear.indexOf("</div>") != -1) {
								resultados = cadenaAParsear.substring(0,
										cadenaAParsear.indexOf("</div>"));
								Pattern p = Pattern
										.compile("^([0-9]+)-([0-9]+)$");
								Matcher m = p.matcher(resultados);
								// El primer caracter es un número. Tendremos un
								// resultado:
								// 2-5
								if (m.find()) {
									golesLocal = Integer.parseInt(m.group(1));
									golesVisitante = Integer.parseInt(m
											.group(2));
									jornadas = temporada.getJornadas();
									List<String> lista = new ArrayList<String>();
									lista.add(equipo1NombrePropio);
									local = new Equipo(temporada.getDivision(),
											lista);
									lista = new ArrayList<String>();
									lista.add(equipo2NombrePropio);
									visitante = new Equipo(
											temporada.getDivision(), lista);
									partido = new Partido();
									partido.setEquipoLocal(local);
									partido.setEquipoVisitante(visitante);
									partido.setGolesLocal(golesLocal);
									partido.setGolesVisitante(golesVisitante);

									meterPartidoEnJornada(
											temporada.getJornadas(),
											numeroJornada, partido);
								}
							}
						}
					}
				}
			}
		}

	}

	public static String conversionNombreEquipos(final String nombreEquipoWeb) {
		String nombreEquipoSistema = "";
		// Sólo tendremos problemas para los equipos de segunda. Por ello, se
		// implementará esta conversión.
		nombreEquipoSistema = Normalizer.normalize(nombreEquipoWeb,
				Normalizer.Form.NFD);
		nombreEquipoSistema = nombreEquipoSistema.replaceAll("[^\\p{ASCII}]",
				"");
		nombreEquipoSistema = nombreEquipoSistema.replaceAll("-", "");
		// Casos raros
		if (nombreEquipoWeb.equals("LasPalmas")) {
			nombreEquipoSistema = "Palmas";
		} else if (nombreEquipoWeb.equals("RMCastilla")) {
			nombreEquipoSistema = "RM-Castilla";
		} else if (nombreEquipoWeb.equals("BarcelonaB")) {
			nombreEquipoSistema = "Barcelona-B";
		}
		if (nombreEquipoWeb.equals("")) {
			logger.error("No se ha podido encontrar una conversión de nombre de equipo para el equipo con nombre web: "
					+ nombreEquipoWeb);
		}
		return nombreEquipoSistema;
	}

	public static void meterPartidoEnJornada(Set<Jornada> jornadas,
			final Integer numeroJornada, final Partido partido) {
		for (Jornada jornada : jornadas) {
			if (jornada.getNumeroJornada().equals(numeroJornada)) {
				jornada.getPartidos().add(partido);
			}
		}
	}
}
