/**
 * 
 */
package es.propio.presentacionCalculo;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import es.propio.procesadoInfo.Algoritmo1;

/**
 * @author i3casa
 * 
 */
public class Principal {

	static final String LOG_PROPERTIES_FILE = "logging/log4j.properties";
	/**
	 * 
	 */
	public Principal() {
		// TODO Auto-generated constructor stub
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
				.println("Aplicaci�n del algoritmo a todos los ficheros de predicci�n:");
		Algoritmo1.calcularPronosticos();
		System.out.println("FIN");

	}

}
