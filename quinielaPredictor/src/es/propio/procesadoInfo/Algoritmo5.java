/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.ParametroNombre;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.PronosticoPartido;
import es.propio.modeladoInfo.Temporada;

/**
 * @author i3casa
 * 
 */
public class Algoritmo5 extends AbstractAlgoritmo {
	static final Logger logger = Logger.getLogger(Algoritmo5.class);

	private final Boolean ACTITUD_AGRESIVA = Boolean.TRUE;

	public Algoritmo5(final Temporada temporadaPrimera,
			final Temporada temporadaSegunda) {
		super();
		setId(IdAlgoritmoEnum.ALGORITMO5);
		setTemporadaPrimera(temporadaPrimera);
		setTemporadaSegunda(temporadaSegunda);
	}

	/**
	 * 1/2 PRIMERA.
	 * 
	 * @param pronostico
	 * @throws Exception
	 */
	private void calcularPronosticoPrimera(PronosticoPartido pronostico)
			throws Exception {
		porParametroDiscriminatorio(getTemporadaPrimera(),
				ParametroNombre.GOLESFUERAENCONTRA, Boolean.FALSE,
				pronostico, null);
	}

	/**
	 * EMPATES PRIMERA
	 */
	@Override
	public void calcularPronosticoPrimera() throws Exception {
		List<PronosticoPartido> lista = getEstimacionJornadaPrimera()
				.getPronosticoPartidos();
		for (PronosticoPartido pronostico : lista) {
			calcularPronosticoPrimera(pronostico);
		}
		if (ACTITUD_AGRESIVA)
			anadirEmpates(getEstimacionJornadaPrimera().getNumeroJornada(),
					getTemporadaPrimera(),
					ParametroNombre.DIFERENCIA_POSICIONES_EN_CLASIFICACION, 0,
					lista);
	}

	/**
	 * 1/2 SEGUNDA.
	 * 
	 * @param pronostico
	 * @throws Exception
	 */
	private void calcularPronosticoSegunda(PronosticoPartido pronostico)
			throws Exception {
		porParametroDiscriminatorio(getTemporadaSegunda(),
				ParametroNombre.GOLESFUERAAFAVOR, Boolean.TRUE, pronostico,
				null);
	}

	/**
	 * EMPATES SEGUNDA
	 */
	@Override
	public void calcularPronosticoSegunda() throws Exception {
		List<PronosticoPartido> lista = getEstimacionJornadaSegunda()
				.getPronosticoPartidos();
		for (PronosticoPartido pronostico : lista) {
			calcularPronosticoSegunda(pronostico);
		}
		if (ACTITUD_AGRESIVA)
			anadirEmpates(getEstimacionJornadaSegunda().getNumeroJornada(),
					getTemporadaSegunda(),
					ParametroNombre.DIFERENCIADEGOLESAFAVOR, 2, lista);
	}

	/**
	 * 
	 * @param temporada
	 * @param parametroDiscriminatorio
	 *            Parámetro de equipo.
	 * @param mayorEsMejor
	 * @param pronostico
	 * @throws Exception
	 */
	private void porParametroDiscriminatorio(final Temporada temporada,
			final ParametroNombre parametroDiscriminatorio,
			final Boolean mayorEsMejor, PronosticoPartido pronostico,
			final ParametroNombre segundoParametro) throws Exception {
		pronostico.reseteaPorcentajes();
		Equipo local = pronostico.getPartido().getEquipoLocal();
		Equipo visitante = pronostico.getPartido().getEquipoVisitante();
		Float valor1, valor2;
		if (segundoParametro == null
				|| segundoParametro.getNombre()
						.equals(ParametroNombre.INVALIDO)) {
			valor1 = Float.valueOf(local.getParametro(parametroDiscriminatorio)
					.getValor());
			valor2 = Float.valueOf(visitante.getParametro(
					parametroDiscriminatorio).getValor());
			if (valor1 <= valor2) {
				pronostico.setPorcentaje1(mayorEsMejor ? 0F : 1F);
			} else {
				pronostico.setPorcentaje2(mayorEsMejor ? 0F : 1F);
			}
		} else {
			Integer a = local.getParametro(parametroDiscriminatorio).getValor();
			Integer b = visitante.getParametro(parametroDiscriminatorio)
					.getValor();
			Integer c = local.getParametro(segundoParametro).getValor();
			Integer d = visitante.getParametro(segundoParametro).getValor();
			valor1 = (a - b) / Float.valueOf(Math.max(a, b));
			valor2 = (d - c) / Float.valueOf(Math.max(c, d));
			if (valor1 >= valor2) {
				pronostico.setPorcentaje1(1F);
			} else {
				pronostico.setPorcentaje2(1F);
			}
		}

	}

	/**
	 * 
	 * @param numeroJornada
	 * @param temporada
	 * @param parametroDiscriminatorio
	 *            Parámetro de partido.
	 * @param numeroEmpatesAMeter
	 * @param lista
	 * @throws Exception
	 */
	private void anadirEmpates(final Integer numeroJornada,
			final Temporada temporada,
			final ParametroNombre parametroDiscriminatorio,
			final Integer numeroEmpatesAMeter, List<PronosticoPartido> lista)
			throws Exception {
		// Asigno empates
		Jornada jornada = temporada.getJornadaExacta(numeroJornada);
		List<Partido> partidosOrdenados = new ArrayList<Partido>(
				jornada.getPartidos());
		Collections.sort(partidosOrdenados, new Comparator<Partido>() {
			public int compare(Partido one, Partido other) {
				int salida = 0;
				try {
					salida = one
							.getParametro(parametroDiscriminatorio)
							.getValor()
							.compareTo(
									other.getParametro(parametroDiscriminatorio)
											.getValor());
				} catch (Exception e) {
					System.out
							.println("ERROR AL COMPARAR DOS PARTIDOS POR PARÁMETRO");
				}
				return salida;
			}
		});
		// Partidos con empates
		for (Partido partido : partidosOrdenados.subList(1,
				1 + numeroEmpatesAMeter)) {
			for (PronosticoPartido pronosticoPartido : lista) {
				if (pronosticoPartido.getPartido().getID()
						.equals(partido.getID())) {
					pronosticoPartido.reseteaPorcentajes();
					pronosticoPartido.setPorcentajeX(1F);
				}
			}
		}
	}
}
