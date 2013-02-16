package es.propio.procesadoInfo.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.ParametrizadorPartido;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.Algoritmo2;
import es.propio.procesadoInfo.Algoritmo3;
import es.propio.procesadoInfo.Algoritmo5;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

public class AbstractAlgoritmoTest {
	public static boolean modoMock = false;

	protected static void calcula(IdAlgoritmoEnum idAlgoritmo) throws Exception {

		BasicConfigurator.configure();
		System.out.println("TEST Algoritmo " + idAlgoritmo.getIdAlgoritmo());
		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				isModoMock());
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Temporada temporadaSegunda = cargador.getTemporadaSegunda();
		AbstractAlgoritmo alg;
		if (idAlgoritmo.equals(IdAlgoritmoEnum.ALGORITMO1)) {
			alg = new Algoritmo1(temporadaPrimera, temporadaSegunda);
		} else if (idAlgoritmo.equals(IdAlgoritmoEnum.ALGORITMO2)) {
			alg = new Algoritmo2(temporadaPrimera, temporadaSegunda);
		} else if (idAlgoritmo.equals(IdAlgoritmoEnum.ALGORITMO3)) {
			alg = new Algoritmo3(temporadaPrimera, temporadaSegunda);
		}  else if (idAlgoritmo.equals(IdAlgoritmoEnum.ALGORITMO5)) {
			alg = new Algoritmo5(temporadaPrimera, temporadaSegunda);
		} else {
			throw new Exception("Algoritmo no controlado");
		}

		Jornada jornadaActualPrimera = cargador.getTemporadaPrimera()
				.getJornadaActual();
		Jornada jornadaActualSegunda = cargador.getTemporadaSegunda()
				.getJornadaActual();

		List<Partido> partidos = new ArrayList<Partido>();
		partidos.addAll(jornadaActualPrimera.getPartidos());
		partidos.addAll(jornadaActualSegunda.getPartidos());

		// Traspaso de partidos a listas para su predicción
		List<PronosticoPartido> listaPartidosPrimera = new ArrayList<PronosticoPartido>();
		List<PronosticoPartido> listaPartidosSegunda = new ArrayList<PronosticoPartido>();
		PronosticoPartido pronostico;
		Partido partido;

		for (int i = 0; i < partidos.size(); i++) {
			partido = partidos.get(i);
			pronostico = new PronosticoPartido();
			pronostico.setPosicionPartido(i);
			pronostico.setPartido(partido);
			if (partido.getEquipoLocal().getDivision().equals(Division.PRIMERA)) {
				listaPartidosPrimera.add(pronostico);
			} else if (partido.getEquipoLocal().getDivision()
					.equals(Division.SEGUNDA)) {
				listaPartidosSegunda.add(pronostico);
			}
		}

		cargarParametrosDePartidos(cargador);

		// PRIMERA
		System.out.println("Predicción de resultados de PRIMERA:");
		PronosticoJornada pronosticoPrimera = new PronosticoJornada(
				listaPartidosPrimera, jornadaActualPrimera.getNumeroJornada(),
				idAlgoritmo);
		alg.setEstimacionJornadaPrimera(pronosticoPrimera);
		alg.calcularPronosticoPrimera();

		// SEGUNDA
		System.out.println("Predicción de resultados de SEGUNDA:");
		PronosticoJornada pronosticoSegunda = new PronosticoJornada(
				listaPartidosSegunda, jornadaActualSegunda.getNumeroJornada(),
				idAlgoritmo);
		alg.setEstimacionJornadaSegunda(pronosticoSegunda);
		alg.calcularPronosticoSegunda();

		alg.pintame();
	}

	private static void cargarParametrosDePartidos(
			CargadorInformacionWebResultados cargador) throws Exception {
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaPrimera());
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaSegunda());
	}

	/**
	 * @return the modoMock
	 */
	public static boolean isModoMock() {
		return modoMock;
	}

	/**
	 * @param modoMock
	 *            the modoMock to set
	 */
	public static void setModoMock(boolean modoMock) {
		AbstractAlgoritmoTest.modoMock = modoMock;
	}

}
