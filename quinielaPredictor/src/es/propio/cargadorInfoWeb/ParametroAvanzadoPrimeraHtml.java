/**
 * 
 */
package es.propio.cargadorInfoWeb;

/**
 * Parametro de Primera leido desde Html.
 * 
 * @author carlos.andres
 * 
 */
public class ParametroAvanzadoPrimeraHtml {

	private String nombreEquipo; // nombre equipo
	private Integer rematesFavor;
	private Integer rematesContra;
	private Integer tarjetasAmarillas;
	private Integer tarjetasRojas;
	private Integer jugadoresUtilizados;
	private Integer paradasPortero;
	private Integer numJornada;

	@Override
	public String toString() {
		return "ParametroAvanzadoPrimeraHtml:" + " nombreEquipo="
				+ nombreEquipo + " rematesFavor=" + rematesFavor
				+ " rematesContra=" + rematesContra + " tarjetasAmarillas="
				+ tarjetasAmarillas + " tarjetasRojas=" + tarjetasRojas
				+ " jugadoresUtilizados=" + jugadoresUtilizados
				+ " paradasPortero=" + paradasPortero + " numJornada="
				+ numJornada;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public Integer getRematesFavor() {
		return rematesFavor;
	}

	public void setRematesFavor(Integer rematesFavor) {
		this.rematesFavor = rematesFavor;
	}

	public Integer getRematesContra() {
		return rematesContra;
	}

	public void setRematesContra(Integer rematesContra) {
		this.rematesContra = rematesContra;
	}

	public Integer getTarjetasAmarillas() {
		return tarjetasAmarillas;
	}

	public void setTarjetasAmarillas(Integer tarjetasAmarillas) {
		this.tarjetasAmarillas = tarjetasAmarillas;
	}

	public Integer getTarjetasRojas() {
		return tarjetasRojas;
	}

	public void setTarjetasRojas(Integer tarjetasRojas) {
		this.tarjetasRojas = tarjetasRojas;
	}

	public Integer getJugadoresUtilizados() {
		return jugadoresUtilizados;
	}

	public void setJugadoresUtilizados(Integer jugadoresUtilizados) {
		this.jugadoresUtilizados = jugadoresUtilizados;
	}

	public Integer getParadasPortero() {
		return paradasPortero;
	}

	public void setParadasPortero(Integer paradasPortero) {
		this.paradasPortero = paradasPortero;
	}

	public Integer getNumJornada() {
		return numJornada;
	}

	public void setNumJornada(Integer numJornada) {
		this.numJornada = numJornada;
	}

}
