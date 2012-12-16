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
		if (salida.equals("R. Madrid")) {
			salida = "Real-Madrid";
		} else if (salida.equals("R. Sociedad")) {
			salida = "R-Sociedad";
		} else if (salida.equals("Zaragoza")) {
			salida = "Real-Zaragoza";
		} else if (salida.equals("RM Castilla")) {
			salida = "RM-Castilla";
		} else if (salida.equals("Barcelona B")) {
			salida = "Barcelona-B";
		} else if (salida.equals("Las Palmas")) {
			salida = "Palmas";
		}
		return salida;
	}

}
