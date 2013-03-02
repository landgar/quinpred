package es.propio.cargadorInfoWeb.test;

import es.propio.cargadorInfoWeb.CargadorWebyXMLPronosticoQuinielista;
import es.propio.modeladoInfo.PronosticoJornada;

public class CargadorWebyXMLPronosticoQuinielistaTest {

	public static void main(String[] args) throws Exception {
		CargadorWebyXMLPronosticoQuinielista cargador = new CargadorWebyXMLPronosticoQuinielista();
		PronosticoJornada pronosticoJornada = cargador.ejecutar();
		pronosticoJornada.pintarme();
	}

}
