/**
 * 
 */
package es.propio.procesadoInfo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	}

	private static void calcula(Algoritmo1 alg) throws Exception {
		CargadorWebNombresProximaQuiniela cargadorBoleto = new CargadorWebNombresProximaQuiniela();
		cargadorBoleto.cargar("24");
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
		PronosticoJornada pronosticoPrimera = new PronosticoJornada(
				listaPartidosPrimera, 1, IdAlgoritmoEnum.ALGORITMO1);
		alg.setEstimacionJornadaPrimera(pronosticoPrimera);
		alg.calcularPronosticoPrimera();
		PronosticoJornada pronosticoSegunda = new PronosticoJornada(
				listaPartidosSegunda, 1, IdAlgoritmoEnum.ALGORITMO1);
		alg.setEstimacionJornadaSegunda(pronosticoSegunda);
		alg.calcularPronosticoSegunda();
	}
}
