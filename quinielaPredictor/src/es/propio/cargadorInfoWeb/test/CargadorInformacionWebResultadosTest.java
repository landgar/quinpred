/**
 * 
 */
package es.propio.cargadorInfoWeb.test;

import junit.framework.TestCase;
import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class CargadorInformacionWebResultadosTest extends TestCase {

	private CargadorInformacionWebResultados cargador;

	@Override
	protected void setUp() {
		cargador = new CargadorInformacionWebResultados(true);

	}

	public void testCargar() {
		try {
			cargador.cargar();

			assertTrue(true);

		} catch (Exception e) {
			System.err.println("Error!!!!!!");
			e.printStackTrace();
		}
		pintar(cargador);
	}

	private static void pintar(final CargadorInformacionWebResultados cargador) {
		System.out.println("TEST CargadorInformacionWebTest");
		pintarTemporada(cargador.getTemporadaPrimera());
		pintarTemporada(cargador.getTemporadaSegunda());
	}

	private static void pintarTemporada(final Temporada temporada) {
		System.out
				.println("******************** Temporada: "
						+ temporada.getDivision().getCodigo()
						+ " ********************");
		for (Jornada jornada : temporada.getJornadas()) {
			System.out.println("******************** Jornada: "
					+ jornada.getNumeroJornada() + " ********************");
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
