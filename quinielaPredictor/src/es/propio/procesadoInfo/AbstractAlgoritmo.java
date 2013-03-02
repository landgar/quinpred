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
	private Temporada temporadaPrimera, temporadaSegunda;

	public abstract void calcularPronosticoPrimera() throws Exception;

	public abstract void calcularPronosticoSegunda() throws Exception;

	public static Integer calcularNumerosJornadas(Division division) {
		Integer numJornadas = 0;
		numJornadas = division.equals(Division.PRIMERA) ? ((Temporada.NUM_EQUIPOS_PRIMERA - 1) * 2)
				: ((Temporada.NUM_EQUIPOS_SEGUNDA - 1) * 2);
		return numJornadas;
	}

	public static Integer calcularNumPartidosPorJornada(Division division) {
		Integer numPartidos = 0;
		numPartidos = division.equals(Division.PRIMERA) ? Temporada.NUM_EQUIPOS_PRIMERA / 2
				: Temporada.NUM_EQUIPOS_SEGUNDA / 2;
		return numPartidos;
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

	/**
	 * @return the temporadaPrimera
	 */
	public Temporada getTemporadaPrimera() {
		return temporadaPrimera;
	}

	/**
	 * @param temporadaPrimera
	 *            the temporadaPrimera to set
	 */
	public void setTemporadaPrimera(Temporada temporadaPrimera) {
		this.temporadaPrimera = temporadaPrimera;
	}

	/**
	 * @return the temporadaSegunda
	 */
	public Temporada getTemporadaSegunda() {
		return temporadaSegunda;
	}

	/**
	 * @param temporadaSegunda
	 *            the temporadaSegunda to set
	 */
	public void setTemporadaSegunda(Temporada temporadaSegunda) {
		this.temporadaSegunda = temporadaSegunda;
	}

}
