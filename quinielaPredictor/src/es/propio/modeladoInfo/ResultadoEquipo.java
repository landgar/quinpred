package es.propio.modeladoInfo;

public class ResultadoEquipo {
	/**
	 * @uml.property name="valor"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private ValorResultadoEquipo valor;

	public ResultadoEquipo(ValorResultadoEquipo valor) {
		super();
		this.valor = valor;
	}

	public ResultadoEquipo(String valor) {
		super();
		for (ValorResultadoEquipo valor_i : ValorResultadoEquipo.values()) {
			if (valor_i.getValor().equals(valor)) {
				this.valor = valor_i;
			}
		}
	}

	/**
	 * @return the valor
	 * @uml.property name="valor"
	 */
	public ValorResultadoEquipo getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 * @uml.property name="valor"
	 */
	public void setValor(ValorResultadoEquipo valor) {
		this.valor = valor;
	}

}
