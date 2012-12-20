/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

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

	private Mean media1PorJornada;
	private Mean mediaXPorJornada;
	private Mean media2PorJornada;
	private StandardDeviation std1PorJornada;
	private StandardDeviation stdXPorJornada;
	private StandardDeviation std2PorJornada;

	public Temporada() {
		super();
	}

	public Temporada(List<Jornada> jornadas, Division division) {
		super();
		this.jornadas = jornadas;
		this.division = division;
	}

	public void calcularEstadisticas() {
		media1PorJornada = new Mean();
		mediaXPorJornada = new Mean();
		media2PorJornada = new Mean();
		std1PorJornada = new StandardDeviation();
		stdXPorJornada = new StandardDeviation();
		std2PorJornada = new StandardDeviation();
		Iterator<Jornada> iter = jornadas.iterator();
		while (iter.hasNext()) {
			Jornada jornada = iter.next();
			if (jornada.getSeHaJugado()) {
				jornada.calcularEstadisticas();
				media1PorJornada.increment(Double.valueOf(jornada.getUnos()));
				mediaXPorJornada
						.increment(Double.valueOf(jornada.getEquises()));
				media2PorJornada.increment(Double.valueOf(jornada.getDoses()));
				std1PorJornada.increment(Double.valueOf(jornada.getUnos()));
				stdXPorJornada.increment(Double.valueOf(jornada.getEquises()));
				std2PorJornada.increment(Double.valueOf(jornada.getDoses()));
			}
		}
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
		return 2 * getNumeroGanadosAnteriores(equipo, numeroJornada)
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

	public List<Partido> getPartidosPasados() {
		List<Jornada> todas = getJornadas();
		List<Partido> partidos = new ArrayList<Partido>();
		Integer actual = getNumeroJornadaActual();
		int indiceActual = actual - 1;
		for (int i = 0; i < indiceActual; i++) {
			partidos.addAll(todas.get(i).getPartidos());
		}
		return partidos;
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
			@Override
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

	/**
	 * @return the media1PorJornada
	 */
	public Mean getMedia1PorJornada() {
		return media1PorJornada;
	}

	/**
	 * @param media1PorJornada
	 *            the media1PorJornada to set
	 */
	public void setMedia1PorJornada(Mean media1PorJornada) {
		this.media1PorJornada = media1PorJornada;
	}

	/**
	 * @return the mediaXPorJornada
	 */
	public Mean getMediaXPorJornada() {
		return mediaXPorJornada;
	}

	/**
	 * @param mediaXPorJornada
	 *            the mediaXPorJornada to set
	 */
	public void setMediaXPorJornada(Mean mediaXPorJornada) {
		this.mediaXPorJornada = mediaXPorJornada;
	}

	/**
	 * @return the media2PorJornada
	 */
	public Mean getMedia2PorJornada() {
		return media2PorJornada;
	}

	/**
	 * @param media2PorJornada
	 *            the media2PorJornada to set
	 */
	public void setMedia2PorJornada(Mean media2PorJornada) {
		this.media2PorJornada = media2PorJornada;
	}

	/**
	 * @return the std1PorJornada
	 */
	public StandardDeviation getStd1PorJornada() {
		return std1PorJornada;
	}

	/**
	 * @param std1PorJornada
	 *            the std1PorJornada to set
	 */
	public void setStd1PorJornada(StandardDeviation std1PorJornada) {
		this.std1PorJornada = std1PorJornada;
	}

	/**
	 * @return the stdXPorJornada
	 */
	public StandardDeviation getStdXPorJornada() {
		return stdXPorJornada;
	}

	/**
	 * @param stdXPorJornada
	 *            the stdXPorJornada to set
	 */
	public void setStdXPorJornada(StandardDeviation stdXPorJornada) {
		this.stdXPorJornada = stdXPorJornada;
	}

	/**
	 * @return the std2PorJornada
	 */
	public StandardDeviation getStd2PorJornada() {
		return std2PorJornada;
	}

	/**
	 * @param std2PorJornada
	 *            the std2PorJornada to set
	 */
	public void setStd2PorJornada(StandardDeviation std2PorJornada) {
		this.std2PorJornada = std2PorJornada;
	}

	public Jornada getJornadaExacta(Integer numeroJornada) {
		Jornada jornada = null;
		if (numeroJornada != null) {
			for (Jornada j : jornadas) {
				if (j.getNumeroJornada().equals(numeroJornada)) {
					jornada = j;
					break;
				}
			}
		}

		return jornada;
	}

}
