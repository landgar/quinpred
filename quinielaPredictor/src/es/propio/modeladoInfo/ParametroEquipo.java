package es.propio.modeladoInfo;

public class ParametroEquipo {

	private ParametroNombre nombre;
	private Integer valor;

	public ParametroEquipo(ParametroNombre nombre, Integer valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	/**
	 * @return the nombre
	 */
	public ParametroNombre getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(ParametroNombre nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
