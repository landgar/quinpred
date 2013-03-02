package es.propio.cargadorInfoWeb;

import java.text.Normalizer;

import es.propio.modeladoInfo.Equipo;

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
		String normalizado = "";
		normalizado = Normalizer
				.normalize(nombreEquipoWeb, Normalizer.Form.NFD);
		normalizado = normalizado.replaceAll("[^\\p{ASCII}]", "");
		normalizado = normalizado.replaceAll("-", "");

		// System.out.println("EL PAIS entrada=" + normalizado);

		// Casos raros
		if (normalizado.trim().equalsIgnoreCase("LasPalmas")) {
			normalizado = Equipo.S_LAS_PALMAS;
		} else if (normalizado.trim().equalsIgnoreCase("RMCastilla")) {
			normalizado = Equipo.S_RM_CASTILLA;
		} else if (normalizado.trim().equalsIgnoreCase("BarcelonaB")) {
			normalizado = Equipo.S_BARCELONA_B;
		} else if (normalizado.trim().equalsIgnoreCase("RealZaragoza")
				|| normalizado.trim().equalsIgnoreCase("Zaragoza")
				|| normalizado.trim().equalsIgnoreCase("Real Zaragoza")) {
			normalizado = Equipo.P_ZARAGOZA;
		} else if (normalizado.trim().equalsIgnoreCase("RealMadrid")
				|| normalizado.trim().equalsIgnoreCase("Real Madrid")) {
			normalizado = Equipo.P_REAL_MADRID;
		} else if (normalizado.trim().equalsIgnoreCase("R.Sociedad")
				|| normalizado.trim().equalsIgnoreCase("Real Sociedad")
				|| normalizado.trim().equalsIgnoreCase("R. Sociedad")) {
			normalizado = Equipo.P_REAL_SOCIEDAD;
		} else if (normalizado.trim().equalsIgnoreCase("Celta de Vigo")) {
			normalizado = Equipo.P_CELTA;
		} else if (normalizado.trim().equalsIgnoreCase("Athletic Club")) {
			normalizado = Equipo.P_ATHLETIC;
		} else if (normalizado.trim().equalsIgnoreCase("Rayo Vallecano")) {
			normalizado = Equipo.P_RAYO;
		} else if (normalizado.trim().equalsIgnoreCase("Granada C.F")) {
			normalizado = Equipo.P_GRANADA;
		} else if (normalizado.trim()
				.equalsIgnoreCase("Deportivo de La Coruña")
				|| normalizado.trim()
						.equalsIgnoreCase("Deportivo de La Coruna")
				|| normalizado.trim().equalsIgnoreCase("Deportivo de La Corua")) {
			normalizado = Equipo.P_DEPORTIVO;
		} else if (normalizado.trim().equalsIgnoreCase("Málaga")
				|| normalizado.trim().equalsIgnoreCase("Mlaga")
				|| normalizado.trim().equalsIgnoreCase("Milaga")) {
			normalizado = Equipo.P_MALAGA;
		} else if (normalizado.trim().equalsIgnoreCase("At. Madrid")
				|| normalizado.trim().equalsIgnoreCase("Atlitico")
				|| normalizado.trim().equalsIgnoreCase("Atltico")) {
			normalizado = Equipo.P_ATLETICO;
		} else if (normalizado.trim().equalsIgnoreCase("Córdoba")
				|| normalizado.trim().equalsIgnoreCase("Cirdoba")
				|| normalizado.trim().equalsIgnoreCase("Crdoba")) {
			normalizado = Equipo.S_CORDOBA;
		} else if (normalizado.trim().equalsIgnoreCase("Alcorcón")
				|| normalizado.trim().equalsIgnoreCase("Alcorcin")
				|| normalizado.trim().equalsIgnoreCase("Alcorcn")) {
			normalizado = Equipo.S_ALCORCON;
		} else if (normalizado.trim().equalsIgnoreCase("Hércules")
				|| normalizado.trim().equalsIgnoreCase("Hircules")
				|| normalizado.trim().equalsIgnoreCase("Hrcules")) {
			normalizado = Equipo.S_HERCULES;
		} else if (normalizado.trim().equalsIgnoreCase("Mirandés")
				|| normalizado.trim().equalsIgnoreCase("Mirandis")
				|| normalizado.trim().equalsIgnoreCase("Mirands")) {
			normalizado = Equipo.S_MIRANDES;
		} else if (normalizado.trim().equalsIgnoreCase("Almería")
				|| normalizado.trim().equalsIgnoreCase("Almera")) {
			normalizado = Equipo.S_ALMERIA;
		} else if (normalizado.trim().equalsIgnoreCase("Las Palmas")) {
			normalizado = Equipo.S_LAS_PALMAS;
		} else if (normalizado.trim().equalsIgnoreCase("RM Castilla")) {
			normalizado = Equipo.S_RM_CASTILLA;
		} else if (normalizado.trim().equalsIgnoreCase("Barcelona B")) {
			normalizado = Equipo.S_BARCELONA_B;
		}

		// System.out.println("EL PAIS salida=" + normalizado);

		return normalizado;
	}
}
