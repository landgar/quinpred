/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class Algoritmo3 extends AbstractAlgoritmo {
	static final Logger logger = Logger.getLogger(Algoritmo3.class);

	public Algoritmo3(final Temporada temporadaPrimera, final Temporada temporadaSegunda) {
		super();
		setId(IdAlgoritmoEnum.ALGORITMO5);
		setTemporadaPrimera(temporadaPrimera);
		setTemporadaSegunda(temporadaSegunda);
	}

	@Override
	public void calcularPronosticoPrimera() throws Exception {
		List<PronosticoPartido> lista = getEstimacionJornadaPrimera()
				.getPronosticoPartidos();
		for (PronosticoPartido pronostico : lista) {
			pronostico.setPorcentaje1(0F);
			pronostico.setPorcentajeX(1F);
			pronostico.setPorcentaje2(0F);
		}
	}

	@Override
	public void calcularPronosticoSegunda() throws Exception {
		List<PronosticoPartido> lista = getEstimacionJornadaSegunda()
				.getPronosticoPartidos();
		for (PronosticoPartido pronostico : lista) {
			pronostico.setPorcentaje1(0F);
			pronostico.setPorcentajeX(1F);
			pronostico.setPorcentaje2(0F);
		}
	}

	public static Float generateNormalizedRandomNumber() {
		Random rand = new Random();
		return rand.nextFloat();
	}

}
