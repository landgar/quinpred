package es.propio.handlerDatos;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import es.propio.lectorxml.HandlerXMLPronosticos;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author carlos.andres
 * 
 */
public class CargadorDatosPronosticos {

	public static Set<PronosticoJornada> cargarPronosticosJornadas()
			throws Exception {
		Set<PronosticoJornada> pronosticos = new HashSet<PronosticoJornada>();
		File folder = new File("pronosticos_quinielista/");
		for (final File fileEntry : folder.listFiles()) {
			HandlerXMLPronosticos lector = new HandlerXMLPronosticos(null,
					IdAlgoritmoEnum.WEB_QUINIELISTA);
			lector.leer(fileEntry);
			pronosticos.add(lector.getPronostico());
		}
		return pronosticos;
	}
}
