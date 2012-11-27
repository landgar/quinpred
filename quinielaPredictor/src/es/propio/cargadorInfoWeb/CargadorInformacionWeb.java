package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import es.propio.cargadorInfoWeb.test.HandlerHtmlInfo;
import es.propio.modeladoInfo.Temporada;

public class CargadorInformacionWeb {

	private Temporada temporadaPrimeraHastaHoy;
	private Temporada temporadaSegundaHastaHoy;

	public CargadorInformacionWeb() {
	}

	public void cargar() throws Exception {
		final URL urlPrimera = new URL(
				"http://www.elpais.com/deportes/futbol/competicion/primera/calendario");
		final URL urlSegunda = new URL(
				"http://www.elpais.com/deportes/futbol/competicion/segunda/calendario");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlPrimera.openStream()));
		String inputLine, webpage = "";
		while ((inputLine = in.readLine()) != null) {
			webpage += inputLine;
		}
		in.close();

		// Inicialización
		temporadaPrimeraHastaHoy = new Temporada();
		temporadaSegundaHastaHoy = new Temporada();
		
		HandlerHtmlInfo.extraerDatos(webpage, temporadaPrimeraHastaHoy);

		

	}

	/**
	 * @return the temporadaPrimeraHastaHoy
	 */
	public Temporada getTemporadaPrimeraHastaHoy() {
		return temporadaPrimeraHastaHoy;
	}

	/**
	 * @param temporadaPrimeraHastaHoy
	 *            the temporadaPrimeraHastaHoy to set
	 */
	public void setTemporadaPrimeraHastaHoy(Temporada temporadaPrimeraHastaHoy) {
		this.temporadaPrimeraHastaHoy = temporadaPrimeraHastaHoy;
	}

	/**
	 * @return the temporadaSegundaHastaHoy
	 */
	public Temporada getTemporadaSegundaHastaHoy() {
		return temporadaSegundaHastaHoy;
	}

	/**
	 * @param temporadaSegundaHastaHoy
	 *            the temporadaSegundaHastaHoy to set
	 */
	public void setTemporadaSegundaHastaHoy(Temporada temporadaSegundaHastaHoy) {
		this.temporadaSegundaHastaHoy = temporadaSegundaHastaHoy;
	}

}
