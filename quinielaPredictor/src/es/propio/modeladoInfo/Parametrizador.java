package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.presentacionCalculo.Principal;

/**
 * Parametriza los equipos de un partido. No se distingue entre jornadas.
 * 
 * @author i3casa
 * 
 */
public class Parametrizador {

	/**
	 * Busca en fuentes de información parámetros.
	 */
	public static void cargarParametros(Temporada temporada) throws Exception {
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				Principal.MODO_MOCK);
		cargador.cargar();
		List<Parametro> parametros;
		Parametro parametro;
		Integer valorLocal, valorVisitante, diferencia;
		Integer numeroJornadaActual = temporada.getJornadaActual()
				.getNumeroJornada();
		Equipo local, visitante;
		for (Jornada jornada : temporada.getJornadas()) {
			for (Partido partido : jornada.getPartidos()) {
				local = partido.getEquipoLocal();
				visitante = partido.getEquipoVisitante();
				// Parámetros
				parametros = new ArrayList<Parametro>();
				valorLocal = temporada.getGolesTotalesEnContraAnterioresA(
						local, numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesEnContraAnterioresA(
						visitante, numeroJornadaActual);
				diferencia = valorVisitante - valorLocal;
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESENCONTRA, diferencia,
						partido);
				parametros.add(parametro);
				valorLocal = temporada.getGolesTotalesAFavorAnterioresA(local,
						numeroJornadaActual);
				valorVisitante = temporada.getGolesTotalesAFavorAnterioresA(
						visitante, numeroJornadaActual);
				diferencia = valorVisitante - valorLocal;
				parametro = new Parametro(
						ParametroNombre.DIFERENCIADEGOLESAFAVOR, diferencia,
						partido);
				parametros.add(parametro);
				partido.setParametros(parametros);
			}
		}

	}

}
