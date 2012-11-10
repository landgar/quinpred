package es.propio.test.lectorxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xml.sax.SAXException;

import es.propio.lectorxml.HandlerXMLPronosticos;
import es.propio.modeladoInfo.PronosticoPartido;

public class HandlerXMLPronosticosTest {
	public static void main(String[] args) throws FileNotFoundException,
			IOException, SAXException {
		HandlerXMLPronosticos lector = new HandlerXMLPronosticos();
		lector.leer(new File("pronosticos_quinielista/prediccion15.xml"));
		System.out.println("N�mero de jornada: "
				+ lector.getPronostico().getNumeroJornada());
		for (PronosticoPartido pronostico : lector.getPronostico()
				.getPronosticoPartidos()) {
			System.out.println("Posici�n del partido: "
					+ pronostico.getPosicionPartido());
			System.out.println(pronostico.getPorcentaje1() + "\t"
					+ pronostico.getPorcentajeX() + "\t"
					+ +pronostico.getPorcentaje2() + "\t"
					+ +pronostico.getPorcentaje1X() + "\t"
					+ +pronostico.getPorcentaje12() + "\t"
					+ pronostico.getPorcentaje1X2());
		}
	}
}