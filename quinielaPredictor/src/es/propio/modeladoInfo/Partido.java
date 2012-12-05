package es.propio.modeladoInfo;

import org.apache.log4j.Logger;

public class Partido {
	static final Logger logger = Logger.getLogger(Partido.class);
	private Boolean seHaJugado;

	public Partido(final Boolean seHaJugado) {
		super();
		this.seHaJugado = seHaJugado;
	}

	/**
	 * @uml.property name="equipoLocal"
	 * @uml.associationEnd
	 */
	private Equipo equipoLocal;
	/**
	 * @uml.property name="equipoVisitante"
	 * @uml.associationEnd
	 */
	private Equipo equipoVisitante;
	/**
	 * @uml.property name="golesLocal"
	 */
	private Integer golesLocal;
	/**
	 * @uml.property name="golesVisitante"
	 */
	private Integer golesVisitante;

	public ResultadoEquipo getResultadoEquipo(final Equipo equipo) {
		ResultadoEquipo resultado = new ResultadoEquipo(
				ValorResultadoEquipo.INVALIDO);
		String nombreEquipo = equipo.getNombre();
		if (equipo != null && nombreEquipo != null) {
			if (nombreEquipo.equals(equipoLocal.getNombre())) {
				if (getResultadoQuiniela().getValor()
						.equals(ValorResultado.UNO)) {
					resultado.setValor(ValorResultadoEquipo.GANADO);
				} else if (getResultadoQuiniela().getValor().equals(
						ValorResultado.DOS)) {
					resultado.setValor(ValorResultadoEquipo.PERDIDO);
				} else {
					resultado.setValor(ValorResultadoEquipo.EMPATADO);
				}
			} else if (nombreEquipo.equals(equipoVisitante.getNombre())) {
				if (getResultadoQuiniela().getValor()
						.equals(ValorResultado.UNO)) {
					resultado.setValor(ValorResultadoEquipo.PERDIDO);
				} else if (getResultadoQuiniela().getValor().equals(
						ValorResultado.DOS)) {
					resultado.setValor(ValorResultadoEquipo.GANADO);
				} else {
					resultado.setValor(ValorResultadoEquipo.EMPATADO);
				}
			} else {
				logger.error("El equipo del que obtener resultados del partido no es correcto");
			}
		} else {
			logger.error("El equipo del que obtener resultados del partido está vacío");
		}
		return resultado;
	}

	/**
	 * @return the resultadoQuiniela
	 * @uml.property name="resultadoQuiniela"
	 */
	public ResultadoQuiniela getResultadoQuiniela() {
		ResultadoQuiniela resultado;

		if (golesLocal == null || golesVisitante == null || golesLocal < 0
				|| golesVisitante < 0) {
			resultado = new ResultadoQuiniela(ValorResultado.INVALIDO);
		} else if (golesLocal > golesVisitante) {
			resultado = new ResultadoQuiniela(ValorResultado.UNO);
		} else if (golesLocal == golesVisitante) {
			resultado = new ResultadoQuiniela(ValorResultado.EQUIS);
		} else if (golesLocal < golesVisitante) {
			resultado = new ResultadoQuiniela(ValorResultado.DOS);
		} else {
			resultado = new ResultadoQuiniela(ValorResultado.INVALIDO);
		}

		return resultado;
	}

	/**
	 * @return the equipoLocal
	 * @uml.property name="equipoLocal"
	 */
	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	/**
	 * @param equipoLocal
	 *            the equipoLocal to set
	 * @uml.property name="equipoLocal"
	 */
	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	/**
	 * @return the equipoVisitante
	 * @uml.property name="equipoVisitante"
	 */
	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	/**
	 * @param equipoVisitante
	 *            the equipoVisitante to set
	 * @uml.property name="equipoVisitante"
	 */
	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	/**
	 * @return the golesLocal
	 * @uml.property name="golesLocal"
	 */
	public Integer getGolesLocal() {
		return golesLocal;
	}

	/**
	 * @param golesLocal
	 *            the golesLocal to set
	 * @uml.property name="golesLocal"
	 */
	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	/**
	 * @return the golesVisitante
	 * @uml.property name="golesVisitante"
	 */
	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	/**
	 * @param golesVisitante
	 *            the golesVisitante to set
	 * @uml.property name="golesVisitante"
	 */
	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Boolean esLocal(Equipo equipo) {
		Boolean salida = Boolean.FALSE;
		if (equipo.getNombre().equals(equipoLocal.getNombre())) {
			salida = Boolean.TRUE;
		}
		return salida;
	}

	public Boolean esVisitante(Equipo equipo) {
		Boolean salida = Boolean.FALSE;
		if (equipo.getNombre().equals(equipoVisitante.getNombre())) {
			salida = Boolean.TRUE;
		}
		return salida;
	}

	/**
	 * @return the seHaJugado
	 */
	public Boolean getSeHaJugado() {
		return seHaJugado;
	}

	/**
	 * @param seHaJugado
	 *            the seHaJugado to set
	 */
	public void setSeHaJugado(Boolean seHaJugado) {
		this.seHaJugado = seHaJugado;
	}

}
