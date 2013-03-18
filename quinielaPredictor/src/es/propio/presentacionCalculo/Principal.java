/**
 * 
 */
package es.propio.presentacionCalculo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.GestorParametrosAnalisis;
import es.propio.modeladoInfo.ParametrizadorPartido;
import es.propio.modeladoInfo.ParametroNombre;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.modeladoInfo.TuplaParametrosAnalisis;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.Algoritmo5;

/**
 * @author i3casa
 * 
 */
public class Principal {

	static final String LOG_PROPERTIES_FILE = "logging/log4j.properties";

	/**
	 * MUY IMPORTANTE
	 */
	public static final boolean MODO_MOCK = true;

	public static final boolean MOSTRAR_GRAFICOS = true;

	/**
	 * Cuando queremos obtener los 4 parametros optimos para algoritmo 5. Hay
	 * ciertos parametros para los que no hay mock, asi que hay que comentarlos
	 * en GestorParametrosAnalisis para no usarlos y que no falle. Por ejemplo,
	 * los Parametros Avanzados de Primera solo existen para primera, pero
	 * cuando lo ejecute con Segunda, saltará excepción porque no tenemos datos
	 * de parametros avanzados en Segunda.
	 */
	public static final boolean ANALISIS_PARAMETROS_ALGORITMO_5 = false;

	public static final ParametroNombre DEFAULT_PARAM_PRIMERA_12 = ParametroNombre.RF_JUGADA_COLECTIVA;
	public static final ParametroNombre DEFAULT_PARAM_PRIMERA_X = ParametroNombre.DIFERENCIA_PUNTOS;
	public static final ParametroNombre DEFAULT_PARAM_SEGUNDA_12 = ParametroNombre.POSICION_EN_CLASIFICACION;
	public static final ParametroNombre DEFAULT_PARAM_SEGUNDA_X = ParametroNombre.DIFERENCIA_PUNTOS_PONDERADOS;

	/**
	 * Numero de jornadas previas que tiene en cuenta el algoritmo.
	 */
	public static final Integer NUMERO_JORNADAS_A_CONSIDERAR = 9;

	public static final Integer DEFAULT_NUM_EMPATES_PRIMERA = Integer
			.valueOf(2);
	public static final Integer DEFAULT_NUM_EMPATES_SEGUNDA = Integer
			.valueOf(3);

	public Principal(String title) {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
		PropertyConfigurator.configure(logProperties);

		if (ANALISIS_PARAMETROS_ALGORITMO_5) {
			analizarParametrosEn4grupos();
		} else {
			ejecucion(GestorParametrosAnalisis.getTuplaDefault(),
					MOSTRAR_GRAFICOS);
		}

		System.out.println("FIN");
	}

	private static void analizarParametrosEn4grupos() throws Exception {

		// PRIMERA 1/2
		System.out
				.println("\n\n\n ###############  PRIMERA 1/2 ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoIndividual()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 1);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=1/2 Division="
						+ Division.PRIMERA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// PRIMERA X
		System.out
				.println("\n\n\n ###############  PRIMERA X ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoComparativo()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 2);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=X Division="
						+ Division.PRIMERA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// SEGUNDA 1/2
		System.out
				.println("\n\n\n ###############  SEGUNDA 1/2 ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoIndividual()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 3);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=1/2 Division="
						+ Division.SEGUNDA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// SEGUNDA X
		System.out
				.println("\n\n\n ###############  SEGUNDA X  ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoComparativo()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 4);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=X Division="
						+ Division.SEGUNDA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

	}

	private static void ejecucion(TuplaParametrosAnalisis tupla,
			boolean graficosVisibles) throws Exception {

		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				MODO_MOCK);
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Temporada temporadaSegunda = cargador.getTemporadaSegunda();

		// Parametrizador
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaPrimera());
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaSegunda());

		// System.out
		// .println("PASADO: Comparando algoritmos con datos pasados...");
		List<AbstractAlgoritmo> algoritmosUsados = new ArrayList<AbstractAlgoritmo>();
		// algoritmosUsados
		// .add(new Algoritmo1(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo2(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo3(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo4(temporadaPrimera, temporadaSegunda));
		algoritmosUsados.add(new Algoritmo5(temporadaPrimera, temporadaSegunda,
				tupla));

		AnalizadorDelPasado.estudiarJornadasPasadas(algoritmosUsados,
				temporadaPrimera, temporadaSegunda, graficosVisibles);

		// System.out.println("FUTURO: PREDICCION DEL BOLETO ACTUAL...");
		// PredictorDelFuturo.analizarJornadaActual(temporadaPrimera,
		// temporadaSegunda);

	}

	public static List<PronosticoPartido> obtenerPartidos(Division division,
			String numeroBoleto) throws Exception {

		// Carga de boleto
		CargadorWebNombresProximaQuiniela cargadorBoleto = new CargadorWebNombresProximaQuiniela();
		cargadorBoleto.cargar(numeroBoleto);

		// Traspaso de partidos a listas para su predicción
		List<PronosticoPartido> partidos = new ArrayList<PronosticoPartido>();
		PronosticoPartido pronostico;
		Partido partido;
		Boleto boleto = cargadorBoleto.getBoleto();
		for (Map.Entry<Integer, Partido> entry : boleto.getPartidos()
				.entrySet()) {
			partido = entry.getValue();
			pronostico = new PronosticoPartido();
			pronostico.setPosicionPartido(entry.getKey());
			pronostico.setPartido(partido);
			if (partido.getEquipoLocal().getDivision().equals(division)) {
				partidos.add(pronostico);
			}
		}

		return partidos;
	}

}
