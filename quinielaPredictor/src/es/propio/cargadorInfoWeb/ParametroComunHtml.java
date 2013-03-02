/**
 * 
 */
package es.propio.cargadorInfoWeb;

import es.propio.modeladoInfo.AbstractParametro;

/**
 * Parametro comun leido desde Html.
 * 
 * @author carlos.andres
 * 
 */
public class ParametroComunHtml extends AbstractParametro {

	private String nombre; // nombre equipo
	private Integer posicion;
	private Integer partidosJugados;
	private Integer partidosGanados;
	private Integer partidosEmpatados;
	private Integer partidosPerdidos;
	private Integer golesFavor;
	private Integer golesContra;
	private Integer puntos;
	private Integer numJornada;// util solo para red neuronal

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ParametroComunHtml:" + " nombre=" + nombre + " posicion="
				+ posicion + " partidosJugados=" + partidosJugados
				+ " partidosGanados=" + partidosGanados + " partidosEmpatados="
				+ partidosEmpatados + " partidosPerdidos=" + partidosPerdidos
				+ " golesFavor=" + golesFavor + " golesContra=" + golesContra
				+ " puntos=" + puntos + " numJornada=" + numJornada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Integer getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(Integer partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public Integer getPartidosGanados() {
		return partidosGanados;
	}

	public void setPartidosGanados(Integer partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public Integer getPartidosEmpatados() {
		return partidosEmpatados;
	}

	public void setPartidosEmpatados(Integer partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}

	public Integer getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public void setPartidosPerdidos(Integer partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	public Integer getGolesFavor() {
		return golesFavor;
	}

	public void setGolesFavor(Integer golesFavor) {
		this.golesFavor = golesFavor;
	}

	public Integer getGolesContra() {
		return golesContra;
	}

	public void setGolesContra(Integer golesContra) {
		this.golesContra = golesContra;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public Integer getNumJornada() {
		return numJornada;
	}

	public void setNumJornada(Integer numJornada) {
		this.numJornada = numJornada;
	}

}
