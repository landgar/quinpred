package es.propio.procesadoInfo;

public enum IdAlgoritmoEnum {
	REAL(0), ALGORITMO1(1), ALGORITMO2(2), ALGORITMO3(3), ALGORITMO5(5), WEB_QUINIELISTA(10);

	private Integer idAlgoritmo;

	IdAlgoritmoEnum(Integer id) {
		this.idAlgoritmo = id;
	}

	public Integer getIdAlgoritmo() {
		return idAlgoritmo;
	}

	public void setIdAlgoritmo(Integer idAlgoritmo) {
		this.idAlgoritmo = idAlgoritmo;
	}

}
