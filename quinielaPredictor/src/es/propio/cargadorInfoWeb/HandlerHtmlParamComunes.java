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
 * Manejador de los Parametros comunes que viene en un HTML.
 * 
 * @author carlos.andres
 * 
 */
public class HandlerHtmlParamComunes {

	private static final String CSS_TABLA_BUSCADA = "tablaclasificacion";

	private static final String CSS_PARAM_EQUIPO = "equipo";
	private static final String CSS_PARAM_POSICION = "posicion";
	private static final String CSS_PARAM_PARTIDOS_JUGADOS = "pj";
	private static final String CSS_PARAM_PARTIDOS_GANADOS = "pg";
	private static final String CSS_PARAM_PARTIDOS_EMPATADOS = "pe";
	private static final String CSS_PARAM_PARTIDOS_PERDIDOS = "pp";
	private static final String CSS_PARAM_GOLES_FAVOR = "gf";
	private static final String CSS_PARAM_GOLES_CONTRA = "gc";
	private static final String CSS_PARAM_PUNTOS = "pts";

	/**
	 * Singleton
	 */
	static HandlerHtmlParamComunes instancia;

	private String webPrimeraParamComunes;
	private String webSegundaParamComunes;

	public HandlerHtmlParamComunes(String webPrimeraParamComunes,
			String webSegundaParamComunes) {
		super();
		this.webPrimeraParamComunes = webPrimeraParamComunes;
		this.webSegundaParamComunes = webSegundaParamComunes;
	}

	public static HandlerHtmlParamComunes build(String webPrimeraParamComunes,
			String webSegundaParamComunes) {
		instancia = new HandlerHtmlParamComunes(webPrimeraParamComunes,
				webSegundaParamComunes);
		return instancia;
	}

	public void cargarParamsComunes(Temporada temporadaPrimera,
			Temporada temporadaSegunda) {

		Document docPrimera = Jsoup.parse(webPrimeraParamComunes);
		Document docSegunda = Jsoup.parse(webSegundaParamComunes);

		List<ParametroComunHtml> parametrosHtmlPrimera = parsearDoc(docPrimera);
		List<ParametroComunHtml> parametrosHtmlSegunda = parsearDoc(docSegunda);

		int numJornadaPrimera = extraerNumJornada(docPrimera);
		int numJornadaSegunda = extraerNumJornada(docSegunda);

		rellenarTemporada(temporadaPrimera, numJornadaPrimera,
				parametrosHtmlPrimera);
		rellenarTemporada(temporadaSegunda, numJornadaSegunda,
				parametrosHtmlSegunda);

	}

	private int extraerNumJornada(Document doc) {
		Element contenido = doc.getElementById("contenido");
		Elements encabezados = contenido.getElementsByClass("encabezado");
		Elements titulosh3 = encabezados.select("h3");

		Element jornadaynum = titulosh3.get(0);
		String jornadaynumStr = jornadaynum.text();

		String numero = jornadaynumStr.substring("Jornada ".length());

		return Integer.parseInt(numero);
	}

