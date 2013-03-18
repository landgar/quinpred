package es.propio.graficos.aciertosjornada;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.presentacionCalculo.Principal;
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
	public void marcarAciertos(Division division) {

		Integer aciertosAcumulados = 0, totalAcumulados = 0;
		List<Integer> aciertosPorJornada = new ArrayList<Integer>();
		final Integer JORNADA_MAS_ALTA = getNumeroJornadaMasAlta(pronosticosJornadaBulk);

		// Considero la jornada actual y algunas de las anteriores
		final Integer NUMERO_JORNADA_PRIMERA_A_CONSIDERAR = JORNADA_MAS_ALTA
				- Principal.NUMERO_JORNADAS_A_CONSIDERAR;

		if (pronosticosJornadaBulk != null && resultadosReales != null) {
			for (PronosticoJornada realj : resultadosReales) {
				for (PronosticoJornada pronosticoj : pronosticosJornadaBulk) {

					Integer valor = 0;
					if ((valor = pronosticoj.obtenerNumAciertos(realj)) != null) {
						if (pronosticoj.getNumeroJornada() > NUMERO_JORNADA_PRIMERA_A_CONSIDERAR) {
							aciertosAcumulados += valor;
							totalAcumulados += pronosticoj
									.getPronosticoPartidos().size();
							aciertosPorJornada.add(valor);
						}
						// System.out
						// .println("*********************************************ALGORITMO "
						// + pronosticoj
						// .getIdAlgoritmoPronosticador()
						// .getIdAlgoritmo()
						// +
						// " *******************************************************");
					}
				}
			}
		}
		Mean mediaPorJornada = new Mean();
		StandardDeviation stdPorJornada = new StandardDeviation();
		Iterator<Integer> iter = aciertosPorJornada.iterator();
		Integer aux;
		while (iter.hasNext()) {
			aux = iter.next();
			mediaPorJornada.increment(Double.valueOf(aux.toString()));
			stdPorJornada.increment(Double.valueOf(aux.toString()));
		}

		DecimalFormat df = new DecimalFormat("0.0000");

		System.out.println(division.toString() + "\t>>> Desde la jornada "
				+ NUMERO_JORNADA_PRIMERA_A_CONSIDERAR + " --> "
				+ "\tAciertos acumulados = " + aciertosAcumulados + " / "
				+ totalAcumulados + "\t<<< " + "Media por jornada = "
				+ df.format(mediaPorJornada.getResult()) + "\t<<< "
				+ "Stddev por jornada = "
				+ df.format(stdPorJornada.getResult()));

	}

	// public Map<Jornada, List<PronosticoJornada>>
	// organizarPronosticosBulkPorJornada() {
	// Map<Jornada, List<PronosticoJornada>> mapaJornadaPronosticos = new
	// HashMap<Jornada, List<PronosticoJornada>>();
	// if (pronosticosJornadaBulk != null && !pronosticosJornadaBulk.isEmpty())
	// {
	// mapaJornadaPronosticos = organizarPorJornada(pronosticosJornadaBulk);
	// }
	// return mapaJornadaPronosticos;
	// }
	//
	// public Map<Jornada, List<PronosticoJornada>>
	// organizarResultadosRealesPorJornada() {
	// Map<Jornada, List<PronosticoJornada>> mapaJornadaPronosticos = new
	// HashMap<Jornada, List<PronosticoJornada>>();
	// if (resultadosReales != null && !resultadosReales.isEmpty()) {
	// mapaJornadaPronosticos = organizarPorJornada(resultadosReales);
	// }
	// return mapaJornadaPronosticos;
	// }
	//
	// private Map<Jornada, List<PronosticoJornada>> organizarPorJornada(
	// List<PronosticoJornada> lista) {
	// Map<Jornada, List<PronosticoJornada>> mapa = new HashMap<Jornada,
	// List<PronosticoJornada>>();
	// // TODO rellenar el mapa leyendo los pronosticos y ordenando por
	// // idJornada
	//
	// return mapa;
	// }

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

	public Integer getNumeroJornadaMasAlta(
			final List<PronosticoJornada> pronosticos) {
		Integer salida = 0, aux;
		for (PronosticoJornada pronosticoj : pronosticos) {
			aux = pronosticoj.getNumeroJornada();
			if (aux > salida)
				salida = aux;
		}
		return salida;
	}

	public List<PronosticoJornada> getPronosticosJornadaBulk() {
		return pronosticosJornadaBulk;
	}

	public List<PronosticoJornada> getResultadosReales() {
		return resultadosReales;
	}

}
