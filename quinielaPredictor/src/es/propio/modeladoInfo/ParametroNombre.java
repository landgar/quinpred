package es.propio.modeladoInfo;

import java.util.List;

/**
 * @author i3casa
 * 
 */
public enum ParametroNombre {

	// Para comparar partidos tipo 1/2
	NUMEROJORNADA("NUMEROJORNADA"), JUEGA_EN_CASA("JUEGA_EN_CASA"), JUEGA_FUERA(
			"JUEGA_FUERA"), NUMERO_UNOS_ANTERIORES("NUMERO_UNOS_ANTERIORES"), NUMERO_EQUIS_ANTERIORES(
			"NUMERO_EQUIS_ANTERIORES"), NUMERO_DOSES_ANTERIORES(
			"NUMERO_DOSES_ANTERIORES"), GOLESENCASAAFAVOR("GOLESENCASAAFAVOR"), GOLESENCASAENCONTRA(
			"GOLESENCASAENCONTRA"), GOLESFUERAAFAVOR("GOLESFUERAAFAVOR"), GOLESFUERAENCONTRA(
			"GOLESFUERAENCONTRA"), GOLESTOTALESAFAVOR("GOLESTOTALESAFAVOR"), GOLESTOTALESENCONTRA(
			"GOLESTOTALESENCONTRA"), PUNTOSSIMPLES("PUNTOSSIMPLES"), GANADOS(
			"GANADOS"), EMPATADOS("EMPATADOS"), PERDIDOS("PERDIDOS"), PUNTOSNORMALES(
			"PUNTOSNORMALES"), DIFERENCIADEGOLESENCONTRA(
			"DIFERENCIADEGOLESENCONTRA"), DIFERENCIADEGOLESAFAVOR(
			"DIFERENCIADEGOLESAFAVOR"), POSICION_EN_CLASIFICACION(
			"POSICION_EN_CLASIFICACION"), PARTIDOS_JUGADOS("PARTIDOS_JUGADOS"), PARTIDOS_GANADOS(
			"PARTIDOS_GANADOS"), PARTIDOS_EMPATADOS("PARTIDOS_EMPATADOS"), PARTIDOS_PERDIDOS(
			"PARTIDOS_PERDIDOS"), GOLES_A_FAVOR("GOLES_A_FAVOR"), GOLES_EN_CONTRA(
			"GOLES_EN_CONTRA"), REMATES_EN_CONTRA("REMATES_EN_CONTRA"), JUGADORES_UTILIZADOS(
			"JUGADORES_UTILIZADOS"), PARADAS_DEL_PORTERO("PARADAS_DEL_PORTERO"), AF_BALON_PARADO(
			"AF_BALON_PARADO"), AF_CABEZA("AF_CABEZA"), AF_FALTA_DIRECTA(
			"AF_FALTA_DIRECTA"), AF_JUGADA_COLECTIVA("AF_JUGADA_COLECTIVA"), AF_JUGADA_INDIVIDUAL(
			"AF_JUGADA_INDIVIDUAL"), AF_PENALTY("AF_PENALTY"), AF_PIE_IZQUIERDO(
			"AF_PIE_IZQUIERDO"), AF_PIE_DERECHO("AF_PIE_DERECHO"), EN_CONTRA(
			"EN_CONTRA"), GC_CABEZA("GC_CABEZA"), GC_PENALTY("GC_PENALTY"), GOLES_TITULAR(
			"GOLES_TITULAR"), GOLES_SUPLENTES("GOLES_SUPLENTES"), REMATES_FAVOR(
			"REMATES_FAVOR"), RF_BALON_PARADO("RF_BALON_PARADO"), RF_CABEZA(
			"RF_CABEZA"), RF_FUERA("RF_FUERA"), RF_JUGADA_COLECTIVA(
			"RF_JUGADA_COLECTIVA"), RF_JUGADA_INDIVIDUAL("RF_JUGADA_INDIVIDUAL"), RF_PENALTY(
			"RF_PENALTY"), RF_PIE_IZQUIERDO("RF_PIE_IZQUIERDO"), RF_PIE_DERECHO(
			"RF_PIE_DERECHO"), RF_POSTE("RF_POSTE"), RF_PORTERIA("RF_PORTERIA"),

	RC_CABEZA("RC_CABEZA"), RC_FUERA("RC_FUERA"), RC_PORTERIA("RC_PORTERIA"), RC_POSTE(
			"RC_POSTE"), TARJETAS_AMARILLAS("TARJETAS_AMARILLAS"), TARJETAS_ROJAS(
			"TARJETAS_ROJAS"),

	PUNTOS_PONDERADOS("PUNTOS_PONDERADOS"), PUNTOS_TENDENCIA("PUNTOS_TENDENCIA"), PUNTOS_EN_CASA(
			"PUNTOS_EN_CASA"), PUNTOS_FUERA("PUNTOS_FUERA"),

