/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Donde se almacenarán los resultados de toda la temporada hasta hoy.
 * 
 * @author i3casa
 * 
 */
public class Temporada {

	public static Integer NUM_JORNADAS_PRIMERA = 20;
	public static Integer NUM_JORNADAS_SEGUNDA = 21;

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
		for (int i = 1; i < jornadas.size(); i++) {
			jornada = jornadas.get(i);
			if (jornada.getNumeroJornada() < numeroJornada) {
				Partido partido = jornada.getPartidoDondeJuega(equipo);
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

	public Integer getNumeroDosesAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosAnteriores(equipo, numeroJornada,
				ValorResultado.DOS);
	}

	public Integer getNumeroEmpatesAnteriores(final Equipo equipo,
			final Integer numeroJornada) {
		return getNumeroResultadosAnteriores(equipo, numeroJornada,
				ValorResultado.EQUIS);
	}

	private Integer getNumeroResultadosAnteriores(final Equipo equipo,
			final Integer numeroJornada, ValorResultado resultado) {
		Integer salida = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		for (int i = 1; i < jornadas.size(); i++) {
			jornada = jornadas.get(i);
			if (jornada.getNumeroJornada() < numeroJornada) {
				Partido partido = jornada.getPartidoDondeJuega(equipo);
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
