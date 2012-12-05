/**
 * 
 */
package es.propio.procesadoInfo.test;

import java.util.ArrayList;
import java.util.List;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.procesadoInfo.Algoritmo1;

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
		//TODO: precargar la jornada a estimar
//		PronosticoJornada pronosticoJornada = CargadorInformacionWebResultados.
//		List<PronosticoPartido> pronosticos = new ArrayList<PronosticoPartido>();
//		for (int i = 1; i <= 15; i++) {
//			PronosticoPartido pronostico = new PronosticoPartido();
//			pronostico.setLocal(new Equipo("Celta"));
//			pronostico.setVisitante(new Equipo("Malaga"));
//			pronostico.setPosicionPartido(i);
//		}
//		pronosticoJornada.setPronosticoPartidos(pronosticoJornada
//				.getPronosticoPartidos());
//		alg.setEstimacionJornadaPrimera(pronosticoJornada);
		
		alg.calcularPronosticoPrimera();
		alg.calcularPronosticoSegunda();
	}

}
