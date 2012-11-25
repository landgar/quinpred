/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.Arrays;
import java.util.List;

/**
 * @author i3casa
 * 
 */
public enum NombresEquipos {
	EQUIPOS_PRIMERA("Osasuna", "Barcelona"),  EQUIPOS_SEGUNDA("Almeria",
			"Hercules");

	private final List<String> equipos;

	NombresEquipos(String... equipos) {
		this.equipos = Arrays.asList(equipos);
	}

	/**
	 * @return the equipos
	 */
	public List<String> getEquipos() {
		return equipos;
	}

}
