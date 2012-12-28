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
		nombreEquipoSistema = Normalizer.normalize(nombreEquipoWeb,
				Normalizer.Form.NFD);
		nombreEquipoSistema = nombreEquipoSistema.replaceAll("[^\\p{ASCII}]",
				"");
		nombreEquipoSistema = nombreEquipoSistema.replaceAll("-", "");
		// Casos raros
		if (nombreEquipoWeb.trim().equalsIgnoreCase("LasPalmas")) {
			nombreEquipoSistema = "Palmas";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("RMCastilla")) {
			nombreEquipoSistema = "RM-Castilla";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("BarcelonaB")) {
			nombreEquipoSistema = "Barcelona-B";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("RealZaragoza")
				|| nombreEquipoWeb.trim().equalsIgnoreCase("Zaragoza")) {
			nombreEquipoSistema = "Real-Zaragoza";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("RealMadrid")
				|| nombreEquipoWeb.trim().equalsIgnoreCase("Real Madrid")) {
			nombreEquipoSistema = "Real-Madrid";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("R.Sociedad")
				|| nombreEquipoWeb.trim().equalsIgnoreCase("Real Sociedad")) {
			nombreEquipoSistema = "R-Sociedad";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("Celta de Vigo")) {
			nombreEquipoSistema = "Celta";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("Athletic Club")) {
			nombreEquipoSistema = "Athletic";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("Rayo Vallecano")) {
			nombreEquipoSistema = "Rayo";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("Granada C.F")) {
			nombreEquipoSistema = "Granada";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase(
				"Deportivo de La Coruña")) {
			nombreEquipoSistema = "Deportivo";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("Málaga")) {
			nombreEquipoSistema = "Malaga";
		} else if (nombreEquipoWeb.trim().equalsIgnoreCase("At. Madrid")) {
			nombreEquipoSistema = "Atletico";
		}
		return nombreEquipoSistema;
	}
}
