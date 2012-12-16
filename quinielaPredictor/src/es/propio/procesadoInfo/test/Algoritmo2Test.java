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
import es.propio.modeladoInfo.Parametrizador;
import es.propio.modeladoInfo.Parametro;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		System.out.println("TEST Algoritmo2Test");
		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				true);
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Temporada temporadaSegunda = cargador.getTemporadaSegunda();
		Algoritmo2 alg = new Algoritmo2(temporadaPrimera, temporadaSegunda);
		calcula(alg, cargador);
		// TODO: descomentar
		// alg.pintame();

		// FIXME: Esto sólo lo pinto para hacer pruebas en Matlab sobre redes
		// neuronales:
		for (Jornada jornada : temporadaPrimera.getJornadasPasadas()) {
			for (Partido partido : jornada.getPartidos()) {
				// Pinto en una línea todas las caracteristicas de cada partido.
				// En Matlab tendré que invertir la matriz
				List<Parametro> parametros = partido.getParametros();
				String resultado = "";
				for (Parametro parametro : parametros) {
					// DIFERENCIADEGOLESENCONTRA, DIFERENCIADEGOLESAFAVOR,
					// GOLESENCASAAFAVOR, GOLESFUERAAFAVOR, GOLESENCASAENCONTRA,
					// GOLESFUERAENCONTRA, GOLESTOTALESAFAVOR,
					// GOLESTOTALESENCONTRA, PUNTOSSIMPLES, GANADOS, EMPATADOS,
					// PERDIDOS, PUNTOSNORMALES, GOLESENCASAAFAVOR,
					// GOLESFUERAAFAVOR, GOLESENCASAENCONTRA,
					// GOLESFUERAENCONTRA, GOLESTOTALESAFAVOR,
					// GOLESTOTALESENCONTRA, PUNTOSSIMPLES, GANADOS, EMPATADOS,
					// PERDIDOS, PUNTOSNORMALES
					resultado += parametro.getValor() + ";";
				}
				System.out.println(partido.getID() + ";" + resultado
						+ partido.getResultadoQuiniela().getValor().getValor());
			}
		}

	}

	private static void calcula(Algoritmo2 alg,
			CargadorInformacionWebResultados cargador) throws Exception {

		List<Partido> partidos = new ArrayList<Partido>();
		partidos.addAll(cargador.getTemporadaPrimera().getJornadaActual()
				.getPartidos());
		partidos.addAll(cargador.getTemporadaSegunda().getJornadaActual()
				.getPartidos());
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
		// Parametrizador
		Parametrizador.cargarParametros(cargador.getTemporadaPrimera());
		Parametrizador.cargarParametros(cargador.getTemporadaSegunda());

		// Predicción de resultados
		PronosticoJornada pronosticoPrimera = new PronosticoJornada(
				listaPartidosPrimera, 1, IdAlgoritmoEnum.ALGORITMO2);
		// FIXME: para evitar que tome la primera jornada
		pronosticoPrimera.setNumeroJornada(10);
		alg.setEstimacionJornadaPrimera(pronosticoPrimera);
		alg.calcularPronosticoPrimera();
		PronosticoJornada pronosticoSegunda = new PronosticoJornada(
				listaPartidosSegunda, 1, IdAlgoritmoEnum.ALGORITMO2);
		// FIXME: para evitar que tome la primera jornada
		pronosticoSegunda.setNumeroJornada(10);
		alg.setEstimacionJornadaSegunda(pronosticoSegunda);
		alg.calcularPronosticoSegunda();
	}
}
