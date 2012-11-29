/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author i3casa
 * 
 */
public class PronosticoJornada {
	/**
	 * @uml.property name="pronosticoPartidos"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType="es.propio.modeladoInfo.PronosticoPartido"
	 */
	private List<PronosticoPartido> pronosticoPartidos;
	/**
	 * @uml.property name="numeroJornada"
	 */
	private Integer numeroJornada;

	public void pintarme() {
		System.out.println("***** Pronostico Jornada: " + numeroJornada
				+ " ******");
		for (PronosticoPartido pronostico : pronosticoPartidos) {
			pronostico.pintarme();
		}
	}

	public PronosticoJornada ordenarPorPosicionPartido() {
		Collections.sort(pronosticoPartidos);
		return this;
	}

	public PronosticoJornada() {
		super();
		pronosticoPartidos = new ArrayList<PronosticoPartido>();
	}

	public PronosticoJornada(Integer numeroJornada) {
		super();
		pronosticoPartidos = new ArrayList<PronosticoPartido>();
		this.numeroJornada = numeroJornada;
	}

	/**
	 * @return the numeroJornada
	 * @uml.property name="numeroJornada"
	 */
	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	/**
	 * @param numeroJornada
	 *            the numeroJornada to set
	 * @uml.property name="numeroJornada"
	 */
	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}

	/**
	 * @return the pronosticoPartidos
	 */
	public List<PronosticoPartido> getPronosticoPartidos() {
		return pronosticoPartidos;
	}

	/**
	 * @param pronosticoPartidos
	 *            the pronosticoPartidos to set
	 */
	public void setPronosticoPartidos(List<PronosticoPartido> pronosticoPartidos) {
		this.pronosticoPartidos = pronosticoPartidos;
	}

}
