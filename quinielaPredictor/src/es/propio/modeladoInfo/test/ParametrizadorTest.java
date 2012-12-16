/**
 * 
 */
package es.propio.modeladoInfo.test;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Parametrizador;
import es.propio.modeladoInfo.Parametro;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class ParametrizadorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("TEST ParametrizadorTest");
		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				true);
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Parametrizador.cargarParametros(temporadaPrimera);
		for (Jornada jornada : temporadaPrimera.getJornadas()) {
			System.out.println("**************************** Jornada "
					+ jornada.getNumeroJornada()
					+ " ***************************");
			for (Partido partido : jornada.getPartidos()) {
				System.out.println("****************************"
						+ partido.toString());
				for (Parametro parametro : partido.getParametros()) {
					System.out.println(parametro.getNombre() + ": "
							+ parametro.getValor());
				}
			}
		}
	}

}
