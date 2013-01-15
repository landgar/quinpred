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

/**
 * Cargador de parametros de EQUIPO (no de partido).
 * 
 * @author nosotros
 * 
 */
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
	private static final String EL_PAIS_PRIMERA_AF_BALON_PARADO_MOCK = "websSimuladas/el_pais_primera_af_balon_parado.htm";
	private static final String EL_PAIS_PRIMERA_AF_CABEZA_MOCK = "websSimuladas/el_pais_primera_af_cabeza.htm";
	private static final String EL_PAIS_PRIMERA_AF_FALTA_DIRECTA_MOCK = "websSimuladas/el_pais_primera_af_falta_directa.htm";
	private static final String EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA_MOCK = "websSimuladas/el_pais_primera_af_jugada_colectiva.htm";
	private static final String EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL_MOCK = "websSimuladas/el_pais_primera_af_jugada_individual.htm";
	private static final String EL_PAIS_PRIMERA_AF_PENALTY_MOCK = "websSimuladas/el_pais_primera_af_penalty.htm";
	private static final String EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO_MOCK = "websSimuladas/el_pais_primera_af_pie_derecho.htm";
	private static final String EL_PAIS_PRIMERA_AF_PIE_DERECHO_MOCK = "websSimuladas/el_pais_primera_af_pie_izquierdo.htm";
	private static final String EL_PAIS_PRIMERA_EN_CONTRA_MOCK = "websSimuladas/el_pais_primera_en_contra.htm";
	private static final String EL_PAIS_PRIMERA_GC_CABEZA_MOCK = "websSimuladas/el_pais_primera_gc_cabeza.htm";
	private static final String EL_PAIS_PRIMERA_GC_PENALTY_MOCK = "websSimuladas/el_pais_primera_gc_penalty.htm";
	private static final String EL_PAIS_PRIMERA_GOLES_TITULAR_MOCK = "websSimuladas/el_pais_primera_goles_titular.htm";
	private static final String EL_PAIS_PRIMERA_GOLES_SUPLENTES_MOCK = "websSimuladas/el_pais_primera_goles_suplentes.htm";
	private static final String EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK = "websSimuladas/el_pais_primera_remates_favor.htm";
	private static final String EL_PAIS_PRIMERA_RF_BALON_PARADO_MOCK = "websSimuladas/el_pais_primera_rf_balon_parado.htm";
	private static final String EL_PAIS_PRIMERA_RF_CABEZA_MOCK = "websSimuladas/el_pais_primera_rf_cabeza.htm";
	private static final String EL_PAIS_PRIMERA_RF_FUERA_MOCK = "websSimuladas/el_pais_primera_rf_fuera.htm";
	private static final String EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA_MOCK = "websSimuladas/el_pais_primera_rf_jugada_colectiva.htm";
	private static final String EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL_MOCK = "websSimuladas/el_pais_primera_rf_jugada_individual.htm";
	private static final String EL_PAIS_PRIMERA_RF_PENALTY_MOCK = "websSimuladas/el_pais_primera_rf_penalty.htm";
	private static final String EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO_MOCK = "websSimuladas/el_pais_primera_rf_pie_izquierdo.htm";
	private static final String EL_PAIS_PRIMERA_RF_PIE_DERECHO_MOCK = "websSimuladas/el_pais_primera_rf_pie_derecho.htm";
	private static final String EL_PAIS_PRIMERA_RF_POSTE_MOCK = "websSimuladas/el_pais_primera_rf_poste.htm";
	private static final String EL_PAIS_PRIMERA_RF_PORTERIA_MOCK = "websSimuladas/el_pais_primera_rf_porteria.htm";
	private static final String EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK = "websSimuladas/el_pais_primera_remates_contra.htm";
	private static final String EL_PAIS_PRIMERA_RC_CABEZA_MOCK = "websSimuladas/el_pais_primera_rc_cabeza.htm";
	private static final String EL_PAIS_PRIMERA_RC_FUERA_MOCK = "websSimuladas/el_pais_primera_rc_fuera.htm";
	private static final String EL_PAIS_PRIMERA_RC_PORTERIA_MOCK = "websSimuladas/el_pais_primera_rc_porteria.htm";
	private static final String EL_PAIS_PRIMERA_RC_POSTE_MOCK = "websSimuladas/el_pais_primera_rc_poste.htm";
	private static final String EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK = "websSimuladas/el_pais_primera_tarjetas_amarillas.htm";
	private static final String EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK = "websSimuladas/el_pais_primera_tarjetas_rojas.htm";

	private static final String EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK = "websSimuladas/el_pais_primera_jugadores_utilizados.htm";
	private static final String EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK = "websSimuladas/el_pais_primera_paradas_portero.htm";

	private static final String EL_PAIS_PRIMERA_AF_BALON_PARADO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=1";
	private static final String EL_PAIS_PRIMERA_AF_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=2";
	private static final String EL_PAIS_PRIMERA_AF_FALTA_DIRECTA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=3";
	private static final String EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=4";
	private static final String EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=5";
	private static final String EL_PAIS_PRIMERA_AF_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=7";
	private static final String EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=8";
	private static final String EL_PAIS_PRIMERA_AF_PIE_DERECHO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=9";
	private static final String EL_PAIS_PRIMERA_EN_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=10";
	private static final String EL_PAIS_PRIMERA_GC_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=11";
	private static final String EL_PAIS_PRIMERA_GC_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=12";
	private static final String EL_PAIS_PRIMERA_GOLES_TITULAR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=13";
	private static final String EL_PAIS_PRIMERA_GOLES_SUPLENTES = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=14";
	private static final String EL_PAIS_PRIMERA_REMATES_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=15";
	private static final String EL_PAIS_PRIMERA_RF_BALON_PARADO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=16";
	private static final String EL_PAIS_PRIMERA_RF_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=17";
	private static final String EL_PAIS_PRIMERA_RF_FUERA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=18";
	private static final String EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=19";
	private static final String EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=20";
	private static final String EL_PAIS_PRIMERA_RF_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=21";
	private static final String EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=22";
	private static final String EL_PAIS_PRIMERA_RF_PIE_DERECHO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=23";
	private static final String EL_PAIS_PRIMERA_RF_POSTE = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=24";
	private static final String EL_PAIS_PRIMERA_RF_PORTERIA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=25";
	private static final String EL_PAIS_PRIMERA_REMATES_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=26";
	private static final String EL_PAIS_PRIMERA_RC_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=27";
	private static final String EL_PAIS_PRIMERA_RC_FUERA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=28";
	private static final String EL_PAIS_PRIMERA_RC_PORTERIA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=29";
	private static final String EL_PAIS_PRIMERA_RC_POSTE = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=30";
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
		int numJornadaActualPrimera = cargarParametrosComunesEquipos();
		cargarParametrosPrimeraEquipos(numJornadaActualPrimera);
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

	/**
	 * Carga los parametros comunes de los equipos SOLO PARA LA ULTIMA JORNADA
	 * (¡¡ y los copio a las jornadas anteriores: DECISION TOMADA !!).
	 * 
	 * @throws IOException
	 */
	private int cargarParametrosComunesEquipos() throws IOException {
		// LEER WEBS
		String webPrimeraParamComunes = leerContenidoHtml(
				MARCA_CLASIFICACION_PRIMERA_MOCK, MARCA_CLASIFICACION_PRIMERA);
		String webSegundaParamComunes = leerContenidoHtml(
				MARCA_CLASIFICACION_SEGUNDA_MOCK, MARCA_CLASIFICACION_SEGUNDA);

		// RELLENO LA ESTRUCTURA DE CLASES
		int numJornadaActualPrimera = HandlerHtmlParamComunes.build(
				webPrimeraParamComunes, webSegundaParamComunes)
				.cargarParamsComunes(temporadaPrimera, temporadaSegunda);
		return numJornadaActualPrimera;
	}

	/**
	 * Carga los parametros avanzados (solo los conocemos para la ultima jornada
	 * de Primera division). ¡¡¡¡ DECISION TOMADA: los replicamos para las
	 * jornadas anteriores de Primera division !!!!
	 * 
	 * @throws IOException
	 * 
	 */
	private void cargarParametrosPrimeraEquipos(int numJornadaActualPrimera)
			throws IOException {

		cargarParamPrimera(ParametroNombre.REMATES_EN_CONTRA,
				EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK,
				EL_PAIS_PRIMERA_REMATES_CONTRA, numJornadaActualPrimera);

		cargarParamPrimera(ParametroNombre.TARJETAS_AMARILLAS,
				EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK,
				EL_PAIS_PRIMERA_TARJETAS_AMARILLAS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.TARJETAS_ROJAS,
				EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK,
				EL_PAIS_PRIMERA_TARJETAS_ROJAS, numJornadaActualPrimera);

		cargarParamPrimera(ParametroNombre.JUGADORES_UTILIZADOS,
				EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK,
				EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.PARADAS_DEL_PORTERO,
				EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK,
				EL_PAIS_PRIMERA_PARADAS_PORTERO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_BALON_PARADO,
				EL_PAIS_PRIMERA_AF_BALON_PARADO_MOCK,
				EL_PAIS_PRIMERA_AF_BALON_PARADO, numJornadaActualPrimera);

		cargarParamPrimera(ParametroNombre.AF_CABEZA,
				EL_PAIS_PRIMERA_AF_CABEZA_MOCK, EL_PAIS_PRIMERA_AF_CABEZA,
				numJornadaActualPrimera);

		cargarParamPrimera(ParametroNombre.AF_FALTA_DIRECTA,
				EL_PAIS_PRIMERA_AF_FALTA_DIRECTA_MOCK,
				EL_PAIS_PRIMERA_AF_FALTA_DIRECTA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_JUGADA_COLECTIVA,
				EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA_MOCK,
				EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_JUGADA_INDIVIDUAL,
				EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL_MOCK,
				EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_PENALTY,
				EL_PAIS_PRIMERA_AF_PENALTY_MOCK, EL_PAIS_PRIMERA_AF_PENALTY,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_PIE_IZQUIERDO,
				EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO_MOCK,
				EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.AF_PIE_DERECHO,
				EL_PAIS_PRIMERA_AF_PIE_DERECHO_MOCK,
				EL_PAIS_PRIMERA_AF_PIE_DERECHO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.EN_CONTRA,
				EL_PAIS_PRIMERA_EN_CONTRA_MOCK, EL_PAIS_PRIMERA_EN_CONTRA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.GC_CABEZA,
				EL_PAIS_PRIMERA_GC_CABEZA_MOCK, EL_PAIS_PRIMERA_GC_CABEZA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.GC_PENALTY,
				EL_PAIS_PRIMERA_GC_PENALTY_MOCK, EL_PAIS_PRIMERA_GC_PENALTY,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.GOLES_TITULAR,
				EL_PAIS_PRIMERA_GOLES_TITULAR_MOCK,
				EL_PAIS_PRIMERA_GOLES_TITULAR, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.GOLES_SUPLENTES,
				EL_PAIS_PRIMERA_GOLES_SUPLENTES_MOCK,
				EL_PAIS_PRIMERA_GOLES_SUPLENTES, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.REMATES_FAVOR,
				EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK,
				EL_PAIS_PRIMERA_REMATES_FAVOR, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_BALON_PARADO,
				EL_PAIS_PRIMERA_RF_BALON_PARADO_MOCK,
				EL_PAIS_PRIMERA_RF_BALON_PARADO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_CABEZA,
				EL_PAIS_PRIMERA_RF_CABEZA_MOCK, EL_PAIS_PRIMERA_RF_CABEZA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_FUERA,
				EL_PAIS_PRIMERA_RF_FUERA_MOCK, EL_PAIS_PRIMERA_RF_FUERA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_JUGADA_COLECTIVA,
				EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA_MOCK,
				EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_JUGADA_INDIVIDUAL,
				EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL_MOCK,
				EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_PENALTY,
				EL_PAIS_PRIMERA_RF_PENALTY_MOCK, EL_PAIS_PRIMERA_RF_PENALTY,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_PIE_IZQUIERDO,
				EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO_MOCK,
				EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_PIE_DERECHO,
				EL_PAIS_PRIMERA_RF_PIE_DERECHO_MOCK,
				EL_PAIS_PRIMERA_RF_PIE_DERECHO, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_POSTE,
				EL_PAIS_PRIMERA_RF_POSTE_MOCK, EL_PAIS_PRIMERA_RF_POSTE,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RF_PORTERIA,
				EL_PAIS_PRIMERA_RF_PORTERIA_MOCK, EL_PAIS_PRIMERA_RF_PORTERIA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RC_CABEZA,
				EL_PAIS_PRIMERA_RC_CABEZA_MOCK, EL_PAIS_PRIMERA_RC_CABEZA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RC_FUERA,
				EL_PAIS_PRIMERA_RC_FUERA_MOCK, EL_PAIS_PRIMERA_RC_FUERA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RC_PORTERIA,
				EL_PAIS_PRIMERA_RC_PORTERIA_MOCK, EL_PAIS_PRIMERA_RC_PORTERIA,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.RC_POSTE,
				EL_PAIS_PRIMERA_RC_POSTE_MOCK, EL_PAIS_PRIMERA_RC_POSTE,
				numJornadaActualPrimera);

		// // util para red neuronal
		// cargarParamPrimera(ParametroNombre.NUMEROJORNADA, "", "",
		// numJornadaActualPrimera);

	}

	private void cargarParamPrimera(ParametroNombre tipoParam,
			String pathRelativoHtmlMock, String URLHtmlOnline,
			int numJornadaActualPrimera) throws IOException {

		// LEER WEBS
		String webStr = "";
		// if (!pathRelativoHtmlMock.isEmpty() && !URLHtmlOnline.isEmpty()) {
		webStr = leerContenidoHtml(pathRelativoHtmlMock, URLHtmlOnline);
		// }

		// RELLENO LA ESTRUCTURA DE CLASES
		HandlerHtmlParamPrimera.build(webStr, tipoParam).cargarParamsAvanzados(
				temporadaPrimera, numJornadaActualPrimera);

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
