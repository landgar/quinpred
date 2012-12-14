package es.propio.modeladoInfo;

public class Parametro {

	private ParametroNombre nombre;
	private Integer valor;
	private Partido partido;

	public Parametro(ParametroNombre nombre, Integer valor, Partido partido) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.partido = partido;
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
	 * @return the partido
	 */
	public Partido getPartido() {
		return partido;
	}

	/**
	 * @param partido
	 *            the partido to set
	 */
	public void setPartido(Partido partido) {
		this.partido = partido;
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
