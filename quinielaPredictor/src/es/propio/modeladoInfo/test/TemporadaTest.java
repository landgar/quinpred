/**
 * 
 */
package es.propio.modeladoInfo.test;

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
		System.out.println("TEST TemporadaTest");
		obtenerResultadosAnteriores();
	}

	private static void obtenerResultadosAnteriores() throws Exception {
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados();
		cargador.cargar();
		Temporada temporada = cargador.getTemporadaSegunda();
		Equipo equipo = new Equipo("Numancia");
		Integer numeroJornada = 20;
		System.out.println("# ganados: "
				+ temporada.getNumeroGanadosAnteriores(equipo, numeroJornada));
		System.out
				.println("# empatados: "
						+ temporada.getNumeroEmpatadosAnteriores(equipo,
								numeroJornada));
		System.out.println("# perdidos: "
				+ temporada.getNumeroPerdidosAnteriores(equipo, numeroJornada));
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
				+ temporada.getGolesEnCasaAFavor(equipo));
		System.out.println("Goles fuera a favor: "
				+ temporada.getGolesFueraAFavor(equipo));
		System.out.println("Goles totales a favor: "
				+ temporada.getGolesTotalesAFavor(equipo));
		System.out.println("Goles en casa en contra: "
				+ temporada.getGolesEnCasaEnContra(equipo));
		System.out.println("Goles fuera en contra: "
				+ temporada.getGolesFueraEnContra(equipo));
		System.out.println("Goles totales en contra: "
				+ temporada.getGolesTotalesEnContra(equipo));
	}
}
