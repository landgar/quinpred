/**
 * 
 */
package es.propio.procesadoInfo;

import es.propio.modeladoInfo.PronosticoJornada;

/**
 * @author i3casa
 * 
 */
public abstract class AbstractAlgoritmo {

	private PronosticoJornada estimacionJornada;

	abstract void calcularPronostico() throws Exception;

	/**
	 * @return the estimacionJornada
	 */
	public PronosticoJornada getEstimacionJornada() {
		return estimacionJornada;
	}

	/**
	 * @param estimacionJornada
	 *            the estimacionJornada to set
	 */
	public void setEstimacionJornada(PronosticoJornada estimacionJornada) {
		this.estimacionJornada = estimacionJornada;
	}

}
