package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ParametroNombre {

	NUMEROJORNADA("NUMEROJORNADA"), GOLESENCASAAFAVOR("GOLESENCASAAFAVOR"), GOLESENCASAENCONTRA(
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
			"GOLES_EN_CONTRA"), REMATES_A_FAVOR("REMATES_A_FAVOR"), REMATES_EN_CONTRA(
			"REMATES_EN_CONTRA"), TARJETAS_AMARILLAS("TARJETAS_AMARILLAS"), TARJETAS_ROJAS(
			"TARJETAS_ROJAS"), JUGADORES_UTILIZADOS("JUGADORES_UTILIZADOS"), PARADAS_DEL_PORTERO(
			"PARADAS_DEL_PORTERO"), INVALIDO("-");

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
