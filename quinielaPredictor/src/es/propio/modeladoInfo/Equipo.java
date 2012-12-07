package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.StringUtils;

public class Equipo implements Comparable<Equipo>{

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
		List<String> nombresPrimera = getNombresEquiposPrimera();
		List<String> nombresSegunda = getNombresEquiposSegunda();
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
	
	@Override
	public int compareTo(Equipo o) {
		int comparison=-1; //defaults different
		if(nombre!=null && !nombre.isEmpty() && o!=null && o.getNombre()!=null && !o.getNombre().isEmpty() && nombre.equals(o.getNombre())){
			comparison=0;
		}
		return comparison;
	}

	public Division getDivision() {
		List<String> nombresPrimera = getNombresEquiposPrimera();
		List<String> nombresSegunda = getNombresEquiposSegunda();
		// Primera división
		Division division = Division.INVALIDO;
		if (nombresPrimera.contains(nombre)) {
			division = Division.PRIMERA;
		} else if (nombresSegunda.contains(nombre)) {
			division = Division.SEGUNDA;
		} else {
			logger.error("No se ha podido encontrar una división para el equipo con nombre: "
					+ nombre);
		}
		return division;
	}

	public static List<String> getNombresEquiposPrimera() {
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
		return nombresPrimera;
	}

	public static List<String> getNombresEquiposSegunda() {
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
		return nombresSegunda;
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
			logger.error("ERROR: ESTIMACIÓN INCORRECTA. Pesos mal calculados, ya que no hay estadísticas para el equipo: "
					+ this.getNombre());
		} else {
			pesoGanados = pesoGanados / numerojornadas;
			pesoEmpatados = pesoEmpatados / numerojornadas;
			pesoPerdidos = pesoPerdidos / numerojornadas;
		}
	}

	/**Es el identificador unico del equipo.
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**Es el identificador unico del equipo.
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}
