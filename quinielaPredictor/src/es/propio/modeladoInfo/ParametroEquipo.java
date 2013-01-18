package es.propio.modeladoInfo;

public class ParametroEquipo extends AbstractParametro implements
		Comparable<ParametroEquipo> {

	private ParametroNombre nombre;
	private Integer valor;

	public ParametroEquipo(ParametroNombre nombre, Integer valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ParametroEquipo:" + " nombre=" + nombre.toString() + " valor="
				+ valor;
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

	@Override
	public int compareTo(ParametroEquipo parametro) {
		return nombre.compareTo(parametro.getNombre());
	}

}
