package es.propio.presentacionCalculo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import es.propio.cargadorInfoWeb.CargadorWebyXMLPronosticoQuinielista;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.procesadoInfo.Algoritmo1;
import es.propio.procesadoInfo.IdAlgoritmoEnum;

public class PredictorDelFuturo {

	static Algoritmo1 algo1;

	public PredictorDelFuturo() {
	}

	/**
	 * En base a las jornadas pasadas (y el algoritmo ya elegido), pinta la
	 * quiniela que tendríamos que rellenar a papel en la JORNADA ACTUAL, para
	 * cada algoritmo y pronosticos que leemos en webs externas.
	 * 
	 * @return Numero de boleto actua (segun la web quinielista)
	 * @throws Exception
	 */
	public static void analizarJornadaActual(Temporada temporadaPrimera,
			Temporada temporadaSegunda) throws Exception {

		System.out
				.println("Pintando quinielas previstas para jornada actual, segun varios algoritmos y webs");

		// ALGORITMOS factory
		if (algo1 == null) {
			algo1 = new Algoritmo1(temporadaPrimera, temporadaSegunda);
		}

		// WEB QUINIELISTA
		mostrarPrediccionesQuinielistaWeb();

		// NUESTRAS PREDICCIONES
		predecirResultadosPrimera(temporadaPrimera.getNumeroJornadaActual()
				.toString());
		predecirResultadosSegunda(temporadaSegunda.getNumeroJornadaActual()
				.toString());

	}

	private static Integer mostrarPrediccionesQuinielistaWeb()
			throws IOException, SAXException {
		System.out
				.println("Obteniendo pronosticos de WEB QUINIELISTA.COM para Boleto actual (tarda unos 10 segundos)...");

		CargadorWebyXMLPronosticoQuinielista cargador = new CargadorWebyXMLPronosticoQuinielista();
		PronosticoJornada pronosticoJornada = cargador.ejecutar();
		pronosticoJornada.pintarme();

		return cargador.getNumeroBoletoSegunQuinielista();

	}

	private static void predecirResultadosPrimera(String numBoleto)
			throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();

		List<PronosticoPartido> partidos = Principal.obtenerPartidos(
				Division.PRIMERA, numBoleto);

		// ALGORITMO 1
		algo1.setEstimacionJornadaPrimera(new PronosticoJornada(partidos,
				Integer.valueOf(numBoleto), IdAlgoritmoEnum.ALGORITMO1));
		algo1.calcularPronosticoPrimera();
		pronosticosJornadas.add(algo1.getEstimacionJornadaPrimera());

		// TODO ALGORITMO 2
		// rellenar con todos los algoritmos, para PRIMERA
	}

	private static void predecirResultadosSegunda(String numBoleto)
			throws Exception {

		List<PronosticoJornada> pronosticosJornadas = new ArrayList<PronosticoJornada>();

		List<PronosticoPartido> partidos = Principal.obtenerPartidos(
				Division.SEGUNDA, numBoleto);

		// ALGORITMO 1
		algo1.setEstimacionJornadaSegunda(new PronosticoJornada(partidos,
				Integer.valueOf(numBoleto), IdAlgoritmoEnum.ALGORITMO1));
		algo1.calcularPronosticoSegunda();
		pronosticosJornadas.add(algo1.getEstimacionJornadaSegunda());

		// TODO ALGORITMO 2
		// rellenar con todos los algoritmos, para SEGUNDA
	}

}
