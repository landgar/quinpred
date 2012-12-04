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

	private Boleto boleto;

	public CargadorWebNombresProximaQuiniela() {
	}

	public void cargar(final String numeroBoleto) throws Exception {
		String seleccionBoleto = "";
		if (numeroBoleto != "") {
			seleccionBoleto = "quiniela.asp?jornada=" + numeroBoleto;
		}
		final URL urlPrimera = new URL("http://www.elfutbolin.com/quiniela/"
				+ seleccionBoleto);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlPrimera.openStream()));
		String inputLine, webpage = "";
		while ((inputLine = in.readLine()) != null) {
			webpage += inputLine;

		}
		in.close();
		Boleto boletoLeido = new Boleto();
		HandlerWebBoleto.extraerDatos(webpage, boletoLeido);
		setBoleto(boletoLeido);

		System.out.println("FIN");
	}

	public void cargar() throws Exception {
		cargar("");
	}

	/**
	 * @return the boleto
	 */
	public Boleto getBoleto() {
		return boleto;
	}

	/**
	 * @param boleto
	 *            the boleto to set
	 */
	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
}
