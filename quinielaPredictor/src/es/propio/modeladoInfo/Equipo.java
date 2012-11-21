package es.propio.modeladoInfo;

public class Equipo {

	/**
	 * @uml.property  name="valor"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private EquipoValor valor;
	/**
	 * @uml.property  name="pesoGanados"
	 */
	private Float pesoGanados;
	/**
	 * @uml.property  name="pesoEmpatados"
	 */
	private Float pesoEmpatados;
	/**
	 * @uml.property  name="pesoPerdidos"
	 */
	private Float pesoPerdidos;

	/**
	 * @return  the pesoGanados
	 * @uml.property  name="pesoGanados"
	 */
	public Float getPesoGanados() {
		return pesoGanados;
	}

	/**
	 * @param pesoGanados  the pesoGanados to set
	 * @uml.property  name="pesoGanados"
	 */
	public void setPesoGanados(Float pesoGanados) {
		this.pesoGanados = pesoGanados;
	}

	/**
	 * @return  the pesoEmpatados
	 * @uml.property  name="pesoEmpatados"
	 */
	public Float getPesoEmpatados() {
		return pesoEmpatados;
	}

	/**
	 * @param pesoEmpatados  the pesoEmpatados to set
	 * @uml.property  name="pesoEmpatados"
	 */
	public void setPesoEmpatados(Float pesoEmpatados) {
		this.pesoEmpatados = pesoEmpatados;
	}

	/**
	 * @return  the pesoPerdidos
	 * @uml.property  name="pesoPerdidos"
	 */
	public Float getPesoPerdidos() {
		return pesoPerdidos;
	}

	/**
	 * @param pesoPerdidos  the pesoPerdidos to set
	 * @uml.property  name="pesoPerdidos"
	 */
	public void setPesoPerdidos(Float pesoPerdidos) {
		this.pesoPerdidos = pesoPerdidos;
	}

	public Equipo(EquipoValor nombre) {
		super();
		this.valor = nombre;
	}

	public Equipo(String nombre) {
		super();
		Boolean encontrado = Boolean.FALSE;
		for (EquipoValor valor_i : EquipoValor.values()) {
			if (valor_i.getNombre().equals(nombre)) {
				this.valor = valor_i;
				encontrado = Boolean.TRUE;
			}
		}
		if (!encontrado.booleanValue()) {
			System.out.println("ERROR: Equipo " + nombre
					+ " no definido en el conjunto de equipos del sistema");
		}
	}

	/**
	 * @return  the valor
	 * @uml.property  name="valor"
	 */
	public EquipoValor getValor() {
		return valor;
	}

	/**
	 * @param valor  the valor to set
	 * @uml.property  name="valor"
	 */
	public void setValor(EquipoValor valor) {
		this.valor = valor;
	}

	public void pesosRelativos(final Temporada temporada) {
		pesoGanados = 0F;
		pesoEmpatados = 0F;
		pesoPerdidos = 0F;
		Float numerojornadas = Float.valueOf(String.valueOf(temporada
				.getJornadas().size()));
		for (Jornada jornada : temporada.getJornadas()) {
			Partido partido = jornada.getPartidoDondeJuega(this);
			if (partido != null) {
				if (partido.getResultadoQuiniela() == null
						|| partido.getResultadoQuiniela().getValor() == null) {
					System.out.println("WARNING: No hay resultados para la jornada: "
							+ jornada.getFecha().toString());
				} else if ((partido.esLocal(this) && partido
						.getResultadoQuiniela().getValor()
						.equals(ValorResultado.UNO))
						|| (partido.esVisitante(this) && partido
								.getResultadoQuiniela().getValor()
								.equals(ValorResultado.DOS))) {
					pesoGanados++;
				} else if (partido.getResultadoQuiniela().getValor()
						.equals(ValorResultado.EQUIS)) {
					pesoPerdidos++;
				} else if ((partido.esLocal(this) && partido
						.getResultadoQuiniela().getValor()
						.equals(ValorResultado.DOS))
						|| partido.esVisitante(this)
						&& partido.getResultadoQuiniela().getValor()
								.equals(ValorResultado.UNO)) {
					pesoPerdidos++;
				}
			}
		}
		if (numerojornadas.equals(0F)) {
			System.out
					.println("ERROR: ESTIMACIÓN INCORRECTA. Pesos mal calculados, ya que no hay estadísticas para el equipo: "
							+ this.getValor().getNombre());
		} else {
			pesoGanados = pesoGanados / numerojornadas;
			pesoEmpatados = pesoEmpatados / numerojornadas;
			pesoPerdidos = pesoPerdidos / numerojornadas;
		}
	}
}
