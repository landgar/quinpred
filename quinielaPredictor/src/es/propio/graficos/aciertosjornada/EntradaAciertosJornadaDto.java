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

	public EntradaAciertosJornadaDto(
			List<PronosticoJornada> pronosticosJornadaBulk) {
		this.pronosticosJornadaBulk = pronosticosJornadaBulk;
	}

	public Map<Jornada, List<PronosticoJornada>> organizarPorJornada() {
		Map<Jornada, List<PronosticoJornada>> mapaJornadaPronosticos = new HashMap<Jornada, List<PronosticoJornada>>();

		if (pronosticosJornadaBulk != null && !pronosticosJornadaBulk.isEmpty()) {

			// TODO rellenar el mapa leyendo los pronosticos y ordenando por
			// idJornada

		}

		return mapaJornadaPronosticos;
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
				if (pronostico.getIdAlgoritmoPronosticador()
						.equals(idAlgoritmo)) {
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

}
