package es.propio.graficos.aciertosjornada.test;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;

public class GraficoAciertosJornadaTest {

	public static void main(String[] args) throws Exception {
		// EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
		// generarPronosticosJornadaMock(),
		// generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.REAL));
		// graficoNumAciertosVsJornada(inDto);
	}

	private static void graficoNumAciertosVsJornada(
			EntradaAciertosJornadaDto inDto) {
		String title = "Comparación de algoritmos según resultados pasados";
		GraficoAciertosJornada grafico = new GraficoAciertosJornada(
				"GRAFICO Num aciertos vs. Jornada", title, inDto);
		grafico.pack();
		RefineryUtilities.centerFrameOnScreen(grafico);
		grafico.setVisible(true);
	}

	// private static List<PronosticoJornada> generarPronosticosJornadaMock() {
	//
	// List<PronosticoJornada> pronosticosAlgoritmo1 =
	// generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.ALGORITMO1);
	// List<PronosticoJornada> pronosticosAlgoritmoQuinielista =
	// generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.WEB_QUINIELISTA);
	//
	// List<PronosticoJornada> pronosticosJornadaBulk = new
	// ArrayList<PronosticoJornada>();
	// pronosticosJornadaBulk.addAll(pronosticosAlgoritmo1);
	// pronosticosJornadaBulk.addAll(pronosticosAlgoritmoQuinielista);
	// return pronosticosJornadaBulk;
	// }
	//
	// private static List<PronosticoJornada>
	// generarPronosticosJornadaAlgoritmoMock(
	// IdAlgoritmoEnum idAlgo) {
	// List<PronosticoJornada> pjPrimeraySegunda = new
	// ArrayList<PronosticoJornada>();
	// pjPrimeraySegunda
	// .addAll(generarPronosticosJornadaAlgoritmoDivisionMock(idAlgo,
	// Division.PRIMERA));
	// pjPrimeraySegunda
	// .addAll(generarPronosticosJornadaAlgoritmoDivisionMock(idAlgo,
	// Division.SEGUNDA));
	// return pjPrimeraySegunda;
	// }
	//
	// public static List<PronosticoJornada>
	// generarPronosticosJornadaAlgoritmoDivisionMock(
	// final IdAlgoritmoEnum idAlgo, final Temporada temporada) {
	// // PRIMERA
	// List<PronosticoJornada> pjPrimera = new ArrayList<PronosticoJornada>();
	// List<Integer> numerosJornadaPrimera = generarNumJornadasMock(temporada
	// .getDivision());
	// for (Integer numJornada : numerosJornadaPrimera) {
	// pjPrimera.add(generarPronosticoJornadaMock(numJornada, idAlgo,
	// temporada));
	// }
	// return pjPrimera;
	// }
	//
	// private static List<Integer> generarNumJornadasMock(Division division) {
	// List<Integer> numeros = new ArrayList<Integer>();
	// int numJornadasMax = AbstractAlgoritmo
	// .calcularNumerosJornadas(division);
	// for (int i = 1; i <= numJornadasMax; i++) {
	// numeros.add(Integer.valueOf(i));
	// }
	// return numeros;
	// }
	//
	// private static PronosticoJornada generarPronosticoJornadaMock(
	// final Integer numJornada, final IdAlgoritmoEnum idAlgo,
	// final Temporada temporada) {
	// PronosticoJornada pj = new PronosticoJornada(numJornada, idAlgo);
	// pj.setPronosticoPartidos(generarListaPronosticoPartidoMock(division));
	// return pj;
	// }
	//
	// private static List<PronosticoPartido> generarListaPronosticoPartidoMock(
	// Division division) {
	// List<PronosticoPartido> lista = new ArrayList<PronosticoPartido>();
	// Integer numPartidos = AbstractAlgoritmo
	// .calcularNumPartidosPorJornada(division);
	// for (int i = 1; i <= numPartidos; i++) {
	// lista.add(generarPronosticoPartidoMock(division, i));
	// }
	// return lista;
	// }
	//
	// private static PronosticoPartido generarPronosticoPartidoMock(
	// Division division, Integer numPartidoPosicion) {
	// PronosticoPartido pp = new PronosticoPartido();
	// pp.setPosicionPartido(numPartidoPosicion);
	//
	// pp.setPorcentaje1(1F);
	// pp.setPorcentaje12(0F);
	// pp.setPorcentaje1X(0F);
	// pp.setPorcentaje1X2(0F);
	// pp.setPorcentaje2(0F);
	// pp.setPorcentajeX(0F);
	// pp.setPorcentajeX2(0F);
	// pp.setPorcentaje12(0F);
	//
	// return pp;
	// }
}
