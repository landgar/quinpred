/**
 * 
 */
package es.propio.presentacionCalculo;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import es.propio.modeladoInfo.GestorParametrosAnalisis;
import es.propio.modeladoInfo.ParametroNombre;

/**
 * @author i3casa
 * 
 */
public class Principal {

	static final String LOG_PROPERTIES_FILE = "logging/log4j.properties";

	/**
	 * Accion que vamos a ejecutar.
	 */
	public static final Accion accion = Accion.PREDICCION_QUINIELA;

	/**
	 * MUY IMPORTANTE
	 */
	public static final boolean MODO_MOCK = true;

	public static final boolean MOSTRAR_GRAFICOS = true;

	public static final ParametroNombre DEFAULT_PARAM_PRIMERA_12 = ParametroNombre.RF_JUGADA_COLECTIVA;
	public static final ParametroNombre DEFAULT_PARAM_PRIMERA_X = ParametroNombre.DIFERENCIA_PUNTOS;
	public static final ParametroNombre DEFAULT_PARAM_SEGUNDA_12 = ParametroNombre.POSICION_EN_CLASIFICACION;
	public static final ParametroNombre DEFAULT_PARAM_SEGUNDA_X = ParametroNombre.DIFERENCIA_PUNTOS_TENDENCIA_PARA_EMPATE;

	public static final Integer NUMERO_JORNADAS_A_CONSIDERAR_PRIMERA = 5;
	public static final Integer NUMERO_JORNADAS_A_CONSIDERAR_SEGUNDA = 4;

	public static final Integer NUM_EMPATES_PRIMERA = Integer.valueOf(2);
	public static final Integer NUM_EMPATES_SEGUNDA = Integer.valueOf(2);

	public Principal(String title) {
		super();
	}

	public static void main(String[] args) throws Exception {

		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream(LOG_PROPERTIES_FILE));
		PropertyConfigurator.configure(logProperties);

		if (accion.equals(Accion.PREDICCION_QUINIELA)) {
			PrincipalCore.ejecucion(GestorParametrosAnalisis.getTuplaDefault(),
					MOSTRAR_GRAFICOS);

		} else if (accion.equals(Accion.ANALISIS_ALGORITMO_5)) {
			PrincipalCore.analizarParametrosEn4grupos();

		} else if (accion.equals(Accion.ANALISIS_EMPATES)) {
			PrincipalCore.analizarNumeroDeEmpates();
		}

		System.out.println("FIN");
	}

}
