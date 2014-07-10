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
	public static final String EL_PAIS_CALENDARIO_PRIMERA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/primeraResultados.htm";
	public static final String EL_PAIS_CALENDARIO_SEGUNDA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/segundaResultados.htm";
	public static final String EL_PAIS_CALENDARIO_PRIMERA = "http://www.elpais.com/deportes/futbol/competicion/primera/calendario";
	public static final String EL_PAIS_CALENDARIO_SEGUNDA = "http://www.elpais.com/deportes/futbol/competicion/segunda/calendario";

	// PARAMETROS COMUNES
	public static final String MARCA_CLASIFICACION_PRIMERA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/marca_primera_clasificacion.htm";
	public static final String MARCA_CLASIFICACION_SEGUNDA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/marca_segunda_clasificacion.htm";
	public static final String MARCA_CLASIFICACION_PRIMERA = "http://www.marca.com/estadisticas/futbol/primera/"
			+ Temporada.TEMPORADA_MARCA_ACTUAL;
	public static final String MARCA_CLASIFICACION_SEGUNDA = "http://www.marca.com/estadisticas/futbol/segunda/"
			+ Temporada.TEMPORADA_MARCA_ACTUAL;

	// PARAMETROS AVANZADOS DE PRIMERA
	public static final String EL_PAIS_PRIMERA_A_FAVOR_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/0.htm";
	public static final String EL_PAIS_PRIMERA_AF_BALON_PARADO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/1.htm";
	public static final String EL_PAIS_PRIMERA_AF_CABEZA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/2.htm";
	public static final String EL_PAIS_PRIMERA_AF_FALTA_DIRECTA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/3.htm";
	public static final String EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/4.htm";
	public static final String EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/5.htm";
	public static final String EL_PAIS_PRIMERA_AF_PENALTY_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/7.htm";
	public static final String EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/8.htm";
	public static final String EL_PAIS_PRIMERA_AF_PIE_DERECHO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/9.htm";
	public static final String EL_PAIS_PRIMERA_EN_CONTRA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/10.htm";
	public static final String EL_PAIS_PRIMERA_GC_CABEZA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/11.htm";
	public static final String EL_PAIS_PRIMERA_GC_PENALTY_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/12.htm";
	public static final String EL_PAIS_PRIMERA_GOLES_TITULAR_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/13.htm";
	public static final String EL_PAIS_PRIMERA_GOLES_SUPLENTES_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/14.htm";
	public static final String EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/15.htm";
	public static final String EL_PAIS_PRIMERA_RF_BALON_PARADO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/16.htm";
	public static final String EL_PAIS_PRIMERA_RF_CABEZA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/17.htm";
	public static final String EL_PAIS_PRIMERA_RF_FUERA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/18.htm";
	public static final String EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/19.htm";
	public static final String EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/20.htm";
	public static final String EL_PAIS_PRIMERA_RF_PENALTY_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/21.htm";
	public static final String EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/22.htm";
	public static final String EL_PAIS_PRIMERA_RF_PIE_DERECHO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/23.htm";
	public static final String EL_PAIS_PRIMERA_RF_POSTE_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/24.htm";
	public static final String EL_PAIS_PRIMERA_RF_PORTERIA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/25.htm";
	public static final String EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/26.htm";
	public static final String EL_PAIS_PRIMERA_RC_CABEZA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/27.htm";
	public static final String EL_PAIS_PRIMERA_RC_FUERA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/28.htm";
	public static final String EL_PAIS_PRIMERA_RC_PORTERIA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/29.htm";
	public static final String EL_PAIS_PRIMERA_RC_POSTE_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/30.htm";
	public static final String EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/31.htm";
	public static final String EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/32.htm";
	public static final String EL_PAIS_PRIMERA_FALTAS_COMETIDAS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/33.htm";
	public static final String EL_PAIS_PRIMERA_FALTAS_RECIBIDAS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/34.htm";
	public static final String EL_PAIS_PRIMERA_PENALTY_FAVOR_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/35.htm";
	public static final String EL_PAIS_PRIMERA_PENALTY_CONTRA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/36.htm";
	public static final String EL_PAIS_PRIMERA_FUERA_JUEGO_FAVOR_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/37.htm";
	public static final String EL_PAIS_PRIMERA_FUERA_JUEGO_CONTRA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/38.htm";
	public static final String EL_PAIS_PRIMERA_BALONES_RECUPERADOS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/39.htm";
	public static final String EL_PAIS_PRIMERA_BALONES_PERDIDOS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/40.htm";
	public static final String EL_PAIS_PRIMERA_PASES_BUENOS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/41.htm";
	public static final String EL_PAIS_PRIMERA_PASES_TOTALES_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/42.htm";
	public static final String EL_PAIS_PRIMERA_CENTROS_AREA_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/43.htm";
	public static final String EL_PAIS_PRIMERA_ASISTENCIAS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/44.htm";
	public static final String EL_PAIS_PRIMERA_ASISTENCIA_CON_GOL_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/45.htm";
	public static final String EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/46.htm";
	public static final String EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/47.htm";
	public static final String EL_PAIS_PRIMERA_INTERVENCIONES_MOCK = Temporada.TEMPORADA_PAIS_MOCK
			+ "/websSimuladas/48.htm";

	public static final String EL_PAIS_PRIMERA_A_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=0";
	public static final String EL_PAIS_PRIMERA_AF_BALON_PARADO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=1";
	public static final String EL_PAIS_PRIMERA_AF_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=2";
	public static final String EL_PAIS_PRIMERA_AF_FALTA_DIRECTA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=3";
	public static final String EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=4";
	public static final String EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=5";
	public static final String EL_PAIS_PRIMERA_AF_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=7";
	public static final String EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=8";
	public static final String EL_PAIS_PRIMERA_AF_PIE_DERECHO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=9";
	public static final String EL_PAIS_PRIMERA_EN_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=10";
	public static final String EL_PAIS_PRIMERA_GC_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=11";
	public static final String EL_PAIS_PRIMERA_GC_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=12";
	public static final String EL_PAIS_PRIMERA_GOLES_TITULAR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=13";
	public static final String EL_PAIS_PRIMERA_GOLES_SUPLENTES = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=14";
	public static final String EL_PAIS_PRIMERA_REMATES_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=15";
	public static final String EL_PAIS_PRIMERA_RF_BALON_PARADO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=16";
	public static final String EL_PAIS_PRIMERA_RF_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=17";
	public static final String EL_PAIS_PRIMERA_RF_FUERA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=18";
	public static final String EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=19";
	public static final String EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=20";
	public static final String EL_PAIS_PRIMERA_RF_PENALTY = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=21";
	public static final String EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=22";
	public static final String EL_PAIS_PRIMERA_RF_PIE_DERECHO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=23";
	public static final String EL_PAIS_PRIMERA_RF_POSTE = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=24";
	public static final String EL_PAIS_PRIMERA_RF_PORTERIA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=25";
	public static final String EL_PAIS_PRIMERA_REMATES_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=26";
	public static final String EL_PAIS_PRIMERA_RC_CABEZA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=27";
	public static final String EL_PAIS_PRIMERA_RC_FUERA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=28";
	public static final String EL_PAIS_PRIMERA_RC_PORTERIA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=29";
	public static final String EL_PAIS_PRIMERA_RC_POSTE = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=30";
	public static final String EL_PAIS_PRIMERA_TARJETAS_ROJAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=31";
	public static final String EL_PAIS_PRIMERA_TARJETAS_AMARILLAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=32";
	public static final String EL_PAIS_PRIMERA_FALTAS_COMETIDAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=33";
	public static final String EL_PAIS_PRIMERA_FALTAS_RECIBIDAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=34";
	public static final String EL_PAIS_PRIMERA_PENALTY_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=35";
	public static final String EL_PAIS_PRIMERA_PENALTY_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=36";
	public static final String EL_PAIS_PRIMERA_FUERA_JUEGO_FAVOR = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=37";
	public static final String EL_PAIS_PRIMERA_FUERA_JUEGO_CONTRA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=38";
	public static final String EL_PAIS_PRIMERA_BALONES_RECUPERADOS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=39";
	public static final String EL_PAIS_PRIMERA_BALONES_PERDIDOS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=40";
	public static final String EL_PAIS_PRIMERA_PASES_BUENOS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=41";
	public static final String EL_PAIS_PRIMERA_PASES_TOTALES = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=42";
	public static final String EL_PAIS_PRIMERA_CENTROS_AREA = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=43";
	public static final String EL_PAIS_PRIMERA_ASISTENCIAS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=44";
	public static final String EL_PAIS_PRIMERA_ASISTENCIA_CON_GOL = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=45";
	public static final String EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=46";
	public static final String EL_PAIS_PRIMERA_PARADAS_PORTERO = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=47";
	public static final String EL_PAIS_PRIMERA_INTERVENCIONES = "http://www.elpais.com/deportes/estadisticas/equipos/primera/?categoria=48";

	private Temporada temporadaPrimera;
	private Temporada temporadaSegunda;

	private boolean modoMock;

	public CargadorInformacionWebResultados(boolean modoMock) {
		this.modoMock = modoMock;
	}

	public void cargar() throws Exception {
		cargarEstructuraTemporadas();
		int numJornadaActualPrimera = cargarParametrosComunesEquipos();
		cargarParametrosInternos();
		cargarParametrosPrimeraEquipos(numJornadaActualPrimera);
	}

	private void cargarEstructuraTemporadas() throws IOException {

		// System.out
		// .println("CargadorInformacionWebResultados.cargarEstructuraTemporadas() Cargando ESTRUCTURA de las temporadas...");

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
	 * En cada temporada, carga parámetros autocalculables con los datos propios
	 * de los partidos de la temporada. Por ejemplo, los goles acumulados en
	 * casa, o los partidos ganados en casa, todo para la temporada actual.
	 */
	private void cargarParametrosInternos() throws Exception {
		temporadaPrimera.cargarParametrosDeJornadaAnterioresYActual();
		temporadaSegunda.cargarParametrosDeJornadaAnterioresYActual();
	}

	/**
	 * Carga los parametros comunes de los equipos SOLO PARA LA ULTIMA JORNADA
	 * (¡¡ y los copio a las jornadas anteriores: DECISION TOMADA !!).
	 * 
	 * @throws IOException
	 */
	private int cargarParametrosComunesEquipos() throws IOException {

		// System.out
		// .println("CargadorInformacionWebResultados.cargarParametrosComunesEquipos() Cargando Parametros COMUNES...");

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

		// System.out
		// .println("CargadorInformacionWebResultados.cargarParametrosPrimeraEquipos() Cargando parametros de PRIMERA...");

		cargarParamPrimera(ParametroNombre.REMATES_FAVOR,
				EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK,
				EL_PAIS_PRIMERA_REMATES_FAVOR, numJornadaActualPrimera);
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
		cargarParamPrimera(ParametroNombre.A_FAVOR,
				EL_PAIS_PRIMERA_A_FAVOR_MOCK, EL_PAIS_PRIMERA_A_FAVOR,
				numJornadaActualPrimera);
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

		cargarParamPrimera(ParametroNombre.FALTAS_COMETIDAS,
				EL_PAIS_PRIMERA_FALTAS_COMETIDAS_MOCK,
				EL_PAIS_PRIMERA_FALTAS_COMETIDAS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.FALTAS_RECIBIDAS,
				EL_PAIS_PRIMERA_FALTAS_RECIBIDAS_MOCK,
				EL_PAIS_PRIMERA_FALTAS_RECIBIDAS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.PENALTY_FAVOR,
				EL_PAIS_PRIMERA_PENALTY_FAVOR_MOCK,
				EL_PAIS_PRIMERA_PENALTY_FAVOR, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.PENALTY_CONTRA,
				EL_PAIS_PRIMERA_PENALTY_CONTRA_MOCK,
				EL_PAIS_PRIMERA_PENALTY_CONTRA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.FUERA_JUEGO_FAVOR,
				EL_PAIS_PRIMERA_FUERA_JUEGO_FAVOR_MOCK,
				EL_PAIS_PRIMERA_FUERA_JUEGO_FAVOR, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.FUERA_JUEGO_CONTRA,
				EL_PAIS_PRIMERA_FUERA_JUEGO_CONTRA_MOCK,
				EL_PAIS_PRIMERA_FUERA_JUEGO_CONTRA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.BALONES_RECUPERADOS,
				EL_PAIS_PRIMERA_BALONES_RECUPERADOS_MOCK,
				EL_PAIS_PRIMERA_BALONES_RECUPERADOS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.BALONES_PERDIDOS,
				EL_PAIS_PRIMERA_BALONES_PERDIDOS_MOCK,
				EL_PAIS_PRIMERA_BALONES_PERDIDOS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.PASES_BUENOS,
				EL_PAIS_PRIMERA_PASES_BUENOS_MOCK,
				EL_PAIS_PRIMERA_PASES_BUENOS, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.PASES_TOTALES,
				EL_PAIS_PRIMERA_PASES_TOTALES_MOCK,
				EL_PAIS_PRIMERA_PASES_TOTALES, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.CENTROS_AREA,
				EL_PAIS_PRIMERA_CENTROS_AREA_MOCK,
				EL_PAIS_PRIMERA_CENTROS_AREA, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.ASISTENCIAS,
				EL_PAIS_PRIMERA_ASISTENCIAS_MOCK, EL_PAIS_PRIMERA_ASISTENCIAS,
				numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.ASISTENCIA_CON_GOL,
				EL_PAIS_PRIMERA_ASISTENCIA_CON_GOL_MOCK,
				EL_PAIS_PRIMERA_ASISTENCIA_CON_GOL, numJornadaActualPrimera);
		cargarParamPrimera(ParametroNombre.INTERVENCIONES,
				EL_PAIS_PRIMERA_INTERVENCIONES_MOCK,
				EL_PAIS_PRIMERA_INTERVENCIONES, numJornadaActualPrimera);

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
