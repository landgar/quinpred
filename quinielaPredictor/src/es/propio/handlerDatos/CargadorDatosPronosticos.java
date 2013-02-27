package es.propio.handlerDatos;

import java.util.ArrayList;
import java.util.List;

import es.propio.modeladoInfo.PronosticoJornada;

/**
 * @author carlos.andres
 * 
 */
public class CargadorDatosPronosticos {

	public static List<PronosticoJornada> cargarPronosticosJornadas()
			throws Exception {
		List<PronosticoJornada> pronosticos = new ArrayList<PronosticoJornada>();
		// File folder = new File("pronosticos_quinielista/");
		// for (final File fileEntry : folder.listFiles()) {
		// HandlerXMLPronosticos lector = new HandlerXMLPronosticos(numJornada,
		// IdAlgoritmoEnum.WEB_QUINIELISTA);
		// lector.leer(fileEntry);
		// pronosticos.add(lector.getPronostico());
		// }
		return pronosticos;
	}
}
