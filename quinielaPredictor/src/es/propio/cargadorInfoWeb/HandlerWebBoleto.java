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
	 *         los datos leídos contienen equipos que no están en el sistema
	 *         (champions, 2ªB, etc).
	 */
	public static Boolean extraerDatos(final String paginaHtml, Boleto boleto)
			throws Exception {
		Boolean salida = Boolean.FALSE;
		if (boleto.getPartidos() == null) {
			boleto.setPartidos(new HashMap<Integer, Partido>());
		}
		String cadenaABuscar = " <td nowrap=\"nowrap\" class=\"par separador\">";
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

				// Tendré que parsear:
				// <a
				// href='/segundadivision/calendario/partido/cordoba-xerez'>Córdoba-Xerez</a></td>
				// <td align="right" valign="bottom" class="separador">8</td>
				// ó
				// <td nowrap="nowrap"
				// class="par separador">Barcelona-Alavés</td>
				// <td align="right" valign="bottom" class="separador">4</td
				cadenaAParsear = paginaHtml.substring(lastIndex,
						paginaHtml.indexOf("</td>", lastIndex));
				cadenaAParsear2 = paginaHtml.substring(paginaHtml.indexOf(
						"\">" + 2, lastIndex + cadenaAParsear.length()));
				cadenaAParsear2 = cadenaAParsear2.substring(2,
						cadenaAParsear2.indexOf("</td>")).trim();
				String equipo1NombrePropio = "", equipo2NombrePropio = "";
				Integer posicion;
				if (cadenaAParsear.charAt(0) == '<') {
					aux = cadenaAParsear.substring(
							cadenaAParsear.indexOf("'>") + 2,
							cadenaAParsear.indexOf("</a>"));
				} else {
					aux = cadenaAParsear;
				}
				Pattern p = Pattern.compile("^([^-]+)-([^-]+)$");
				Matcher m = p.matcher(aux);
				// Córdoba-Xerez
				if (m.find()) {
					equipo1NombrePropio = m.group(1);
					equipo2NombrePropio = m.group(2);
				}
				posicion = Integer.valueOf(cadenaAParsear2);

				// Se rellena el boleto
				equipo1NombrePropio = conversionNombreEquipos(equipo1NombrePropio);
				local = new Equipo(equipo1NombrePropio);
				equipo2NombrePropio = conversionNombreEquipos(equipo2NombrePropio);
				visitante = new Equipo(equipo2NombrePropio);
				partido = new Partido();
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
			aux=m.group(3);
			boleto.setFecha(formateador.parse(aux));
		}

		return salida;
	}

	private static String conversionNombreEquipos(String nombreEquipoWeb) {
		String salida;
		// Primera división
		if (nombreEquipoWeb.equals("Celta")) {
			salida = "Celta";
		} else if (nombreEquipoWeb.equals("Málaga")) {
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

		} else if (nombreEquipoWeb.equals("Atlético de Madrid")) {
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
		// Segunda división
		else if (nombreEquipoWeb.equals("Racing de Santander")) {
			salida = "Racing";

		} else if (nombreEquipoWeb.equals("Las Palmas")) {
			salida = "Palmas";

		} else if (nombreEquipoWeb.equals("Mirandés")) {
			salida = "Mirandes";

		} else if (nombreEquipoWeb.equals("Huesca")) {
			salida = "Huesca";

		} else if (nombreEquipoWeb.equals("Lugo")) {
			salida = "Lugo";

		} else if (nombreEquipoWeb.equals("Hércules")) {
			salida = "Hercules";

		} else if (nombreEquipoWeb.equals("Xerez")) {
			salida = "Xerez";

		} else if (nombreEquipoWeb.equals("Villarreal")) {
			salida = "Villarreal";

		} else if (nombreEquipoWeb.equals("Real Madrid Castilla")) {
			salida = "RM-Castilla";

		} else if (nombreEquipoWeb.equals("Barcelona B")) {
			salida = "Barcelona-B";

		} else if (nombreEquipoWeb.equals("Almería")) {
			salida = "Almeria";

		} else if (nombreEquipoWeb.equals("Elche")) {
			salida = "Elche";

		} else if (nombreEquipoWeb.equals("Córdoba")) {
			salida = "Cordoba";

		} else if (nombreEquipoWeb.equals("Sporting de Gijón")) {
			salida = "Sporting";

		} else if (nombreEquipoWeb.equals("Murcia")) {
			salida = "Murcia";

		} else if (nombreEquipoWeb.equals("Alcorcón")) {
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
			logger.error("No se ha podido encontrar una conversión de nombre de equipo para el equipo con nombre web: "
					+ nombreEquipoWeb);
		}
		return salida;
	}
}