	// Para comparar partidos tipo EMPATES
	DIFERENCIA_POSICIONES_EN_CLASIFICACION(
			"DIFERENCIA_POSICIONES_EN_CLASIFICACION"), DIFERENCIA_PUNTOS_PONDERADOS(
			"DIFERENCIA_PUNTOS_PONDERADOS"), DIFERENCIA_PUNTOS_PARA_EMPATE(
			"DIFERENCIA_PUNTOS_PARA_EMPATE"), DIFERENCIA_PUNTOS_SIMPLES(
			"DIFERENCIA_PUNTOS_SIMPLES"), DIFERENCIA_PUNTOS_TENDENCIA_PARA_EMPATE(
			"DIFERENCIA_PUNTOS_TENDENCIA_PARA_EMPATE"), DIFERENCIA_PUNTOS(
			"DIFERENCIA_PUNTOS"), FORTALEZA_LUGAR("FORTALEZA_LUGAR"), PROBABILIDAD_COMPENSAR_PARA_EMPATE(
			"PROBABILIDAD_COMPENSAR_PARA_EMPATE"),

	INVALIDO("-");

	private String nombre;

	private ParametroNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return name();
	}

	public boolean isParametroComunDeEquipo() {
		boolean esComun = false;
		if (this.equals(POSICION_EN_CLASIFICACION)
				|| this.equals(PARTIDOS_JUGADOS)
				|| this.equals(PARTIDOS_GANADOS)
				|| this.equals(PARTIDOS_EMPATADOS)
				|| this.equals(PARTIDOS_PERDIDOS) || this.equals(GOLES_A_FAVOR)
				|| this.equals(GOLES_EN_CONTRA)) {
			esComun = true;
		}

		return esComun;
	}

	/**
	 * @return True si a mayor cantidad del parametro, es mejor para el equipo.
	 *         Solo se usa para los PARAMETROS DE EQUIPO, pero no para los de
	 *         EMPATES
	 * @throws Exception
	 */
	public boolean isPositivo() throws Exception {

		boolean positivo = false;

		if (isTipoEquipoIndividual()) {
			// Parameros de equipos individuales

			positivo = this.equals(NUMEROJORNADA)
					|| this.equals(JUEGA_EN_CASA)
					|| this.equals(NUMERO_UNOS_ANTERIORES)
					|| this.equals(GOLESENCASAAFAVOR)
					|| this.equals(GOLESFUERAAFAVOR)
					|| this.equals(GOLESTOTALESAFAVOR)
					|| this.equals(PUNTOSSIMPLES)
					|| this.equals(GANADOS)
					|| this.equals(PUNTOSNORMALES)
					|| this.equals(DIFERENCIADEGOLESAFAVOR)
					|| this.equals(PARTIDOS_JUGADOS)
					|| this.equals(PARTIDOS_GANADOS)
					|| this.equals(GOLES_A_FAVOR)
					// || this.equals(JUGADORES_UTILIZADOS) duda
					// || this.equals(PARADAS_DEL_PORTERO) duda
					|| this.equals(AF_BALON_PARADO)
					|| this.equals(AF_CABEZA)
					|| this.equals(AF_FALTA_DIRECTA)
					|| this.equals(AF_JUGADA_COLECTIVA)
					|| this.equals(AF_JUGADA_INDIVIDUAL)
					|| this.equals(AF_PENALTY)
					|| this.equals(AF_PIE_IZQUIERDO)
					|| this.equals(AF_PIE_DERECHO)
					|| this.equals(GOLES_TITULAR)
					|| this.equals(GOLES_SUPLENTES) // duda
					|| this.equals(REMATES_FAVOR)
					|| this.equals(RF_BALON_PARADO) || this.equals(RF_CABEZA)
					|| this.equals(RF_FUERA)
					|| this.equals(RF_JUGADA_COLECTIVA)
					|| this.equals(RF_JUGADA_INDIVIDUAL)
					|| this.equals(RF_PENALTY) || this.equals(RF_PIE_IZQUIERDO)
					|| this.equals(RF_PIE_DERECHO) || this.equals(RF_POSTE)
					|| this.equals(RF_PORTERIA)
					|| this.equals(PUNTOS_PONDERADOS)
					|| this.equals(PUNTOS_TENDENCIA)
					|| this.equals(PUNTOS_EN_CASA) || this.equals(PUNTOS_FUERA);

		} else {
			// //Parametros para comparar empates o invalidos
			System.err.println();
			throw new Exception(
					"Parametro del que no podemos decir si es positivo o negativo a mayor cantidad, ya que es un parametro comparativo o invalido. Parametro: "
							+ this.getNombre());
		}

		return positivo;
	}

	/**
	 * @return True si es un parametro para definir a un equipo individualmente,
	 *         sin comparar con otro.
	 */
	public boolean isTipoEquipoIndividual() {
		List<ParametroNombre> lista = GestorParametrosAnalisis
				.getParamsTipoIndividual();
		boolean encontrado = false;
		for (ParametroNombre paramNombre : lista) {
			if (this.equals(paramNombre)) {
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}

	/**
	 * @return True si es un parametro de comparacion, para comparar dos
	 *         equipos.
	 */
	public boolean isTipoComparativo() {
		List<ParametroNombre> lista = GestorParametrosAnalisis
				.getParamsTipoComparativo();
		boolean encontrado = false;
		for (ParametroNombre paramNombre : lista) {
			if (this.equals(paramNombre)) {
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int compareTo(String anotherString) {
		return nombre.compareTo(anotherString);
	}

}
