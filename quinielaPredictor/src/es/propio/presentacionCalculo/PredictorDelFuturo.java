package es.propio.presentacionCalculo;

import es.propio.cargadorInfoWeb.CargadorWebyXMLPronosticoQuinielista;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.procesadoInfo.Algoritmo1;

public class PredictorDelFuturo {

	public PredictorDelFuturo() {
	}

	/**
	 * En base a las jornadas pasadas (y el algoritmo ya elegido), pinta la
	 * quiniela que tendríamos que rellenar a papel en la JORNADA ACTUAL, para
	 * cada algoritmo y pronosticos que leemos en webs externas.
	 * 
	 * @throws Exception
	 */
	public static void analizarJornadaActual() throws Exception {

		System.out
				.println("Pintando quinielas previstas para jornada actual, segun varios algoritmos y webs");
		// TODO Modulo para que nos ayude a rellenar la quiniela en papel, en
		// función del algoritmo que queramos usar.
		Algoritmo1.calcularPronosticos();

		// System.out
		// .println("Pintando GRAFICOS para comparar los algoritmos ...");
		// String title = "-- pruebas de graficos --";
		// GraficosManager demo = new GraficosManager(title, title);
		// demo.pack();
		// RefineryUtilities.centerFrameOnScreen(demo);
		// demo.setVisible(true);

		System.out
				.println("Obteniendo pronosticos de WEB QUINIELISTA.COM para Jornada actual (tarda unos 10 segundos)...");
		CargadorWebyXMLPronosticoQuinielista cargador = new CargadorWebyXMLPronosticoQuinielista();
		PronosticoJornada pronosticoJornada = cargador.ejecutar();
		pronosticoJornada.pintarme();

	}

}
