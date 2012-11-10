package es.propio.test.lectorxml;

import java.io.File;

import es.propio.lectorxml.HandlerTxtResultados;
import es.propio.modeladoInfo.Partido;

public class HandlerTxtResultadosTest {
	public static void main(String[] args) throws Exception {
		HandlerTxtResultados lector = new HandlerTxtResultados();
		lector.leer(new File(
				"resultadosPartidos_LoteriasYApuestas/resultadosSorteos (2).txt"));
		System.out.println("Fecha: "
				+ lector.getJornada().getFecha().toString());
		for (Partido partido : lector.getJornada().getPartidos()) {
			System.out.println("Posición: " + partido.getPosicion());
			System.out.println("Resultado: "
					+ partido.getResultadoQuiniela().getValor());
		}

	}
}
