package es.propio.cargadorInfoWeb.test;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Temporada;

public class HandlerHtmlInfo {
	static final Logger logger = Logger.getLogger(HandlerHtmlInfo.class);
	private Temporada temporada;

	public void extraerDatos(final String paginaHtml) {

		// División
		if (paginaHtml.lastIndexOf("<strong>Primera</strong>") != -1) {
			temporada.setDivision(Division.PRIMERA);
		} else if (paginaHtml.lastIndexOf("<strong>Segunda</strong>") != -1) {
			temporada.setDivision(Division.SEGUNDA);
		} else {
			logger.error("La DIVISIÓN de la temporada no se ha encontrado");
			return;
		}

		String cadenaABuscar = "/deportes/futbol/partido/";
		int lastIndex = 0;
		String cadenaAParsear, equipoLocal, equipoVisitante;
		Integer golesLocal, golesVisitante;
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
				cadenaAParsear.substring(0,
						cadenaAParsear.indexOf("08", lastIndex));
			}
		}
	}

	/**
	 * @return the temporada
	 */
	public Temporada getTemporada() {
		return temporada;
	}

	/**
	 * @param temporada
	 *            the temporada to set
	 */
	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

}
