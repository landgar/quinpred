package es.propio.graficos.aciertosjornada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * Modelo de datos de entrada al grafico de "Num de Aciertos vs. num. jornada".
 * 
 * @author carlos.andres
 * 
 */
public class EntradaAciertosJornadaDto {

	private final List<PronosticoJornada> pronosticosJornadaBulk;

	private final List<PronosticoJornada> resultadosReales;

	public EntradaAciertosJornadaDto(
			List<PronosticoJornada> pronosticosJornadaBulk,
			List<PronosticoJornada> resultadosReales) {
		this.pronosticosJornadaBulk = pronosticosJornadaBulk;
		this.resultadosReales = resultadosReales;
	}

	/**
	 * Compara los pronosticos bulk y los resultados reales y marca los
	 * pronosticos que acertaron.
	 */
	public void marcarAciertos() {

		if (pronosticosJornadaBulk != null && resultadosReales != null) {
			for (PronosticoJornada realj : resultadosReales) {
				for (PronosticoJornada pronosticoj : pronosticosJornadaBulk) {
					System.out
							.println("*********************************************ALGORITMO "
									+ pronosticoj.getIdAlgoritmoPronosticador()
											.getIdAlgoritmo()
									+ " *******************************************************");
					pronosticoj.obtenerNumAciertos(realj);
				}
			}
		}
	}

	public Map<Jornada, List<PronosticoJornada>> organizarPronosticosBulkPorJornada() {
		Map<Jornada, List<PronosticoJornada>> mapaJornadaPronosticos = new HashMap<Jornada, List<PronosticoJornada>>();
		if (pronosticosJornadaBulk != null && !pronosticosJornadaBulk.isEmpty()) {
			mapaJornadaPronosticos = organizarPorJornada(pronosticosJornadaBulk);
		}
		return mapaJornadaPronosticos;
	}

	public Map<Jornada, List<PronosticoJornada>> organizarResultadosRealesPorJornada() {
		Map<Jornada, List<PronosticoJornada>> mapaJornadaPronosticos = new HashMap<Jornada, List<PronosticoJornada>>();
		if (resultadosReales != null && !resultadosReales.isEmpty()) {
			mapaJornadaPronosticos = organizarPorJornada(resultadosReales);
		}
		return mapaJornadaPronosticos;
	}

	private Map<Jornada, List<PronosticoJornada>> organizarPorJornada(
			List<PronosticoJornada> lista) {
		Map<Jornada, List<PronosticoJornada>> mapa = new HashMap<Jornada, List<PronosticoJornada>>();
		// TODO rellenar el mapa leyendo los pronosticos y ordenando por
		// idJornada

		return mapa;
	}

	public List<IdAlgoritmoEnum> extraerAlgoritmosUsados() {
		List<IdAlgoritmoEnum> idsAlgoritmos = new ArrayList<IdAlgoritmoEnum>();

		if (pronosticosJornadaBulk != null && !pronosticosJornadaBulk.isEmpty()) {

			for (PronosticoJornada pronostico : pronosticosJornadaBulk) {

				IdAlgoritmoEnum id = pronostico.getIdAlgoritmoPronosticador();
				if (!idsAlgoritmos.contains(id)) {
					idsAlgoritmos.add(id);
				}
			}
		}
		return idsAlgoritmos;
	}

	public Map<IdAlgoritmoEnum, List<PronosticoJornada>> organizarPorAlgoritmo() {
		Map<IdAlgoritmoEnum, List<PronosticoJornada>> mapa = new HashMap<IdAlgoritmoEnum, List<PronosticoJornada>>();

		List<IdAlgoritmoEnum> idsAlgoritmos = extraerAlgoritmosUsados();

		List<PronosticoJornada> pronosticos;

		for (IdAlgoritmoEnum idAlgoritmo : idsAlgoritmos) {

			pronosticos = new ArrayList<PronosticoJornada>();

			for (PronosticoJornada pronostico : pronosticosJornadaBulk) {
				if (idAlgoritmo != null
						&& pronostico.getIdAlgoritmoPronosticador().equals(
								idAlgoritmo)) {
					pronosticos.add(pronostico);
				}
			}

			mapa.put(idAlgoritmo, pronosticos);
		}

		return mapa;
	}

	public List<PronosticoJornada> getPronosticosJornadaBulk() {
		return pronosticosJornadaBulk;
	}

	public List<PronosticoJornada> getResultadosReales() {
		return resultadosReales;
	}

}
