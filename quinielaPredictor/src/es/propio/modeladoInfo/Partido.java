package es.propio.modeladoInfo;

public class Partido {
	/**
	 * @uml.property  name="posicion"
	 */
	private Integer posicion;

	/**
	 * @uml.property  name="equipoLocal"
	 * @uml.associationEnd  
	 */
	private Equipo equipoLocal;
	/**
	 * @uml.property  name="equipoVisitante"
	 * @uml.associationEnd  
	 */
	private Equipo equipoVisitante;
	/**
	 * @uml.property  name="golesLocal"
	 */
	private Integer golesLocal;
	/**
	 * @uml.property  name="golesVisitante"
	 */
	private Integer golesVisitante;
	/**
	 * @uml.property  name="resultadoQuiniela"
	 * @uml.associationEnd  
	 */
	private ResultadoQuiniela resultadoQuiniela;

	/**
	 * @return  the equipoLocal
	 * @uml.property  name="equipoLocal"
	 */
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	/**
	 * @param equipoLocal  the equipoLocal to set
	 * @uml.property  name="equipoLocal"
	 */
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	/**
	 * @return  the equipoVisitante
	 * @uml.property  name="equipoVisitante"
	 */
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	/**
	 * @param equipoVisitante  the equipoVisitante to set
	 * @uml.property  name="equipoVisitante"
	 */
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	/**
	 * @return  the golesLocal
	 * @uml.property  name="golesLocal"
	 */
	public Integer getGolesLocal() {
		return golesLocal;
	}

	/**
	 * @param golesLocal  the golesLocal to set
	 * @uml.property  name="golesLocal"
	 */
	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	/**
	 * @return  the golesVisitante
	 * @uml.property  name="golesVisitante"
	 */
	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	/**
	 * @param golesVisitante  the golesVisitante to set
	 * @uml.property  name="golesVisitante"
	 */
	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	/**
	 * @return  the resultadoQuiniela
	 * @uml.property  name="resultadoQuiniela"
	 */
	public ResultadoQuiniela getResultadoQuiniela() {
		return resultadoQuiniela;
	}

	/**
	 * @param resultadoQuiniela  the resultadoQuiniela to set
	 * @uml.property  name="resultadoQuiniela"
	 */
	public void setResultadoQuiniela(ResultadoQuiniela resultadoQuiniela) {
		this.resultadoQuiniela = resultadoQuiniela;
	}

	/**
	 * @return  the posicion
	 * @uml.property  name="posicion"
	 */
	public Integer getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion  the posicion to set
	 * @uml.property  name="posicion"
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
