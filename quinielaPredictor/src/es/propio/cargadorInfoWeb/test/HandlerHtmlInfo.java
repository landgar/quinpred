package es.propio.cargadorInfoWeb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

public class HandlerHtmlInfo {
	static final Logger logger = Logger.getLogger(HandlerHtmlInfo.class);

	public static void extraerDatos(final String paginaHtml, Temporada temporada) {

		// Divisi�n
		if (paginaHtml.lastIndexOf("<strong>Primera</strong>") != -1) {
			temporada.setDivision(Division.PRIMERA);
		} else if (paginaHtml.lastIndexOf("<strong>Segunda</strong>") != -1) {
			temporada.setDivision(Division.SEGUNDA);
		} else {
			logger.error("La DIVISI�N de la temporada no se ha encontrado");
			return;
		}

		String cadenaABuscar = "/deportes/futbol/partido/";
		int lastIndex = 0;
		String cadenaAParsear;
		Equipo local, visitante;
		Integer golesLocal, golesVisitante, numeroJornada, numeroJornadaAnterior = -1;
		Partido partido;
		Jornada jornada;
		Set<Partido> partidos = new HashSet<Partido>();
		Set<Jornada> jornadas = new HashSet<Jornada>();
		while (lastIndex != -1) {
			lastIndex = paginaHtml.indexOf(cadenaABuscar, lastIndex);
			if (lastIndex != -1) {
				lastIndex += cadenaABuscar.length();

				// Tendr� que parsear:
				// /deportes/futbol/partido/Real-Madrid-Real-Zaragoza-0841_00_10_0013_0022">4-0</a>
				// �
				// /deportes/futbol/partido/Barcelona-B-Racing-0842_00_15_0842_0129">D25</a>
				cadenaAParsear = paginaHtml.substring(lastIndex,
						paginaHtml.indexOf("</a>", lastIndex));
				String nombresEquipos = cadenaAParsear.substring(0,
						cadenaAParsear.indexOf("08"));
				String numeros = cadenaAParsear.substring(cadenaAParsear
						.indexOf("08"));
				// Se asume que nunca se meter�n m�s de 9 goles por un equipo.
				String resultado = cadenaAParsear.substring(cadenaAParsear
						.length() - 3);
				try {
					golesLocal = Integer.valueOf(resultado.substring(0, 1));

					golesVisitante = Integer.valueOf(resultado.substring(2, 3));
				} catch (NumberFormatException ex) {
					// Partidos sin resultado todav�a. Les ponemos un -1
					golesLocal = -1;
					golesVisitante = -1;
				}
				List<String> listaNumeros = Arrays.asList(numeros.split("_"));
				numeroJornada = Integer.valueOf(listaNumeros.get(3));
				System.out.println(nombresEquipos);
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

				partidos.add(partido);
				if (numeroJornada != numeroJornadaAnterior) {
					// Jornada completada con sus partidos
					jornada = new Jornada(partidos, numeroJornada);
					jornadas.add(jornada);
					partidos = new HashSet<Partido>();
				}

				numeroJornadaAnterior = numeroJornada;
			}
		}
		temporada.setJornadas(jornadas);
	}
}
