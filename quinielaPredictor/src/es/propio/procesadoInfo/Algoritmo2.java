/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.Arrays;
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

	private static final Integer NUM_ITERACIONES = Integer.valueOf(10);

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
	 *            Lista de pronosticos prerellena en la que solo falta rellenar
	 *            las probabilidades de 1 X 2.
	 */
	private void predecir(Temporada temporada,
			List<PronosticoPartido> pronosticos,
			final Integer numeroJornadaActual) throws Exception {
		List<Partido> partidosYaJugados = temporada
				.getPartidosPasados(numeroJornadaActual);
		List<Partido> partidosAPredecir = extraerPartidos(pronosticos);

		if (!partidosYaJugados.isEmpty()) {

			// INPUTS
			Partido partidoDeReferencia = partidosYaJugados.get(0);
			int numParamsPartido = partidoDeReferencia.getParametros().size();
			int numParamsEquipoLocal = partidoDeReferencia.getEquipoLocal()
					.getParametros().size();
			int numParamsEquipoVisit = partidoDeReferencia.getEquipoVisitante()
					.getParametros().size();

			Integer numeroParametros = numParamsPartido + numParamsEquipoLocal
					+ numParamsEquipoVisit;
			RealMatrix matrixInputs = new Array2DRowRealMatrix(
					partidosYaJugados.size(), numeroParametros);
			System.out.println("RED NEURONAL: numero de INPUTS = "
					+ String.valueOf(numParamsPartido) + "+"
					+ String.valueOf(numParamsEquipoLocal) + "+"
					+ String.valueOf(numParamsEquipoVisit) + "="
					+ numeroParametros);

			// TARGETS
			Integer numeroTargets = 3;// 1X2
			RealMatrix matrixTargets = new Array2DRowRealMatrix(
					partidosYaJugados.size(), numeroTargets);
			System.out.println("RED NEURONAL: numero de TARGETS = "
					+ numeroTargets);

			Map<String, RealVector> mapInputsAPredecir = new HashMap<String, RealVector>();

			System.out.println("Se rellenan las matrices de entrenamiento...");

			for (int i = 0; i < partidosYaJugados.size(); i++) {
				Partido partido = partidosYaJugados.get(i);
				List<Parametro> parametros = partido.getParametros();
				RealVector fila = matrixInputs.getRowVector(i);

				// Parámetros del partido
				rellenarFilaMatriz(parametros, partido, fila);

				// control
				controlarDimensiones("PARTIDOS YA JUGADOS", fila, parametros,
						partido);

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
			System.out.println("Matrices de entrenamiento rellenas.");

			System.out.println("Se rellenan las matrices de prediccion...");
			for (Partido partido : partidosAPredecir) {
				List<Parametro> parametros = partido.getParametros();
				numeroParametros = partido.getParametros().size()
						+ partido.getEquipoLocal().getParametros().size()
						+ partido.getEquipoVisitante().getParametros().size();
				RealVector fila = new ArrayRealVector(numeroParametros);

				rellenarFilaMatriz(parametros, partido, fila);

				// control
				controlarDimensiones("PARTIDOS A PREDECIR", fila, parametros,
						partido);

				System.out.println("RED NEURONAL:"
						+ "Creando Input (fila de tamanio="
						+ fila.getDimension() + ")" + " para partido="
						+ partido.getID() + " ...");
				mapInputsAPredecir.put(partido.getID(), fila);
			}
			System.out.println("Matrices de prediccion rellenas.");

			// Preparación para el uso de redes neuronales
			// http://neuroph.sourceforge.net/tutorials/zoo/classification_of_animal_species_using_neural_network.html
			// 1º. Normalización por columnas. Se normaliza linealmente entre 0
			// y 1
			System.out
					.println("Preparación para el uso de redes neuronales (normalizamos todas las columnas)...");
			Double maximo, minimo, valor;
			for (int col = 0; col < matrixInputs.getColumnDimension(); col++) {
				RealVector columna = matrixInputs.getColumnVector(col);
				maximo = columna.getMaxValue();
				minimo = columna.getMinValue();
				Double diferencia = maximo - minimo;
				if (diferencia == 0) {
					// TODO no hay que lanzar excepcion
					// throw new Exception(
					// "No se pueden normalizar estos datos de la red neuronal, porque todos son iguales. Columna de matrixInputs col="
					// + col);
					diferencia = maximo;
				}

				// Se normalizan todos sus valores
				for (int z = 0; z < columna.getDimension(); z++) {
					valor = 0 + (columna.getEntry(z) - minimo) / diferencia;
					columna.setEntry(z, valor);
				}

				// Se reemplaza la columna poniendo la columna normalizada
				matrixInputs.setColumnVector(col, columna);
			}

			// 3º. Creo la red neuronal, la entreno y la uso

			System.out.println("CREANDO RED NEURONAL...");
			// create training set
			DataSet trainingSet = new DataSet(
					matrixInputs.getColumnDimension(),
					matrixTargets.getColumnDimension());
			for (int i = 0; i < matrixInputs.getRowDimension(); i++) {
				trainingSet.addRow(new DataSetRow(matrixInputs.getRow(i),
						matrixTargets.getRow(i)));
			}

			// create multi layer perceptron
			List<Integer> numberOfNeuronsInLayers = new ArrayList<Integer>();
			// Fijado según indicaciones de:
			// http://www.heatonresearch.com/node/707
			// Aumento hasta 2f según:
			// http://neuroph.sourceforge.net/tutorials/SportsPrediction/Premier%20League%20Prediction.html
			final float AUMENTO_NEURONAS = 1F;
			final Integer NUMERO_NEURONAS_HIDDEN_LAYER = Double.valueOf(
					Math.floor(AUMENTO_NEURONAS * (2 / 3) * numeroParametros
							+ matrixTargets.getColumnDimension())).intValue();
			numberOfNeuronsInLayers.add(matrixInputs.getColumnDimension());
			numberOfNeuronsInLayers.add(NUMERO_NEURONAS_HIDDEN_LAYER);
			numberOfNeuronsInLayers.add(matrixTargets.getColumnDimension());
			MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(
					numberOfNeuronsInLayers);
			((SupervisedLearning) myMlPerceptron.getLearningRule())
					.setMaxError(0.05);
			((SupervisedLearning) myMlPerceptron.getLearningRule())
					.setLearningRate(0.2);

			// TODO revisar el maximo numero de iteraciones (el error debe
			// converger hacia 0)
			((SupervisedLearning) myMlPerceptron.getLearningRule())
					.setMaxIterations(NUM_ITERACIONES);

			// enable batch if using MomentumBackpropagation
			if (myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation) {
				((MomentumBackpropagation) myMlPerceptron.getLearningRule())
						.setBatchMode(true);
				((MomentumBackpropagation) myMlPerceptron.getLearningRule())
						.setMomentum(0.7);
			}

			LearningRule learningRule = myMlPerceptron.getLearningRule();
			learningRule.addListener(this);

			// learn the training set
			System.out.println("ENTRENANDO RED NEURONAL...");
			myMlPerceptron.learn(trainingSet);

			// test loaded neural network
			System.out.println("TESTEANDO RED NEURONAL CARGADA...");
			Map<String, RealVector> resultados = testNeuralNetwork(
					myMlPerceptron, mapInputsAPredecir);

			// Para los resultados obtenidos, se obtendrá una predicción
			for (Map.Entry<String, RealVector> entry : resultados.entrySet()) {
				for (PronosticoPartido pronostico : pronosticos) {
					if (pronostico.getPartido().getID().equals(entry.getKey())) {
						pronostico.setPorcentaje1(0F);
						pronostico.setPorcentajeX(0F);
						pronostico.setPorcentaje2(0F);
						RealVector prediccionPartido = entry.getValue();
						if (prediccionPartido.getEntry(0) > prediccionPartido
								.getEntry(1)) {
							if (prediccionPartido.getEntry(0) > prediccionPartido
									.getEntry(2)) {
								pronostico.setPorcentaje1(1F);
							} else {
								pronostico.setPorcentaje2(1F);
							}
						} else if (prediccionPartido.getEntry(1) > prediccionPartido
								.getEntry(2)) {
							pronostico.setPorcentajeX(1F);
						} else {
							pronostico.setPorcentaje2(1F);
						}
						// pronostico.setPorcentaje1(Double.valueOf(
						// prediccionPartido.getEntry(0)).floatValue());
						// pronostico.setPorcentajeX(Double.valueOf(
						// prediccionPartido.getEntry(1)).floatValue());
						// pronostico.setPorcentaje2(Double.valueOf(
						// prediccionPartido.getEntry(2)).floatValue());
					}
				}
			}
		}
	}

	private void rellenarFilaMatriz(List<Parametro> parametros,
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

		System.out.println("RED NEURONAL: numero de entradas esperadas="
				+ neuralNet.getInputNeurons().length);

		for (Map.Entry<String, RealVector> entry : mapInputsAPredecir
				.entrySet()) {

			System.out.println("RED NEURONAL: metiendo entrada de tamanio="
					+ entry.getValue().toArray().length);

			neuralNet.setInput(entry.getValue().toArray());
			neuralNet.calculate();
			resultados.put(entry.getKey(),
					new ArrayRealVector(neuralNet.getOutput()));
			System.out.println("Input: " + entry.getKey());
			System.out.println("Output: "
					+ Arrays.toString(neuralNet.getOutput()));
		}
		return resultados;
	}

	@Override
	public void handleLearningEvent(LearningEvent event) {
		BackPropagation bp = (BackPropagation) event.getSource();
		System.out.println("Iteración: " + bp.getCurrentIteration()
				+ ". Error total: " + bp.getTotalNetworkError());
	}

	private void controlarDimensiones(String tipo, RealVector fila,
			List<Parametro> parametros, Partido partido) {
		int tamanoFila = fila.getDimension();
		int numParamsPartido = parametros.size();
		int numParamsEquipoLocal = partido.getEquipoLocal().getParametros()
				.size();
		int numParamsEquipoVisitante = partido.getEquipoVisitante()
				.getParametros().size();

		String nombreLocal = partido.getEquipoLocal().getNombre();
		String nombreVisitante = partido.getEquipoVisitante().getNombre();

		// System.out.println("CONTROL de " + tipo + ":" + " fila=" + tamanoFila
		// + " partido=" + numParamsPartido + " local (" + nombreLocal
		// + ")=" + numParamsEquipoLocal + " visitante ("
		// + nombreVisitante + ")=" + numParamsEquipoVisitante);

		if (numParamsEquipoLocal != numParamsEquipoVisitante) {
			System.out.println(" ** DISTINTOS:" + " local="
					+ partido.getEquipoLocal().getNombre() + " visitante="
					+ partido.getEquipoVisitante().getNombre());
		}

	}
}
