/**
 * 
 */
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
import es.propio.procesadoInfo.Algoritmo2;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author nosotros
 * 
 */
public class Algoritmo2Test {
	public static final boolean MODO_MOCK = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		System.out.println("TEST Algoritmo2Test");
		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				MODO_MOCK);
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Temporada temporadaSegunda = cargador.getTemporadaSegunda();
		Algoritmo2 alg = new Algoritmo2(temporadaPrimera, temporadaSegunda);
		calcula(alg, cargador);
	}

	private static void calcula(Algoritmo2 alg,
			CargadorInformacionWebResultados cargador) throws Exception {

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
				IdAlgoritmoEnum.ALGORITMO2);
		alg.setEstimacionJornadaPrimera(pronosticoPrimera);
		alg.calcularPronosticoPrimera();

		// SEGUNDA
		System.out.println("Predicción de resultados de SEGUNDA:");
		PronosticoJornada pronosticoSegunda = new PronosticoJornada(
				listaPartidosSegunda, jornadaActualSegunda.getNumeroJornada(),
				IdAlgoritmoEnum.ALGORITMO2);
		alg.setEstimacionJornadaSegunda(pronosticoSegunda);
		alg.calcularPronosticoSegunda();
	}

	private static void cargarParametrosDePartidos(
			CargadorInformacionWebResultados cargador) throws Exception {
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaPrimera());
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaSegunda());
	}

}
