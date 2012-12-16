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

	public void cargarParamsAvanzados(Temporada temporadaPrimera) {
		Document docPrimera = Jsoup.parse(webStr);
		List<ParametroAvanzadoPrimeraHtml> parametrosHtmlPrimera = parsearDoc(docPrimera);
		rellenarTemporada(temporadaPrimera, parametrosHtmlPrimera);
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

		if (tipoParametro.equals(ParametroNombre.REMATES_A_FAVOR)) {
			param.setRematesFavor(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.REMATES_EN_CONTRA)) {
			param.setRematesContra(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.TARJETAS_AMARILLAS)) {
			param.setTarjetasAmarillas(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.TARJETAS_ROJAS)) {
			param.setTarjetasRojas(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.JUGADORES_UTILIZADOS)) {
			param.setJugadoresUtilizados(Integer.valueOf(total));
		} else if (tipoParametro.equals(ParametroNombre.PARADAS_DEL_PORTERO)) {
			param.setParadasPortero(Integer.valueOf(total));
		}

		// System.out.println(param.toString());
		return param;
	}

	/**
	 * Los parametros de entrada son datos de la última jornada, pero hemos
	 * decidido incluir estos datos en los equipos de todas las jornadas (es
	 * incorrecto, pero lo hemos decidido así).
	 * 
	 * @param temporada
	 * @param params
	 */
	private void rellenarTemporada(Temporada temporada,
			List<ParametroAvanzadoPrimeraHtml> params) {

		Jornada jornadaAfectada = null;

		for (Jornada j : temporada.getJornadas()) {
			jornadaAfectada = j;
		}

		if (jornadaAfectada == null) {
			System.err
					.println("ERROR: jornada no encontrada en el html de EL PAIS");
		} else {

			for (ParametroAvanzadoPrimeraHtml param : params) {
				meteParametroEnPartido(param, jornadaAfectada.getPartidos());
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

		if (paramHtml.getRematesFavor() != null) {
			params.add(new ParametroEquipo(ParametroNombre.REMATES_A_FAVOR,
					paramHtml.getRematesFavor()));
		}
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
