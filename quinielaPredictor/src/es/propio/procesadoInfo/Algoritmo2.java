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
				.getPronosticoPartidos());
	}

	/**
	 */
	@Override
	public void calcularPronosticoSegunda() throws Exception {
		predecir(getTemporadaSegunda(), getEstimacionJornadaSegunda()
				.getPronosticoPartidos());
	}

	private void predecir(Temporada temporada,
			List<PronosticoPartido> pronosticos) {
		List<Partido> partidosYaJugados = temporada.getPartidosPasados();
		List<Partido> partidosAPredecir = new ArrayList<Partido>();
		partidosAPredecir.addAll(temporada.getJornadaActual().getPartidos());
		Integer numeroParametros = partidosYaJugados.get(1).getParametros()
				.size();
		Integer numeroTargets = 3;// 1X2
		RealMatrix matrixInputs = new Array2DRowRealMatrix(
				partidosYaJugados.size(), numeroParametros);
		RealMatrix matrixTargets = new Array2DRowRealMatrix(
				partidosYaJugados.size(), numeroTargets);
		Map<String, RealVector> mapInputsAPredecir = new HashMap<String, RealVector>();
		// Se rellenan las matrices de entrenamiento
		for (int i = 0; i < partidosYaJugados.size(); i++) {
			Partido partido = partidosYaJugados.get(i);
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

		// Se rellenan las matrices de predicci�n
		for (Partido partido : partidosAPredecir) {
			List<Parametro> parametros = partido.getParametros();
			RealVector fila = new ArrayRealVector(parametros.size());
			for (int j = 0; j < parametros.size(); j++) {
				fila.addToEntry(j, parametros.get(j).getValor());
			}
			mapInputsAPredecir.put(partido.getID(), fila);
		}

		// Preparaci�n para el uso de redes neuronales
		// http://neuroph.sourceforge.net/tutorials/zoo/classification_of_animal_species_using_neural_network.html
		// 1�. Normalizaci�n por columnas. Se normaliza linealmente entre 0 y 1
		Double maximo, minimo, valor;
		for (int i = 0; i < matrixInputs.getColumnDimension(); i++) {
			RealVector columna = matrixInputs.getColumnVector(i);
			maximo = columna.getMaxValue();
			minimo = columna.getMinValue();
			for (int z = 0; z < columna.getDimension(); z++) {
				valor = 0 + (columna.getEntry(z) - minimo) / (maximo - minimo);
				columna.setEntry(z, valor);
			}
			matrixInputs.setColumnVector(i, columna);
		}

		// 2�. Pintado en el log para meterlo en Neuroph studio y ver qu� red es
		// la mejor
		for (int i = 0; i < matrixInputs.getColumnDimension(); i++) {
			RealVector columnaInputs = matrixInputs.getColumnVector(i);
			for (int z = 0; z < columnaInputs.getDimension(); z++) {
				System.out.print(columnaInputs.getEntry(z));
				System.out.print("\t");
			}
			RealVector columnaTargets = matrixTargets.getRowVector(i);
			for (int z = 0; z < columnaTargets.getDimension(); z++) {
				System.out.print(columnaInputs.getEntry(z));
				System.out.print("\t");
			}
			System.out.print("\n");
		}

		// 3�. Creo la red neuronal, la entreno y la uso

		// create training set
		DataSet trainingSet = new DataSet(matrixInputs.getColumnDimension(),
				matrixTargets.getColumnDimension());
		for (int i = 0; i < matrixInputs.getRowDimension(); i++) {
			trainingSet.addRow(new DataSetRow(matrixInputs.getRow(i),
					matrixTargets.getRow(i)));
		}

		// create multi layer perceptron
		List<Integer> numberOfNeuronsInLayers = new ArrayList<Integer>();
		numberOfNeuronsInLayers.add(matrixInputs.getColumnDimension());
		numberOfNeuronsInLayers.add(15);
		numberOfNeuronsInLayers.add(matrixTargets.getColumnDimension());
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(
				numberOfNeuronsInLayers);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setMaxError(0.1);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setLearningRate(0.1);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setMaxIterations(10000);

		// enable batch if using MomentumBackpropagation
		if (myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation) {
			((MomentumBackpropagation) myMlPerceptron.getLearningRule())
					.setBatchMode(true);
			((MomentumBackpropagation) myMlPerceptron.getLearningRule())
					.setMomentum(0.1D);
		}

		LearningRule learningRule = myMlPerceptron.getLearningRule();
		learningRule.addListener(this);

		// learn the training set
		System.out.println("Training neural network...");
		myMlPerceptron.learn(trainingSet);

		// test loaded neural network
		System.out.println("Testing loaded neural network");
		Map<String, RealVector> resultados = testNeuralNetwork(myMlPerceptron,
				mapInputsAPredecir);

		// Para los resultados obtenidos, se obtendr� una predicci�n
		for (Map.Entry<String, RealVector> entry : resultados.entrySet()) {
			for (PronosticoPartido pronostico : pronosticos) {
				if (pronostico.getPartido().getID().equals(entry.getKey())) {
					RealVector prediccionPartido = entry.getValue();
					pronostico.setPorcentaje1(Double.valueOf(
							prediccionPartido.getEntry(0)).floatValue());
					pronostico.setPorcentajeX(Double.valueOf(
							prediccionPartido.getEntry(1)).floatValue());
					pronostico.setPorcentaje2(Double.valueOf(
							prediccionPartido.getEntry(2)).floatValue());
				}
			}
		}
		int a=0;
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
			System.out.println("Input: " + entry.getKey());
			System.out.println("Output: "
					+ Arrays.toString(neuralNet.getOutput()));
		}
		return resultados;
	}

	@Override
	public void handleLearningEvent(LearningEvent event) {
		BackPropagation bp = (BackPropagation) event.getSource();
		System.out.println(bp.getCurrentIteration() + ". iteration : "
				+ bp.getTotalNetworkError());
	}

}
