/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
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
import org.neuroph.nnet.learning.MomentumBackpropagation;

import es.propio.modeladoInfo.Equipo;
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

	private static final Integer NUM_ITERACIONES = 10;
	Integer NUM_NEURONAS_HIDDEN_LAYER = 20;
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
		Integer NUM_NEURONAS_HIDDEN_LAYER_array[] = { 1, 2 };
		Double LEARNING_RATE_array[] = { 0.1, 0.3 };
		Double MOMENTUM_array[] = { 0.1, 0.3 };

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

			for (int i = 0; i < NUM_NEURONAS_HIDDEN_LAYER_array.length; i++) {
				for (int j = 0; j < LEARNING_RATE_array.length; j++) {
					for (int k = 0; k < MOMENTUM_array.length; k++) {
						NUM_NEURONAS_HIDDEN_LAYER = NUM_NEURONAS_HIDDEN_LAYER_array[i];
						LEARNING_RATE = LEARNING_RATE_array[j];
						MOMENTUM = MOMENTUM_array[k];

						// learn the training set
						entrenarRedNeuronal(matrixInputs, matrixTargets,
								myMlPerceptron);

						// Predicción de salidas
						Map<String, RealVector> resultados = testNeuralNetwork(
								myMlPerceptron,
								rellenarMatricesPrediccion(pronosticos,
										numeroParametros));

						// Para los resultados obtenidos, se obtendrá una
						// predicción
						generarPronosticos(resultados, pronosticos);

						// Comprobación de efectividad del sistema
						validacionEfectividadAlgoritmo(pronosticos);
					}
				}
			}
		}
	}

	private void validacionEfectividadAlgoritmo(
			final List<PronosticoPartido> pronosticos) throws Exception {
		Map<String, ValorResultado> resultadosCiertos = new HashMap<>();

		// primera: jornada jugada = 17
		resultadosCiertos.put(Equipo.P_ATHLETIC + "-" + Equipo.P_ZARAGOZA,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.P_ATLETICO + "-" + Equipo.P_CELTA,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.P_OSASUNA + "-" + Equipo.P_GRANADA,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.P_BETIS + "-" + Equipo.P_MALLORCA,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.P_VALLADOLID + "-" + Equipo.P_BARCELONA,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.P_ESPANYOL + "-" + Equipo.P_DEPORTIVO,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.P_REAL_SOCIEDAD + "-" + Equipo.P_SEVILLA,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.P_RAYO + "-" + Equipo.P_LEVANTE,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.P_MALAGA + "-" + Equipo.P_REAL_MADRID,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.P_VALENCIA + "-" + Equipo.P_GETAFE,
				ValorResultado.UNO);

		// segunda: jornada jugada = 19
		resultadosCiertos.put(Equipo.S_JEREZ + "-" + Equipo.S_VILLAREAL,
				ValorResultado.EQUIS);
		resultadosCiertos.put(Equipo.S_SABADELL + "-" + Equipo.S_LUGO,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.S_ALMERIA + "-" + Equipo.S_ALCORCON,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.S_RM_CASTILLA + "-" + Equipo.S_ELCHE,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.S_CORDOBA + "-" + Equipo.S_RECREATIVO,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.S_SPORTING + "-" + Equipo.S_HUESCA,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.S_PONFERRADINA + "-" + Equipo.S_MURCIA,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.S_GUADALAJARA + "-" + Equipo.S_RACING,
				ValorResultado.EQUIS);
		resultadosCiertos.put(Equipo.S_HERCULES + "-" + Equipo.S_NUMANCIA,
				ValorResultado.DOS);
		resultadosCiertos.put(Equipo.S_LAS_PALMAS + "-" + Equipo.S_MIRANDES,
				ValorResultado.UNO);
		resultadosCiertos.put(Equipo.S_BARCELONA_B + "-" + Equipo.S_GIRONA,
				ValorResultado.EQUIS);

		Integer totalUNOSCiertos = 0, totalDOSESCiertos = 0, totalEQUISCiertos = 0, totalCiertos = 0, totalPartidos = 0;
		Integer totalUNOSAcertados = 0, totalDOSESAcertados = 0, totalEQUISAcertados = 0, totalAcertados = 0;
		Double porcentajeUNOSAcertados = 0D, porcentajeDOSESAcertados = 0D, porcentajeEQUISAcertados = 0D, porcentajeTotalAcertados = 0D;

		for (Map.Entry<String, ValorResultado> entry : resultadosCiertos
				.entrySet()) {
			ValorResultado resultadoCierto = entry.getValue();
			totalCiertos++;
			if (resultadoCierto.equals(ValorResultado.UNO)) {
				totalUNOSCiertos++;
			} else if (resultadoCierto.equals(ValorResultado.EQUIS)) {
				totalEQUISCiertos++;
			} else if (resultadoCierto.equals(ValorResultado.DOS)) {
				totalDOSESCiertos++;
			} else {
				throw new Exception("Resultado no permitido");
			}
			for (PronosticoPartido pronostico : pronosticos) {
				if (pronostico.getPartido().getID().equals(entry.getKey())) {
					// Para cada pronóstico, se va a comprobar si coincide con
					// el real, y se van a guardar los datos.
					ValorResultado resultadoPronosticado = pronostico
							.getResultadoMasProbable();
					totalPartidos++;
					if (resultadoCierto.equals(resultadoPronosticado)) {
						totalAcertados++;
						if (resultadoPronosticado.equals(ValorResultado.UNO)) {
							totalUNOSAcertados++;
						} else if (resultadoPronosticado
								.equals(ValorResultado.EQUIS)) {
							totalEQUISAcertados++;
						} else if (resultadoPronosticado
								.equals(ValorResultado.DOS)) {
							totalDOSESAcertados++;
						} else {
							throw new Exception("Resultado no permitido");
						}
					}
				}
			}
		}

		porcentajeUNOSAcertados = totalUNOSCiertos == null ? Integer.valueOf(0)
				: (totalUNOSAcertados / Double.valueOf(totalUNOSCiertos));
		porcentajeEQUISAcertados = totalEQUISCiertos == null ? Integer
				.valueOf(0) : (totalEQUISAcertados / Double
				.valueOf(totalEQUISCiertos));
		porcentajeDOSESAcertados = totalDOSESCiertos == null ? Integer
				.valueOf(0) : (totalDOSESAcertados / Double
				.valueOf(totalDOSESCiertos));
		porcentajeTotalAcertados = totalCiertos == null ? Integer.valueOf(0)
				: (totalAcertados / Double.valueOf(totalCiertos));

		System.out.println("Análisis de rendimiento del algoritmo:");
		System.out.println("num_neuronas_hidden_layer: "
				+ NUM_NEURONAS_HIDDEN_LAYER + " learning_rate: "
				+ LEARNING_RATE + " momentum: " + MOMENTUM);
		System.out.println("porcentajeUNOSAcertados: "
				+ porcentajeUNOSAcertados);
		System.out.println("porcentajeEQUISAcertados: "
				+ porcentajeEQUISAcertados);
		System.out.println("porcentajeDOSESAcertados: "
				+ porcentajeDOSESAcertados);
		System.out.println("porcentajeTotalAcertados: "
				+ porcentajeTotalAcertados);
	}

	private void generarPronosticos(final Map<String, RealVector> resultados,
			List<PronosticoPartido> pronosticos) {
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
				}
			}
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
		final float AUMENTO_NEURONAS = 0.5F;
		Integer NUMERO_NEURONAS_HIDDEN_LAYER = Double.valueOf(
				Math.floor(AUMENTO_NEURONAS * (2 / 3D) * numeroParametros
						+ matrixTargets.getColumnDimension())).intValue();

		NUMERO_NEURONAS_HIDDEN_LAYER = NUM_NEURONAS_HIDDEN_LAYER;
		numberOfNeuronsInLayers.add(matrixInputs.getColumnDimension());
		numberOfNeuronsInLayers.add(NUMERO_NEURONAS_HIDDEN_LAYER);
		numberOfNeuronsInLayers.add(matrixTargets.getColumnDimension());
		MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(
				numberOfNeuronsInLayers);
		((SupervisedLearning) myMlPerceptron.getLearningRule())
				.setMaxError(0.1);
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
			rellenarFilaMatriz(parametros, partido, fila);

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
			final Integer numeroParametros) {

		System.out.println("Rellenando matrices de prediccion...");

		List<Partido> partidosAPredecir = extraerPartidos(pronosticos);
		Map<String, RealVector> mapInputsAPredecir = new HashMap<String, RealVector>();
		for (Partido partido : partidosAPredecir) {
			List<Parametro> parametros = partido.getParametros();
			RealVector fila = new ArrayRealVector(numeroParametros);

			rellenarFilaMatriz(parametros, partido, fila);
			mapInputsAPredecir.put(partido.getID(), fila);
		}

		System.out.println("Matrices de prediccion llenas.");
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
				throw new Exception(
						"No se pueden normalizar estos datos de la red neuronal, porque todos son iguales. Columna de matrixInputs col="
								+ col);
			}
			// Se normalizan todos sus valores
			for (int z = 0; z < columna.getDimension(); z++) {
				valor = 0 + (columna.getEntry(z) - minimo) / diferencia;
				columna.setEntry(z, valor);
			}
			// Se reemplaza la columna poniendo la columna normalizada
			matrixInputs.setColumnVector(col, columna);
		}

		System.out.println("Matriz normalizada.");

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
		// BackPropagation bp = (BackPropagation) event.getSource();
		// System.out.println("Iteración: " + bp.getCurrentIteration()
		// + ". Error total: " + bp.getTotalNetworkError());
	}

}
