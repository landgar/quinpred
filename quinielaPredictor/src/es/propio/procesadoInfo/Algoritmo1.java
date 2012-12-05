/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.PronosticoPartido;

/**
 * @author i3casa
 * 
 */
public class Algoritmo1 extends AbstractAlgoritmo {
	static final Logger logger = Logger.getLogger(Algoritmo1.class);

	@Override
	public void calcularPronosticoPrimera() throws Exception {
		List<PronosticoPartido> lista = getEstimacionJornadaPrimera()
				.getPronosticoPartidos();
		for (PronosticoPartido pronostico : lista) {
			pronostico.setPorcentaje1(generateNormalizedRandomNumber());
			pronostico.setPorcentajeX(generateNormalizedRandomNumber());
			pronostico.setPorcentaje2(generateNormalizedRandomNumber());
		}
	}

	@Override
	public void calcularPronosticoSegunda() throws Exception {
		// TODO
	}

	private static Float generateNormalizedRandomNumber() {
		return generateRandomNumber(0, 1);
	}

	private static Float generateRandomNumber(final Integer minX,
			final Integer maxX) {
		Random rand = new Random();
		return rand.nextFloat() * (maxX - minX) + minX;
	}
}
