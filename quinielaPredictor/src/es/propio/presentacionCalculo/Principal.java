/**
 * 
 */
package es.propio.presentacionCalculo;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.GraficosManager;
import es.propio.procesadoInfo.Algoritmo1;

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
		// TODO Auto-generated method stub

		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
		PropertyConfigurator.configure(logProperties);

		System.out.println("COMIENZO");
		System.out
				.println("Aplicación del algoritmo a todos los ficheros de predicción:");
		Algoritmo1.calcularPronosticos();
		System.out.println("FIN");

		System.out.println("PINTANDO GRAFICOS...");
		String title = "-- pruebas de graficos --";
		GraficosManager demo = new GraficosManager(title);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

}
