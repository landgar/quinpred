/**
 * 
 */
package es.propio.handlerDatos;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import es.propio.lectorxml.HandlerTxtNombresEquipos;
import es.propio.lectorxml.HandlerTxtResultados;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;

/**
 * @author i3casa
 * 
 */
public class CombinadorInfoJornadas {

	/**
	 * 
	 * @return Información de todas jornadas almacenadas.
	 */
	public static Set<Jornada> obtenerTodaInfoJornadas() throws Exception {
		// Precarga Jornadas
		File folder = new File("resultadosPartidos_LoteriasYApuestas/");
		Set<Jornada> jornadas = new HashSet<Jornada>();
		for (final File fileEntry : folder.listFiles()) {
			HandlerTxtResultados lector = new HandlerTxtResultados();
			lector.leer(fileEntry);
			jornadas.add(lector.getJornada());
		}

		// Se leen los datos de los equipos, para cada jornada
		folder = new File("nombresPartidos_LoteriasYApuestas/");
		Set<Jornada> jornadas2 = new HashSet<Jornada>();
		for (final File fileEntry : folder.listFiles()) {
			HandlerTxtNombresEquipos lectorNombres = new HandlerTxtNombresEquipos();
			lectorNombres.leer(fileEntry);
			jornadas2.add(lectorNombres.getJornada());
		}

		// Finalmente, se combinan los nombres de los equipos y número de
		// jornada, con sus resultados
		// en un mismo objeto jornada. Se toman las jornadas más próximas en
		// tiempo.
		Set<Jornada> jornadasFinales = new HashSet<Jornada>();
		for (Jornada jornada : jornadas2) {
			Jornada jornadaMasProxima = jornada.getNearestDate(jornadas);
			// Añado los resultados a las jornadas
			if (jornadaMasProxima != null) {
				jornadaMasProxima.setNumeroJornada(jornada.getNumeroJornada());
				for (Partido partido2 : jornadaMasProxima.getPartidos()) {
					for (Partido partido : jornada.getPartidos()) {
//						if (partido.getPosicion()
//								.equals(partido2.getPosicion())) {
//							partido2.setEquipoLocal(partido.getEquipoLocal());
//							partido2.setEquipoVisitante(partido
//									.getEquipoVisitante());
//						}
					}
				}
			} else {
				// Si no se ha encontrado ninguna jornada próxima con
				// resultados, se añade la jornada gestionada sin resultados.
				jornadaMasProxima = jornada;
			}
			jornadasFinales.add(jornadaMasProxima);
		}
		return jornadasFinales;
	}

}
