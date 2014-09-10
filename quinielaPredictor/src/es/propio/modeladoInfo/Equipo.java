package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

public class Equipo implements Comparable<Equipo> {

	static final Logger logger = Logger.getLogger(Equipo.class);

	public static final String P_CELTA = "Celta";
	public static final String P_MALAGA = "Malaga";
	public static final String P_ZARAGOZA = "Real-Zaragoza";
	public static final String P_VALLADOLID = "Valladolid";
	public static final String P_RAYO = "Rayo";
	public static final String P_GRANADA = "Granada";
	public static final String P_REAL_MADRID = "Real-Madrid";
	public static final String P_VALENCIA = "Valencia";
	public static final String P_LEVANTE = "Levante";
	public static final String P_ATLETICO = "Atletico";
	public static final String P_DEPORTIVO = "Deportivo";
	public static final String P_OSASUNA = "Osasuna";
	public static final String P_MALLORCA = "Mallorca";
	public static final String P_ESPANYOL = "Espanyol";
	public static final String P_SEVILLA = "Sevilla";
	public static final String P_GETAFE = "Getafe";
	public static final String P_ATHLETIC = "Athletic";
	public static final String P_BETIS = "Betis";
	public static final String P_BARCELONA = "Barcelona";
	public static final String P_REAL_SOCIEDAD = "R-Sociedad";

	public static final String S_RACING = "Racing";
	public static final String S_LAS_PALMAS = "Palmas";
	public static final String S_MIRANDES = "Mirandes";
	public static final String S_HUESCA = "Huesca";
	public static final String S_LUGO = "Lugo";
	public static final String S_HERCULES = "Hercules";
	public static final String S_JEREZ = "Xerez";
	public static final String S_RECREATIVO = "Recreativo";
	public static final String S_VILLAREAL = "Villarreal";
	public static final String S_RM_CASTILLA = "RM-Castilla";
	public static final String S_BARCELONA_B = "Barcelona-B";
	public static final String S_ALMERIA = "Almeria";
	public static final String S_ELCHE = "Elche";
	public static final String S_CORDOBA = "Cordoba";
	public static final String S_SPORTING = "Sporting";
	public static final String S_MURCIA = "Murcia";
	public static final String S_ALCORCON = "Alcorcon";
	public static final String S_GUADALAJARA = "Guadalajara";
	public static final String S_PONFERRADINA = "Ponferradina";
	public static final String S_SABADELL = "Sabadell";
	public static final String S_GIRONA = "Girona";
	public static final String S_NUMANCIA = "Numancia";

	public static final String S_EIBAR = "Eibar";
	public static final String S_TENERIFE = "Tenerife";
	public static final String S_ALAVES = "Alaves";
	public static final String S_R_JAEN = "Real Jaen";
	public static final String S_ALBACETE = "Albacete";
	public static final String S_LLAGOSTERA = "Llagostera";
	public static final String S_LEGANES = "Leganes";
	
	

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

	/**
	 * Extraidos de marca.com
	 */
	private List<ParametroEquipo> parametros;

	public Equipo(final String nombre) {
		super();
		this.nombre = nombre;
		parametros = new ArrayList<ParametroEquipo>();// elementos no repetidos
	}

	public String getID() {
		return getNombre();
	}

	public List<ParametroEquipo> getParametrosComunes() {
		List<ParametroEquipo> comunes = new ArrayList<ParametroEquipo>();
		for (ParametroEquipo pe : parametros) {
			if (pe.getNombre().isParametroComunDeEquipo()) {
				comunes.add(pe);
			}
		}
		return comunes;
	}

	public Equipo(final Division division, final List<String> posiblesNombres) {
		super();
		parametros = new ArrayList<ParametroEquipo>();// elementos no repetidos
		try {
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
		} catch (Exception e) {
			// TODO: manage exception
		}
	}

	@Override
	public int compareTo(Equipo o) {
		int comparison = -1; // defaults different
		if (nombre != null && !nombre.isEmpty() && o != null
				&& o.getNombre() != null && !o.getNombre().isEmpty()
				&& nombre.equals(o.getNombre())) {
			comparison = 0;
		}
		return comparison;
	}

	public Division getDivision() {
		Division division = Division.INVALIDO;
		try {
			List<String> nombresPrimera = getNombresEquiposPrimera();
			List<String> nombresSegunda = getNombresEquiposSegunda();

			// Primera divisiï¿½n

			if (nombresPrimera.contains(nombre)) {
				division = Division.PRIMERA;
			} else if (nombresSegunda.contains(nombre)) {
				division = Division.SEGUNDA;
			} else {
				logger.error("No se ha podido encontrar una divisiï¿½n para el equipo con nombre: "
						+ nombre);
			}

		} catch (Exception e) {
			// TODO: manage exception
		}
		return division;
	}

