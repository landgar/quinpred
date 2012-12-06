/**
 * 
 */
package es.propio.procesadoInfo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author nosotros
 * 
 */
public class Algoritmo1Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("TEST Algoritmo1Test");
		Algoritmo1 alg = new Algoritmo1();
		 calcula(alg);
		 alg.pintame();
		generadorAleatorio();
	}

	private static void calcula(Algoritmo1 alg) throws Exception {
		// Carga de boleto
		CargadorWebNombresProximaQuiniela cargadorBoleto = new CargadorWebNombresProximaQuiniela();
		cargadorBoleto.cargar("24");
		// Traspaso de partidos a listas para su predicción
		List<PronosticoPartido> listaPartidosPrimera = new ArrayList<PronosticoPartido>();
		List<PronosticoPartido> listaPartidosSegunda = new ArrayList<PronosticoPartido>();
		PronosticoPartido pronostico;
		Partido partido;
		Boleto boleto = cargadorBoleto.getBoleto();
		for (Map.Entry<Integer, Partido> entry : boleto.getPartidos()
				.entrySet()) {
			partido = entry.getValue();
			pronostico = new PronosticoPartido();
			pronostico.setPosicionPartido(entry.getKey());
			pronostico.setPartido(partido);
			if (partido.getEquipoLocal().getDivision().equals(Division.PRIMERA)) {
				listaPartidosPrimera.add(pronostico);
			} else if (partido.getEquipoLocal().getDivision()
					.equals(Division.SEGUNDA)) {
				listaPartidosSegunda.add(pronostico);
			}
		}
		// Predicción de resultados
		PronosticoJornada pronosticoPrimera = new PronosticoJornada(
				listaPartidosPrimera, 1, IdAlgoritmoEnum.ALGORITMO1);
		alg.setEstimacionJornadaPrimera(pronosticoPrimera);
		alg.calcularPronosticoPrimera();
		PronosticoJornada pronosticoSegunda = new PronosticoJornada(
				listaPartidosSegunda, 1, IdAlgoritmoEnum.ALGORITMO1);
		alg.setEstimacionJornadaSegunda(pronosticoSegunda);
		alg.calcularPronosticoSegunda();
	}

	public static void generadorAleatorio() {
		System.out.println("Generador aleatorio de valores normalizados: ");
		// Get a DescriptiveStatistics instance
		DescriptiveStatistics stats = new DescriptiveStatistics();
		// Add the data from the array
		for (int i = 0; i < 1000; i++) {
			stats.addValue(Algoritmo1.generateNormalizedRandomNumber());
		}
		Double mean = stats.getMean();
		Double std = stats.getStandardDeviation();
		Double median = stats.getPercentile(50);
		System.out.println("Media: " + mean);
		System.out.println("Desviación típica: " + std);
		System.out.println("Mediana: " + median);
	}

}
