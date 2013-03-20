package es.propio.presentacionCalculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.ui.RefineryUtilities;

import es.propio.cargadorInfoWeb.CargadorInformacionWebResultados;
import es.propio.cargadorInfoWeb.CargadorWebNombresProximaQuiniela;
import es.propio.graficos.empatesjornada.GraficoEmpatesJornada;
import es.propio.modeladoInfo.Boleto;
import es.propio.modeladoInfo.Division;
import es.propio.modeladoInfo.GestorParametrosAnalisis;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.ParametrizadorPartido;
import es.propio.modeladoInfo.ParametroNombre;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;
import es.propio.modeladoInfo.TuplaParametrosAnalisis;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.Algoritmo5;

public class PrincipalCore {

	public PrincipalCore() {
	}

	public static void analizarParametrosEn4grupos() throws Exception {

		System.out.println("Numero jornadas previas consideradas Primera = "
				+ Principal.NUMERO_JORNADAS_A_CONSIDERAR_PRIMERA);
		System.out.println("Numero jornadas previas consideradas Segunda = "
				+ Principal.NUMERO_JORNADAS_A_CONSIDERAR_SEGUNDA);
		System.out.println("Numero empates primera fijado = "
				+ Principal.NUM_EMPATES_PRIMERA);
		System.out.println("Numero empates segunda fijado = "
				+ Principal.NUM_EMPATES_SEGUNDA);

		// PRIMERA 1/2
		System.out
				.println("\n\n\n ###############  PRIMERA 1/2 ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoIndividual()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 1);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=1/2 Division="
						+ Division.PRIMERA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// PRIMERA X
		System.out
				.println("\n\n\n ###############  PRIMERA X ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoComparativo()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 2);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=X Division="
						+ Division.PRIMERA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// SEGUNDA 1/2
		System.out
				.println("\n\n\n ###############  SEGUNDA 1/2 ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoIndividual()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 3);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=1/2 Division="
						+ Division.SEGUNDA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

		// SEGUNDA X
		System.out
				.println("\n\n\n ###############  SEGUNDA X  ############### ");
		for (ParametroNombre parametroNombre : GestorParametrosAnalisis
				.getParamsTipoComparativo()) {
			try {
				TuplaParametrosAnalisis tupla = GestorParametrosAnalisis
						.getTupla(parametroNombre, 4);
				ejecucion(tupla, false);
			} catch (Exception ex) {
				System.out.println("ERROR TIPO=X Division="
						+ Division.SEGUNDA.toString() + " Parametro="
						+ parametroNombre);
			}
		}

	}

	/**
	 * Averigua el numero de empates
	 * 
	 * @throws Exception
	 */
	public static void analizarNumeroDeEmpates() throws Exception {

		System.out
				.println("Pintando grafica de numero de empates (eje Y) vs numero jornada (eje X)");

		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				Principal.MODO_MOCK);
		cargador.cargar();

		List<Jornada> jornadasPasadasPrimera = cargador.getTemporadaPrimera()
				.getJornadasPasadas();
		List<Jornada> jornadasPasadasSegunda = cargador.getTemporadaSegunda()
				.getJornadasPasadas();

		HashMap<Integer, Double> numEmpatesPorJornadaPrimera = new HashMap<Integer, Double>();
		HashMap<Integer, Double> numEmpatesPorJornadaSegunda = new HashMap<Integer, Double>();

		int i = 0;
		for (Jornada jornada : jornadasPasadasPrimera) {
			i++;
			jornada.calcularEstadisticas();
			numEmpatesPorJornadaPrimera.put(Integer.valueOf(i),
					Double.valueOf(jornada.getEquises()));
		}

		int j = 0;
		for (Jornada jornada : jornadasPasadasSegunda) {
			j++;
			jornada.calcularEstadisticas();
			numEmpatesPorJornadaSegunda.put(Integer.valueOf(j),
					Double.valueOf(jornada.getEquises()));
		}

		GraficoEmpatesJornada grafico1 = new GraficoEmpatesJornada(
				"GRAFICO Num empates vs. Jornada PRIMERA",
				numEmpatesPorJornadaPrimera);
		grafico1.pack();
		RefineryUtilities.centerFrameOnScreen(grafico1);
		grafico1.setVisible(true);

		GraficoEmpatesJornada grafico2 = new GraficoEmpatesJornada(
				"GRAFICO Num empates vs. Jornada SEGUNDA",
				numEmpatesPorJornadaSegunda);
		grafico2.pack();
		RefineryUtilities.centerFrameOnScreen(grafico2);
		grafico2.setVisible(true);
	}

	public static void ejecucion(TuplaParametrosAnalisis tupla,
			boolean graficosVisibles) throws Exception {

		// Relleno el universo Temporada
		CargadorInformacionWebResultados cargador = new CargadorInformacionWebResultados(
				Principal.MODO_MOCK);
		cargador.cargar();
		Temporada temporadaPrimera = cargador.getTemporadaPrimera();
		Temporada temporadaSegunda = cargador.getTemporadaSegunda();

		// Parametrizador
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaPrimera());
		ParametrizadorPartido.cargarParametrosDePartidos(cargador
				.getTemporadaSegunda());

		// System.out
		// .println("PASADO: Comparando algoritmos con datos pasados...");
		List<AbstractAlgoritmo> algoritmosUsados = new ArrayList<AbstractAlgoritmo>();
		// algoritmosUsados
		// .add(new Algoritmo1(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo2(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo3(temporadaPrimera, temporadaSegunda));
		// algoritmosUsados
		// .add(new Algoritmo4(temporadaPrimera, temporadaSegunda));
		algoritmosUsados.add(new Algoritmo5(temporadaPrimera, temporadaSegunda,
				tupla));

		AnalizadorDelPasado.estudiarJornadasPasadas(algoritmosUsados,
				temporadaPrimera, temporadaSegunda, graficosVisibles);

		// System.out.println("FUTURO: PREDICCION DEL BOLETO ACTUAL...");
		// PredictorDelFuturo.analizarJornadaActual(temporadaPrimera,
		// temporadaSegunda);

	}

	public static List<PronosticoPartido> obtenerPartidos(Division division,
			String numeroBoleto) throws Exception {

		// Carga de boleto
		CargadorWebNombresProximaQuiniela cargadorBoleto = new CargadorWebNombresProximaQuiniela();
		cargadorBoleto.cargar(numeroBoleto);

		// Traspaso de partidos a listas para su predicción
		List<PronosticoPartido> partidos = new ArrayList<PronosticoPartido>();
		PronosticoPartido pronostico;
		Partido partido;
		Boleto boleto = cargadorBoleto.getBoleto();
		for (Map.Entry<Integer, Partido> entry : boleto.getPartidos()
				.entrySet()) {
			partido = entry.getValue();
			pronostico = new PronosticoPartido();
			pronostico.setPosicionPartido(entry.getKey());
			pronostico.setPartido(partido);
			if (partido.getEquipoLocal().getDivision().equals(division)) {
				partidos.add(pronostico);
			}
		}

		return partidos;
	}

}
