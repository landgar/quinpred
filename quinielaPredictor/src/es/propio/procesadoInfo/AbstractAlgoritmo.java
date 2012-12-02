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

	private PronosticoJornada estimacionJornadaPrimera;
	private PronosticoJornada estimacionJornadaSegunda;

	abstract void calcularPronostico() throws Exception;

	public IdAlgoritmoEnum getId() {
		return id;
	}

	public void setId(IdAlgoritmoEnum id) {
		this.id = id;
	}

	public PronosticoJornada getEstimacionJornadaPrimera() {
		return estimacionJornadaPrimera;
	}

	public void setEstimacionJornadaPrimera(
			PronosticoJornada estimacionJornadaPrimera) {
		this.estimacionJornadaPrimera = estimacionJornadaPrimera;
	}

	public PronosticoJornada getEstimacionJornadaSegunda() {
		return estimacionJornadaSegunda;
	}

	public void setEstimacionJornadaSegunda(
			PronosticoJornada estimacionJornadaSegunda) {
		this.estimacionJornadaSegunda = estimacionJornadaSegunda;
	}

}
