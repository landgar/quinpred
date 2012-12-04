/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import es.propio.cargadorInfoWeb.CargadorWebyXMLPronosticoQuinielista;
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
public class Algoritmo1 extends AbstractAlgoritmo{
	static final Logger logger = Logger.getLogger(Algoritmo1.class);

	public static <T extends Comparable<? super T>> List<T> asSortedList(
			Collection<T> c) {
		List<T> list = new ArrayList<T>(c);
		java.util.Collections.sort(list);
		return list;
	}

	/**
	 * Algoritmo:
	 * 
	 * Se toman los pron�sticos de cada jornada. Para cada pron�stico de cada
	 * partido de cada jornada, se multiplica el pron�stico por el peso relativo
	 * de 1, X, 2 que ha tenido ese equipo a lo largo de las jornadas
	 * anteriores.
	 * 
	 */
	public static Set<PronosticoJornada> calcularPronosticos() throws Exception {

		// TODO Cargar los resultados reales de la WEB, no de un fichero
		Set<PronosticoJornada> pronosticos = CargadorDatosPronosticos
				.cargarPronosticosJornadas();

		Temporada temporada = new Temporada();
		temporada.setJornadas(CombinadorInfoJornadas.obtenerTodaInfoJornadas());
		for (PronosticoJornada pronosticoJornada : pronosticos) {
			Integer numeroJornada = pronosticoJornada.getNumeroJornada();
			logger.info("Pron�stico de la jornada: " + numeroJornada);
			Jornada jornadaAPredecir = null;
			for (Jornada jornada : temporada.getJornadas()) {
				if (jornada != null && jornada.getNumeroJornada() != null) {
					logger.debug("Tenemos informaci�n de la jornada: "
							+ jornada.getNumeroJornada());
					if (jornada.getNumeroJornada().equals(numeroJornada)) {
						jornadaAPredecir = jornada;
					}
				}
			}

			if (pronosticoJornada != null) {
				for (PronosticoPartido pronosticoPartido : pronosticoJornada
						.getPronosticoPartidos()) {

					if (jornadaAPredecir != null) {

//						for (Partido partido : jornadaAPredecir.getPartidos()) {
//							if (pronosticoPartido.getPosicionPartido().equals(
//									partido.getPosicion())) {
//								System.out.println("Posici�n del partido: "
//										+ pronosticoPartido
//												.getPosicionPartido());
//								// Se busca qu� equipo es el local del partido
//								// actual
//								pronosticoPartido.setLocal(partido
//										.getEquipoLocal());
//
//								// Se busca qu� equipo es el visitante del
//								// partido
//								// actual
//								pronosticoPartido.setVisitante(partido
//										.getEquipoVisitante());
//								logger.info(pronosticoPartido.getLocal()
//										.getNombre()
//										+ " - "
//										+ pronosticoPartido.getVisitante()
//												.getNombre());
//
//								// Para cada equipo, se buscar�n sus resultados
//								// relativos (pesos)
//								partido.getEquipoLocal().pesosRelativos(
//										temporada);
//								partido.getEquipoVisitante().pesosRelativos(
//										temporada);
//
//								// Se multiplicar� cada predicci�n por los
//								// pesos.
//								reestimacionPronostico(pronosticoPartido,
//										partido.getEquipoLocal(),
//										partido.getEquipoVisitante());
//								if (pronosticoPartido.getPorcentaje1() > pronosticoPartido
//										.getPorcentajeX()) {
//									if (pronosticoPartido.getPorcentaje1() > pronosticoPartido
//											.getPorcentaje2()) {
//										System.out.println("Predicci�n: 1");
//									} else {
//										System.out.println("Predicci�n: 2");
//									}
//								} else {
//									System.out.println("Predicci�n: X");
//								}
//								System.out
//										.println("----------------------------------");
//							}
//						}
					}
				}
			}
		}
		return pronosticos;
	}

	/**
	 * 
	 * FIXME: esto es una falacia: que un equipo haya empatado antes, no quiere
	 * decir que haya m�s probabilidad de que empate ahora.
	 * 
	 * Se pondera el pron�stico en funci�n de las estad�sticas. Se cruzan las
	 * valores, de manera, que si:
	 * 
	 * El local (Barcelona) ten�a 70% de pron�stico, pero hab�a ganado todos los
	 * anteriores, entonces lo multiplico por pesoGanadosL (ser� 1.0), y al
	 * visitante se le multiplicar� por pesoPerdidosL (ser� 0.0). Con el
	 * visitante, al rev�s. Y los empates, directamente multiplicados (si alguno
	 * nunca ha empatado, no habr� empate (FIXME: falacia)).
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

	@Override
	void calcularPronosticoPrimera() throws Exception {
		CargadorWebyXMLPronosticoQuinielista cargador = new CargadorWebyXMLPronosticoQuinielista();
		PronosticoJornada pronosticoJornada = cargador.ejecutar();
		List<PronosticoPartido> pronosticos=new ArrayList<PronosticoPartido>();
		for(int i=1;i<=15;i++){
			PronosticoPartido pronostico=new PronosticoPartido();
			pronostico.setLocal(new Equipo("Celta"));
			pronostico.setVisitante(new Equipo("Malaga"));
			pronostico.setPosicionPartido(i);
			pronostico.setPorcentaje1(1F);
			pronostico.setPorcentajeX(0F);
			pronostico.setPorcentaje2(0F);
			pronosticos.add(pronostico);
		}
		pronosticoJornada.setPronosticoPartidos(pronosticoJornada.getPronosticoPartidos());
		setEstimacionJornadaPrimera(pronosticoJornada);
	}

	@Override
	void calcularPronosticoSegunda() throws Exception {
		PronosticoJornada pronosticoJornada=new PronosticoJornada(1, IdAlgoritmoEnum.ALGORTIMO2);
		List<PronosticoPartido> pronosticos=new ArrayList<PronosticoPartido>();
		for(int i=1;i<=15;i++){
			PronosticoPartido pronostico=new PronosticoPartido();
			pronostico.setLocal(new Equipo("Xerez"));
			pronostico.setVisitante(new Equipo("Hercules"));
			pronostico.setPosicionPartido(i);
			pronostico.setPorcentaje1(1F);
			pronostico.setPorcentajeX(0F);
			pronostico.setPorcentaje2(0F);
			pronosticos.add(pronostico);
		}
		pronosticoJornada.setPronosticoPartidos(pronosticos);
		setEstimacionJornadaPrimera(pronosticoJornada);
		
	}


}
