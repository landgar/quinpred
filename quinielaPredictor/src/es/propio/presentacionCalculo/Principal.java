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

import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;

/**
 * @author i3casa
 * 
 */
public class Principal {

	static final String LOG_PROPERTIES_FILE = "logging/log4j.properties";

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

		System.out.println("-- PREDICCION DEL BOLETO ACTUAL --");
		Integer numBoletoActual = PredictorDelFuturo.analizarJornadaActual();

		System.out.println("Comparando algoritmos con datos pasados...");
		AnalizadorDelPasado.estudiarJornadasPasadas(numBoletoActual - 1);
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
