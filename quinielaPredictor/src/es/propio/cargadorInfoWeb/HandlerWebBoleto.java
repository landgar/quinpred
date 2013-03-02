package es.propio.cargadorInfoWeb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Partido;

public class HandlerWebBoleto {

	static final Logger logger = Logger.getLogger(HandlerHtmlResultados.class);

	/**
	 * 
	 * @param paginaHtml
	 * @param boleto
	 * @return True si se ha rellenado el boleto. False si no. No se rellena si
	 *         los datos le�dos contienen equipos que no est�n en el sistema
	 *         (champions, 2�B, etc).
	 */
	public static Boolean extraerDatos(final String paginaHtml, Boleto boleto)
			throws Exception {
		Boolean salida = Boolean.FALSE;
		if (boleto.getPartidos() == null) {
			boleto.setPartidos(new HashMap<Integer, Partido>());
		}
		String cadenaABuscar = "calendario/partido";
		int lastIndex = 0;
		String cadenaAParsear, cadenaAParsear2, aux;
		cadenaAParsear = paginaHtml;
		Partido partido;
		Equipo local, visitante;
		Integer i = 0;
		while (lastIndex != -1) {
			lastIndex = paginaHtml.indexOf(cadenaABuscar, lastIndex);
			if (lastIndex != -1) {
				lastIndex += cadenaABuscar.length();
				lastIndex = paginaHtml.indexOf(">", lastIndex) + 1;
				// Tendr� que parsear:
				// C�rdoba-Xerez</a></td>
				// <td align="right" valign="bottom" class="separador">8</td>
				cadenaAParsear = paginaHtml.substring(lastIndex,
						paginaHtml.indexOf("</", lastIndex));
				cadenaAParsear2 = paginaHtml.substring(paginaHtml.indexOf(
						"\">", lastIndex) + 2);
				cadenaAParsear2 = cadenaAParsear2.substring(0,
						cadenaAParsear2.indexOf("<"));
				Pattern p = Pattern
						.compile("^([^>]+)([>]+)([0-9]+)([<]+)([^<]+).");
				aux = paginaHtml.substring(paginaHtml.indexOf("bottom",
						lastIndex + cadenaAParsear2.length()));
				Matcher m = p.matcher(aux);
				Integer posicion = 0;
				if (m.find()) {
					posicion = Integer.valueOf(m.group(3));
				} else {
					posicion = 15;// Pleno al 15.
				}
				String equipo1NombrePropio = "", equipo2NombrePropio = "";

				aux = cadenaAParsear;
				p = Pattern.compile("^([^-]+)-([^-]+)$");
				m = p.matcher(aux);
				// C�rdoba-Xerez
				if (m.find()) {
					equipo1NombrePropio = m.group(1);
					equipo2NombrePropio = m.group(2);
				}

				// Se rellena el boleto
				equipo1NombrePropio = conversionNombreEquipos(equipo1NombrePropio);
				local = new Equipo(equipo1NombrePropio);
				equipo2NombrePropio = conversionNombreEquipos(equipo2NombrePropio);
				visitante = new Equipo(equipo2NombrePropio);
				partido = new Partido(Boolean.FALSE);
				partido.setEquipoLocal(local);
				partido.setEquipoVisitante(visitante);
				boleto.getPartidos().put(posicion, partido);
			}
		}
		cadenaABuscar = "Quiniela de la Jornada ";
		i = paginaHtml.indexOf(cadenaABuscar) + cadenaABuscar.length();
		aux = paginaHtml.substring(i, paginaHtml.indexOf("<", i));
		// 22, 02/12/2012
		Pattern p = Pattern.compile("^([0-9]+)([, ]+)([0-9/]+)$");
		Matcher m = p.matcher(aux);
		if (m.find()) {
			boleto.setNumeroBoleto(Integer.valueOf(m.group(1)));
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			aux = m.group(3);
			boleto.setFecha(formateador.parse(aux));
		}

		return salida;
	}

