package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Equipo {

	static final Logger logger = Logger.getLogger(Equipo.class);
	/**
	 * @uml.property name="valor"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private String nombre;
	/**
	 * @uml.property name="pesoGanados"
	 */
	private Float pesoGanados;
	/**
	 * @uml.property name="pesoEmpatados"
	 */
	private Float pesoEmpatados;
	/**
	 * @uml.property name="pesoPerdidos"
	 */
	private Float pesoPerdidos;

	public Equipo(final String nombre) {
		super();
		this.nombre = nombre;
	}

	public Equipo(final Division division, final List<String> posiblesNombres) {
		super();
		List<String> nombresPrimera = new ArrayList<>();
		nombresPrimera.add("Celta");
		nombresPrimera.add("Malaga");
		nombresPrimera.add("Real-Zaragoza");
		nombresPrimera.add("Valladolid");
		nombresPrimera.add("Rayo");
		nombresPrimera.add("Granada");
		nombresPrimera.add("Real-Madrid");
		nombresPrimera.add("Valencia");
		nombresPrimera.add("Levante");
		nombresPrimera.add("Atletico");
		nombresPrimera.add("Deportivo");
		nombresPrimera.add("Osasuna");
		nombresPrimera.add("Mallorca");
		nombresPrimera.add("Espanyol");
		nombresPrimera.add("Sevilla");
		nombresPrimera.add("Getafe");
		nombresPrimera.add("Athletic");
		nombresPrimera.add("Betis");
		nombresPrimera.add("Barcelona");
		nombresPrimera.add("R-Sociedad");
		List<String> nombresSegunda = new ArrayList<>();
		nombresSegunda.add("Racing");
		nombresSegunda.add("Palmas");
		nombresSegunda.add("Mirandes");
		nombresSegunda.add("Huesca");
		nombresSegunda.add("Lugo");
		nombresSegunda.add("Hercules");
		nombresSegunda.add("Xerez");
		nombresSegunda.add("Recreativo");
		nombresSegunda.add("Villarreal");
		nombresSegunda.add("RM-Castilla");
		nombresSegunda.add("Barcelona-B");
		nombresSegunda.add("Almeria");
		nombresSegunda.add("Elche");
		nombresSegunda.add("Cordoba");
		nombresSegunda.add("Sporting");
		nombresSegunda.add("Murcia");
		nombresSegunda.add("Alcorcon");
		nombresSegunda.add("Guadalajara");
		nombresSegunda.add("Recreativo");
		nombresSegunda.add("Ponferradina");
		nombresSegunda.add("Sabadell");
		nombresSegunda.add("Girona");
		nombresSegunda.add("Numancia");

		String nombreEncontrado = "invalido";

		for (String posibleNombre : posiblesNombres) {
			if (division.equals((Division.PRIMERA))) {
				for (String nombrePrimera : nombresPrimera) {
					if (nombrePrimera.equals(posibleNombre)) {
						nombreEncontrado = nombrePrimera;
						break;
					}
				}
			} else if (division.equals((Division.SEGUNDA))) {
				for (String nombreSegunda : nombresSegunda) {
					if (nombreSegunda.equals(posibleNombre)) {
						nombreEncontrado = nombreSegunda;
						break;
					}
				}
			}
		}
		this.nombre = nombreEncontrado;
	}

	public Division getDivision() {
		// Primera divisi�n
		Division division = Division.INVALIDO;
		if (nombre.equals("Celta")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("M�laga")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Zaragoza")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Valladolid")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Rayo Vallecano")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Granada")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Real Madrid")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Valencia")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Levante")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Atl�tico de Madrid")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Deportivo")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Osasuna")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Mallorca")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Espanyol")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Sevilla")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Getafe")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Athletic de Bilbao")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Betis")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Barcelona")) {
			division = Division.PRIMERA;
		} else if (nombre.equals("Real Sociedad")) {
			division = Division.PRIMERA;
		}
		// Segunda divisi�n
		else if (nombre.equals("Racing de Santander")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Las Palmas")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Mirand�s")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Huesca")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Lugo")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("H�rcules")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Xerez")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Villarreal")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Real Madrid Castilla")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Barcelona B")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Almer�a")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Elche")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("C�rdoba")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Sporting de Gij�n")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Murcia")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Alcorc�n")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Guadalajara")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Recreativo de Huelva")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Ponferradina")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Sabadell")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Girona")) {
			division = Division.SEGUNDA;
		} else if (nombre.equals("Numancia")) {
			division = Division.SEGUNDA;
		} else {
			logger.error("No se ha podido encontrar una divisi�n para el equipo con nombre: "
					+ nombre);
		}
		return division;
	}

	/**
	 * @return the pesoGanados
	 * @uml.property name="pesoGanados"
	 */
	public Float getPesoGanados() {
		return pesoGanados;
	}

	/**
	 * @param pesoGanados
	 *            the pesoGanados to set
	 * @uml.property name="pesoGanados"
	 */
	public void setPesoGanados(Float pesoGanados) {
		this.pesoGanados = pesoGanados;
	}

	/**
	 * @return the pesoEmpatados
	 * @uml.property name="pesoEmpatados"
	 */
	public Float getPesoEmpatados() {
		return pesoEmpatados;
	}

	/**
	 * @param pesoEmpatados
	 *            the pesoEmpatados to set
	 * @uml.property name="pesoEmpatados"
	 */
	public void setPesoEmpatados(Float pesoEmpatados) {
		this.pesoEmpatados = pesoEmpatados;
	}

	/**
	 * @return the pesoPerdidos
	 * @uml.property name="pesoPerdidos"
	 */
	public Float getPesoPerdidos() {
		return pesoPerdidos;
	}

	/**
	 * @param pesoPerdidos
	 *            the pesoPerdidos to set
	 * @uml.property name="pesoPerdidos"
	 */
	public void setPesoPerdidos(Float pesoPerdidos) {
		this.pesoPerdidos = pesoPerdidos;
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
					logger.warn("WARNING: No hay resultados para la jornada: "
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
			logger.error("ERROR: ESTIMACI�N INCORRECTA. Pesos mal calculados, ya que no hay estad�sticas para el equipo: "
					+ this.getNombre());
		} else {
			pesoGanados = pesoGanados / numerojornadas;
			pesoEmpatados = pesoEmpatados / numerojornadas;
			pesoPerdidos = pesoPerdidos / numerojornadas;
		}
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
