package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.propio.modeladoInfo.Temporada;

public class CargadorInformacionWebResultados {

	private static final String EL_PAIS_CALENDARIO_PRIMERA_MOCK = "websSimuladas/primeraResultados.htm";
	private static final String EL_PAIS_CALENDARIO_SEGUNDA_MOCK = "websSimuladas/segundaResultados.htm";
	private static final String EL_PAIS_CALENDARIO_PRIMERA = "http://www.elpais.com/deportes/futbol/competicion/primera/calendario";
	private static final String EL_PAIS_CALENDARIO_SEGUNDA = "http://www.elpais.com/deportes/futbol/competicion/segunda/calendario";

	private static final String MARCA_CLASIFICACION_PRIMERA_MOCK = "websSimuladas/marca_primera_clasificacion.htm";
	private static final String MARCA_CLASIFICACION_SEGUNDA_MOCK = "websSimuladas/marca_segunda_clasificacion.htm";
	private static final String MARCA_CLASIFICACION_PRIMERA = "http://www.marca.com/estadisticas/futbol/primera/2012_13";
	private static final String MARCA_CLASIFICACION_SEGUNDA = "http://www.marca.com/estadisticas/futbol/segunda/2012_13";

	private Temporada temporadaPrimera;
	private Temporada temporadaSegunda;

	private boolean modoMock;

	public CargadorInformacionWebResultados(boolean modoMock) {
		this.modoMock = modoMock;
	}

	public void cargar() throws Exception {
		cargarEstructuraTemporadas();
		cargarParametrosComunesEquipos();
	}

	private void cargarEstructuraTemporadas() throws IOException {

		// LEER WEBS
		String webPrimeraCalendario = leerContenidoHtml(
				EL_PAIS_CALENDARIO_PRIMERA_MOCK, EL_PAIS_CALENDARIO_PRIMERA);
		String webSegundaCalendario = leerContenidoHtml(
				EL_PAIS_CALENDARIO_SEGUNDA_MOCK, EL_PAIS_CALENDARIO_SEGUNDA);

		// RELLENO LA ESTRUCTURA DE CLASES JAVA
		temporadaPrimera = HandlerHtmlResultados
				.extraerDatos(webPrimeraCalendario);
		temporadaSegunda = HandlerHtmlResultados
				.extraerDatos(webSegundaCalendario);
	}

	private void cargarParametrosComunesEquipos() throws IOException {
		// LEER WEBS
		String webPrimeraParamComunes = leerContenidoHtml(
				MARCA_CLASIFICACION_PRIMERA_MOCK, MARCA_CLASIFICACION_PRIMERA);
		String webSegundaParamComunes = leerContenidoHtml(
				MARCA_CLASIFICACION_SEGUNDA_MOCK, MARCA_CLASIFICACION_SEGUNDA);

		// RELLENO LA ESTRUCTURA DE CLASES
		HandlerHtmlParamComunes.build(webPrimeraParamComunes,
				webSegundaParamComunes).cargarParamsComunes(temporadaPrimera,
				temporadaSegunda);

	}

	private String leerContenidoHtml(String pathRelativoHtmlMock,
			String URLHtmlOnline) throws IOException {

		BufferedReader in = null;

		if (modoMock) {
			FileInputStream fileIS = new FileInputStream(pathRelativoHtmlMock);
			in = new BufferedReader(new InputStreamReader(new DataInputStream(
					fileIS)));

		} else {
			URL url = new URL(URLHtmlOnline);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
		}

		String leido, acumulado = "";
		while ((leido = in.readLine()) != null) {
			acumulado += leido;
		}
		in.close();

		return acumulado;

	}

	public List<Temporada> getTemporadas() {
		List<Temporada> temporadas = new ArrayList<Temporada>();
		temporadas.add(getTemporadaPrimera());
		temporadas.add(getTemporadaSegunda());
		return temporadas;
	}

	public Temporada getTemporadaPrimera() {
		return temporadaPrimera;
	}

	public void setTemporadaPrimera(Temporada temporadaPrimeraHastaHoy) {
		this.temporadaPrimera = temporadaPrimeraHastaHoy;
	}

	public Temporada getTemporadaSegunda() {
		return temporadaSegunda;
	}

	public void setTemporadaSegunda(Temporada temporadaSegundaHastaHoy) {
		this.temporadaSegunda = temporadaSegundaHastaHoy;
	}

	public boolean isModoMock() {
		return modoMock;
	}

	public void setModoMock(boolean modoMock) {
		this.modoMock = modoMock;
	}

}
