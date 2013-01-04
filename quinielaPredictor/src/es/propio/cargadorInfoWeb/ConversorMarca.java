package es.propio.cargadorInfoWeb;

import java.text.Normalizer;

/**
 * Conversor de los campos que leemos en ww.marca.com
 * 
 * @author carlos.andres
 * 
 */
public class ConversorMarca {

	public ConversorMarca() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Convierte los nombres de los equipos de marca.com a los que entiende
	 * nuestro sistema (ver clase Equipo)
	 * 
	 * @return
	 */
	public static String convertirNombreEquipo(String entrada) {
		String salida = Normalizer.normalize(entrada, Normalizer.Form.NFD);
		salida = salida.replaceAll("[^\\p{ASCII}]", "");
		salida = salida.replaceAll("-", "");

		// Casos raros en Marca.com
		if (salida.trim().equalsIgnoreCase("R. Madrid")) {
			salida = "Real-Madrid";
		} else if (salida.trim().equalsIgnoreCase("R. Sociedad")) {
			salida = "R-Sociedad";
		} else if (salida.trim().equalsIgnoreCase("Málaga")
				|| salida.trim().equalsIgnoreCase("MAlaga")) {
			salida = "Malaga";
		} else if (salida.trim().equalsIgnoreCase("Atlético")
				|| salida.trim().equalsIgnoreCase("AtlAtico")) {
			salida = "Atletico";
		} else if (salida.trim().equalsIgnoreCase("Zaragoza")) {
			salida = "Real-Zaragoza";
		} else if (salida.trim().equalsIgnoreCase("RM Castilla")) {
			salida = "RM-Castilla";
		} else if (salida.trim().equalsIgnoreCase("Barcelona B")) {
			salida = "Barcelona-B";
		} else if (salida.trim().equalsIgnoreCase("Las Palmas")) {
			salida = "Palmas";
		}
		return salida;
	}

}
