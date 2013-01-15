/**
 * 
 */
package es.propio.cargadorInfoWeb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.ParametroEquipo;
import es.propio.modeladoInfo.ParametroNombre;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.Temporada;

/**
 * Manejador de los Parametros avanzados de Primera que viene en un HTML.
 * 
 * @author carlos.andres
 * 
 */
public class HandlerHtmlParamPrimera {

	private static final String CSS_TABLA_BUSCADA = "ordenacioncss_listado_equipos";

	/**
	 * Singleton
	 */
	static HandlerHtmlParamPrimera instancia;

	private final String webStr;
	private final ParametroNombre tipoParametro;

	public HandlerHtmlParamPrimera(String webStr, ParametroNombre tipoParametro) {
		super();
		this.webStr = webStr;
		this.tipoParametro = tipoParametro;
	}

	public static HandlerHtmlParamPrimera build(String webStr,
			ParametroNombre tipoParametro) {
		instancia = new HandlerHtmlParamPrimera(webStr, tipoParametro);
		return instancia;
	}

	/**
	 * Rellena los parametros avanzados de los equipos de primera en las
	 * jornadas actual y ¡¡ anteriores !! (DECISION TOMADA: duplicando los de la
	 * jornada actual)
	 * 
	 * @param temporadaPrimera
	 * @param numJornadaActualPrimera
	 *            Numero de jornada actual en primera
	 */
	public void cargarParamsAvanzados(Temporada temporadaPrimera,
			int numJornadaActualPrimera) {

		List<ParametroAvanzadoPrimeraHtml> parametrosHtmlPrimera = new ArrayList<ParametroAvanzadoPrimeraHtml>();

		// if (webStr.isEmpty()) {
		// // Parametro numero de jornada
		// ParametroAvanzadoPrimeraHtml param = new
		// ParametroAvanzadoPrimeraHtml();
		// param.setNumJornada(numJornadaActualPrimera);
		// parametrosHtmlPrimera.add(param);
		//
		// } else {
		Document docPrimera = Jsoup.parse(webStr);
		parametrosHtmlPrimera.addAll(parsearDoc(docPrimera));
		// }

		// Params avanzados de actual y anteriores
		rellenarTemporadaActualyAnteriores(temporadaPrimera,
				numJornadaActualPrimera, parametrosHtmlPrimera);
	}

	/**
	 * Parsea HTML con la herramienta JSOUP
	 * 
	 * @param doc
	 */
	private List<ParametroAvanzadoPrimeraHtml> parsearDoc(Document doc) {

		List<ParametroAvanzadoPrimeraHtml> parametros = new ArrayList<ParametroAvanzadoPrimeraHtml>();

		Element tabla = doc.getElementById(CSS_TABLA_BUSCADA);

		if (tabla == null) {
			System.err
					.println("ERROR al parsear HTML a Java: no encuentra la tabla de clasificacion");
		} else {

			// EXCLUYA LA HEADER thead. Coge los tr
			Elements tableRowElements = tabla.select(":not(thead) tr");

			for (Element row : tableRowElements) {
				parametros.add(crearParamAvanzado(row));
			}

		}

		return parametros;
	}

