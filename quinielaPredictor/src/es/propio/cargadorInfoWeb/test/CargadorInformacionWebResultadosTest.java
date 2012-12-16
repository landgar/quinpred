/**
 * 
 */
package es.propio.cargadorInfoWeb.test;

import java.util.Set;

import junit.framework.TestCase;
import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.ParametroEquipo;
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
			evaluarEstructuraTemporadas();
			evaluarParametrosComunesCargados();

		} catch (Exception e) {
			System.err.println("Error!!!!!!");
			e.printStackTrace();
		}
		pintar(cargador);
	}

	private void evaluarEstructuraTemporadas() {
		evaluarTemporadaCargada(cargador.getTemporadaPrimera());
		evaluarTemporadaCargada(cargador.getTemporadaSegunda());
	}

	private void evaluarTemporadaCargada(Temporada t) {
		assertNotNull(t);
		assertFalse(t.getJornadas().isEmpty());
		Jornada jornada = t.getJornadas().get(0);
		assertFalse(jornada.getPartidos().isEmpty());
		assertNotNull(jornada.getPartidos().size());
	}

	private void evaluarParametrosComunesCargados() {

		// En modo mock, los parametros comunes rellenados son de la jornada 2
		// (en primera y segunda)
		Jornada jornadaDePrimera = cargador.getTemporadaPrimera()
				.getJornadaExacta(Integer.valueOf(2));
		Set<Partido> partidosPrimeraEnJornada2 = jornadaDePrimera.getPartidos();
		for (Partido p : partidosPrimeraEnJornada2) {
			evaluarParamsComunesEnEquipo(p.getEquipoLocal());
			evaluarParamsComunesEnEquipo(p.getEquipoVisitante());
		}

		Jornada jornadaDeSegunda = cargador.getTemporadaSegunda()
				.getJornadaExacta(Integer.valueOf(2));
		Set<Partido> partidosSegundaEnJornada2 = jornadaDeSegunda.getPartidos();
		for (Partido p : partidosSegundaEnJornada2) {
			evaluarParamsComunesEnEquipo(p.getEquipoLocal());
			evaluarParamsComunesEnEquipo(p.getEquipoVisitante());
		}
	}

	private void evaluarParamsComunesEnEquipo(Equipo e) {
		Set<ParametroEquipo> comunes = e.getParametrosComunes();
		assertFalse(comunes.isEmpty());
		for (ParametroEquipo pe : comunes) {
			assertNotNull(pe.getValor());
		}
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