	public static List<String> getNombresEquiposPrimera() throws Exception {
		List<String> nombresPrimera = new ArrayList<>();

		if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1213) {
			nombresPrimera.add(P_BARCELONA);
			nombresPrimera.add(P_REAL_MADRID);
			nombresPrimera.add(P_ATLETICO);
			nombresPrimera.add(P_REAL_SOCIEDAD);
			nombresPrimera.add(P_VALENCIA);
			nombresPrimera.add(P_MALAGA);
			nombresPrimera.add(P_BETIS);
			nombresPrimera.add(P_RAYO);
			nombresPrimera.add(P_SEVILLA);
			nombresPrimera.add(P_GETAFE);
			nombresPrimera.add(P_LEVANTE);
			nombresPrimera.add(P_ATHLETIC);
			nombresPrimera.add(P_ESPANYOL);
			nombresPrimera.add(P_VALLADOLID);
			nombresPrimera.add(P_GRANADA);
			nombresPrimera.add(P_OSASUNA);
			nombresPrimera.add(P_CELTA);
			nombresPrimera.add(P_MALLORCA);
			nombresPrimera.add(P_DEPORTIVO);
			nombresPrimera.add(P_ZARAGOZA);
		} else if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1314) {
			nombresPrimera.add(P_ATLETICO);
			nombresPrimera.add(P_BARCELONA);
			nombresPrimera.add(P_REAL_MADRID);
			nombresPrimera.add(P_ATHLETIC);
			nombresPrimera.add(P_SEVILLA);
			nombresPrimera.add(S_VILLAREAL);
			nombresPrimera.add(P_REAL_SOCIEDAD);
			nombresPrimera.add(P_VALENCIA);
			nombresPrimera.add(P_CELTA);
			nombresPrimera.add(P_LEVANTE);
			nombresPrimera.add(P_MALAGA);
			nombresPrimera.add(P_RAYO);
			nombresPrimera.add(P_GETAFE);
			nombresPrimera.add(P_ESPANYOL);
			nombresPrimera.add(P_GRANADA);
			nombresPrimera.add(S_ELCHE);
			nombresPrimera.add(S_ALMERIA);
			nombresPrimera.add(P_OSASUNA);
			nombresPrimera.add(P_VALLADOLID);
			nombresPrimera.add(P_BETIS);
		} else if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1415) {
			nombresPrimera.add(S_ALMERIA);
			nombresPrimera.add(P_ATHLETIC);
			nombresPrimera.add(P_ATLETICO);
			nombresPrimera.add(P_BARCELONA);
			nombresPrimera.add(P_CELTA);
			nombresPrimera.add(S_CORDOBA);
			nombresPrimera.add(P_DEPORTIVO);
			nombresPrimera.add(S_EIBAR);
			nombresPrimera.add(S_ELCHE);
			nombresPrimera.add(P_ESPANYOL);
			nombresPrimera.add(P_GETAFE);
			nombresPrimera.add(P_GRANADA);
			nombresPrimera.add(P_LEVANTE);
			nombresPrimera.add(P_MALAGA);
			nombresPrimera.add(P_REAL_MADRID);
			nombresPrimera.add(P_REAL_SOCIEDAD);
			nombresPrimera.add(P_RAYO);
			nombresPrimera.add(P_SEVILLA);
			nombresPrimera.add(P_VALENCIA);
			nombresPrimera.add(S_VILLAREAL);
		}

		if (nombresPrimera.size() != Temporada.NUM_EQUIPOS_PRIMERA) {
			throw new Exception(
					"El número de equipos añadidos a la lista de equipos de primera es "
							+ nombresPrimera.size() + ", pero se esperaban "
							+ Temporada.NUM_EQUIPOS_PRIMERA + " equipos");
		}

		return nombresPrimera;
	}

	public static List<String> getNombresEquiposSegunda() throws Exception {
		List<String> nombresSegunda = new ArrayList<>();
		if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1213) {
			nombresSegunda.add(S_ELCHE);
			nombresSegunda.add(S_VILLAREAL);
			nombresSegunda.add(S_ALMERIA);
			nombresSegunda.add(S_GIRONA);
			nombresSegunda.add(S_ALCORCON);
			nombresSegunda.add(S_LAS_PALMAS);
			nombresSegunda.add(S_PONFERRADINA);
			nombresSegunda.add(S_RM_CASTILLA);
			nombresSegunda.add(S_BARCELONA_B);
			nombresSegunda.add(S_SPORTING);
			nombresSegunda.add(S_LUGO);
			nombresSegunda.add(S_NUMANCIA);
			nombresSegunda.add(S_RECREATIVO);
			nombresSegunda.add(S_CORDOBA);
			nombresSegunda.add(S_MIRANDES);
			nombresSegunda.add(S_SABADELL);
			nombresSegunda.add(S_HERCULES);
			nombresSegunda.add(S_GUADALAJARA);
			nombresSegunda.add(S_MURCIA);
			nombresSegunda.add(S_RACING);
			nombresSegunda.add(S_HUESCA);
			nombresSegunda.add(S_JEREZ);
		} else if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1314) {
			nombresSegunda.add(S_EIBAR);
			nombresSegunda.add(P_DEPORTIVO);
			nombresSegunda.add(S_BARCELONA_B);
			nombresSegunda.add(S_MURCIA);
			nombresSegunda.add(S_SPORTING);
			nombresSegunda.add(S_LAS_PALMAS);
			nombresSegunda.add(S_CORDOBA);
			nombresSegunda.add(S_RECREATIVO);
			nombresSegunda.add(S_ALCORCON);
			nombresSegunda.add(S_SABADELL);
			nombresSegunda.add(S_TENERIFE);
			nombresSegunda.add(S_LUGO);
			nombresSegunda.add(S_NUMANCIA);
			nombresSegunda.add(P_ZARAGOZA);
			nombresSegunda.add(S_PONFERRADINA);
			nombresSegunda.add(S_GIRONA);
			nombresSegunda.add(P_MALLORCA);
			nombresSegunda.add(S_ALAVES);
			nombresSegunda.add(S_MIRANDES);
			nombresSegunda.add(S_RM_CASTILLA);
			nombresSegunda.add(S_R_JAEN);
			nombresSegunda.add(S_HERCULES);
		} else if (Temporada.temporadaAConsiderar == Temporada.TEMPORADA1415) {
			nombresSegunda.add(S_ALAVES);
			nombresSegunda.add(S_ALBACETE);
			nombresSegunda.add(S_ALCORCON);
			nombresSegunda.add(S_BARCELONA_B);
			nombresSegunda.add(S_GIRONA);
			nombresSegunda.add(S_LAS_PALMAS);
			nombresSegunda.add(S_LEGANES);
			nombresSegunda.add(S_LLAGOSTERA);
			nombresSegunda.add(S_LUGO);
			nombresSegunda.add(P_MALLORCA);
			nombresSegunda.add(S_MIRANDES);
			nombresSegunda.add(S_NUMANCIA);
			nombresSegunda.add(P_OSASUNA);
			nombresSegunda.add(S_PONFERRADINA);
			nombresSegunda.add(P_BETIS);
			nombresSegunda.add(S_RACING);
			nombresSegunda.add(S_RECREATIVO);
			nombresSegunda.add(S_SABADELL);
			nombresSegunda.add(S_SPORTING);
			nombresSegunda.add(S_TENERIFE);
			nombresSegunda.add(P_VALLADOLID);
			nombresSegunda.add(P_ZARAGOZA);
		}

		if (nombresSegunda.size() != Temporada.NUM_EQUIPOS_SEGUNDA) {
			throw new Exception(
					"El número de equipos añadidos a la lista de equipos de segunda es "
							+ nombresSegunda.size() + ", pero se esperaban "
							+ Temporada.NUM_EQUIPOS_SEGUNDA + " equipos");
		}

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
			logger.error("ERROR: ESTIMACIï¿½N INCORRECTA. Pesos mal calculados, ya que no hay estadï¿½sticas para el equipo: "
					+ this.getNombre());
		} else {
			pesoGanados = pesoGanados / numerojornadas;
			pesoEmpatados = pesoEmpatados / numerojornadas;
			pesoPerdidos = pesoPerdidos / numerojornadas;
		}
	}

	/**
	 * Es el identificador unico del equipo.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Es el identificador unico del equipo.
	 * 
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ParametroEquipo> getParametros() {
		Collections.sort(parametros);
		return parametros;
	}

	public List<ParametroEquipo> getParametros(
			final List<ParametroNombre> nombresParametros) {
		List<ParametroEquipo> parametrosADevolver = new ArrayList<ParametroEquipo>();
		Collections.sort(parametros);
		for (ParametroEquipo parametroEquipo : parametros) {
			if (nombresParametros.contains(parametroEquipo.getNombre()))
				parametrosADevolver.add(parametroEquipo);
		}
		return parametrosADevolver;
	}

	public void setParametros(List<ParametroEquipo> parametros) {
		this.parametros = parametros;
	}

	public ParametroEquipo getParametro(final ParametroNombre nombre)
			throws Exception {
		ParametroEquipo parametroSalida = new ParametroEquipo(
				ParametroNombre.INVALIDO, 0);
		for (ParametroEquipo parametro : parametros) {
			if (parametro.getNombre().equals(nombre)) {
				parametroSalida = parametro;
				break;
			}
		}
		if (parametroSalida.getNombre().equals(ParametroNombre.INVALIDO)) {
			throw new Exception("Parámetro no encontrado");
		}
		return parametroSalida;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNombre();
	}

}