	/**
	 * Parsea HTML con la herramienta JSOUP
	 * 
	 * @param doc
	 */
	private List<ParametroComunHtml> parsearDoc(Document doc) {

		List<ParametroComunHtml> parametros = new ArrayList<ParametroComunHtml>();

		Elements tableElements = doc.getElementsByClass("tablaclasificacion");

		if (tableElements.isEmpty()) {
			System.err
					.println("ERROR al parsear HTML a Java: no encuentra la tabla de clasificacion");
		} else {

			// EXCLUYA LA HEADER thead
			Elements tableRowElements = tableElements.select(":not(thead) tr");
			ParametroComunHtml param;

			for (Element row : tableRowElements) {

				param = new ParametroComunHtml();

				param.setNombre(ConversorMarca.convertirNombreEquipo(row
						.getElementsByClass(CSS_PARAM_EQUIPO).get(0).text()));

				param.setPosicion(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_POSICION).get(0).text()));
				param.setPartidosJugados(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_PARTIDOS_JUGADOS).get(0)
						.text()));
				param.setPartidosGanados(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_PARTIDOS_GANADOS).get(0)
						.text()));
				param.setPartidosEmpatados(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_PARTIDOS_EMPATADOS)
						.get(0).text()));
				param.setPartidosPerdidos(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_PARTIDOS_PERDIDOS).get(0)
						.text()));
				param.setGolesFavor(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_GOLES_FAVOR).get(0)
						.text()));
				param.setGolesContra(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_GOLES_CONTRA).get(0)
						.text()));
				param.setPuntos(Integer.valueOf(row
						.getElementsByClass(CSS_PARAM_PUNTOS).get(0).text()));

				// System.out.println(param.toString());

				parametros.add(param);

			}

		}

		return parametros;
	}

	private void rellenarTemporada(Temporada temporada, int numJornada,
			List<ParametroComunHtml> params) {

		Jornada jornadaAfectada = null;

		for (Jornada j : temporada.getJornadas()) {
			if (j.getNumeroJornada().intValue() == numJornada) {
				jornadaAfectada = j;
			}
		}

		if (jornadaAfectada == null) {
			System.err
					.println("ERROR: jornada no encontrada en el html de marca.com");
		} else {

			for (ParametroComunHtml param : params) {
				meteParametroEnPartido(param, jornadaAfectada.getPartidos());
				int x = 0;
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
	private void meteParametroEnPartido(ParametroComunHtml paramHtml,
			Set<Partido> partidos) {

		Set<ParametroEquipo> parametrosEquipo = convertirParametro(paramHtml);

		for (Partido p : partidos) {
			if (p.getEquipoLocal().getNombre().equals(paramHtml.getNombre())) {
				p.getEquipoLocal().getParametros().addAll(parametrosEquipo);

			} else if (p.getEquipoVisitante().getNombre()
					.equals(paramHtml.getNombre())) {
				p.getEquipoVisitante().getParametros().addAll(parametrosEquipo);
			}
		}

	}

	private Set<ParametroEquipo> convertirParametro(ParametroComunHtml paramHtml) {

		Set<ParametroEquipo> params = new HashSet<ParametroEquipo>();

		if (paramHtml.getPosicion() != null) {
			params.add(new ParametroEquipo(
					ParametroNombre.POSICION_EN_CLASIFICACION, paramHtml
							.getPosicion()));
		}
		if (paramHtml.getPartidosJugados() != null) {
			params.add(new ParametroEquipo(ParametroNombre.PARTIDOS_JUGADOS,
					paramHtml.getPartidosJugados()));
		}
		if (paramHtml.getPartidosGanados() != null) {
			params.add(new ParametroEquipo(ParametroNombre.PARTIDOS_GANADOS,
					paramHtml.getPartidosGanados()));
		}
		if (paramHtml.getPartidosEmpatados() != null) {
			params.add(new ParametroEquipo(ParametroNombre.PARTIDOS_EMPATADOS,
					paramHtml.getPartidosEmpatados()));
		}
		if (paramHtml.getPartidosPerdidos() != null) {
			params.add(new ParametroEquipo(ParametroNombre.PARTIDOS_PERDIDOS,
					paramHtml.getPartidosPerdidos()));
		}
		if (paramHtml.getGolesFavor() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GOLES_A_FAVOR,
					paramHtml.getGolesFavor()));
		}
		if (paramHtml.getGolesContra() != null) {
			params.add(new ParametroEquipo(ParametroNombre.GOLES_EN_CONTRA,
					paramHtml.getGolesContra()));
		}

		return params;
	}

	public static HandlerHtmlParamComunes getInstancia() {
		return instancia;
	}

	public static void setInstancia(HandlerHtmlParamComunes instancia) {
		HandlerHtmlParamComunes.instancia = instancia;
	}

	public String getWebPrimeraParamComunes() {
		return webPrimeraParamComunes;
	}

	public void setWebPrimeraParamComunes(String webPrimeraParamComunes) {
		this.webPrimeraParamComunes = webPrimeraParamComunes;
	}

	public String getWebSegundaParamComunes() {
		return webSegundaParamComunes;
	}

	public void setWebSegundaParamComunes(String webSegundaParamComunes) {
		this.webSegundaParamComunes = webSegundaParamComunes;
	}

}
