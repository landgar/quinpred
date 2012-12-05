/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Donde se almacenarán los resultados de toda la temporada hasta hoy.
 * 
 * @author i3casa
 * 
 */
public class Temporada {


	public static Integer NUM_JORNADAS_PRIMERA = 20;
	public static Integer NUM_JORNADAS_SEGUNDA = 21;
	
	/**
	 * @uml.property name="jornadas"
	 */
	private List<Jornada> jornadas;
	private Division division;

	public Temporada() {
		super();
	}

	public Temporada(List<Jornada> jornadas, Division division) {
		super();
		this.jornadas = jornadas;
		this.division = division;
	}

	/**
	 * @return the division
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	/**
	 * @return the jornadas
	 */
	public List<Jornada> getJornadas() {
		Collections.sort(jornadas, new Comparator<Jornada>() {
			public int compare(Jornada o1, Jornada o2) {
				return o1.getNumeroJornada().compareTo(o2.getNumeroJornada());
			}
		});
		return jornadas;
	}

	/**
	 * @param jornadas
	 *            the jornadas to set
	 */
	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

}
