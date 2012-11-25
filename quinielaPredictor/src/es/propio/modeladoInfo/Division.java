package es.propio.modeladoInfo;

public enum Division {
	PRIMERA(1), SEGUNDA(2);

	private Integer codigo;

	private Division(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

};
