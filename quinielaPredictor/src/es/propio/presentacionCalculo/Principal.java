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
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
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
				GestorParametrosAnalisis.getTuplaDefault()));

		AnalizadorDelPasado.estudiarJornadasPasadas(algoritmosUsados,
				temporadaPrimera, temporadaSegunda);

		// System.out.println("FUTURO: PREDICCION DEL BOLETO ACTUAL...");
		// PredictorDelFuturo.analizarJornadaActual(temporadaPrimera,
		// temporadaSegunda);

		System.out.println("FIN");
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
