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
		Temporada temporada = cargador.getTemporadaPrimeraHastaHoy();
		Equipo equipo = new Equipo("Barcelona");
		Integer numeroJornada = 15;
		System.out.println("# ganados: "
				+ temporada.getNumeroGanadosAnteriores(equipo, numeroJornada));
		System.out.println("# empatados: "
				+ temporada.getNumeroPerdidosAnteriores(equipo, numeroJornada));
		System.out
				.println("# perdidos: "
						+ temporada.getNumeroEmpatadosAnteriores(equipo,
								numeroJornada));
		System.out.println("#1s: "
				+ temporada.getNumeroUnosAnteriores(equipo, numeroJornada));
		System.out.println("#Xs: "
				+ temporada.getNumeroEmpatesAnteriores(equipo, numeroJornada));
		System.out.println("#2s: "
				+ temporada.getNumeroDosesAnteriores(equipo, numeroJornada));

	}
}
