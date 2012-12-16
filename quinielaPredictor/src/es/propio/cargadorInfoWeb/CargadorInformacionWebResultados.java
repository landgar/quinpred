package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.propio.modeladoInfo.ParametroNombre;
import es.propio.modeladoInfo.Temporada;

public class CargadorInformacionWebResultados {


	// ESTRUCTURA DE TEMPORADA
	private static final String EL_PAIS_CALENDARIO_PRIMERA_MOCK = "websSimuladas/primeraResultados.htm";
	private static final String EL_PAIS_CALENDARIO_SEGUNDA_MOCK = "websSimuladas/segundaResultados.htm";
	private static final String EL_PAIS_CALENDARIO_PRIMERA = "http://www.elpais.com/deportes/futbol/competicion/primera/calendario";
	private static final String EL_PAIS_CALENDARIO_SEGUNDA = "http://www.elpais.com/deportes/futbol/competicion/segunda/calendario";

	// PARAMETROS COMUNES
	private static final String MARCA_CLASIFICACION_PRIMERA_MOCK = "websSimuladas/marca_primera_clasificacion.htm";
	private static final String MARCA_CLASIFICACION_SEGUNDA_MOCK = "websSimuladas/marca_segunda_clasificacion.htm";
	private static final String MARCA_CLASIFICACION_PRIMERA = "http://www.marca.com/estadisticas/futbol/primera/2012_13";
	private static final String MARCA_CLASIFICACION_SEGUNDA = "http://www.marca.com/estadisticas/futbol/segunda/2012_13";

	// PARAMETROS AVANZADOS DE PRIMERA
	private static final String EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK = "websSimuladas/el_pais_primera_remates_favor.htm";
	private static final String EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK = "websSimuladas/el_pais_primera_remates_contra.htm";
	private static final String EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK = "websSimuladas/el_pais_primera_tarjetas_amarillas.htm";
	private static final String EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK = "websSimuladas/el_pais_primera_tarjetas_rojas.htm";
	private static final String EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK = "websSimuladas/el_pais_primera_jugadores_utilizados.htm";
	private static final String EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK = "websSimuladas/el_pais_primera_paradas_portero.htm";

	private static final String EL_PAIS_PRIMERA_REMATES_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=15";
	private static final String EL_PAIS_PRIMERA_REMATES_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=26";
	private static final String EL_PAIS_PRIMERA_TARJETAS_AMARILLAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=32";
	private static final String EL_PAIS_PRIMERA_TARJETAS_ROJAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=31";
	private static final String EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=46";
	private static final String EL_PAIS_PRIMERA_PARADAS_PORTERO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=47";


	private Temporada temporadaPrimera;
	private Temporada temporadaSegunda;

	private boolean modoMock;

	public CargadorInformacionWebResultados(boolean modoMock) {
		this.modoMock = modoMock;
	}

	public void cargar() throws Exception {
		cargarEstructuraTemporadas();
		cargarParametrosComunesEquipos();
		cargarParametrosPrimeraEquipos();

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

	/**
	 * Carga los parametros avanzados (solo los conocemos para Primera division)
	 * 
	 * @throws IOException
	 * 
	 */
	private void cargarParametrosPrimeraEquipos() throws IOException {

		cargarParamPrimera(ParametroNombre.REMATES_A_FAVOR,
				EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK,
				EL_PAIS_PRIMERA_REMATES_FAVOR);
		cargarParamPrimera(ParametroNombre.REMATES_EN_CONTRA,
				EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK,
				EL_PAIS_PRIMERA_REMATES_CONTRA);

		cargarParamPrimera(ParametroNombre.TARJETAS_AMARILLAS,
				EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK,
				EL_PAIS_PRIMERA_TARJETAS_AMARILLAS);
		cargarParamPrimera(ParametroNombre.TARJETAS_ROJAS,
				EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK,
				EL_PAIS_PRIMERA_TARJETAS_ROJAS);

		cargarParamPrimera(ParametroNombre.JUGADORES_UTILIZADOS,
				EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK,
				EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS);
		cargarParamPrimera(ParametroNombre.PARADAS_DEL_PORTERO,
				EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK,
				EL_PAIS_PRIMERA_PARADAS_PORTERO);

	}

	private void cargarParamPrimera(ParametroNombre tipoParam,
			String pathRelativoHtmlMock, String URLHtmlOnline)
			throws IOException {

		// LEER WEBS
		String webStr = leerContenidoHtml(pathRelativoHtmlMock, URLHtmlOnline);

		// RELLENO LA ESTRUCTURA DE CLASES
		HandlerHtmlParamPrimera.build(webStr, tipoParam).cargarParamsAvanzados(
				temporadaPrimera);


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
