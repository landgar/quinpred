package es.propio.presentacionCalculo;

public enum Accion {
	PREDECIR_QUINIELA(0), ACTUALIZAR_MOCKS(1), ANALIZAR_ALGORITMO_5(2), ANALIZAR_EMPATES(
			3);

	private int id;

	private Accion(int tipoEjecucion) {
		this.id = tipoEjecucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
