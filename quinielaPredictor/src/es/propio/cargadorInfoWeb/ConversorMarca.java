package es.propio.cargadorInfoWeb;

import java.text.Normalizer;

import es.propio.modeladoInfo.Equipo;

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

		// System.out.println("MARCA entrada=" + salida);

		// Casos raros en Marca.com
		if (salida.trim().equalsIgnoreCase("R. Madrid")) {
			salida = Equipo.P_REAL_MADRID;
		} else if (salida.trim().equalsIgnoreCase("R. Sociedad")) {
			salida = Equipo.P_REAL_SOCIEDAD;
		} else if (salida.trim().equalsIgnoreCase("Málaga")
				|| salida.trim().equalsIgnoreCase("MAlaga")) {
			salida = Equipo.P_MALAGA;
		} else if (salida.trim().equalsIgnoreCase("Atlético")
				|| salida.trim().equalsIgnoreCase("AtlAtico")) {
			salida = Equipo.P_ATLETICO;
		} else if (salida.trim().equalsIgnoreCase("Zaragoza")) {
			salida = Equipo.P_ZARAGOZA;
		} else if (salida.trim().equalsIgnoreCase("RM Castilla")) {
			salida = Equipo.S_RM_CASTILLA;
		} else if (salida.trim().equalsIgnoreCase("Barcelona B")) {
			salida = Equipo.S_BARCELONA_B;
		} else if (salida.trim().equalsIgnoreCase("Las Palmas")) {
			salida = Equipo.S_LAS_PALMAS;
		} else if (salida.trim().equalsIgnoreCase("Alcorcón")
				|| salida.trim().equalsIgnoreCase("AlcorcAn")) {
			salida = Equipo.S_ALCORCON;
		} else if (salida.trim().equalsIgnoreCase("Almería")
				|| salida.trim().equalsIgnoreCase("AlmerAa")) {
			salida = Equipo.S_ALMERIA;
		} else if (salida.trim().equalsIgnoreCase("Córdoba")
				|| salida.trim().equalsIgnoreCase("CArdoba")) {
			salida = Equipo.S_CORDOBA;
		} else if (salida.trim().equalsIgnoreCase("Mirandés")
				|| salida.trim().equalsIgnoreCase("MirandAs")) {
			salida = Equipo.S_MIRANDES;
		} else if (salida.trim().equalsIgnoreCase("Hércules")
				|| salida.trim().equalsIgnoreCase("HArcules")) {
			salida = Equipo.S_HERCULES;
		}

		// System.out.println("MARCA salida=" + salida);

		return salida;
	}

}
