/**
 * 
 */
package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.PronosticoJornada;

/**
 * @author i3casa
 * 
 */
public class CargadorWebNombresProximaQuiniela {

	private PronosticoJornada pronosticoJornada;

	public CargadorWebNombresProximaQuiniela() {
	}

	public void cargar() throws Exception {
		final URL urlPrimera = new URL("http://www.elfutbolin.com/quiniela/");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlPrimera.openStream()));
		String inputLine, webpage = "";
		while ((inputLine = in.readLine()) != null) {
			webpage += inputLine;

		}
		in.close();

		System.out.println(webpage);

		// TODO: meter un handler que guarde el contenido.
		Boleto boletoActual = new Boleto();
		HandlerWebBoleto.extraerDatos(webpage, boletoActual);

		System.out.println("FIN");
	}

	/**
	 * @return the pronosticoJornada
	 */
	public PronosticoJornada getPronosticoJornada() {
		return pronosticoJornada;
	}

	/**
	 * @param pronosticoJornada
	 *            the pronosticoJornada to set
	 */
	public void setPronosticoJornada(PronosticoJornada pronosticoJornada) {
		this.pronosticoJornada = pronosticoJornada;
	}
}
