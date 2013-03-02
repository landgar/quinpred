/**
 * 
 */
package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ValorResultado {
	UNO("1"), EQUIS("X"), DOS("2"), INVALIDO("-");
	/**
	 * @uml.property name="valor"
	 */
	private String valor;

	private ValorResultado(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the valor
	 * @uml.property name="valor"
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 * @uml.property name="valor"
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}
