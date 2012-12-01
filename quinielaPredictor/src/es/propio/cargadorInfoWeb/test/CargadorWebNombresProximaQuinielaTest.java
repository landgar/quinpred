/**
 * 
 */
package es.propio.cargadorInfoWeb.test;

import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;

/**
 * @author i3casa
 * 
 */
public class CargadorWebNombresProximaQuinielaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CargadorWebNombresProximaQuiniela cargador = new CargadorWebNombresProximaQuiniela();
		cargador.cargar();

		pintar(cargador);
	}

	private static void pintar(final CargadorWebNombresProximaQuiniela cargador)
			throws Exception {
		System.out.println("TEST CargadorWebNombresProximaQuinielaTest");
		cargador.cargar();
		pintarPronosticoJornada(cargador.getPronosticoJornada());
	}

	private static void pintarPronosticoJornada(
			final PronosticoJornada pronosticoJornada) {
		System.out.println("******************** Pronóstico jornada: "
				+ pronosticoJornada.getNumeroJornada()
				+ "ª ********************");
		for (PronosticoPartido pronosticoPartido : pronosticoJornada
				.getPronosticoPartidos()) {
			System.out.println(pronosticoPartido.getPosicionPartido() + ".  "
					+ pronosticoPartido.getLocal().getNombre() + " - "
					+ pronosticoPartido.getVisitante().getNombre() + " : "
					+ pronosticoPartido.getResultadoMasProbable().getValor());
		}
	}

}
