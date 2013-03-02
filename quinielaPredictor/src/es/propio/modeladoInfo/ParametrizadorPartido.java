package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Carga los parámetros de los PARTIDOs (no de equipos). No se distingue entre
 * jornadas.
 * 
 * @author i3casa
 * 
 */
public class ParametrizadorPartido {

	/**
	 * Carga los parámetros de los partidos (no de los equipos). Los parámetros
	 * del partido son: info de parámetros de sus equipos y más info.
	 */
	public static void cargarParametrosDePartidos(Temporada temporada)
			throws Exception {

		List<Parametro> parametros;
		Parametro parametro;
		Integer valorLocal, valorVisitante, valor;
		Integer numeroJornada_i;
		Integer numJornadaActual = temporada.getNumeroJornadaActual();
		Equipo local, visitante;

		for (Jornada jornada : temporada.getJornadas()) {
			numeroJornada_i = jornada.getNumeroJornada();
			if (numeroJornada_i <= numJornadaActual) {
				for (Partido partido : jornada.getPartidos()) {
					local = partido.getEquipoLocal();
					visitante = partido.getEquipoVisitante();
					parametros = new ArrayList<Parametro>();

					// Parámetros
					parametro = new Parametro(ParametroNombre.NUMEROJORNADA,
							numeroJornada_i, partido);
					parametros.add(parametro);
					valorLocal = temporada.getGolesTotalesEnContraAnterioresA(
							local, numeroJornada_i);
					valorVisitante = temporada
							.getGolesTotalesEnContraAnterioresA(visitante,
									numeroJornada_i);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIADEGOLESENCONTRA, valor,
							partido);
					parametros.add(parametro);
					valorLocal = temporada.getGolesTotalesAFavorAnterioresA(
							local, numeroJornada_i);
					valorVisitante = temporada
							.getGolesTotalesAFavorAnterioresA(visitante,
									numeroJornada_i);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIADEGOLESAFAVOR, valor,
							partido);
					parametros.add(parametro);

					// valorLocal = temporada.getPuntosAnterioresA(local,
					// numeroJornadaActual);
					// valorVisitante =
					// temporada.getPuntosAnterioresA(visitante,
					// numeroJornadaActual);
					valorLocal = local.getParametro(
							ParametroNombre.POSICION_EN_CLASIFICACION)
							.getValor();
					valorVisitante = visitante.getParametro(
							ParametroNombre.POSICION_EN_CLASIFICACION)
							.getValor();
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_PUNTOS, valor, partido);
					parametros.add(parametro);

					valorLocal = temporada.getPuntosAnterioresA(local,
							numeroJornada_i);
					valorVisitante = temporada.getPuntosAnterioresA(visitante,
							numeroJornada_i);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_POSICIONES_EN_CLASIFICACION,
							valor, partido);
					parametros.add(parametro);

					valorLocal = temporada.getPuntosPonderadosAnterioresA(
							local, numeroJornada_i);
					valorVisitante = temporada.getPuntosPonderadosAnterioresA(
							visitante, numeroJornada_i);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_PUNTOS_PONDERADOS,
							valor, partido);
					parametros.add(parametro);

					valorLocal = temporada
							.getPuntosSimplesAnterioresADeUltimasJornadas(
									local, numeroJornada_i, 1);
					valorVisitante = temporada
							.getPuntosSimplesAnterioresADeUltimasJornadas(
									visitante, numeroJornada_i, 1);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_PUNTOS_SIMPLES, valor,
							partido);
					parametros.add(parametro);

					valorLocal = temporada.getPuntosPonderadosAnterioresA(
							local, numeroJornada_i);
					valorVisitante = temporada.getPuntosPonderadosAnterioresA(
							visitante, numeroJornada_i);
					Integer diferencia = valorVisitante - valorLocal;
					// Hay menos empate (más diferencia) si el local es mejor
					// que el visitante
					if (valorLocal > valorVisitante)
						diferencia = diferencia + 1;
					valor = Math.abs(diferencia);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_PUNTOS_PARA_EMPATE,
							valor, partido);
					parametros.add(parametro);

					Integer NUMERO_JORNADAS_TENDENCIA = 4;
					valorLocal = temporada
							.getPuntosPonderadosDeUltimasJornadas(local,
									numeroJornada_i, numeroJornada_i
											- NUMERO_JORNADAS_TENDENCIA);
					valorVisitante = temporada
							.getPuntosPonderadosDeUltimasJornadas(visitante,
									numeroJornada_i, numeroJornada_i
											- NUMERO_JORNADAS_TENDENCIA);
					valor = Math.abs(valorVisitante - valorLocal);
					parametro = new Parametro(
							ParametroNombre.DIFERENCIA_PUNTOS_TENDENCIA_PARA_EMPATE,
							valor, partido);
					parametros.add(parametro);

					NUMERO_JORNADAS_TENDENCIA = 8;
					parametro = new Parametro(ParametroNombre.FORTALEZA_LUGAR,
							temporada.getFortalezaDeUltimasJornadas(local,
									numeroJornada_i, numeroJornada_i
											- NUMERO_JORNADAS_TENDENCIA,
									visitante), partido);
					parametros.add(parametro);

					// Si un equipo es peor, pero juega en casa, intentará
					// empatar. El empate óptimo es cuando la separación de los
					// equipos es X
					Integer SEPARACION_OPTIMA = 7;
					valorLocal = local.getParametro(
							ParametroNombre.POSICION_EN_CLASIFICACION)
							.getValor();
					valorVisitante = visitante.getParametro(
							ParametroNombre.POSICION_EN_CLASIFICACION)
							.getValor();
					diferencia = SEPARACION_OPTIMA + valorVisitante
							- valorLocal;
					valor = Math.abs(diferencia);
					parametro = new Parametro(
							ParametroNombre.PROBABILIDAD_COMPENSAR_PARA_EMPATE,
							valor, partido);
					parametros.add(parametro);

					partido.setParametros(parametros);
				}
			}
		}

	}
}
