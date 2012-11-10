package es.propio.modeladoInfo;

public class Partido {
	private Integer posicion;

	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private Integer golesLocal;
	private Integer golesVisitante;
	private ResultadoQuiniela resultadoQuiniela;

	/**
	 * @return the equipoLocal
	 */
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	/**
	 * @param equipoLocal
	 *            the equipoLocal to set
	 */
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	/**
	 * @return the equipoVisitante
	 */
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	/**
	 * @param equipoVisitante
	 *            the equipoVisitante to set
	 */
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	/**
	 * @return the golesLocal
	 */
	public Integer getGolesLocal() {
		return golesLocal;
	}

	/**
	 * @param golesLocal
	 *            the golesLocal to set
	 */
	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	/**
	 * @return the golesVisitante
	 */
	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	/**
	 * @param golesVisitante
	 *            the golesVisitante to set
	 */
	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	/**
	 * @return the resultadoQuiniela
	 */
	public ResultadoQuiniela getResultadoQuiniela() {
		return resultadoQuiniela;
	}

	/**
	 * @param resultadoQuiniela
	 *            the resultadoQuiniela to set
	 */
	public void setResultadoQuiniela(ResultadoQuiniela resultadoQuiniela) {
		this.resultadoQuiniela = resultadoQuiniela;
	}

	/**
	 * @return the posicion
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion
	 *            the posicion to set
	 */
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public Boolean esLocal(Equipo equipo) {
		Boolean salida = Boolean.FALSE;
		if (equipo.getValor().getNombre()
				.equals(equipoLocal.getValor().getNombre())) {
			salida = Boolean.TRUE;
		}
		return salida;
	}

	public Boolean esVisitante(Equipo equipo) {
		Boolean salida = Boolean.FALSE;
		if (equipo.getValor().getNombre()
				.equals(equipoVisitante.getValor().getNombre())) {
			salida = Boolean.TRUE;
		}
		return salida;
	}

}
