package es.propio.modeladoInfo;

public class ResultadoQuiniela {
	private ValorResultado valor;

	public ResultadoQuiniela(ValorResultado valor) {
		super();
		this.valor = valor;
	}

	public ResultadoQuiniela(String valor) {
		super();
		for (ValorResultado valor_i : ValorResultado.values()) {
			if (valor_i.getValor().equals(valor)) {
				this.valor = valor_i;
			}
		}
	}

	/**
	 * @return the valor
	 */
	public ValorResultado getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(ValorResultado valor) {
		this.valor = valor;
	}

}
