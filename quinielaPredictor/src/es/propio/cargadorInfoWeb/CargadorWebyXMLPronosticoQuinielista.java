package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.xml.sax.SAXException;

import es.propio.lectorxml.HandlerXMLPronosticos;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Objetivo: cargar los pronosticos de la jornada actual de quinielista.com
 * Metodo: entra en quinielista, procesa HTML hasta encontrar la URL hacia el
 * XML con los pronósticos. Después, carga la URL de ese XML y llama al
 * HandlerXMLPronosticos para cargarlo en el sistema.
 * 
 * @author carlos.andres
 * 
 */
public class CargadorWebyXMLPronosticoQuinielista {

	private static String URL_QUINIELISTA_HTML_JORNADA_ACTUAL = "https://www.quinielista.es/sistemistas/index.asp?fuseaction=estadistica";
	private static String URL_QUINIELISTA_HACIA_XML_PARTE1 = "https://www.quinielista.es/";
	private static String URL_QUINIELISTA_HACIA_XML_PARTE2 = "xml/sistemistas.asp?jornada=";

	private Integer numeroBoletoSegunQuinielista;

	public CargadorWebyXMLPronosticoQuinielista() {
	}

	public PronosticoJornada ejecutar() throws IOException, SAXException {
		String htmlConLinkHaciaXML = cargarHtml();
		String urlXml = extraerLinkHaciaXML(htmlConLinkHaciaXML);
		String contenidoXML = cargarXML(urlXml);
		return procesarXML(contenidoXML);
	}

	private String cargarHtml() throws IOException {

		String contenidoHtml = "";
		URL url = new URL(URL_QUINIELISTA_HTML_JORNADA_ACTUAL);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String aux = "";
		while ((aux = in.readLine()) != null) {
			contenidoHtml += aux;
		}
		in.close();
		return contenidoHtml;
	}

	private String extraerLinkHaciaXML(String htmlConLinkHaciaXML) {

		int indiceLink = htmlConLinkHaciaXML
				.indexOf(URL_QUINIELISTA_HACIA_XML_PARTE2);
		int longitudBase = URL_QUINIELISTA_HACIA_XML_PARTE2.length();
		String resto = htmlConLinkHaciaXML.substring(indiceLink + longitudBase);
		int indiceComilla = resto.indexOf("\"");

		String numeroBoletoActual = resto.subSequence(0, indiceComilla)
				.toString();
		this.numeroBoletoSegunQuinielista = Integer.valueOf(numeroBoletoActual);

		String linkHaciaXML = URL_QUINIELISTA_HACIA_XML_PARTE1
				+ URL_QUINIELISTA_HACIA_XML_PARTE2 + numeroBoletoActual;

		System.out.println("URL = " + linkHaciaXML);
		return linkHaciaXML;
	}

	private String cargarXML(String urlXml) throws IOException {
		String contenidoXml = "";
		URL url = new URL(urlXml);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String aux = "";
		while ((aux = in.readLine()) != null) {
			contenidoXml += aux;
		}
		in.close();
		return contenidoXml;
	}

	private PronosticoJornada procesarXML(String contenidoXml)
			throws SAXException, FileNotFoundException, IOException {
		HandlerXMLPronosticos handlerXml = new HandlerXMLPronosticos(
				numeroBoletoSegunQuinielista, IdAlgoritmoEnum.WEB_QUINIELISTA);
		handlerXml.leer(contenidoXml);
		PronosticoJornada pronosticoJornada = handlerXml.getPronostico();
		return pronosticoJornada.ordenarPorPosicionPartido();
	}

	public Integer getNumeroBoletoSegunQuinielista() {
		return numeroBoletoSegunQuinielista;
	}

	public void setNumeroBoletoSegunQuinielista(
			Integer numeroBoletoSegunQuinielista) {
		this.numeroBoletoSegunQuinielista = numeroBoletoSegunQuinielista;
	}

}
