/**
 * 
 */
package es.propio.procesadoInfo;

import java.text.DecimalFormat;
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
				valor = 0 + (columna.getEntry(z) - minimo) / (maximo - minimo);
				columna.setEntry(z, valor);
			}
			matrixInputs.setColumnVector(i, columna);
		}

		// 2º. Pintado en el log para meterlo en Neuroph studio y ver qué red es
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
	}

	/**
	 */
	@Override
	public void calcularPronosticoSegunda() throws Exception {
	}

}
