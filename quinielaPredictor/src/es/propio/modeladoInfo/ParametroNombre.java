package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ParametroNombre {
	DIFERENCIADEGOLESENCONTRA("DIFERENCIADEGOLESENCONTRA"), DIFERENCIADEGOLESAFAVOR(
			"DIFERENCIADEGOLESAFAVOR"), INVALIDO("-");
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