package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Carga los parametros de los PARTIDOs (no de equipos). No se distingue entre
 * jornadas.
 * 
 * @author i3casa
 * 
 */
public class ParametrizadorPartido {

	/**
	 * Carga los parametros de los partidos (no de los equipos). Los parametros
	 * del partido son: info de parametros de sus equipos y mas info.
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
				// Parámetros
				parametro = new Parametro(ParametroNombre.NUMEROJORNADA,
						numeroJornadaActual, partido);
				parametros.add(parametro);
				valorLocal = temporada.getGolesTotalesEnContraAnterioresA(
						local, numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesEnContraAnterioresA(
						visitante, numeroJornadaActual);
				valor = valorVisitante - valorLocal;
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESENCONTRA, valor,
						partido);
				parametros.add(parametro);
				valorLocal = temporada.getGolesTotalesAFavorAnterioresA(local,
						numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesAFavorAnterioresA(
						visitante, numeroJornadaActual);
				valor = valorVisitante - valorLocal;
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESAFAVOR, valor, partido);
				parametros.add(parametro);

				// // Parámetros de partidos calculados a partir de los
				// parametros
				// // de equipo (calculados previamente).
				// for (Equipo equipo : partido.getEquipos()) {
				//
				// for (ParametroEquipo parametroEquipo : equipo
				// .getParametros()) {
				//
				// valor = parametroEquipo.getValor();
				// parametro = new Parametro(parametroEquipo.getNombre(),
				// valor, partido);
				// parametros.add(parametro);
				// }
				//
				// }

				partido.setParametros(parametros);

			}
		}

	}

}
