/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author i3casa
 * 
 */
public class PronosticoJornada implements Comparable<PronosticoJornada> {
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

	/**
	 * El algoritmo que ha creado este pronostico.
	 */
	private IdAlgoritmoEnum idAlgoritmoPronosticador;

	public void pintarme() {
		System.out.println("***** Pronostico Jornada: " + numeroJornada
				+ " ******");

		if (pronosticoPartidos != null && pronosticoPartidos.size() == 15) {
			for (PronosticoPartido pronostico : pronosticoPartidos) {
				pronostico.pintarme();
			}
		} else {
			System.out
					.println("ATENCION: PRONÓSTICO NO VÁLIDO. Debería tener exactamente 15 partidos !!!");
		}

	}

	public PronosticoJornada ordenarPorPosicionPartido() {
		Collections.sort(pronosticoPartidos);
		return this;
	}

	public PronosticoJornada(IdAlgoritmoEnum idAlgoritmoPronosticador) {
		super();
		pronosticoPartidos = new ArrayList<PronosticoPartido>();
	}

	public PronosticoJornada(Integer numeroJornada,
			IdAlgoritmoEnum idAlgoritmoPronosticador) {
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

	public IdAlgoritmoEnum getIdAlgoritmoPronosticador() {
		return idAlgoritmoPronosticador;
	}

	public void setIdAlgoritmoPronosticador(
			IdAlgoritmoEnum idAlgoritmoPronosticador) {
		this.idAlgoritmoPronosticador = idAlgoritmoPronosticador;
	}

	@Override
	public int compareTo(PronosticoJornada o) {
		int comparison = 0;
		if (numeroJornada != null && numeroJornada != null) {
			comparison = numeroJornada.compareTo(o.getNumeroJornada());
		}
		return comparison;
	}

}
