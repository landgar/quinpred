package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ParametroNombre {

	DIFERENCIADEGOLESENCONTRA("DIFERENCIADEGOLESENCONTRA"), DIFERENCIADEGOLESAFAVOR(
			"DIFERENCIADEGOLESAFAVOR"), POSICION_EN_CLASIFICACION(
			"POSICION_EN_CLASIFICACION"), PARTIDOS_JUGADOS("PARTIDOS_JUGADOS"), PARTIDOS_GANADOS(
			"PARTIDOS_GANADOS"), PARTIDOS_EMPATADOS("PARTIDOS_EMPATADOS"), PARTIDOS_PERDIDOS(
			"PARTIDOS_PERDIDOS"), GOLES_A_FAVOR("GOLES_A_FAVOR"), GOLES_EN_CONTRA(
			"GOLES_EN_CONTRA"), REMATES_A_FAVOR("REMATES_A_FAVOR"), REMATES_EN_CONTRA(
			"REMATES_EN_CONTRA"), TARJETAS_AMARILLAS("TARJETAS_AMARILLAS"), TARJETAS_ROJAS(
			"TARJETAS_ROJAS"), JUGADORES_UTILIZADOS("JUGADORES_UTILIZADOS"), PARADAS_DEL_PORTERO(
			"PARADAS_DEL_PORTERO"), INVALIDO("-");

	/**
	 * @uml.property name="valor"
	 */
	private String nombre;

	private ParametroNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
