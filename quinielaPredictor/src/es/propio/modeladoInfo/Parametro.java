package es.propio.modeladoInfo;

public class Parametro implements Comparable<Parametro> {

	private ParametroNombre nombre;
	private Integer valor;
	private Partido partido;
	private Integer peso;

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

	/**
	 * @return the peso
	 */
	public Integer getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            the peso to set
	 */
	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNombre().getNombre();
	}

	@Override
	public int compareTo(Parametro parametro) {
		return nombre.compareTo(parametro.getNombre());
	}

}
