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
		Integer valorLocal, valorVisitante, valor;
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
				// Parámetros por equipo
				for (Equipo equipo : partido.getEquipos()) {
					valor = temporada.getGolesEnCasaAFavorAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(
							ParametroNombre.GOLESENCASAAFAVOR, valor, partido);
					parametros.add(parametro);
					valor = temporada.getGolesFueraAFavorAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.GOLESFUERAAFAVOR,
							valor, partido);
					parametros.add(parametro);
					valor = temporada.getGolesEnCasaEnContraAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(
							ParametroNombre.GOLESENCASAENCONTRA, valor, partido);
					parametros.add(parametro);
					valor = temporada.getGolesFueraEnContraAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(
							ParametroNombre.GOLESFUERAENCONTRA, valor, partido);
					parametros.add(parametro);
					valor = temporada.getGolesTotalesAFavorAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(
							ParametroNombre.GOLESTOTALESAFAVOR, valor, partido);
					parametros.add(parametro);
					valor = temporada.getGolesTotalesEnContraAnterioresA(
							equipo, numeroJornadaActual);
					parametro = new Parametro(
							ParametroNombre.GOLESTOTALESENCONTRA, valor,
							partido);
					parametros.add(parametro);
					valor = temporada.getPuntosSimplesAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.PUNTOSSIMPLES,
							valor, partido);
					parametros.add(parametro);
					valor = temporada.getNumeroGanadosAnteriores(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.GANADOS, valor,
							partido);
					parametros.add(parametro);
					valor = temporada.getNumeroEmpatadosAnteriores(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.EMPATADOS, valor,
							partido);
					parametros.add(parametro);
					valor = temporada.getNumeroPerdidosAnteriores(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.PERDIDOS, valor,
							partido);
					parametros.add(parametro);
					valor = temporada.getPuntosAnterioresA(equipo,
							numeroJornadaActual);
					parametro = new Parametro(ParametroNombre.PUNTOSNORMALES,
							valor, partido);
					parametros.add(parametro);
				}
				partido.setParametros(parametros);

			}
		}

	}

}
