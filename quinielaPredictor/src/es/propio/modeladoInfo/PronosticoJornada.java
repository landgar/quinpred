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

	/**
	 * Variable auxiliar para indicar el numero de aciertos de la jornada. Usar
	 * el metodo obtenerNumAciertos().
	 */
	private Integer numeroAciertos;

	public void pintarme() {
		System.out.println("***** Pronostico Jornada/Boleto: " + numeroJornada
				+ " ******");
		for (PronosticoPartido pronostico : pronosticoPartidos) {
			pronostico.pintarme();
		}
	}

	/**
	 * Calcula el numero de aciertos de este pronostico de jornada. Obviamente,
	 * necesito conocer el resultado que se ha dado en la realidad.
	 * 
	 * @param resultadoRealJornada
	 *            Resultado real de la jornada, para poder comparar con este
	 *            pronostico.
	 */
	public Integer obtenerNumAciertos(PronosticoJornada resultadoRealJornada) {

		Integer num = null;

		if (resultadoRealJornada.getNumeroJornada().equals(numeroJornada)) {

			// misma jornada: comparo cada partido
			for (PronosticoPartido realp : resultadoRealJornada
					.getPronosticoPartidos()) {

				for (PronosticoPartido pronosticop : pronosticoPartidos) {

					boolean mismoLocal = realp
							.getPartido()
							.getEquipoLocal()
							.getNombre()
							.equals(pronosticop.getPartido().getEquipoLocal()
									.getNombre());
					boolean mismoVisitante = realp
							.getPartido()
							.getEquipoVisitante()
							.getNombre()
							.equals(pronosticop.getPartido()
									.getEquipoVisitante().getNombre());

					// mismo partido
					if (mismoLocal && mismoVisitante) {

						if (num == null) {
							num = Integer.valueOf(0);
						}

						if (!realp.getResultadoMasProbable().equals(
								ValorResultado.INVALIDO)
								&& realp.getResultadoMasProbable().equals(
										pronosticop.getResultadoMasProbable())) {
							num++;
						} else {
//							System.out.println("Fallo en predicción jornada: "
//									+ resultadoRealJornada.getNumeroJornada()
//									+ " Partido: " + realp.getPartido().getID()
//									+ " con resultado: "
//									+ realp.getPartido().getGolesLocal() + "-"
//									+ realp.getPartido().getGolesVisitante());
						}

					}
				}
			}
		}
		if (num != null) {
			numeroAciertos = num;
		}
		return num;
	}

	public PronosticoJornada ordenarPorPosicionPartido() {
		Collections.sort(pronosticoPartidos);
		return this;
	}

	public PronosticoJornada(Integer numeroJornada,
			IdAlgoritmoEnum idAlgoritmoPronosticador) {
		super();
		pronosticoPartidos = new ArrayList<PronosticoPartido>();
		this.numeroJornada = numeroJornada;
		this.idAlgoritmoPronosticador = idAlgoritmoPronosticador;
	}

	/**
	 * @param pronosticoPartidos
	 *            Lista de pronosticos, uno para cada partido.
	 * @param numeroJornada
	 *            Numero de la jornada del pronostico.
	 * @param idAlgoritmoPronosticador
	 */
	public PronosticoJornada(List<PronosticoPartido> pronosticoPartidos,
			Integer numeroJornada, IdAlgoritmoEnum idAlgoritmoPronosticador) {
		super();
		this.pronosticoPartidos = pronosticoPartidos;
		this.numeroJornada = numeroJornada;
		this.idAlgoritmoPronosticador = idAlgoritmoPronosticador;
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

	public Integer getNumeroAciertos() {
		return numeroAciertos;
	}

	public void setNumeroAciertos(Integer numeroAciertos) {
		this.numeroAciertos = numeroAciertos;
	}

}
