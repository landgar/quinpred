package es.propio.test.lectorxml;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import es.propio.lectorxml.HandlerTxtNombresEquipos;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;

public class HandlerTxtNombresEquiposTest {

	public static void main(String[] args) throws Exception {

		// Se leen los datos de los equipos, para cada jornada
		HandlerTxtNombresEquipos lectorNombres = new HandlerTxtNombresEquipos();
		lectorNombres.leer(new File(
				"nombresPartidos_LoteriasYApuestas/nombres1.txt"));
		Set<Jornada> nombresJornadas = new HashSet<Jornada>();
		nombresJornadas.add(lectorNombres.getJornada());
		for (Jornada jornada : nombresJornadas) {
			System.out.println("Fecha: " + jornada.getFecha());
			System.out.println("Jornada: " + jornada.getNumeroJornada());
			for (Partido partido : jornada.getPartidos()) {
				System.out.println(partido.getPosicion() + ";"
						+ partido.getEquipoLocal().getNombre() + ";"
						+ partido.getEquipoVisitante().getNombre());
			}
		}
	}
}
