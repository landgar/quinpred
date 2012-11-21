/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author i3casa
 * 
 */
public class PronosticoJornada {
	/**
	 * @uml.property  name="pronosticoPartidos"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="es.propio.modeladoInfo.PronosticoPartido"
	 */
	private Set<PronosticoPartido> pronosticoPartidos;
	/**
	 * @uml.property  name="numeroJornada"
	 */
	private Integer numeroJornada;

	public PronosticoJornada() {
		super();
		pronosticoPartidos = new HashSet<PronosticoPartido>();
	}

	/**
	 * @return  the numeroJornada
	 * @uml.property  name="numeroJornada"
	 */
	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	/**
	 * @param numeroJornada  the numeroJornada to set
	 * @uml.property  name="numeroJornada"
	 */
	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}

	/**
	 * @return the pronosticoPartidos
	 */
	public Set<PronosticoPartido> getPronosticoPartidos() {
		return pronosticoPartidos;
	}

	/**
	 * @param pronosticoPartidos
	 *            the pronosticoPartidos to set
	 */
	public void setPronosticoPartidos(Set<PronosticoPartido> pronosticoPartidos) {
		this.pronosticoPartidos = pronosticoPartidos;
	}

}