	private static String conversionNombreEquipos(String nombreEquipoWeb) {
		String salida;
		// Primera divisi�n
		if (nombreEquipoWeb.equals("Celta")) {
			salida = "Celta";
		} else if (nombreEquipoWeb.equals("M�laga")) {
			salida = "Malaga";
		} else if (nombreEquipoWeb.equals("Zaragoza")) {
			salida = "Real-Zaragoza";
		} else if (nombreEquipoWeb.equals("Valladolid")) {
			salida = "Valladolid";
		} else if (nombreEquipoWeb.equals("Rayo Vallecano")) {
			salida = "Rayo";
		} else if (nombreEquipoWeb.equals("Granada")) {
			salida = "Granada";

		} else if (nombreEquipoWeb.equals("Real Madrid")) {
			salida = "Real-Madrid";

		} else if (nombreEquipoWeb.equals("Valencia")) {
			salida = "Valencia";

		} else if (nombreEquipoWeb.equals("Levante")) {
			salida = "Levante";

		} else if (nombreEquipoWeb.equals("Atl�tico de Madrid")) {
			salida = "Atletico";

		} else if (nombreEquipoWeb.equals("Deportivo")) {
			salida = "Deportivo";

		} else if (nombreEquipoWeb.equals("Osasuna")) {
			salida = "Osasuna";

		} else if (nombreEquipoWeb.equals("Mallorca")) {
			salida = "Mallorca";

		} else if (nombreEquipoWeb.equals("Espanyol")) {
			salida = "Espanyol";

		} else if (nombreEquipoWeb.equals("Sevilla")) {
			salida = "Sevilla";

		} else if (nombreEquipoWeb.equals("Getafe")) {
			salida = "Getafe";

		} else if (nombreEquipoWeb.equals("Athletic de Bilbao")) {
			salida = "Athletic";

		} else if (nombreEquipoWeb.equals("Betis")) {
			salida = "Betis";

		} else if (nombreEquipoWeb.equals("Barcelona")) {
			salida = "Barcelona";

		} else if (nombreEquipoWeb.equals("Real Sociedad")) {
			salida = "R-Sociedad";

		}
		// Segunda divisi�n
		else if (nombreEquipoWeb.equals("Racing de Santander")) {
			salida = "Racing";

		} else if (nombreEquipoWeb.equals("Las Palmas")) {
			salida = "Palmas";

		} else if (nombreEquipoWeb.equals("Mirand�s")) {
			salida = "Mirandes";

		} else if (nombreEquipoWeb.equals("Huesca")) {
			salida = "Huesca";

		} else if (nombreEquipoWeb.equals("Lugo")) {
			salida = "Lugo";

		} else if (nombreEquipoWeb.equals("H�rcules")) {
			salida = "Hercules";

		} else if (nombreEquipoWeb.equals("Xerez")) {
			salida = "Xerez";

		} else if (nombreEquipoWeb.equals("Villarreal")) {
			salida = "Villarreal";

		} else if (nombreEquipoWeb.equals("Real Madrid Castilla")) {
			salida = "RM-Castilla";

		} else if (nombreEquipoWeb.equals("Barcelona B")) {
			salida = "Barcelona-B";

		} else if (nombreEquipoWeb.equals("Almer�a")) {
			salida = "Almeria";

		} else if (nombreEquipoWeb.equals("Elche")) {
			salida = "Elche";

		} else if (nombreEquipoWeb.equals("C�rdoba")) {
			salida = "Cordoba";

		} else if (nombreEquipoWeb.equals("Sporting de Gij�n")) {
			salida = "Sporting";

		} else if (nombreEquipoWeb.equals("Murcia")) {
			salida = "Murcia";

		} else if (nombreEquipoWeb.equals("Alcorc�n")) {
			salida = "Alcorcon";

		} else if (nombreEquipoWeb.equals("Guadalajara")) {
			salida = "Guadalajara";

		} else if (nombreEquipoWeb.equals("Recreativo de Huelva")) {
			salida = "Recreativo";

		} else if (nombreEquipoWeb.equals("Ponferradina")) {
			salida = "Ponferradina";

		} else if (nombreEquipoWeb.equals("Sabadell")) {
			salida = "Sabadell";

		} else if (nombreEquipoWeb.equals("Girona")) {
			salida = "Girona";

		} else if (nombreEquipoWeb.equals("Numancia")) {
			salida = "Numancia";

		} else {
			salida = "";
		}
		if (nombreEquipoWeb.equals("")) {
			logger.error("No se ha podido encontrar una conversi�n de nombre de equipo para el equipo con nombre web: "
					+ nombreEquipoWeb);
		}
		return salida;
	}
}
