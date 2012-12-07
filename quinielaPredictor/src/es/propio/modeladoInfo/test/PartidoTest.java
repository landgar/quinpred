/**
 * 
 */
package es.propio.modeladoInfo.test;

import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Partido;

/**
 * @author nosotros
 * 
 */
public class PartidoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TEST PartidoTest");
		testGoles();
	}

	private static void testGoles() {
		// Inicialización
		Partido partido = new Partido(Boolean.TRUE);
		Equipo local = new Equipo("A");
		Equipo visitante = new Equipo("B");
		partido.setEquipoLocal(local);
		partido.setEquipoVisitante(visitante);
		partido.setGolesLocal(1);
		partido.setGolesVisitante(2);
		// Test unitario
		System.out.println("Goles del local: " + partido.goles(local));
		System.out.println("Goles del visitante: " + partido.goles(visitante));
	}
}
