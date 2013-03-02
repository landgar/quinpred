/**
 * 
 */
package es.propio.cargadorInfoWeb.test;

import java.util.Map;

import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Partido;

/**
 * @author i3casa
 * 
 */
public class CargadorWebNombresProximaQuinielaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("TEST CargadorWebNombresProximaQuinielaTest");
		CargadorWebNombresProximaQuiniela cargador = new CargadorWebNombresProximaQuiniela();
		cargador.cargar("22");

		pintar(cargador);
	}

	private static void pintar(final CargadorWebNombresProximaQuiniela cargador)
			throws Exception {
		pintarPronosticoJornada(cargador.getBoleto());
	}

	private static void pintarPronosticoJornada(final Boleto boleto) {
		System.out.println("******************** Pronóstico jornada: "
				+ boleto.getNumeroBoleto() + "ª ********* "
				+ boleto.getFecha().toString() + " ***********");
		Map<Integer, Partido> mapa = boleto.getPartidos();
		for (Map.Entry<Integer, Partido> entry : mapa.entrySet()) {
			Partido partido = entry.getValue();
			System.out.println("Posición: " + entry.getKey() + ".  "
					+ partido.getEquipoLocal().getNombre() + " - "
					+ partido.getEquipoVisitante().getNombre());
		}
	}
}
