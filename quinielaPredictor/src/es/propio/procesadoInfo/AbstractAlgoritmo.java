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

	private static Integer NUM_JORNADAS_PRIMERA = 20;
	private static Integer NUM_JORNADAS_SEGUNDA = 22;

	private IdAlgoritmoEnum id; // id del algoritmo

	private PronosticoJornada estimacionJornadaPrimera;
	private PronosticoJornada estimacionJornadaSegunda;

	abstract void calcularPronostico() throws Exception;

	public static Integer calcularNumerosJornadas(TipoDivisionEnum division) {
		Integer numJornadas = 0;
		numJornadas = division.equals(TipoDivisionEnum.PRIMERA) ? NUM_JORNADAS_PRIMERA
				: NUM_JORNADAS_SEGUNDA;
		return numJornadas;
	}

	public static Integer calcularNumPartidosPorJornada(
			TipoDivisionEnum division) {
		Integer numJornadas = 0;
		// Es igual que el numero de jornadas
		numJornadas = division.equals(TipoDivisionEnum.PRIMERA) ? NUM_JORNADAS_PRIMERA
				: NUM_JORNADAS_SEGUNDA;
		return numJornadas;
	}

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
