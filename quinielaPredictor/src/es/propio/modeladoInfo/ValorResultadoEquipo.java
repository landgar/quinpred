/**
 * 
 */
package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum ValorResultadoEquipo {
	GANADO("1"), PERDIDO("-1"), EMPATADO("0"), INVALIDO("-");
	/**
	 * @uml.property name="valor"
	 */
	private String valor;

	private ValorResultadoEquipo(String valor) {
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
