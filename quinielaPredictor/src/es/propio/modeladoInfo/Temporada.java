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

	public Integer getNumeroGanadosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return getNumeroResultadosEquipoAnterioresDeUltimasJornadas(equipo,
				numeroJornada, ValorResultadoEquipo.GANADO,
				primeraJornadaAMirar);
	}

	public Integer getNumeroEmpatadosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return getNumeroResultadosEquipoAnterioresDeUltimasJornadas(equipo,
				numeroJornada, ValorResultadoEquipo.EMPATADO,
				primeraJornadaAMirar);
	}

	public Integer getNumeroPerdidosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return getNumeroResultadosEquipoAnterioresDeUltimasJornadas(equipo,
				numeroJornada, ValorResultadoEquipo.PERDIDO,
				primeraJornadaAMirar);
	}

	public Integer getNumeroGanadosPonderadosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) throws Exception {
		return getNumeroResultadosEquipoPonderadosAnterioresDeUltimasJornadas(
				equipo, numeroJornada, ValorResultadoEquipo.GANADO,
				primeraJornadaAMirar);
	}

	public Integer getNumeroEmpatadosPonderadosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) throws Exception {
		return getNumeroResultadosEquipoPonderadosAnterioresDeUltimasJornadas(
				equipo, numeroJornada, ValorResultadoEquipo.EMPATADO,
				primeraJornadaAMirar);
	}

	private Integer getNumeroResultadosEquipoAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			ValorResultadoEquipo resultado, final Integer primeraJornadaAMirar) {
		Integer salida = 0;
		salida += getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
				equipo, numeroJornada, resultado, primeraJornadaAMirar,
				Boolean.TRUE);
		salida += getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
				equipo, numeroJornada, resultado, primeraJornadaAMirar,
				Boolean.FALSE);
		return salida;
	}

	/**
	 * - para el equipo1, toma la distancia al equipo2 en POSICIONES EN
	 * CLASIFICACIÓN (sin Math.abs) en los partidos en casa del equipo1 con
	 * tendencia.
	 * 
	 * - para el equipo2, toma la distancia al equipo1 en POSICIONES EN
	 * CLASIFICACIÓN (sin Math.abs) en casa del equipo2 con tendencia.
	 * 
	 * @param equipo
	 * @param numeroJornada
	 * @param primeraJornadaAMirar
	 * @param rival
	 * @return
	 */
	public Integer getFortalezaDeUltimasJornadas(final Equipo equipo,
			final Integer numeroJornada, final Integer primeraJornadaAMirar,
			final Equipo rival) {
		Integer valor1 = getDiferenciaPuntosAnterioresADeUltimasJornadasEnCasa(
				equipo, numeroJornada, primeraJornadaAMirar, rival);
		Integer valor2 = getDiferenciaPuntosAnterioresADeUltimasJornadasFuera(
				rival, numeroJornada, primeraJornadaAMirar, equipo);
		return valor2 - valor1;
	}

	public Integer getDiferenciaPuntosAnterioresADeUltimasJornadasEnCasa(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar, final Equipo rival) {
		Integer valor1 = getPuntosAnterioresADeUltimasJornadasEnCasa(equipo,
				numeroJornada, primeraJornadaAMirar);
		Integer valor2 = getPuntosAnterioresADeUltimasJornadasEnCasa(rival,
				numeroJornada, primeraJornadaAMirar);
		return valor2 - valor1;
	}

	public Integer getDiferenciaPuntosAnterioresADeUltimasJornadasFuera(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar, final Equipo rival) {
		Integer valor1 = getPuntosAnterioresADeUltimasJornadasFuera(equipo,
				numeroJornada, primeraJornadaAMirar);
		Integer valor2 = getPuntosAnterioresADeUltimasJornadasFuera(rival,
				numeroJornada, primeraJornadaAMirar);
		return valor2 - valor1;
	}

	public Integer getPuntosAnterioresAEnCasa(final Equipo equipo,
			final Integer numeroJornada) {
		return getPuntosAnterioresADeUltimasJornadasEnCasa(equipo,
				numeroJornada, 1);
	}

	public Integer getPuntosAnterioresAFuera(final Equipo equipo,
			final Integer numeroJornada) {
		return getPuntosAnterioresADeUltimasJornadasFuera(equipo,
				numeroJornada, 1);
	}

	public Integer getPuntosAnterioresADeUltimasJornadasEnCasa(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return 3
				* getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
						equipo, numeroJornada, ValorResultadoEquipo.GANADO,
						primeraJornadaAMirar, Boolean.TRUE)
				+ getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
						equipo, numeroJornada, ValorResultadoEquipo.EMPATADO,
						primeraJornadaAMirar, Boolean.TRUE);
	}

	public Integer getPuntosAnterioresADeUltimasJornadasFuera(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return 3
				* getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
						equipo, numeroJornada, ValorResultadoEquipo.GANADO,
						primeraJornadaAMirar, Boolean.FALSE)
				+ getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
						equipo, numeroJornada, ValorResultadoEquipo.EMPATADO,
						primeraJornadaAMirar, Boolean.FALSE);
	}

	private Integer getNumeroResultadosEquipoAnterioresDeUltimasJornadasPorLugar(
			final Equipo equipo, final Integer numeroJornada,
			ValorResultadoEquipo resultado, final Integer primeraJornadaAMirar,
			final Boolean enCasa) {
		Integer salida = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		for (int i = 0; i < min; i++) {
			if (i >= 0) {
				jornada = jornadas.get(i);
				Partido partido = jornada.getPartidoDondeJuega(equipo);
				if (partido != null) {
					ValorResultadoEquipo resultadoEquipo = partido
							.getResultadoEquipo(equipo).getValor();
					if (partido.getSeHaJugado()
							&& resultadoEquipo.equals(resultado)) {
						if (enCasa && partido.getEquipoLocal().equals(equipo)) {
							salida++;
						} else if (!enCasa
								&& partido.getEquipoVisitante().equals(equipo)) {
							salida++;
						}
					}
				}
			}
		}
		return salida;
	}

	private Integer getNumeroResultadosEquipoPonderadosAnterioresDeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			ValorResultadoEquipo resultado, final Integer primeraJornadaAMirar)
			throws Exception {
		Integer salida = 0;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		Integer min = Math.min(jornadas.size(), numeroJornada - 1);
		Integer multiplicadorPorJugarFuera;
		Equipo equipoRival;
		for (int i = primeraJornadaAMirar + 1; i < min; i++) {
			if (i >= 0) {
				jornada = jornadas.get(i);
				Partido partido = jornada.getPartidoDondeJuega(equipo);
				if (partido != null) {
					ValorResultadoEquipo resultadoEquipo = partido
							.getResultadoEquipo(equipo).getValor();
					if (partido.getSeHaJugado()
							&& resultadoEquipo.equals(resultado)) {
						multiplicadorPorJugarFuera = 3;
						if (partido.getEquipoVisitante().getNombre()
								.equals(equipo.getNombre())) {
							multiplicadorPorJugarFuera = 4;
							equipoRival = partido.getEquipoLocal();
						} else {
							equipoRival = partido.getEquipoVisitante();
						}
						Integer totalEquipos;
						if (equipo.getDivision().equals(Division.PRIMERA)) {
							totalEquipos = NUM_EQUIPOS_PRIMERA;
						} else if (equipo.getDivision()
								.equals(Division.SEGUNDA)) {
							totalEquipos = NUM_EQUIPOS_SEGUNDA;
						} else {
							throw new Exception("ERROR: División no controlada");
						}
						salida += multiplicadorPorJugarFuera
								* (totalEquipos - equipoRival
										.getParametro(
												ParametroNombre.POSICION_EN_CLASIFICACION)
										.getValor());
					}
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
		return getPuntosAnterioresADeUltimasJornadas(equipo, numeroJornada, 1);
	}

	public Integer getPuntosSimplesAnterioresA(final Equipo equipo,
			final Integer numeroJornada, final Integer primeraJornadaAMirar) {
		return getPuntosSimplesAnterioresADeUltimasJornadas(equipo,
				numeroJornada, 1);
	}

	public Integer getPuntosAnterioresADeUltimasJornadas(final Equipo equipo,
			final Integer numeroJornada, final Integer primeraJornadaAMirar) {
		return 3
				* getNumeroGanadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, primeraJornadaAMirar)
				+ getNumeroEmpatadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, primeraJornadaAMirar);
	}

	public Integer getPuntosSimplesAnterioresADeUltimasJornadas(
			final Equipo equipo, final Integer numeroJornada,
			final Integer primeraJornadaAMirar) {
		return 2
				* getNumeroGanadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, primeraJornadaAMirar)
				+ getNumeroEmpatadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, primeraJornadaAMirar);
	}

	public Integer getPuntosPonderadosDeUltimasJornadas(final Equipo equipo,
			final Integer numeroJornada, final Integer primeraJornadaAMirar)
			throws Exception {
		return 2
				* getNumeroGanadosPonderadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, primeraJornadaAMirar)
				+ getNumeroEmpatadosPonderadosAnterioresDeUltimasJornadas(
						equipo, numeroJornada, primeraJornadaAMirar);
	}

	/**
	 * Será el número de ganados o empatados, pero ponderados por el valor del
	 * rival de cada partido.
	 * 
	 * @param equipo
	 * @param numeroJornada
	 * @return
	 */
	public Integer getPuntosPonderadosAnterioresA(final Equipo equipo,
			final Integer numeroJornada) throws Exception {
		return getPuntosPonderadosDeUltimasJornadas(equipo, numeroJornada, 1);
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
		return getPartidosPasados(getNumeroJornadaActual());
	}

	public List<Partido> getPartidosPasados(final Integer numeroJornada) {
		List<Jornada> todas = getJornadas();
		List<Partido> partidos = new ArrayList<Partido>();
		int indiceActual = numeroJornada - 1;
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
			List<Partido> partidos = jornada.getPartidos();
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
			List<Partido> partidos = jornada.getPartidos();
			for (Partido partido : partidos) {
				if (partido != null && !partido.getSeHaJugado()) {
					return jornada.getNumeroJornada();
				}
			}
		}
		return numeroJornadaActual;
	}

	public Integer getNumeroJornadaDePartido(final Partido partido) {
		Integer numeroJornada = -1;
		List<Jornada> jornadas = getJornadas();
		Jornada jornada;
		for (int i = 0; i < jornadas.size(); i++) {
			jornada = jornadas.get(i);
			List<Partido> partidos = jornada.getPartidos();
			for (Partido partidox : partidos) {
				if (partidox != null
						&& partidox.getID().equals(partido.getID())) {
					numeroJornada = jornada.getNumeroJornada();
				}
			}
		}
		return numeroJornada;
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

	public void cargarParametrosDeJornadaAnterioresYActual() throws Exception {
		Integer numJornadaActual = getNumeroJornadaActual();
		for (Jornada j : this.getJornadas()) {
			Integer numeroJornada = j.getNumeroJornada();
			if (numeroJornada <= numJornadaActual) {
				for (Partido partido : j.getPartidos()) {
					// Se añaden los parámetros del partido
					Equipo local = partido.getEquipoLocal();
					Equipo visitante = partido.getEquipoVisitante();

					cargarParametrosEquipo(local, numeroJornada);
					cargarParametrosEquipo(visitante, numeroJornada);

					// Parámetros especiales propios de partido
					local.getParametros().add(
							new ParametroEquipo(ParametroNombre.JUEGA_EN_CASA,
									1));
					local.getParametros()
							.add(new ParametroEquipo(
									ParametroNombre.JUEGA_FUERA, 0));
					visitante.getParametros().add(
							new ParametroEquipo(ParametroNombre.JUEGA_EN_CASA,
									0));
					visitante.getParametros()
							.add(new ParametroEquipo(
									ParametroNombre.JUEGA_FUERA, 1));

				}
			}
		}
	}

	private void cargarParametrosEquipo(Equipo equipo, Integer numeroJornada)
			throws Exception {
		List<ParametroEquipo> parametrosNuevos = new ArrayList<ParametroEquipo>();

		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PARTIDOS_GANADOS,
				getNumeroGanadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, 1)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PARTIDOS_EMPATADOS,
				getNumeroEmpatadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, 1)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PARTIDOS_PERDIDOS,
				getNumeroPerdidosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, 1)));
		parametrosNuevos.add(new ParametroEquipo(ParametroNombre.NUMEROJORNADA,
				numeroJornada));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.NUMERO_UNOS_ANTERIORES,
				getNumeroUnosAnteriores(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.NUMERO_EQUIS_ANTERIORES,
				getNumeroEmpatesAnteriores(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.NUMERO_DOSES_ANTERIORES,
				getNumeroDosesAnteriores(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PUNTOSNORMALES, getPuntosAnterioresA(equipo,
						numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(ParametroNombre.PUNTOSSIMPLES,
				getPuntosSimplesAnterioresADeUltimasJornadas(equipo,
						numeroJornada, 1)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PUNTOS_PONDERADOS,
				getPuntosPonderadosAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESTOTALESAFAVOR,
				getGolesTotalesAFavorAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESENCASAAFAVOR,
				getGolesEnCasaAFavorAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESFUERAAFAVOR,
				getGolesFueraAFavorAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESTOTALESENCONTRA,
				getGolesTotalesEnContraAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESENCASAENCONTRA,
				getGolesEnCasaEnContraAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.GOLESFUERAENCONTRA,
				getGolesFueraEnContraAnterioresA(equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PUNTOS_TENDENCIA,
				getPuntosPonderadosDeUltimasJornadas(equipo, numeroJornada,
						numeroJornada - 4)));
		parametrosNuevos.add(new ParametroEquipo(
				ParametroNombre.PUNTOS_EN_CASA, getPuntosAnterioresAEnCasa(
						equipo, numeroJornada)));
		parametrosNuevos.add(new ParametroEquipo(ParametroNombre.PUNTOS_FUERA,
				getPuntosAnterioresAFuera(equipo, numeroJornada)));

		equipo.getParametros().addAll(parametrosNuevos);
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
