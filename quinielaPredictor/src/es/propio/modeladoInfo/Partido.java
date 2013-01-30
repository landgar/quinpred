package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	private List<Parametro> parametros;

	public String getID() {
		String id = "";
		if (equipoLocal != null && equipoVisitante != null) {
			id = equipoLocal.getNombre() + "-" + equipoVisitante.getNombre();
		} else {
			logger.error("Se está devolviendo in ID vacío para este partido");
		}
		return id;
	}

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

	public Integer goles(final Equipo equipo) {
		Integer goles = -1;
		if (getSeHaJugado()) {
			if (equipo.getNombre().equals(equipoLocal.getNombre())) {
				goles = golesLocal;
			} else if (equipo.getNombre().equals(equipoVisitante.getNombre())) {
				goles = golesVisitante;
			} else {
				logger.error("Se están intentando obtener los goles de un quipo no presente en este partido");
			}
		} else {
			logger.error("Se están intentando obtener los goles de un partido no jugado");
		}
		return goles;
	}

	public Parametro getParametro(final ParametroNombre nombre)
			throws Exception {
		Parametro parametroSalida = new Parametro(ParametroNombre.INVALIDO, 0,
				this);
		for (Parametro parametro : parametros) {
			if (parametro.getNombre().equals(nombre)) {
				parametroSalida = parametro;
				break;
			}
		}
		if (parametroSalida.getNombre().equals(ParametroNombre.INVALIDO)) {
			throw new Exception("Parámetro no encontrado");
		}
		return parametroSalida;
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

	public List<Equipo> getEquipos() {
		List<Equipo> equipos = new ArrayList<Equipo>();
		equipos.add(getEquipoLocal());
		equipos.add(getEquipoVisitante());
		return equipos;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getID();
	}

	/**
	 * @return the parametros
	 */
	public List<Parametro> getParametros() {
		Collections.sort(parametros);
		return parametros;
	}

	/**
	 * @param parametros
	 *            the parametros to set
	 */
	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

}
