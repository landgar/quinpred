package es.propio.cargadorInfoWeb;

import java.text.Normalizer;

/**
 * Convierte los nombres de los equipos de El Pais a los que entiende nuestro
 * sistSema.
 * 
 * @author carlos.andres
 * 
 */
public class ConversorElPais {

	public ConversorElPais() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parsea los nombres de los equipos a los estaticos buenos nuestros:
	 * Equipo.getNombresEquiposPrimera() y Equipo.getNombresEquiposSegunda
	 * 
	 * @param nombreEquipoWeb
	 * @return
	 */
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
		} else if (nombreEquipoWeb.equals("RealZaragoza")) {
			nombreEquipoSistema = "Real-Zaragoza";
		} else if (nombreEquipoWeb.equals("RealMadrid")) {
			nombreEquipoSistema = "Real-Madrid";
		} else if (nombreEquipoWeb.equals("R.Sociedad")) {
			nombreEquipoSistema = "R-Sociedad";
		}
		return nombreEquipoSistema;
	}

}
