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

	private Integer numJornada;

	private String nombreEquipo; // nombre equipo
	private Integer rematesContra;
	private Integer tarjetasAmarillas;
	private Integer tarjetasRojas;
	private Integer jugadoresUtilizados;
	private Integer paradasPortero;
	private Integer afBalonParado;
	private Integer afCabeza;
	private Integer afFaltaDirecta;
	private Integer AfJugadaColectiva;
	private Integer afJugadaIndividual;
	private Integer afPenalty;
	private Integer afPieIzquierdo;
	private Integer afPieDerecho;
	private Integer enContra;
	private Integer gcCabeza;
	private Integer gcPenalty;
	private Integer golesTitular;
	private Integer golesSuplentes;
	private Integer rematesFavor;
	private Integer rfBalonParado;
	private Integer rfCabeza;
	private Integer rfFuera;
	private Integer rfJugadaColectiva;
	private Integer rfJugadaIndividual;
	private Integer rfPenalty;
	private Integer rfPieIzquierdo;
	private Integer rfPieDerecho;
	private Integer rfPoste;
	private Integer rfPorteria;

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

	/**
	 * @return the afBalonParado
	 */
	public Integer getAfBalonParado() {
		return afBalonParado;
	}

	/**
	 * @param afBalonParado
	 *            the afBalonParado to set
	 */
	public void setAfBalonParado(Integer afBalonParado) {
		this.afBalonParado = afBalonParado;
	}

	/**
	 * @return the afCabeza
	 */
	public Integer getAfCabeza() {
		return afCabeza;
	}

	/**
	 * @param afCabeza
	 *            the afCabeza to set
	 */
	public void setAfCabeza(Integer afCabeza) {
		this.afCabeza = afCabeza;
	}

	/**
	 * @return the afFaltaDirecta
	 */
	public Integer getAfFaltaDirecta() {
		return afFaltaDirecta;
	}

	/**
	 * @param afFaltaDirecta
	 *            the afFaltaDirecta to set
	 */
	public void setAfFaltaDirecta(Integer afFaltaDirecta) {
		this.afFaltaDirecta = afFaltaDirecta;
	}

	/**
	 * @return the afJugadaColectiva
	 */
	public Integer getAfJugadaColectiva() {
		return AfJugadaColectiva;
	}

	/**
	 * @param afJugadaColectiva
	 *            the afJugadaColectiva to set
	 */
	public void setAfJugadaColectiva(Integer afJugadaColectiva) {
		AfJugadaColectiva = afJugadaColectiva;
	}

	/**
	 * @return the afJugadaIndividual
	 */
	public Integer getAfJugadaIndividual() {
		return afJugadaIndividual;
	}

	/**
	 * @param afJugadaIndividual
	 *            the afJugadaIndividual to set
	 */
	public void setAfJugadaIndividual(Integer afJugadaIndividual) {
		this.afJugadaIndividual = afJugadaIndividual;
	}

	/**
	 * @return the afPenalty
	 */
	public Integer getAfPenalty() {
		return afPenalty;
	}

	/**
	 * @param afPenalty
	 *            the afPenalty to set
	 */
	public void setAfPenalty(Integer afPenalty) {
		this.afPenalty = afPenalty;
	}

	/**
	 * @return the afPieIzquierdo
	 */
	public Integer getAfPieIzquierdo() {
		return afPieIzquierdo;
	}

	/**
	 * @param afPieIzquierdo
	 *            the afPieIzquierdo to set
	 */
	public void setAfPieIzquierdo(Integer afPieIzquierdo) {
		this.afPieIzquierdo = afPieIzquierdo;
	}

	/**
	 * @return the afPieDerecho
	 */
	public Integer getAfPieDerecho() {
		return afPieDerecho;
	}

	/**
	 * @param afPieDerecho
	 *            the afPieDerecho to set
	 */
	public void setAfPieDerecho(Integer afPieDerecho) {
		this.afPieDerecho = afPieDerecho;
	}

	/**
	 * @return the enContra
	 */
	public Integer getEnContra() {
		return enContra;
	}

	/**
	 * @param enContra
	 *            the enContra to set
	 */
	public void setEnContra(Integer enContra) {
		this.enContra = enContra;
	}

	/**
	 * @return the gcCabeza
	 */
	public Integer getGcCabeza() {
		return gcCabeza;
	}

	/**
	 * @param gcCabeza
	 *            the gcCabeza to set
	 */
	public void setGcCabeza(Integer gcCabeza) {
		this.gcCabeza = gcCabeza;
	}

	/**
	 * @return the gcPenalty
	 */
	public Integer getGcPenalty() {
		return gcPenalty;
	}

	/**
	 * @param gcPenalty
	 *            the gcPenalty to set
	 */
	public void setGcPenalty(Integer gcPenalty) {
		this.gcPenalty = gcPenalty;
	}

	/**
	 * @return the golesTitular
	 */
	public Integer getGolesTitular() {
		return golesTitular;
	}

	/**
	 * @param golesTitular
	 *            the golesTitular to set
	 */
	public void setGolesTitular(Integer golesTitular) {
		this.golesTitular = golesTitular;
	}

	/**
	 * @return the golesSuplentes
	 */
	public Integer getGolesSuplentes() {
		return golesSuplentes;
	}

	/**
	 * @param golesSuplentes
	 *            the golesSuplentes to set
	 */
	public void setGolesSuplentes(Integer golesSuplentes) {
		this.golesSuplentes = golesSuplentes;
	}

	/**
	 * @return the rfBalonParado
	 */
	public Integer getRfBalonParado() {
		return rfBalonParado;
	}

	/**
	 * @param rfBalonParado the rfBalonParado to set
	 */
	public void setRfBalonParado(Integer rfBalonParado) {
		this.rfBalonParado = rfBalonParado;
	}

	/**
	 * @return the rfCabeza
	 */
	public Integer getRfCabeza() {
		return rfCabeza;
	}

	/**
	 * @param rfCabeza the rfCabeza to set
	 */
	public void setRfCabeza(Integer rfCabeza) {
		this.rfCabeza = rfCabeza;
	}

	/**
	 * @return the rfFuera
	 */
	public Integer getRfFuera() {
		return rfFuera;
	}

	/**
	 * @param rfFuera the rfFuera to set
	 */
	public void setRfFuera(Integer rfFuera) {
		this.rfFuera = rfFuera;
	}

	/**
	 * @return the rfJugadaColectiva
	 */
	public Integer getRfJugadaColectiva() {
		return rfJugadaColectiva;
	}

	/**
	 * @param rfJugadaColectiva the rfJugadaColectiva to set
	 */
	public void setRfJugadaColectiva(Integer rfJugadaColectiva) {
		this.rfJugadaColectiva = rfJugadaColectiva;
	}

	/**
	 * @return the rfJugadaIndividual
	 */
	public Integer getRfJugadaIndividual() {
		return rfJugadaIndividual;
	}

	/**
	 * @param rfJugadaIndividual the rfJugadaIndividual to set
	 */
	public void setRfJugadaIndividual(Integer rfJugadaIndividual) {
		this.rfJugadaIndividual = rfJugadaIndividual;
	}

	/**
	 * @return the rfPenalty
	 */
	public Integer getRfPenalty() {
		return rfPenalty;
	}

	/**
	 * @param rfPenalty the rfPenalty to set
	 */
	public void setRfPenalty(Integer rfPenalty) {
		this.rfPenalty = rfPenalty;
	}

	/**
	 * @return the rfPieIzquierdo
	 */
	public Integer getRfPieIzquierdo() {
		return rfPieIzquierdo;
	}

	/**
	 * @param rfPieIzquierdo the rfPieIzquierdo to set
	 */
	public void setRfPieIzquierdo(Integer rfPieIzquierdo) {
		this.rfPieIzquierdo = rfPieIzquierdo;
	}

	/**
	 * @return the rfPieDerecho
	 */
	public Integer getRfPieDerecho() {
		return rfPieDerecho;
	}

	/**
	 * @param rfPieDerecho the rfPieDerecho to set
	 */
	public void setRfPieDerecho(Integer rfPieDerecho) {
		this.rfPieDerecho = rfPieDerecho;
	}

	/**
	 * @return the rfPoste
	 */
	public Integer getRfPoste() {
		return rfPoste;
	}

	/**
	 * @param rfPoste the rfPoste to set
	 */
	public void setRfPoste(Integer rfPoste) {
		this.rfPoste = rfPoste;
	}

	/**
	 * @return the rfPorteria
	 */
	public Integer getRfPorteria() {
		return rfPorteria;
	}

	/**
	 * @param rfPorteria the rfPorteria to set
	 */
	public void setRfPorteria(Integer rfPorteria) {
		this.rfPorteria = rfPorteria;
	}


}
