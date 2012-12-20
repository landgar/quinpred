/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Parametro;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;
import es.propio.modeladoInfo.ValorResultado;

/**
 * @author i3casa
 * 
 */
public class Algoritmo2 extends AbstractAlgoritmo {
	static final Logger logger = Logger.getLogger(Algoritmo2.class);

	public Algoritmo2(final Temporada temporadaPrimera,
			final Temporada temporadaSegunda) {
		super();
		setId(IdAlgoritmoEnum.ALGORITMO2);
		setTemporadaPrimera(temporadaPrimera);
		setTemporadaSegunda(temporadaSegunda);
	}

	/**
	 */
	@Override
	public void calcularPronosticoPrimera() throws Exception {
		Temporada temporada = getTemporadaPrimera();
		List<Partido> partidos = temporada.getPartidosPasados();
		Integer numeroParametros = partidos.get(1).getParametros().size();
		Integer numeroTargets = 3;// 1X2
		RealMatrix matrixInputs = new Array2DRowRealMatrix(partidos.size(),
				numeroParametros);
		RealMatrix matrixTargets = new Array2DRowRealMatrix(partidos.size(),
				numeroTargets);

		for (int i = 0; i < partidos.size(); i++) {
			Partido partido = partidos.get(i);
			List<Parametro> parametros = partido.getParametros();
			RealVector fila = matrixInputs.getRowVector(i);
			for (int j = 0; j < parametros.size(); j++) {
				fila.addToEntry(j, parametros.get(j).getValor());
			}
			matrixInputs.setRowVector(i, fila);
			// Targets (resultados esperados)
			ValorResultado resultado = partido.getResultadoQuiniela()
					.getValor();
			if (resultado.equals(ValorResultado.UNO)) {
				matrixTargets.addToEntry(i, 0, 1);
			} else {
				matrixTargets.addToEntry(i, 0, 0);
			}
			if (resultado.equals(ValorResultado.EQUIS)) {
				matrixTargets.addToEntry(i, 1, 1);
			} else {
				matrixTargets.addToEntry(i, 1, 0);
			}
			if (resultado.equals(ValorResultado.DOS)) {
				matrixTargets.addToEntry(i, 2, 1);
			} else {
				matrixTargets.addToEntry(i, 2, 0);
			}
		}

		// Preparación para el uso de redes neuronales
		// http://neuroph.sourceforge.net/tutorials/zoo/classification_of_animal_species_using_neural_network.html
		// 1º. Normalización por columnas. Se normaliza linealmente entre 0 y 1
		Double maximo, minimo, valor;
		for (int i = 0; i < matrixInputs.getColumnDimension(); i++) {
			RealVector columna = matrixInputs.getColumnVector(i);
			maximo = columna.getMaxValue();
			minimo = columna.getMinValue();
			for (int z = 0; z < columna.getDimension(); z++) {
				valor = 0+(columna.getEntry(z) -
						minimo) / (maximo - minimo);
				columna.setEntry(z, valor);
			}
			matrixInputs.setColumnVector(i, columna);
		}

		// 2º. Pintado en el log para meterlo en Neuroph studio y ver qué red es
		// la mejor
		for (int i = 0; i < matrixInputs.getColumnDimension(); i++) {
			RealVector columnaInputs = matrixInputs.getColumnVector(i);
			for (int z = 0; z < columnaInputs.getDimension(); z++) {
				System.out.print(columnaInputs.getEntry(z) + "\t");
			}
			RealVector columnaTargets = matrixTargets.getRowVector(i);
			for (int z = 0; z < columnaTargets.getDimension(); z++) {
				System.out.print(columnaTargets.getEntry(z) + "\t");
			}
			System.out.print("\n");
		}
	}

	/**
	 */
	@Override
	public void calcularPronosticoSegunda() throws Exception {
	}

