package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import es.propio.modeladoInfo.Temporada;

public class CargadorInformacionWebResultados {

	private Temporada temporadaPrimera;
	private Temporada temporadaSegunda;

	public CargadorInformacionWebResultados() {
	}

	public void cargar() throws Exception {
		// final URL urlPrimera = new URL(
		// "http://www.elpais.com/deportes/futbol/competicion/primera/calendario");
		// final URL urlSegunda = new URL(
		// "http://www.elpais.com/deportes/futbol/competicion/segunda/calendario");
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// urlPrimera.openStream()));
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// urlPrimera.openStream()));
		// BufferedReader in2 = new BufferedReader(new InputStreamReader(
		// urlSegunda.openStream()));
		final FileInputStream urlPrimera = new FileInputStream(
				"websSimuladas/primeraResultados.htm");
		final FileInputStream urlSegunda = new FileInputStream(
				"websSimuladas/segundaResultados.htm");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new DataInputStream(urlPrimera)));
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new DataInputStream(urlSegunda)));

		String inputLine, webpage = "";
		while ((inputLine = in.readLine()) != null) {
			webpage += inputLine;
		}
		in.close();

		String inputLine2, webpage2 = "";
		while ((inputLine2 = in2.readLine()) != null) {
			webpage2 += inputLine2;
		}
		in2.close();

		// Inicialización

		temporadaPrimera = HandlerHtmlResultados.extraerDatos(webpage);
		temporadaSegunda = HandlerHtmlResultados.extraerDatos(webpage2);

	}

	/**
	 * @return the temporadaPrimeraHastaHoy
	 */
	public Temporada getTemporadaPrimera() {
		return temporadaPrimera;
	}

	/**
	 * @param temporadaPrimeraHastaHoy
	 *            the temporadaPrimeraHastaHoy to set
	 */
	public void setTemporadaPrimera(Temporada temporadaPrimeraHastaHoy) {
		this.temporadaPrimera = temporadaPrimeraHastaHoy;
	}

	/**
	 * @return the temporadaSegundaHastaHoy
	 */
	public Temporada getTemporadaSegunda() {
		return temporadaSegunda;
	}

	/**
	 * @param temporadaSegundaHastaHoy
	 *            the temporadaSegundaHastaHoy to set
	 */
	public void setTemporadaSegunda(Temporada temporadaSegundaHastaHoy) {
		this.temporadaSegunda = temporadaSegundaHastaHoy;
	}

}
