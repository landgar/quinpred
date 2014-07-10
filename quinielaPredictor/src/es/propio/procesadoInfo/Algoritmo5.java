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
import es.propio.modeladoInfo.TuplaParametrosAnalisis;

/**
 * @author i3casa
 * 
 */
public class Algoritmo5 extends AbstractAlgoritmo {
	static final Logger logger = Logger.getLogger(Algoritmo5.class);

	private final Boolean ACTITUD_AGRESIVA = Boolean.TRUE;

	private final TuplaParametrosAnalisis parametrosAnalisis;

	public Algoritmo5(final Temporada temporadaPrimera,
			final Temporada temporadaSegunda,
			final TuplaParametrosAnalisis parametrosAnalisis) {
		super();
		setId(IdAlgoritmoEnum.ALGORITMO5);
		setTemporadaPrimera(temporadaPrimera);
		setTemporadaSegunda(temporadaSegunda);
		this.parametrosAnalisis = parametrosAnalisis;

		System.out.println("Algoritmo5 --> " + parametrosAnalisis.toString());
	}

	/**
	 * 1/2 PRIMERA.
	 * 
	 * @param pronostico
	 * @throws Exception
	 */
	private void calcularPronosticoPrimera(PronosticoPartido pronostico)
			throws Exception {
		porParametroDiscriminatorio(parametrosAnalisis.getParamPrimera12(),
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
					parametrosAnalisis.getParamPrimeraEmpates(),
					parametrosAnalisis.getNumEmpatesPrimera(), lista);
	}

	/**
	 * 1/2 SEGUNDA.
	 * 
	 * @param pronostico
	 * @throws Exception
	 */
	private void calcularPronosticoSegunda(PronosticoPartido pronostico)
			throws Exception {
		porParametroDiscriminatorio(parametrosAnalisis.getParamSegunda12(),
				pronostico, null);
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
					parametrosAnalisis.getParamSegundaEmpates(),
					parametrosAnalisis.getNumEmpatesSegunda(), lista);
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
	private void porParametroDiscriminatorio(
			final ParametroNombre parametroDiscriminatorio,
			PronosticoPartido pronostico, final ParametroNombre segundoParametro)
			throws Exception {
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
				pronostico
						.setPorcentaje1(parametroDiscriminatorio.isPositivo() ? 0F
								: 1F);
				pronostico
						.setPorcentaje2(parametroDiscriminatorio.isPositivo() ? 1F
								: 0F);
			} else {
				pronostico
						.setPorcentaje2(parametroDiscriminatorio.isPositivo() ? 0F
								: 1F);
				pronostico
						.setPorcentaje1(parametroDiscriminatorio.isPositivo() ? 1F
								: 0F);
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
		final Integer numeroJornadaAux = jornada.getNumeroJornada();
		Collections.sort(partidosOrdenados, new Comparator<Partido>() {
			@Override
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
					System.out.println("ERROR en la jornada "
							+ numeroJornadaAux + " al comparar los partidos "
							+ one.getEquipoLocal().getNombre() + " - "
							+ one.getEquipoVisitante().getNombre() + " y "
							+ other.getEquipoLocal().getNombre() + " - "
							+ other.getEquipoVisitante().getNombre()
							+ " por el parámetro: " + parametroDiscriminatorio);
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

	/**
	 * @return the parametrosAnalisis
	 */
	public TuplaParametrosAnalisis getParametrosAnalisis() {
		return parametrosAnalisis;
	}
}
