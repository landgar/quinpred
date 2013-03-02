package es.propio.test.lectorxml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.xml.sax.SAXException;

import es.propio.lectorxml.HandlerXMLPronosticos;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

public class HandlerXMLPronosticosTest {

	public static void main(String[] args) throws FileNotFoundException,
			IOException, SAXException {

		Integer numJornada = 18;

		HandlerXMLPronosticos lector = new HandlerXMLPronosticos(numJornada,
				IdAlgoritmoEnum.WEB_QUINIELISTA);
		// lector.leer(new File("pronosticos_quinielista/prediccion15.xml"));

		lector.leer(new URL(
				"https://www.quinielista.es/xml/concurso2.asp?jornada="
						+ numJornada.toString()), numJornada,
				IdAlgoritmoEnum.WEB_QUINIELISTA);
		System.out.println("Número de jornada: "
				+ lector.getPronostico().getNumeroJornada());
		for (PronosticoPartido pronostico : lector.getPronostico()
				.getPronosticoPartidos()) {
			System.out.println(pronostico.getPorcentaje1() + "\t"
					+ pronostico.getPorcentajeX() + "\t"
					+ +pronostico.getPorcentaje2() + "\t"
					+ +pronostico.getPorcentaje1X() + "\t"
					+ +pronostico.getPorcentaje12() + "\t"
					+ pronostico.getPorcentaje1X2());
		}
	}
}