	public ParametroAvanzadoPrimeraHtml crearParamAvanzado(Element row) {
		String nombreEquipo, total, media;
		ParametroAvanzadoPrimeraHtml param = new ParametroAvanzadoPrimeraHtml();
		Elements campos = row.getAllElements();
		nombreEquipo = campos.get(1).text();
		total = campos.get(4).text();
		media = campos.get(6).text();

		if (media.contains(",")) {
			media = media.replace(",", ".");
		}

		param.setNombreEquipo(ConversorElPais
				.conversionNombreEquipos(nombreEquipo));

		if (tipoParametro.equals(ParametroNombre.REMATES_EN_CONTRA)) {
			param.setRematesContra(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.TARJETAS_AMARILLAS)) {
			param.setTarjetasAmarillas(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.TARJETAS_ROJAS)) {
			param.setTarjetasRojas(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.JUGADORES_UTILIZADOS)) {
			param.setJugadoresUtilizados(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.PARADAS_DEL_PORTERO)) {
			param.setParadasPortero(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_BALON_PARADO)) {
			param.setAfBalonParado(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_CABEZA)) {
			param.setAfCabeza(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_FALTA_DIRECTA)) {
			param.setAfFaltaDirecta(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_JUGADA_COLECTIVA)) {
			param.setAfJugadaColectiva(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_JUGADA_INDIVIDUAL)) {
			param.setAfJugadaIndividual(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_PENALTY)) {
			param.setAfPenalty(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_PIE_IZQUIERDO)) {
			param.setAfPieIzquierdo(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.AF_PIE_DERECHO)) {
			param.setAfPieDerecho(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.EN_CONTRA)) {
			param.setEnContra(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.GC_PENALTY)) {
			param.setGcPenalty(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.GOLES_TITULAR)) {
			param.setGolesTitular(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.GOLES_SUPLENTES)) {
			param.setGolesSuplentes(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.REMATES_FAVOR)) {
			param.setRematesFavor(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_BALON_PARADO)) {
			param.setRfBalonParado(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_CABEZA)) {
			param.setRfCabeza(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_FUERA)) {
			param.setRfFuera(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_JUGADA_COLECTIVA)) {
			param.setRfJugadaColectiva(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_JUGADA_INDIVIDUAL)) {
			param.setRfJugadaIndividual(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_PENALTY)) {
			param.setRfPenalty(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_PIE_IZQUIERDO)) {
			param.setRfPieIzquierdo(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_PIE_DERECHO)) {
			param.setRfPieDerecho(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_POSTE)) {
			param.setRfPoste(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RF_PORTERIA)) {
			param.setRfPorteria(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RC_CABEZA)) {
			param.setRcCabeza(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RC_FUERA)) {
			param.setRcFuera(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RC_PORTERIA)) {
			param.setRcPorteria(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.RC_POSTE)) {
			param.setRcPoste(Integer.valueOf(total));
		}

		// System.out.println(param.toString());
		return param;
	}

	/**
	 * Los parametros de entrada son datos de la última jornada. DECISION
	 * TOMADA: rellenar jornada actual y anteriores (es incorrecto, pero lo
	 * hemos decidido así).
	 * 
	 * @param temporada
	 * @param params
	 */
	private void rellenarTemporadaActualyAnteriores(Temporada temporada,
			int numJornadaActual, List<ParametroAvanzadoPrimeraHtml> params) {

		List<Jornada> jornadasAfectadas = new ArrayList<Jornada>();

		for (Jornada j : temporada.getJornadas()) {

			if (j.getNumeroJornada().intValue() <= numJornadaActual) {
				jornadasAfectadas.add(j);
			}
		}

		if (jornadasAfectadas.isEmpty()) {
			System.err
					.println("ERROR: jornada no encontrada en el html de EL PAIS");
		} else {

			// DECISION TOMADA: rellenar jornada actual y anteriores
			for (Jornada j : jornadasAfectadas) {
				for (ParametroAvanzadoPrimeraHtml param : params) {
					meteParametroEnPartido(param, j.getPartidos());
				}
			}
		}

	}

	/**
	 * Busca el equipo afectado por el parametro (dentro de un partido de la
	 * lista) y se lo rellena.
	 * 
	 * @param param
	 * @param partidos
	 */
	private void meteParametroEnPartido(ParametroAvanzadoPrimeraHtml paramHtml,
			Set<Partido> partidos) {

		Set<ParametroEquipo> parametrosEquipo = convertirParametro(paramHtml);

		for (Partido p : partidos) {
			if (p.getEquipoLocal().getNombre()
					.equals(paramHtml.getNombreEquipo())) {
				p.getEquipoLocal().getParametros().addAll(parametrosEquipo);

			} else if (p.getEquipoVisitante().getNombre()
					.equals(paramHtml.getNombreEquipo())) {
				p.getEquipoVisitante().getParametros().addAll(parametrosEquipo);
			}
		}

	}

