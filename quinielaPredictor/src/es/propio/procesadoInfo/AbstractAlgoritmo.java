/**
 * 
 */
package es.propio.procesadoInfo;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public abstract class AbstractAlgoritmo {

	private IdAlgoritmoEnum id; // id del algoritmo

	private PronosticoJornada estimacionJornadaPrimera;
	private PronosticoJornada estimacionJornadaSegunda;

	public abstract void calcularPronosticoPrimera() throws Exception;

	public abstract void calcularPronosticoSegunda() throws Exception;

	public static Integer calcularNumerosJornadas(Division division) {
		Integer numJornadas = 0;
		numJornadas = division.equals(Division.PRIMERA) ? Temporada.NUM_JORNADAS_PRIMERA
				: Temporada.NUM_JORNADAS_SEGUNDA;
		return numJornadas;
	}

	public static Integer calcularNumPartidosPorJornada(Division division) {
		Integer numJornadas = 0;
		// Es igual que el numero de jornadas
		numJornadas = division.equals(Division.PRIMERA) ? Temporada.NUM_JORNADAS_PRIMERA
				: Temporada.NUM_JORNADAS_SEGUNDA;
		return numJornadas;
	}

	public void pintame() {
		pintarEstimacionJornada(estimacionJornadaPrimera);
		pintarEstimacionJornada(estimacionJornadaSegunda);
	}

	private void pintarEstimacionJornada(
			final PronosticoJornada estimacionJornada) {
		if (estimacionJornada != null) {
			estimacionJornada.pintarme();
		}
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
