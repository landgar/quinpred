/**
 * 
 */
package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ValorResultado {
	UNO("1"), EQUIS("X"), DOS("2");
	private String valor;

	private ValorResultado(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}
