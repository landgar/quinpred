package es.propio.modeladoInfo;

public class PronosticoPartido {

	private Integer posicionPartido;

	private Equipo local;
	private Equipo visitante;

	private Float porcentaje1;
	private Float porcentaje12;
	private Float porcentaje1X;
	private Float porcentaje1X2;
	private Float porcentaje2;
	private Float porcentajeX;
	private Float porcentajeX2;

	/**
	 * @return the porcentaje1
	 */
	public Float getPorcentaje1() {
		return porcentaje1;
	}

	/**
	 * @param porcentaje1
	 *            the porcentaje1 to set
	 */
	public void setPorcentaje1(Float porcentaje1) {
		this.porcentaje1 = porcentaje1;
	}

	/**
	 * @return the porcentaje12
	 */
	public Float getPorcentaje12() {
		return porcentaje12;
	}

	/**
	 * @param porcentaje12
	 *            the porcentaje12 to set
	 */
	public void setPorcentaje12(Float porcentaje12) {
		this.porcentaje12 = porcentaje12;
	}

	/**
	 * @return the porcentaje1X
	 */
	public Float getPorcentaje1X() {
		return porcentaje1X;
	}

	/**
	 * @param porcentaje1x
	 *            the porcentaje1X to set
	 */
	public void setPorcentaje1X(Float porcentaje1x) {
		porcentaje1X = porcentaje1x;
	}

	/**
	 * @return the porcentaje1X2
	 */
	public Float getPorcentaje1X2() {
		return porcentaje1X2;
	}

	/**
	 * @param porcentaje1x2
	 *            the porcentaje1X2 to set
	 */
	public void setPorcentaje1X2(Float porcentaje1x2) {
		porcentaje1X2 = porcentaje1x2;
	}

	/**
	 * @return the porcentaje2
	 */
	public Float getPorcentaje2() {
		return porcentaje2;
	}

	/**
	 * @param porcentaje2
	 *            the porcentaje2 to set
	 */
	public void setPorcentaje2(Float porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}

	/**
	 * @return the porcentajeX
	 */
	public Float getPorcentajeX() {
		return porcentajeX;
	}

	/**
	 * @param porcentajeX
	 *            the porcentajeX to set
	 */
	public void setPorcentajeX(Float porcentajeX) {
		this.porcentajeX = porcentajeX;
	}

	/**
	 * @return the porcentajeX2
	 */
	public Float getPorcentajeX2() {
		return porcentajeX2;
	}

	/**
	 * @param porcentajeX2
	 *            the porcentajeX2 to set
	 */
	public void setPorcentajeX2(Float porcentajeX2) {
		this.porcentajeX2 = porcentajeX2;
	}

	/**
	 * @return the posicionPartido
	 */
	public Integer getPosicionPartido() {
		return posicionPartido;
	}

	/**
	 * @param posicionPartido
	 *            the posicionPartido to set
	 */
	public void setPosicionPartido(Integer posicionPartido) {
		this.posicionPartido = posicionPartido;
	}

	/**
	 * @return the local
	 */
	public Equipo getLocal() {
		return local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(Equipo local) {
		this.local = local;
	}

	/**
	 * @return the visitante
	 */
	public Equipo getVisitante() {
		return visitante;
	}

	/**
	 * @param visitante
	 *            the visitante to set
	 */
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

}
