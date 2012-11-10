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
	private Set<PronosticoPartido> pronosticoPartidos;
	private Integer numeroJornada;

	public PronosticoJornada() {
		super();
		pronosticoPartidos = new HashSet<PronosticoPartido>();
	}

	/**
	 * @return the numeroJornada
	 */
	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	/**
	 * @param numeroJornada
	 *            the numeroJornada to set
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
