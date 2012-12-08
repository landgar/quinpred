/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Donde se almacenarán los resultados de toda la temporada hasta hoy.
 * 
 * @author i3casa
 * 
 */
public class Temporada {

	public static Integer NUM_EQUIPOS_PRIMERA = 20;
	public static Integer NUM_EQUIPOS_SEGUNDA = 22;

	/**
	 * @uml.property name="jornadas"
	 */
	private List<Jornada> jornadas;
	private Division division;

	public Temporada() {
		super();
	}

	public Temporada(List<Jornada> jornadas, Division division) {
		super();
		this.jornadas = jornadas;
		this.division = division;
	}

	public Integer getNumeroGanadosAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosEquipoAnteriores(equipo, numeroJornada,
				ValorResultadoEquipo.GANADO);
	}

	public Integer getNumeroPerdidosAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosEquipoAnteriores(equipo, numeroJornada,
				ValorResultadoEquipo.PERDIDO);
	}

	public Integer getNumeroEmpatadosAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosEquipoAnteriores(equipo, numeroJornada,
				ValorResultadoEquipo.EMPATADO);
	}

	private Integer getNumeroResultadosEquipoAnteriores(final Equipo equipo,
			final Integer numeroJornada, ValorResultadoEquipo resultado) {
		Integer salida = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		for (int i = 0; i < min; i++) {
			jornada = jornadas.get(i);
			Partido partido = jornada.getPartidoDondeJuega(equipo);
			if (partido != null) {
				ValorResultadoEquipo resultadoEquipo = partido
						.getResultadoEquipo(equipo).getValor();
				if (partido.getSeHaJugado()
						&& resultadoEquipo.equals(resultado)) {
					salida++;
				}
			}
		}
		return salida;
	}

	public Integer getNumeroUnosAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosAnteriores(equipo, numeroJornada,
				ValorResultado.UNO);
	}

	public Integer getNumeroEmpatesAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosAnteriores(equipo, numeroJornada,
				ValorResultado.EQUIS);
	}

	public Integer getNumeroDosesAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosAnteriores(equipo, numeroJornada,
				ValorResultado.DOS);
	}

	private Integer getNumeroResultadosAnteriores(final Equipo equipo,
			final Integer numeroJornada, ValorResultado resultado) {
		Integer salida = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		for (int i = 0; i < min; i++) {
			jornada = jornadas.get(i);
			Partido partido = jornada.getPartidoDondeJuega(equipo);
			if (partido != null) {
				ValorResultado resultadoPartido = partido
						.getResultadoQuiniela().getValor();
				if (partido.getSeHaJugado()
						&& resultadoPartido.equals(resultado)) {
					salida++;
				}
			}
		}
		return salida;
	}

	public Integer getPuntosAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return 3 * getNumeroGanadosAnteriores(equipo, numeroJornada)
				+ getNumeroEmpatadosAnteriores(equipo, numeroJornada);
	}
	
	public Integer getPuntosSimplesAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return 2* getNumeroGanadosAnteriores(equipo, numeroJornada)
				+ getNumeroEmpatadosAnteriores(equipo, numeroJornada);
	}

	public List<Jornada> getJornadasPasadas() {
		List<Jornada> todas = getJornadas();
		List<Jornada> pasadas = new ArrayList<Jornada>();
		Integer actual = getNumeroJornadaActual();
		int indiceActual = actual - 1;
		for (int i = 0; i < indiceActual; i++) {
			pasadas.add(todas.get(i));
		}
		return pasadas;
	}

	public Jornada getJornadaActual() {
		Jornada jornada = new Jornada();
		List<Jornada> jornadas = getJornadas();
		for (int i = 0; i < jornadas.size(); i++) {
			jornada = jornadas.get(i);
			Set<Partido> partidos = jornada.getPartidos();
			for (Partido partido : partidos) {
				if (!partido.getSeHaJugado()) {
					return jornada;
				}
			}
		}
		return jornada;
	}

	public Integer getNumeroJornadaActual() {
		Integer numeroJornadaActual = -1;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		for (int i = 0; i < jornadas.size(); i++) {
			jornada = jornadas.get(i);
			Set<Partido> partidos = jornada.getPartidos();
			for (Partido partido : partidos) {
				if (partido != null && !partido.getSeHaJugado()) {
					return jornada.getNumeroJornada();
				}
			}
		}
		return numeroJornadaActual;
	}

	public Integer getGolesTotalesAFavorAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesEnCasaAFavorAnterioresA(equipo, numeroJornada)
				+ getGolesFueraAFavorAnterioresA(equipo, numeroJornada);
	}

	public Integer getGolesEnCasaAFavorAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesPorLugarAFavorAnterioresA(equipo, Boolean.TRUE,
				numeroJornada);
	}

	public Integer getGolesFueraAFavorAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesPorLugarAFavorAnterioresA(equipo, Boolean.FALSE,
				numeroJornada);
	}

	private Integer getGolesPorLugarAFavorAnterioresA(final Equipo equipo,
			final Boolean enCasaOFuera, final Integer numeroJornada) {
		Integer golesTotales = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		for (int i = 0; i < min; i++) {
			jornada = jornadas.get(i);
			Partido partido = jornada.getPartidoDondeJuega(equipo);
			if (partido != null && partido.getSeHaJugado()) {
				if (enCasaOFuera && partido.esLocal(equipo)) {
					golesTotales += partido.getGolesLocal();
				}
				if (!enCasaOFuera && partido.esVisitante(equipo)) {
					golesTotales += partido.getGolesVisitante();
				}
			}
		}
		return golesTotales;
	}

	public Integer getGolesTotalesEnContraAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesEnCasaEnContraAnterioresA(equipo, numeroJornada)
				+ getGolesFueraEnContraAnterioresA(equipo, numeroJornada);
	}

	public Integer getGolesEnCasaEnContraAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesPorLugarEnContraAnterioresA(equipo, Boolean.TRUE,
				numeroJornada);
	}

	public Integer getGolesFueraEnContraAnterioresA(final Equipo equipo,
			final Integer numeroJornada) {
		return getGolesPorLugarEnContraAnterioresA(equipo, Boolean.FALSE,
				numeroJornada);
	}

	private Integer getGolesPorLugarEnContraAnterioresA(final Equipo equipo,
			final Boolean enCasaOFuera, final Integer numeroJornada) {
		Integer golesTotales = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		for (int i = 0; i < min; i++) {
			jornada = jornadas.get(i);
			Partido partido = jornada.getPartidoDondeJuega(equipo);
			if (partido != null && partido.getSeHaJugado()) {
				if (enCasaOFuera && partido.esLocal(equipo)) {
					golesTotales += partido.getGolesVisitante();
				}
				if (!enCasaOFuera && partido.esVisitante(equipo)) {
					golesTotales += partido.getGolesLocal();
				}
			}
		}
		return golesTotales;
	}

	/**
	 * @return the division
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	/**
	 * @return the jornadas
	 */
	public List<Jornada> getJornadas() {
		Collections.sort(jornadas, new Comparator<Jornada>() {
			public int compare(Jornada o1, Jornada o2) {
				return o1.getNumeroJornada().compareTo(o2.getNumeroJornada());
			}
		});
		return jornadas;
	}

	/**
	 * @param jornadas
	 *            the jornadas to set
	 */
	public void setJornadas(List<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

}
