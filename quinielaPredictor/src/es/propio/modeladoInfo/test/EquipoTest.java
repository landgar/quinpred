/**
 * 
 */
package es.propio.modeladoInfo.test;

import java.util.ArrayList;
import java.util.List;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Equipo;

/**
 * @author i3casa
 * 
 */
public class EquipoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TEST EquipoTest");
		List<String> posiblesNombres = new ArrayList<>();
		posiblesNombres.add("Barcelona-B");
		posiblesNombres.add("Osasuna");
		posiblesNombres.add("Barcelona");
		Equipo equipo = new Equipo(Division.PRIMERA, posiblesNombres);
		System.out.println("El equipo es: " + equipo.getNombre());
		posiblesNombres.add("Barcelona-B");
		posiblesNombres.add("Osasuna");
		posiblesNombres.add("Barcelona");
		Equipo equipo2 = new Equipo(Division.SEGUNDA, posiblesNombres);
		System.out.println("El equipo es: " + equipo2.getNombre());
		System.out.println("FIN");

	}

}
