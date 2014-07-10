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
	private Integer jugadoresUtilizados;
	private Integer paradasPortero;
	private Integer aFavor;
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
	private Integer rcCabeza;
	private Integer rcFuera;
	private Integer rcPorteria;
	private Integer rcPoste;
	private Integer tarjetasAmarillas;
	private Integer tarjetasRojas;
	private Integer intervenciones;
	private Integer faltasCometidas;
	private Integer faltasRecibidas;
	private Integer penaltyFavor;
	private Integer penaltyContra;
	private Integer fueraJuegoFavor;
	private Integer fueraJuegoContra;
	private Integer balonesRecuperados;
	private Integer balonesPerdidos;
	private Integer pasesBuenos;
	private Integer pasesTotales;
	private Integer centrosArea;
	private Integer asistencias;
	private Integer asistenciaConGol;
	
	

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

	/**
	 * @return the rcCabeza
	 */
	public Integer getRcCabeza() {
		return rcCabeza;
	}

	/**
	 * @param rcCabeza the rcCabeza to set
	 */
	public void setRcCabeza(Integer rcCabeza) {
		this.rcCabeza = rcCabeza;
	}

	/**
	 * @return the rcFuera
	 */
	public Integer getRcFuera() {
		return rcFuera;
	}

	/**
	 * @param rcFuera the rcFuera to set
	 */
	public void setRcFuera(Integer rcFuera) {
		this.rcFuera = rcFuera;
	}

	/**
	 * @return the rcPorteria
	 */
	public Integer getRcPorteria() {
		return rcPorteria;
	}

	/**
	 * @param rcPorteria the rcPorteria to set
	 */
	public void setRcPorteria(Integer rcPorteria) {
		this.rcPorteria = rcPorteria;
	}

	/**
	 * @return the rcPoste
	 */
	public Integer getRcPoste() {
		return rcPoste;
	}

	/**
	 * @param rcPoste the rcPoste to set
	 */
	public void setRcPoste(Integer rcPoste) {
		this.rcPoste = rcPoste;
	}

	/**
	 * @return the aFavor
	 */
	public Integer getaFavor() {
		return aFavor;
	}

	/**
	 * @param aFavor the aFavor to set
	 */
	public void setaFavor(Integer aFavor) {
		this.aFavor = aFavor;
	}

	/**
	 * @return the intervenciones
	 */
	public Integer getIntervenciones() {
		return intervenciones;
	}

	/**
	 * @param intervenciones the intervenciones to set
	 */
	public void setIntervenciones(Integer intervenciones) {
		this.intervenciones = intervenciones;
	}

	/**
	 * @return the faltasCometidas
	 */
	public Integer getFaltasCometidas() {
		return faltasCometidas;
	}

	/**
	 * @param faltasCometidas the faltasCometidas to set
	 */
	public void setFaltasCometidas(Integer faltasCometidas) {
		this.faltasCometidas = faltasCometidas;
	}

	/**
	 * @return the faltasRecibidas
	 */
	public Integer getFaltasRecibidas() {
		return faltasRecibidas;
	}

	/**
	 * @param faltasRecibidas the faltasRecibidas to set
	 */
	public void setFaltasRecibidas(Integer faltasRecibidas) {
		this.faltasRecibidas = faltasRecibidas;
	}

	/**
	 * @return the penaltyFavor
	 */
	public Integer getPenaltyFavor() {
		return penaltyFavor;
	}

	/**
	 * @param penaltyFavor the penaltyFavor to set
	 */
	public void setPenaltyFavor(Integer penaltyFavor) {
		this.penaltyFavor = penaltyFavor;
	}

	/**
	 * @return the penaltyContra
	 */
	public Integer getPenaltyContra() {
		return penaltyContra;
	}

	/**
	 * @param penaltyContra the penaltyContra to set
	 */
	public void setPenaltyContra(Integer penaltyContra) {
		this.penaltyContra = penaltyContra;
	}

	/**
	 * @return the fueraJuegoFavor
	 */
	public Integer getFueraJuegoFavor() {
		return fueraJuegoFavor;
	}

	/**
	 * @param fueraJuegoFavor the fueraJuegoFavor to set
	 */
	public void setFueraJuegoFavor(Integer fueraJuegoFavor) {
		this.fueraJuegoFavor = fueraJuegoFavor;
	}

	/**
	 * @return the fueraJuegoContra
	 */
	public Integer getFueraJuegoContra() {
		return fueraJuegoContra;
	}

	/**
	 * @param fueraJuegoContra the fueraJuegoContra to set
	 */
	public void setFueraJuegoContra(Integer fueraJuegoContra) {
		this.fueraJuegoContra = fueraJuegoContra;
	}

	/**
	 * @return the balonesRecuperados
	 */
	public Integer getBalonesRecuperados() {
		return balonesRecuperados;
	}

	/**
	 * @param balonesRecuperados the balonesRecuperados to set
	 */
	public void setBalonesRecuperados(Integer balonesRecuperados) {
		this.balonesRecuperados = balonesRecuperados;
	}

	/**
	 * @return the balonesPerdidos
	 */
	public Integer getBalonesPerdidos() {
		return balonesPerdidos;
	}

	/**
	 * @param balonesPerdidos the balonesPerdidos to set
	 */
	public void setBalonesPerdidos(Integer balonesPerdidos) {
		this.balonesPerdidos = balonesPerdidos;
	}

	/**
	 * @return the pasesBuenos
	 */
	public Integer getPasesBuenos() {
		return pasesBuenos;
	}

	/**
	 * @param pasesBuenos the pasesBuenos to set
	 */
	public void setPasesBuenos(Integer pasesBuenos) {
		this.pasesBuenos = pasesBuenos;
	}

	/**
	 * @return the pasesTotales
	 */
	public Integer getPasesTotales() {
		return pasesTotales;
	}

	/**
	 * @param pasesTotales the pasesTotales to set
	 */
	public void setPasesTotales(Integer pasesTotales) {
		this.pasesTotales = pasesTotales;
	}

	/**
	 * @return the centrosArea
	 */
	public Integer getCentrosArea() {
		return centrosArea;
	}

	/**
	 * @param centrosArea the centrosArea to set
	 */
	public void setCentrosArea(Integer centrosArea) {
		this.centrosArea = centrosArea;
	}

	/**
	 * @return the asistencias
	 */
	public Integer getAsistencias() {
		return asistencias;
	}

	/**
	 * @param asistencias the asistencias to set
	 */
	public void setAsistencias(Integer asistencias) {
		this.asistencias = asistencias;
	}

	/**
	 * @return the asistenciaConGol
	 */
	public Integer getAsistenciaConGol() {
		return asistenciaConGol;
	}

	/**
	 * @param asistenciaConGol the asistenciaConGol to set
	 */
	public void setAsistenciaConGol(Integer asistenciaConGol) {
		this.asistenciaConGol = asistenciaConGol;
	}


}
