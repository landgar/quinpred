/**
 * 
 */
package es.propio.modeladoInfo.test;

import org.apache.log4j.BasicConfigurator;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Temporada;

/**
 * @author nosotros
 * 
 */
public class TemporadaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		System.out.println("TEST TemporadaTest");
		obtenerResultadosAnteriores();
	}

	private static void obtenerResultadosAnteriores() throws Exception {
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				true);
		cargador.cargar();
		Temporada temporada = cargador.getTemporadaPrimera();
		Equipo equipo = new Equipo("Valladolid");
		Integer numeroJornada = 20;
		System.out.println("# ganados: "
				+ temporada.getNumeroGanadosAnterioresDeUltimasJornadas(equipo,
						numeroJornada, 1));
		System.out.println("# empatados: "
				+ temporada.getNumeroEmpatadosAnterioresDeUltimasJornadas(
						equipo, numeroJornada, 1));
		System.out.println("# perdidos: "
				+ temporada.getNumeroPerdidosAnterioresDeUltimasJornadas(
						equipo, numeroJornada, 1));
		System.out.println("#1s: "
				+ temporada.getNumeroUnosAnteriores(equipo, numeroJornada));
		System.out.println("#Xs: "
				+ temporada.getNumeroEmpatesAnteriores(equipo, numeroJornada));
		System.out.println("#2s: "
				+ temporada.getNumeroDosesAnteriores(equipo, numeroJornada));
		System.out.println("Puntos: "
				+ temporada.getPuntosAnterioresA(equipo, numeroJornada));
		System.out.println("Jornadas pasadas: "
				+ temporada.getJornadasPasadas().size());
		System.out.println("Jornada actual: "
				+ temporada.getNumeroJornadaActual());
		System.out.println("Goles en casa a favor: "
				+ temporada.getGolesEnCasaAFavorAnterioresA(equipo,
						numeroJornada));
		System.out.println("Goles fuera a favor: "
				+ temporada.getGolesFueraAFavorAnterioresA(equipo,
						numeroJornada));
		System.out.println("Goles totales a favor: "
				+ temporada.getGolesTotalesAFavorAnterioresA(equipo,
						numeroJornada));
		System.out.println("Goles en casa en contra: "
				+ temporada.getGolesEnCasaEnContraAnterioresA(equipo,
						numeroJornada));
		System.out.println("Goles fuera en contra: "
				+ temporada.getGolesFueraEnContraAnterioresA(equipo,
						numeroJornada));
		System.out.println("Goles totales en contra: "
				+ temporada.getGolesTotalesEnContraAnterioresA(equipo,
						numeroJornada));
		System.out.println("Jornada actual: # partidos: "
				+ temporada.getJornadaActual().getPartidos().size());
	}
}
