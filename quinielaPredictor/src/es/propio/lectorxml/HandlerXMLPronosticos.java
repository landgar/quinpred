package es.propio.lectorxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;

public class HandlerXMLPronosticos extends DefaultHandler {

	static final Logger logger = Logger.getLogger(HandlerXMLPronosticos.class);

	/**
	 * @uml.property name="xr"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private final XMLReader xr;
	/**
	 * @uml.property name="pronosticoJornada"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private PronosticoJornada pronosticoJornada;

	public HandlerXMLPronosticos() throws SAXException {
		xr = XMLReaderFactory.createXMLReader();
		xr.setContentHandler(this);
		xr.setErrorHandler(this);
		pronosticoJornada = new PronosticoJornada();
	}

	public HandlerXMLPronosticos(final Integer numeroJornada)
			throws SAXException {
		xr = XMLReaderFactory.createXMLReader();
		xr.setContentHandler(this);
		xr.setErrorHandler(this);
		pronosticoJornada = new PronosticoJornada(numeroJornada);
	}

	public void leer(final File archivoXML) throws FileNotFoundException,
			IOException, SAXException {
		FileReader fr = new FileReader(archivoXML);
		xr.parse(new InputSource(fr));
	}

	public void leer(final String contenidoXml) throws FileNotFoundException,
			IOException, SAXException {
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(contenidoXml));
		xr.parse(is);
	}

	public void leer(final URL url) throws FileNotFoundException, IOException,
			SAXException {
		xr.parse(new InputSource(url.openStream()));
		// Iniialización
		pronosticoJornada = new PronosticoJornada();
	}

	@Override
	public void startDocument() {
		// System.out.println("Comienzo del Documento XML");
	}

	@Override
	public void endDocument() {
		// System.out.println("Final del Documento XML");
	}

	@Override
	public void startElement(String uri, String name, String qName,
			Attributes atts) {
		PronosticoPartido pronosticoPartido = new PronosticoPartido();
		if (name.equals("concurso")) {
			for (int i = 0; i < atts.getLength(); i++) {
				if (atts.getLocalName(i).equals("jornada")) {
					pronosticoJornada.setNumeroJornada(Integer.valueOf(atts
							.getValue(i)));
				}
				if (atts.getLocalName(i).equals("error")
						&& atts.getValue(i) == "1") {
					logger.error("ERROR: fichero de pronósticos no válido.");
					break;
				}
			}
		} else if (name.equals("partido")) {

			for (int i = 0; i < atts.getLength(); i++) {
				if (atts.getLocalName(i).equals("num")) {
					pronosticoPartido.setPosicionPartido(Integer.valueOf(atts
							.getValue(i)));
				} else if (atts.getLocalName(i).equals("p1")) {
					pronosticoPartido.setPorcentaje1(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("uno")) {// quinielista.com
					pronosticoPartido.setPorcentaje1(Float.valueOf(atts
							.getValue(i)));

				} else if (atts.getLocalName(i).equals("pX")) {
					pronosticoPartido.setPorcentajeX(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("equis")) {// quinielista.com
					pronosticoPartido.setPorcentajeX(Float.valueOf(atts
							.getValue(i)));

				} else if (atts.getLocalName(i).equals("p2")) {
					pronosticoPartido.setPorcentaje2(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("dos")) {// quinielista.com
					pronosticoPartido.setPorcentaje2(Float.valueOf(atts
							.getValue(i)));

				} else if (atts.getLocalName(i).equals("p1X")) {
					pronosticoPartido.setPorcentaje1X(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("p12")) {
					pronosticoPartido.setPorcentaje12(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("pX2")) {
					pronosticoPartido.setPorcentajeX2(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				} else if (atts.getLocalName(i).equals("p1X2")) {
					pronosticoPartido.setPorcentaje1X2(Float.valueOf(atts
							.getValue(i).replace(',', '.')));
				}
			}
			pronosticoJornada.getPronosticoPartidos().add(pronosticoPartido);
		}
	}

	@Override
	public void endElement(String uri, String name, String qName) {

	}

	/**
	 * @return the pronostico
	 */
	public PronosticoJornada getPronostico() {
		return pronosticoJornada;
	}

	/**
	 * @param pronostico
	 *            the pronostico to set
	 */
	public void setPronostico(PronosticoJornada pronostico) {
		this.pronosticoJornada = pronostico;
	}

	/**
	 * @return the xr
	 * @uml.property name="xr"
	 */
	public XMLReader getXr() {
		return xr;
	}
}