/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import es.propio.handlerDatos.CargadorDatosPronosticos;
import es.propio.handlerDatos.CombinadorInfoJornadas;
import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class Algoritmo1_1 {

	public static <T extends Comparable<? super T>> List<T> asSortedList(
			Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

	/**
	 * Algoritmo:
	 * 
	 * Se toman los pronósticos de cada jornada. Para cada pronóstico de cada
	 * partido de cada jornada, se multiplica el pronóstico por el peso relativo
	 * de 1, X, 2 que ha tenido ese equipo a lo largo de las jornadas
	 * anteriores.
	 * 
	 */
	public static Set<PronosticoJornada> calcularPronosticos() throws Exception {
		Set<PronosticoJornada> pronosticos = CargadorDatosPronosticos
				.cargarPronosticosJornadas();
		Temporada temporada = new Temporada();
		temporada.setJornadas(CombinadorInfoJornadas.obtenerTodaInfoJornadas());
		for (PronosticoJornada pronosticoJornada : pronosticos) {
			Integer numeroJornada = pronosticoJornada.getNumeroJornada();
			System.out.println("Pronóstico de la jornada: " + numeroJornada);
			Jornada jornadaAPredecir = null;
			for (Jornada jornada : temporada.getJornadas()) {
				if (jornada != null && jornada.getNumeroJornada() != null) {
					if (jornada.getNumeroJornada().equals(numeroJornada)) {
						jornadaAPredecir = jornada;
					}
				}
			}
			for (PronosticoPartido pronosticoPartido : pronosticoJornada
					.getPronosticoPartidos()) {
				for (Partido partido : jornadaAPredecir.getPartidos()) {
					if (pronosticoPartido.getPosicionPartido().equals(
							partido.getPosicion())) {
						System.out.println("Posición del partido: "
								+ pronosticoPartido.getPosicionPartido());
						// Se busca qué equipo es el local del partido actual
						pronosticoPartido.setLocal(partido.getEquipoLocal());
						System.out.println("Local: "
								+ pronosticoPartido.getLocal().getValor());
						// Se busca qué equipo es el visitante del partido
						// actual
						pronosticoPartido.setVisitante(partido
								.getEquipoVisitante());
						System.out.println("Visitante: "
								+ pronosticoPartido.getVisitante().getValor());

						// Para cada equipo, se buscarán sus resultados
						// relativos (pesos)
						partido.getEquipoLocal().pesosRelativos(temporada);
						partido.getEquipoVisitante().pesosRelativos(temporada);

						// Se multiplicará cada predicción por los pesos.
						reestimacionPronostico(pronosticoPartido,
								partido.getEquipoLocal(),
								partido.getEquipoVisitante());
						if (pronosticoPartido.getPorcentaje1() > pronosticoPartido
								.getPorcentajeX()) {
							if (pronosticoPartido.getPorcentaje1() > pronosticoPartido
									.getPorcentaje2()) {
								System.out.println("Predicción: 1");
							} else {
								System.out.println("Predicción: 2");
							}
						} else {
							System.out.println("Predicción: X");
						}
						System.out
								.println("----------------------------------");
					}
				}
			}
		}
		return pronosticos;
	}

	/**
	 * 
	 * FIXME: esto es una falacia: que un equipo haya empatado antes, no quiere
	 * decir que haya más probabilidad de que empate ahora.
	 * 
	 * Se pondera el pronóstico en función de las estadísticas. Se cruzan las
	 * valores, de manera, que si:
	 * 
	 * El local (Barcelona) tenía 70% de pronóstico, pero había ganado todos los
	 * anteriores, entonces lo multiplico por pesoGanadosL (será 1.0), y al
	 * visitante se le multiplicará por pesoPerdidosL (será 0.0). Con el
	 * visitante, al revés. Y los empates, directamente multiplicados (si alguno
	 * nunca ha empatado, no habrá empate (FIXME: falacia)).
	 * 
	 * @param pronostico
	 * @param pesoGanadosL
	 * @param pesoEmpatadosL
	 * @param pesoPerdidosL
	 * @param pesoGanadosV
	 * @param pesoEmpatadosV
	 * @param pesoPerdidosV
	 */
	private static void reestimacionPronostico(PronosticoPartido pronostico,
			final Equipo local, final Equipo visitante) {
		pronostico.setPorcentaje1(pronostico.getPorcentaje1()
				* local.getPesoGanados() * visitante.getPesoPerdidos());
		pronostico.setPorcentajeX(pronostico.getPorcentajeX()
				* local.getPesoEmpatados() * visitante.getPesoEmpatados());
		pronostico.setPorcentaje2(pronostico.getPorcentaje2()
				* local.getPesoPerdidos() * visitante.getPesoGanados());
	}

}
