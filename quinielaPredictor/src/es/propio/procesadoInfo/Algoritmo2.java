/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.log4j.Logger;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;

import es.propio.modeladoInfo.Parametro;
import es.propio.modeladoInfo.ParametroEquipo;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.modeladoInfo.ValorResultado;

/**
 * @author i3casa
 * 
 */
public class Algoritmo2 extends AbstractAlgoritmo implements
		LearningEventListener {

	static final Logger logger = Logger.getLogger(Algoritmo2.class);

	private static final Integer NUM_ITERACIONES = 15000;
	Double LEARNING_RATE = 0.2D;
	Double MOMENTUM = 0.7D;

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
		predecir(getTemporadaPrimera(), getEstimacionJornadaPrimera()
				.getPronosticoPartidos(), getEstimacionJornadaPrimera()
				.getNumeroJornada());
	}

	/**
	 */
	@Override
	public void calcularPronosticoSegunda() throws Exception {
		predecir(getTemporadaSegunda(), getEstimacionJornadaSegunda()
				.getPronosticoPartidos(), getEstimacionJornadaSegunda()
				.getNumeroJornada());
	}

	private List<Partido> extraerPartidos(List<PronosticoPartido> pronosticos) {
		List<Partido> partidos = new ArrayList<Partido>();
		for (PronosticoPartido pp : pronosticos) {
			partidos.add(pp.getPartido());
		}
		return partidos;
	}

	/**
	 * @param temporada
	 * @param pronosticos
	 *            Lista de pronósticos prerrellena en la que sólo falta rellenar
	 *            las probabilidades de 1 X 2.
	 */
	private void predecir(Temporada temporada,
			List<PronosticoPartido> pronosticos,
			final Integer numeroJornadaActual) throws Exception {
		System.out
				.println("*****************************************************");
		List<Partido> partidosYaJugados = temporada
				.getPartidosPasados(numeroJornadaActual);

		if (!partidosYaJugados.isEmpty()) {

			Partido partidoDeReferencia = partidosYaJugados.get(0);
			int numParamsPartido = partidoDeReferencia.getParametros().size();
			int numParamsEquipoLocal = partidoDeReferencia.getEquipoLocal()
					.getParametros().size();
			int numParamsEquipoVisitante = partidoDeReferencia
					.getEquipoVisitante().getParametros().size();

			Integer numeroParametros = numParamsPartido + numParamsEquipoLocal
					+ numParamsEquipoVisitante;
			RealMatrix matrixInputs = new Array2DRowRealMatrix(
					partidosYaJugados.size(), numeroParametros);

			Integer numeroTargets = 3;// 1X2
			RealMatrix matrixTargets = new Array2DRowRealMatrix(
					partidosYaJugados.size(), numeroTargets);

			rellenarMatricesEntrenamiento(partidosYaJugados, matrixInputs,
					matrixTargets);

			// Preparación para el uso de redes neuronales
			normalizarMatriz(matrixInputs);

			// Creo la red neuronal, la entreno y la uso
			MultiLayerPerceptron myMlPerceptron = crearRedNeuronal(
					matrixInputs, matrixTargets, numeroParametros);

			// learn the training set
			entrenarRedNeuronal(matrixInputs, matrixTargets, myMlPerceptron);

			// Predicción de salidas
			Map<String, RealVector> resultados = testNeuralNetwork(
					myMlPerceptron,
					rellenarMatricesPrediccion(pronosticos, numeroParametros));

			// Para los resultados obtenidos, se obtendrá una
			// predicción
			System.out.println("Pronóstico: " + resultados.toString());
			generarPronosticos(resultados, pronosticos, temporada);

		}
	}

	private void generarPronosticos(final Map<String, RealVector> resultados,
			List<PronosticoPartido> pronosticos, final Temporada temporada) {
		// Habrá 'x' empates en primera, e 'y' en segunda. Serán los más
		// probables
		// en esa jornada. Para el resto, será 1 ó 2, según su probabilidad de
		// partido. 'x' e 'y' dependen de la media en la temporada.
		for (Map.Entry<String, RealVector> entry : resultados.entrySet()) {
			for (PronosticoPartido pronostico : pronosticos) {
				if (pronostico.getPartido().getID().equals(entry.getKey())) {
					RealVector prediccionPartido = entry.getValue();
					pronostico.setPorcentaje1((float) prediccionPartido
							.getEntry(0));
					pronostico.setPorcentajeX((float) prediccionPartido
							.getEntry(1));
					pronostico.setPorcentaje2((float) prediccionPartido
							.getEntry(2));
				}
			}
		}
		class ComparatorX implements Comparator<PronosticoPartido> {
			@Override
			public int compare(PronosticoPartido o1, PronosticoPartido o2) {
				return o1.getPorcentajeX().compareTo(o2.getPorcentajeX());
			}
		}
		// Pronósticos ordenados por probabilidad de empates
		Collections.sort(pronosticos,
				Collections.reverseOrder(new ComparatorX()));
		List<PronosticoPartido> pronosticosConEmpates = new ArrayList<PronosticoPartido>();
		Integer numeroEmpatesEnPrediccion;
		temporada.calcularEstadisticas();
		if (temporada.getMediaXPorJornada() != null) {
			numeroEmpatesEnPrediccion = (int) Math.round(temporada
					.getMediaXPorJornada().getResult());
		} else {
			// Caso de la primera jornada
			numeroEmpatesEnPrediccion = 2;
		}
		// Se toman los 'x' primeros partidos
		pronosticosConEmpates.addAll(pronosticos.subList(0,
				numeroEmpatesEnPrediccion));

		for (PronosticoPartido pronostico : pronosticos) {
			for (PronosticoPartido pronosticoEmpate : pronosticosConEmpates) {
				if (pronosticoEmpate.getPartido().getID()
						.equals(pronostico.getPartido().getID())) {
					// Se fija el empate
					pronostico.setPorcentaje1(0F);
					pronostico.setPorcentajeX(1F);
					pronostico.setPorcentaje2(0F);
				} else if (!pronosticosConEmpates.contains(pronostico)) {
					// No se fija empate. Se toma 1 o 2. El más probable.
					if (pronostico.getPorcentaje1() > pronostico
							.getPorcentaje2()) {
						// Se fija un 1
						pronostico.setPorcentaje1(1F);
						pronostico.setPorcentajeX(0F);
						pronostico.setPorcentaje2(0F);
					} else {
						// Se fija un 2
						pronostico.setPorcentaje1(0F);
						pronostico.setPorcentajeX(0F);
						pronostico.setPorcentaje2(1F);
					}
				}
			}
			System.out.println(pronostico.getPartido().getID() + ": "
					+ pronostico.getResultadoMasProbable());
		}
	}

	private void entrenarRedNeuronal(final RealMatrix matrixInputs,
			final RealMatrix matrixTargets, MultiLayerPerceptron myMlPerceptron) {
		// create training set
		DataSet trainingSet = new DataSet(matrixInputs.getColumnDimension(),
				matrixTargets.getColumnDimension());
		for (int i = 0; i < matrixInputs.getRowDimension(); i++) {
			trainingSet.addRow(new DataSetRow(matrixInputs.getRow(i),
					matrixTargets.getRow(i)));
		}
		myMlPerceptron.learn(trainingSet);
	}

	private MultiLayerPerceptron crearRedNeuronal(
			final RealMatrix matrixInputs, final RealMatrix matrixTargets,
			final Integer numeroParametros) {

		System.out.println("RED NEURONAL: numero de parametros total = "
				+ numeroParametros);

		List<Integer> numberOfNeuronsInLayers = new ArrayList<Integer>();
		// Fijado según indicaciones de:
		// http://www.heatonresearch.com/node/707
		// Aumento hasta 2f según:
		// http://neuroph.sourceforge.net/tutorials/SportsPrediction/Premier%20League%20Prediction.html
		final float AUMENTO_NEURONAS = 1F;
		final Integer NUMERO_NEURONAS_HIDDEN_LAYER = Double.valueOf(
				Math.floor(AUMENTO_NEURONAS * (2 / 3D) * numeroParametros
						+ matrixTargets.getColumnDimension())).intValue();
		System.out.println("Número de neuronas de capa oculta: "
				+ NUMERO_NEURONAS_HIDDEN_LAYER);
		numberOfNeuronsInLayers.add(matrixInputs.getColumnDimension());
		numberOfNeuronsInLayers.add(NUMERO_NEURONAS_HIDDEN_LAYER);
		numberOfNeuronsInLayers.add(matrixTargets.getColumnDimension());
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(
				numberOfNeuronsInLayers);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setMaxError(0.02);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setLearningRate(LEARNING_RATE);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setMaxIterations(NUM_ITERACIONES);

		// enable batch if using MomentumBackpropagation
		if (myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation) {
			((MomentumBackpropagation) myMlPerceptron.getLearningRule())
					.setBatchMode(true);
			((MomentumBackpropagation) myMlPerceptron.getLearningRule())
					.setMomentum(MOMENTUM);
		}

		LearningRule learningRule = myMlPerceptron.getLearningRule();
		learningRule.addListener(this);

		return myMlPerceptron;
	}

	private void rellenarMatricesEntrenamiento(
			final List<Partido> partidosYaJugados, RealMatrix matrixInputs,
			RealMatrix matrixTargets) {

		System.out.println("Rellenando matrices de entrenamiento...");

		for (int i = 0; i < partidosYaJugados.size(); i++) {
			Partido partido = partidosYaJugados.get(i);
			List<Parametro> parametros = partido.getParametros();
			RealVector fila = matrixInputs.getRowVector(i);

			// Parámetros del partido
			rellenarFilaVector(parametros, partido, fila);

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

		System.out.println("Matrices de entrenamiento llenas.");
	}

	private Map<String, RealVector> rellenarMatricesPrediccion(
			final List<PronosticoPartido> pronosticos,
			final Integer numeroParametros) throws Exception {

		System.out.println("Rellenando matrices de predicción...");

		List<Partido> partidosAPredecir = extraerPartidos(pronosticos);
		Map<String, RealVector> mapInputsAPredecir = new HashMap<String, RealVector>();
		RealMatrix matriz = new Array2DRowRealMatrix(pronosticos.size(),
				numeroParametros);
		List<Parametro> parametros;
		RealVector fila;
		int i = 0;
		for (Partido partido : partidosAPredecir) {
			parametros = partido.getParametros();
			fila = matriz.getRowVector(i);
			rellenarFilaVector(parametros, partido, fila);
			matriz.setRowVector(i, fila);
			i++;
		}
		normalizarMatriz(matriz);
		i = 0;
		for (Partido partido : partidosAPredecir) {
			mapInputsAPredecir.put(partido.getID(), matriz.getRowVector(i));
			i++;
		}
		System.out.println("Matrices de predicción llenas.");
		return mapInputsAPredecir;
	}

	private void normalizarMatriz(RealMatrix matrixInputs) throws Exception {

		System.out.println("Normalizando matriz...");

		// http://neuroph.sourceforge.net/tutorials/zoo/classification_of_animal_species_using_neural_network.html
		// 1º. Normalización por columnas. Se normaliza linealmente entre 0
		// y 1
		Double maximo, minimo, valor;
		for (int col = 0; col < matrixInputs.getColumnDimension(); col++) {
			RealVector columna = matrixInputs.getColumnVector(col);
			maximo = columna.getMaxValue();
			minimo = columna.getMinValue();
			Double diferencia = maximo - minimo;
			if (diferencia == 0) {
				// Se ha comentado, dado que el número de la jornada es un
				// parámetro, y siempre es el mismo para los partidos a predecir
				// throw new Exception(
				// "No se pueden normalizar estos datos de la red neuronal, porque todos son iguales. Columna de matrixInputs col="
				// + col);
				for (int z = 0; z < columna.getDimension(); z++) {
					columna.setEntry(z, 1);
				}
			} else {
				// Se normalizan todos sus valores
				for (int z = 0; z < columna.getDimension(); z++) {
					valor = 0 + (columna.getEntry(z) - minimo) / diferencia;
					columna.setEntry(z, valor);
				}
			}
			// Se reemplaza la columna poniendo la columna normalizada
			matrixInputs.setColumnVector(col, columna);
		}

		System.out.println("Matriz normalizada.");

	}

	private void rellenarFilaVector(List<Parametro> parametros,
			Partido partido, RealVector fila) {
		int j;
		for (j = 0; j < parametros.size(); j++) {
			fila.addToEntry(j, parametros.get(j).getValor());
		}
		for (ParametroEquipo parametroEquipo : partido.getEquipoLocal()
				.getParametros()) {
			Integer valor = parametroEquipo.getValor();
			fila.addToEntry(j, valor);
			j++;
		}
		for (ParametroEquipo parametroEquipo : partido.getEquipoVisitante()
				.getParametros()) {
			Integer valor = parametroEquipo.getValor();
			fila.addToEntry(j, valor);
			j++;
		}
	}

	/**
	 * Prints network output for the each element from the specified training
	 * set.
	 * 
	 * @param neuralNet
	 *            neural network
	 * @param testSet
	 *            test set
	 * @return Mapa de # fila de partido y resultados.
	 */

	public static Map<String, RealVector> testNeuralNetwork(
			NeuralNetwork neuralNet, Map<String, RealVector> mapInputsAPredecir) {
		Map<String, RealVector> resultados = new HashMap<String, RealVector>();
		for (Map.Entry<String, RealVector> entry : mapInputsAPredecir
				.entrySet()) {
			neuralNet.setInput(entry.getValue().toArray());
			neuralNet.calculate();
			resultados.put(entry.getKey(),
					new ArrayRealVector(neuralNet.getOutput()));
			// System.out.println("Input: " + entry.getKey());
			// System.out.println("Output: "
			// + Arrays.toString(neuralNet.getOutput()));
		}
		return resultados;
	}

	@Override
	public void handleLearningEvent(LearningEvent event) {
		BackPropagation bp = (BackPropagation) event.getSource();
		if (bp.getCurrentIteration() % 5000 == 0)
			System.out.println("Iteración: " + bp.getCurrentIteration()
					+ ". Error total: " + bp.getTotalNetworkError());
	}

}
