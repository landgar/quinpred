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

	private IdAlgoritmoEnum id; // id del algoritmo

	private PronosticoJornada estimacionJornada;

	abstract void calcularPronostico() throws Exception;

	public IdAlgoritmoEnum getId() {
		return id;
	}

	public void setId(IdAlgoritmoEnum id) {
		this.id = id;
	}

	public PronosticoJornada getEstimacionJornada() {
		return estimacionJornada;
	}

	public void setEstimacionJornada(PronosticoJornada estimacionJornada) {
		this.estimacionJornada = estimacionJornada;
	}

}
