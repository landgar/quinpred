package es.propio.procesadoInfo;

public enum TipoDivisionEnum {
	PRIMERA(1), SEGUNDA(2);

	private Integer tipo;

	TipoDivisionEnum(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
