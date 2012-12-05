/**
 * 
 */
package es.propio.cargadorInfoWeb.test;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class CargadorInformacionWebResultadosTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados();
		cargador.cargar();
		pintar(cargador);
	}

	private static void pintar(final CargadorInformacionWebResultados cargador) {
		System.out.println("TEST CargadorInformacionWebTest");
		pintarTemporada(cargador.getTemporadaPrimeraHastaHoy());
		pintarTemporada(cargador.getTemporadaSegundaHastaHoy());
	}

	private static void pintarTemporada(final Temporada temporada) {
		System.out.println("******************** Temporada: "
				+ temporada.getDivision().getCodigo()
				+ "ª ********************");
		for (Jornada jornada : temporada.getJornadas()) {
			System.out.println("******************** Jornada: "
					+ jornada.getNumeroJornada() + "ª ********************");
			for (Partido partido : jornada.getPartidos()) {
				System.out.println(partido.getEquipoLocal().getNombre() + " - "
						+ partido.getEquipoVisitante().getNombre() + " --> "
						+ partido.getGolesLocal() + " - "
						+ partido.getGolesVisitante() + "-->"
						+ partido.getResultadoQuiniela().getValor().getValor());
			}
		}
	}
}