	private Set<ParametroEquipo> convertirParametro(
			ParametroAvanzadoPrimeraHtml paramHtml) {

		Set<ParametroEquipo> params = new HashSet<ParametroEquipo>();

		if (paramHtml.getRematesContra() != null) {
			params.add(new ParametroEquipo(ParametroNombre.REMATES_EN_CONTRA,
					paramHtml.getRematesContra()));
		}

		if (paramHtml.getTarjetasAmarillas() != null) {
			params.add(new ParametroEquipo(ParametroNombre.TARJETAS_AMARILLAS,
					paramHtml.getTarjetasAmarillas()));
		}
		if (paramHtml.getTarjetasRojas() != null) {
			params.add(new ParametroEquipo(ParametroNombre.TARJETAS_ROJAS,
					paramHtml.getTarjetasRojas()));
		}

		if (paramHtml.getJugadoresUtilizados() != null) {
			params.add(new ParametroEquipo(
					ParametroNombre.JUGADORES_UTILIZADOS, paramHtml
							.getJugadoresUtilizados()));
		}
		if (paramHtml.getParadasPortero() != null) {
			params.add(new ParametroEquipo(ParametroNombre.PARADAS_DEL_PORTERO,
					paramHtml.getParadasPortero()));
		}
		if (paramHtml.getAfBalonParado() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_BALON_PARADO,
					paramHtml.getAfBalonParado()));
		}
		if (paramHtml.getAfCabeza() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_CABEZA, paramHtml
					.getAfCabeza()));
		}
		if (paramHtml.getAfFaltaDirecta() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_FALTA_DIRECTA,
					paramHtml.getAfFaltaDirecta()));
		}
		if (paramHtml.getAfJugadaColectiva() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_JUGADA_COLECTIVA,
					paramHtml.getAfJugadaColectiva()));
		}
		if (paramHtml.getAfJugadaIndividual() != null) {
			params.add(new ParametroEquipo(
					ParametroNombre.AF_JUGADA_INDIVIDUAL, paramHtml
							.getAfJugadaIndividual()));
		}
		if (paramHtml.getAfPenalty() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_PENALTY,
					paramHtml.getAfPenalty()));
		}
		if (paramHtml.getAfPieIzquierdo() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_PIE_IZQUIERDO,
					paramHtml.getAfPieIzquierdo()));
		}
		if (paramHtml.getAfPieDerecho() != null) {
			params.add(new ParametroEquipo(ParametroNombre.AF_PIE_DERECHO,
					paramHtml.getAfPieDerecho()));
		}
		if (paramHtml.getEnContra() != null) {
			params.add(new ParametroEquipo(ParametroNombre.EN_CONTRA, paramHtml
					.getEnContra()));
		}
		if (paramHtml.getGcCabeza() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GC_CABEZA, paramHtml
					.getGcCabeza()));
		}
		if (paramHtml.getGcPenalty() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GC_PENALTY,
					paramHtml.getGcPenalty()));
		}
		if (paramHtml.getGolesTitular() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GOLES_TITULAR,
					paramHtml.getGolesTitular()));
		}
		if (paramHtml.getGolesSuplentes() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GOLES_SUPLENTES,
					paramHtml.getGolesSuplentes()));
		}
		if (paramHtml.getRematesFavor() != null) {
			params.add(new ParametroEquipo(ParametroNombre.REMATES_FAVOR,
					paramHtml.getRematesFavor()));
		}
		if (paramHtml.getRfBalonParado() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_BALON_PARADO,
					paramHtml.getRfBalonParado()));
		}
		if (paramHtml.getRfCabeza() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_CABEZA, paramHtml
					.getRfCabeza()));
		}
		if (paramHtml.getRfFuera() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_FUERA, paramHtml
					.getRfFuera()));
		}
		if (paramHtml.getRfJugadaColectiva() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_JUGADA_COLECTIVA,
					paramHtml.getRfJugadaColectiva()));
		}
		if (paramHtml.getRfJugadaIndividual() != null) {
			params.add(new ParametroEquipo(
					ParametroNombre.RF_JUGADA_INDIVIDUAL, paramHtml
							.getRfJugadaIndividual()));
		}
		if (paramHtml.getRfPenalty() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_PENALTY,
					paramHtml.getRfPenalty()));
		}
		if (paramHtml.getRfPieIzquierdo() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_PIE_IZQUIERDO,
					paramHtml.getRfPieIzquierdo()));
		}
		if (paramHtml.getRfPieDerecho() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_PIE_DERECHO,
					paramHtml.getRfPieDerecho()));
		}
		if (paramHtml.getRfPoste() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_POSTE, paramHtml
					.getRfPoste()));
		}
		if (paramHtml.getRfPorteria() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RF_PORTERIA,
					paramHtml.getRfPorteria()));
		}
		if (paramHtml.getRcCabeza() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RC_CABEZA, paramHtml
					.getRcCabeza()));
		}
		if (paramHtml.getRcFuera() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RC_FUERA, paramHtml
					.getRcFuera()));
		}
		if (paramHtml.getRcPorteria() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RC_PORTERIA,
					paramHtml.getRcPorteria()));
		}
		if (paramHtml.getRcPoste() != null) {
			params.add(new ParametroEquipo(ParametroNombre.RC_POSTE, paramHtml
					.getRcPoste()));
		}

		return params;
	}

	public static HandlerHtmlParamPrimera getInstancia() {
		return instancia;
	}

	public static void setInstancia(HandlerHtmlParamPrimera instancia) {
		HandlerHtmlParamPrimera.instancia = instancia;
	}

	public String getWebStr() {
		return webStr;
	}

	public ParametroNombre getTipoParametro() {
		return tipoParametro;
	}

}