	// /**
	// * 1º. Se toma como base la clasificación, para esta jornada (actualmente
	// * requiere cargar toda la temporada para calcular la clasificación. Esto
	// * son muchos costes. Sugerencia: almacenarlo en BBDD y/o reestructurar la
	// * jerarquía de clases). Primero los más arriba en la clasificación.
	// *
	// * 2º. Se buscan las menores distancias entre los equipos de cada partido,
	// y
	// * se asigna X a un 20% de los partidos con menor distancia (en primera,
	// las
	// * distancias son los puntos en la clasificación).
	// */
	// @Override
	// public void calcularPronosticoPrimera() throws Exception {
	// // 1º.
	// Map<String, Float> distancias =
	// calculaDistanciasEntreEquipos(Division.PRIMERA);
	// // 2º.
	// asignaXMasProbables(Division.PRIMERA, 0.2F, distancias);
	// }
	//
	// /**
	// * 1º. Se toma como base la clasificación, para esta jornada (actualmente
	// * requiere cargar toda la temporada para calcular la clasificación. Esto
	// * son muchos costes. Sugerencia: almacenarlo en BBDD y/o reestructurar la
	// * jerarquía de clases). Primero los más arriba en la clasificación. Los
	// * puntos usados son simples (2: ganado; 1: empatado;0:perdido)
	// *
	// * 2º. Se buscan las menores distancias entre los equipos de cada partido,
	// y
	// * se asigna X a un 30% de los partidos con menor distancia (en segunda,
	// son
	// * la diferencia de goles en contra).
	// */
	// @Override
	// public void calcularPronosticoSegunda() throws Exception {
	// // 1º.
	// Map<String, Float> distancias =
	// calculaDistanciasEntreEquipos(Division.SEGUNDA);
	// // 2º.
	// asignaXMasProbables(Division.SEGUNDA, 0.3F, distancias);
	// }
	//
	// class ValueComparator implements Comparator<String> {
	//
	// Map<String, Float> base;
	//
	// public ValueComparator(Map<String, Float> base) {
	// this.base = base;
	// }
	//
	// // Note: this comparator imposes orderings that are inconsistent with
	// // equals.
	// public int compare(String a, String b) {
	// if (base.get(a) <= base.get(b)) {
	// return -1;
	// } else {
	// return 1;
	// } // returning 0 would merge keys
	// }
	// }
	//
	// private Map<String, Float> calculaDistanciasEntreEquipos(
	// final Division division) throws Exception {
	// CargadorInformacionWebResultados cargador = new
	// CargadorInformacionWebResultados();
	// cargador.cargar();
	// Temporada temporada;
	// PronosticoJornada estimacion;
	// if (division.equals(Division.PRIMERA)) {
	// temporada = cargador.getTemporadaPrimera();
	// estimacion = getEstimacionJornadaPrimera();
	// } else {
	// temporada = cargador.getTemporadaSegunda();
	// estimacion = getEstimacionJornadaSegunda();
	// }
	// List<PronosticoPartido> listaPronosticos = estimacion
	// .getPronosticoPartidos();
	// Map<String, Float> distancias = new HashMap<String, Float>();
	// for (PronosticoPartido pronostico : listaPronosticos) {
	// Partido partido = pronostico.getPartido();
	// Float valorLocal, valorVisitante;
	// if (division.equals(Division.PRIMERA)) {
	// valorLocal = Float.valueOf(temporada
	// .getPuntosSimplesAnterioresA(partido.getEquipoLocal(),
	// estimacion.getNumeroJornada()));
	// valorVisitante = Float.valueOf(temporada
	// .getPuntosSimplesAnterioresA(
	// partido.getEquipoVisitante(),
	// estimacion.getNumeroJornada()));
	// } else {
	// valorLocal = 1F / temporada
	// .getGolesTotalesEnContraAnterioresA(
	// partido.getEquipoLocal(),
	// estimacion.getNumeroJornada());
	// valorVisitante = 1F / temporada
	// .getGolesTotalesEnContraAnterioresA(
	// partido.getEquipoVisitante(),
	// estimacion.getNumeroJornada());
	// }
	// pronostico.reseteaPorcentajes();
	// if (valorLocal > valorVisitante) {
	// pronostico.setPorcentaje1(1F);
	// } else if (valorLocal < valorVisitante) {
	// pronostico.setPorcentaje2(1F);
	// } else {
	// pronostico.setPorcentajeX(1F);
	// }
	// distancias.put(partido.getID(),
	// Math.abs(valorLocal - valorVisitante));
	// }
	// return distancias;
	// }
	//
	// public void asignaXMasProbables(final Division division,
	// final Float porcentajePartidos, final Map<String, Float> distancias) {
	//
	// TreeMap<String, Float> sorted_map = new TreeMap<String, Float>(
	// new ValueComparator(distancias));
	// sorted_map.putAll(distancias);
	// Integer empatesAsignados = 0;
	// PronosticoJornada estimacion;
	// if (division.equals(Division.PRIMERA)) {
	// estimacion = getEstimacionJornadaPrimera();
	// } else {
	// estimacion = getEstimacionJornadaSegunda();
	// }
	// List<PronosticoPartido> listaPronosticos = estimacion
	// .getPronosticoPartidos();
	// Integer MAX_PARTIDOS_CON_EMPATE = Math.round(porcentajePartidos
	// * listaPronosticos.size());
	// for (Map.Entry<String, Float> cercano : sorted_map.entrySet()) {
	// if (empatesAsignados < MAX_PARTIDOS_CON_EMPATE) {
	// for (PronosticoPartido pronostico : listaPronosticos) {
	// if (cercano.getKey()
	// .equals(pronostico.getPartido().getID())) {
	// pronostico.reseteaPorcentajes();
	// pronostico.setPorcentajeX(1F);
	// empatesAsignados++;
	// }
	// }
	// }
	// }
	// }

}
