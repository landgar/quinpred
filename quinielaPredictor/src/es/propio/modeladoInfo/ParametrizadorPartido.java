package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Carga los par�metros de los PARTIDOs (no de equipos). No se distingue entre
 * jornadas.
 * 
 * @author i3casa
 * 
 */
public class ParametrizadorPartido {

	/**
	 * Carga los par�metros de los partidos (no de los equipos). Los par�metros
	 * del partido son: info de par�metros de sus equipos y m�s info.
	 */
	public static void cargarParametrosDePartidos(Temporada temporada)
			throws Exception {

		List<Parametro> parametros;
		Parametro parametro;
		Integer valorLocal, valorVisitante, valor;
		Integer numeroJornadaActual;
		Equipo local, visitante;

		for (Jornada jornada : temporada.getJornadas()) {
			numeroJornadaActual = jornada.getNumeroJornada();
			for (Partido partido : jornada.getPartidos()) {

				local = partido.getEquipoLocal();
				visitante = partido.getEquipoVisitante();
				parametros = new ArrayList<Parametro>();

				// Par�metros
				parametro = new Parametro(ParametroNombre.NUMEROJORNADA,
						numeroJornadaActual, partido);
				parametros.add(parametro);
				valorLocal = temporada.getGolesTotalesEnContraAnterioresA(
						local, numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesEnContraAnterioresA(
						visitante, numeroJornadaActual);
				valor = Math.abs(valorVisitante - valorLocal);
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESENCONTRA, valor,
						partido);
				parametros.add(parametro);
				valorLocal = temporada.getGolesTotalesAFavorAnterioresA(local,
						numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesAFavorAnterioresA(
						visitante, numeroJornadaActual);
				valor = Math.abs(valorVisitante - valorLocal);
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESAFAVOR, valor, partido);
				parametros.add(parametro);

				valorLocal = temporada.getPuntosAnterioresA(local,
						numeroJornadaActual);
				valorVisitante = temporada.getPuntosAnterioresA(visitante,
						numeroJornadaActual);
				valor = Math.abs(valorVisitante - valorLocal);
				parametro = new Parametro(
						ParametroNombre.DIFERENCIA_POSICIONES_EN_CLASIFICACION,
						valor, partido);
				parametros.add(parametro);

				partido.setParametros(parametros);

			}
		}

	}

}
