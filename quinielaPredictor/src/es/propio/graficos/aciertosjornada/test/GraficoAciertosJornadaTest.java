package es.propio.graficos.aciertosjornada.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.ui.RefineryUtilities;

import es.propio.graficos.aciertosjornada.EntradaAciertosJornadaDto;
import es.propio.graficos.aciertosjornada.GraficoAciertosJornada;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.procesadoInfo.AbstractAlgoritmo;
import es.propio.procesadoInfo.IdAlgoritmoEnum;
import es.propio.procesadoInfo.TipoDivisionEnum;

public class GraficoAciertosJornadaTest {

	public static void main(String[] args) throws Exception {

		List<PronosticoJornada> pronosticosJornadaBulk = generarPronosticosJornadaMock();

		EntradaAciertosJornadaDto inDto = new EntradaAciertosJornadaDto(
				pronosticosJornadaBulk);
		graficoNumAciertosVsJornada(inDto);

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

	private static List<PronosticoJornada> generarPronosticosJornadaMock() {

		List<PronosticoJornada> pronosticosAlgoritmo1 = generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.REAL);
		List<PronosticoJornada> pronosticosAlgoritmo2 = generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.ALGORITMO1);
		List<PronosticoJornada> pronosticosAlgoritmo3 = generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.ALGORTIMO2);
		List<PronosticoJornada> pronosticosAlgoritmo4 = generarPronosticosJornadaAlgoritmoMock(IdAlgoritmoEnum.WEB_QUINIELISTA);

		List<PronosticoJornada> pronosticosJornadaBulk = new ArrayList<PronosticoJornada>();
		pronosticosJornadaBulk.addAll(pronosticosAlgoritmo1);
		pronosticosJornadaBulk.addAll(pronosticosAlgoritmo2);
		pronosticosJornadaBulk.addAll(pronosticosAlgoritmo3);
		pronosticosJornadaBulk.addAll(pronosticosAlgoritmo4);
		return pronosticosJornadaBulk;
	}

	private static List<PronosticoJornada> generarPronosticosJornadaAlgoritmoMock(
			IdAlgoritmoEnum idAlgo) {
		List<PronosticoJornada> pjPrimeraySegunda = new ArrayList<PronosticoJornada>();
		pjPrimeraySegunda
				.addAll(generarPronosticosJornadaAlgoritmoDivisionMock(idAlgo,
						TipoDivisionEnum.PRIMERA));
		pjPrimeraySegunda
				.addAll(generarPronosticosJornadaAlgoritmoDivisionMock(idAlgo,
						TipoDivisionEnum.SEGUNDA));
		return pjPrimeraySegunda;
	}

	private static List<PronosticoJornada> generarPronosticosJornadaAlgoritmoDivisionMock(
			IdAlgoritmoEnum idAlgo, TipoDivisionEnum division) {
		// PRIMERA
		List<PronosticoJornada> pjPrimera = new ArrayList<PronosticoJornada>();
		List<Integer> numerosJornadaPrimera = generarNumJornadasMock(division);
		for (Integer numJornada : numerosJornadaPrimera) {
			pjPrimera.add(generarPronosticoJornadaMock(numJornada, idAlgo,
					division));
		}
		return pjPrimera;
	}

	private static List<Integer> generarNumJornadasMock(
			TipoDivisionEnum division) {
		List<Integer> numeros = new ArrayList<Integer>();
		int numJornadasMax = AbstractAlgoritmo
				.calcularNumerosJornadas(division);
		for (int i = 1; i <= numJornadasMax; i++) {
			numeros.add(Integer.valueOf(i));
		}
		return numeros;
	}

	private static PronosticoJornada generarPronosticoJornadaMock(
			Integer numJornada, IdAlgoritmoEnum idAlgo,
			TipoDivisionEnum division) {
		PronosticoJornada pj = new PronosticoJornada(numJornada, idAlgo);
		pj.setPronosticoPartidos(generarListaPronosticoPartidoMock(division));
		return pj;
	}

	private static List<PronosticoPartido> generarListaPronosticoPartidoMock(
			TipoDivisionEnum division) {
		List<PronosticoPartido> lista = new ArrayList<PronosticoPartido>();
		Integer numPartidos = AbstractAlgoritmo
				.calcularNumPartidosPorJornada(division);
		for (int i = 1; i <= numPartidos; i++) {
			lista.add(generarPronosticoPartidoMock(division, i));
		}
		return lista;
	}

	private static PronosticoPartido generarPronosticoPartidoMock(
			TipoDivisionEnum division, Integer numPartidoPosicion) {
		PronosticoPartido pp = new PronosticoPartido();
		pp.setPosicionPartido(numPartidoPosicion);

		Random random = new Random();
		pp.setPorcentaje1(random.nextFloat());
		pp.setPorcentaje12(random.nextFloat());
		pp.setPorcentaje1X(random.nextFloat());
		pp.setPorcentaje1X2(random.nextFloat());
		pp.setPorcentaje2(random.nextFloat());
		pp.setPorcentajeX(random.nextFloat());
		pp.setPorcentajeX2(random.nextFloat());
		pp.setPorcentaje12(random.nextFloat());

		return pp;
	}
}
