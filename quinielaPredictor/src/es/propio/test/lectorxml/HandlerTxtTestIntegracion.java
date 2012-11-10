/**
 * 
 */
package es.propio.test.lectorxml;

import java.util.Set;

import es.propio.handlerDatos.CombinadorInfoJornadas;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;

/**
 * @author i3casa
 * 
 */
public class HandlerTxtTestIntegracion {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Set<Jornada> jornadasFinales = CombinadorInfoJornadas
				.obtenerTodaInfoJornadas();

		// FIXME: se comenta ya que se está guardando como resultado el fichero
		// de resultados de la jornada a predecir, que no tiene valores de
		// resultado. Se hace así para tomar sus nombres.

		// for (Jornada jornada : jornadasFinales) {
		// System.out.println("-----------------------\n");
		// System.out.println(jornada.getFecha() + "\n");
		// for (Partido partido : jornada.getPartidos()) {
		// System.out.println(partido.getPosicion() + ";"
		// + partido.getEquipoLocal().getValor().getNombre() + ";"
		// + partido.getEquipoVisitante().getValor().getNombre());
		// if (partido.getResultadoQuiniela() != null) {
		// System.out.println(";"
		// + partido.getResultadoQuiniela().getValor());
		// }
		// }
		// }
	}
}
