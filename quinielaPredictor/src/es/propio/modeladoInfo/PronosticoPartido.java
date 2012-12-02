package es.propio.modeladoInfo;

public class PronosticoPartido implements Comparable<PronosticoPartido> {

	/**
	 * @uml.property name="posicionPartido"
	 */
	private Integer posicionPartido;

	/**
	 * @uml.property name="local"
	 * @uml.associationEnd
	 */
	private Equipo local;
	/**
	 * @uml.property name="visitante"
	 * @uml.associationEnd
	 */
	private Equipo visitante;

	/**
	 * @uml.property name="porcentaje1"
	 */
	private Float porcentaje1;
	/**
	 * @uml.property name="porcentaje12"
	 */
	private Float porcentaje12;
	/**
	 * @uml.property name="porcentaje1X"
	 */
	private Float porcentaje1X;
	/**
	 * @uml.property name="porcentaje1X2"
	 */
	private Float porcentaje1X2;
	/**
	 * @uml.property name="porcentaje2"
	 */
	private Float porcentaje2;
	/**
	 * @uml.property name="porcentajeX"
	 */
	private Float porcentajeX;
	/**
	 * @uml.property name="porcentajeX2"
	 */
	private Float porcentajeX2;

	public void pintarme() {
		String nombresEquipos = (local != null ? (local.getNombre() + " - ")
				: "")
				+ (visitante != null ? (visitante.getNombre() + " --> ") : "");

		String porcentajes = " Porcentajes: " + " UNO = " + porcentaje1
				+ " EQUIS = " + porcentajeX + " DOS = " + porcentaje2;

		System.out.println(nombresEquipos + "posicion = " + posicionPartido
				+ porcentajes);
	}

	public ValorResultado getResultadoMasProbable() {
		ValorResultado salida = ValorResultado.INVALIDO;
		if (porcentaje1 > porcentajeX) {
			if (porcentaje1 > porcentaje2) {
				salida = ValorResultado.UNO;
			} else {
				salida = ValorResultado.DOS;
			}
		} else if (porcentajeX > porcentaje2) {
			if (porcentaje1 > porcentaje2) {
				salida = ValorResultado.EQUIS;
			} else {
				salida = ValorResultado.DOS;
			}
		}
		return salida;
	}

	@Override
	public int compareTo(PronosticoPartido pronosticoPartido) {
		int comparison = 0;
		if (posicionPartido != null && pronosticoPartido != null
				&& pronosticoPartido.getPosicionPartido() != null) {
			comparison = posicionPartido.compareTo(pronosticoPartido
					.getPosicionPartido());
		}
		return comparison;
	}

	/**
	 * @return the porcentaje1
	 * @uml.property name="porcentaje1"
	 */
	public Float getPorcentaje1() {
		return porcentaje1;
	}

	/**
	 * @param porcentaje1
	 *            the porcentaje1 to set
	 * @uml.property name="porcentaje1"
	 */
	public void setPorcentaje1(Float porcentaje1) {
		this.porcentaje1 = porcentaje1;
	}

	/**
	 * @return the porcentaje12
	 * @uml.property name="porcentaje12"
	 */
	public Float getPorcentaje12() {
		return porcentaje12;
	}

	/**
	 * @param porcentaje12
	 *            the porcentaje12 to set
	 * @uml.property name="porcentaje12"
	 */
	public void setPorcentaje12(Float porcentaje12) {
		this.porcentaje12 = porcentaje12;
	}

	/**
	 * @return the porcentaje1X
	 * @uml.property name="porcentaje1X"
	 */
	public Float getPorcentaje1X() {
		return porcentaje1X;
	}

	/**
	 * @param porcentaje1x
	 *            the porcentaje1X to set
	 * @uml.property name="porcentaje1X"
	 */
	public void setPorcentaje1X(Float porcentaje1x) {
		porcentaje1X = porcentaje1x;
	}

	/**
	 * @return the porcentaje1X2
	 * @uml.property name="porcentaje1X2"
	 */
	public Float getPorcentaje1X2() {
		return porcentaje1X2;
	}

	/**
	 * @param porcentaje1x2
	 *            the porcentaje1X2 to set
	 * @uml.property name="porcentaje1X2"
	 */
	public void setPorcentaje1X2(Float porcentaje1x2) {
		porcentaje1X2 = porcentaje1x2;
	}

	/**
	 * @return the porcentaje2
	 * @uml.property name="porcentaje2"
	 */
	public Float getPorcentaje2() {
		return porcentaje2;
	}

	/**
	 * @param porcentaje2
	 *            the porcentaje2 to set
	 * @uml.property name="porcentaje2"
	 */
	public void setPorcentaje2(Float porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}

	/**
	 * @return the porcentajeX
	 * @uml.property name="porcentajeX"
	 */
	public Float getPorcentajeX() {
		return porcentajeX;
	}

	/**
	 * @param porcentajeX
	 *            the porcentajeX to set
	 * @uml.property name="porcentajeX"
	 */
	public void setPorcentajeX(Float porcentajeX) {
		this.porcentajeX = porcentajeX;
	}

	/**
	 * @return the porcentajeX2
	 * @uml.property name="porcentajeX2"
	 */
	public Float getPorcentajeX2() {
		return porcentajeX2;
	}

	/**
	 * @param porcentajeX2
	 *            the porcentajeX2 to set
	 * @uml.property name="porcentajeX2"
	 */
	public void setPorcentajeX2(Float porcentajeX2) {
		this.porcentajeX2 = porcentajeX2;
	}

	/**
	 * @return the posicionPartido
	 * @uml.property name="posicionPartido"
	 */
	public Integer getPosicionPartido() {
		return posicionPartido;
	}

	/**
	 * @param posicionPartido
	 *            the posicionPartido to set
	 * @uml.property name="posicionPartido"
	 */
	public void setPosicionPartido(Integer posicionPartido) {
		this.posicionPartido = posicionPartido;
	}

	/**
	 * @return the local
	 * @uml.property name="local"
	 */
	public Equipo getLocal() {
		return local;
	}

	/**
	 * @param local
	 *            the local to set
	 * @uml.property name="local"
	 */
	public void setLocal(Equipo local) {
		this.local = local;
	}

	/**
	 * @return the visitante
	 * @uml.property name="visitante"
	 */
	public Equipo getVisitante() {
		return visitante;
	}

	/**
	 * @param visitante
	 *            the visitante to set
	 * @uml.property name="visitante"
	 */
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

}
