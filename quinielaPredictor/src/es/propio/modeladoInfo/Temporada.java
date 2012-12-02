/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.Set;

/**
 * Donde se almacenarán los resultados de toda la temporada hasta hoy.
 * @author i3casa
 * 
 */
public class Temporada {

	/**
	 * @uml.property name="jornadas"
	 */
	private Set<Jornada> jornadas;
	private Division division;

	public Temporada() {
		super();
	}

	public Temporada(Set<Jornada> jornadas, Division division) {
		super();
		this.jornadas = jornadas;
		this.division = division;
	}

	/**
	 * @return the jornadasD
	 */
	public Set<Jornada> getJornadas() {
		return jornadas;
	}

	/**
	 * @param jornadas
	 *            the jornadas to set
	 */
	public void setJornadas(Set<Jornada> jornadas) {
		this.jornadas = jornadas;
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

}
