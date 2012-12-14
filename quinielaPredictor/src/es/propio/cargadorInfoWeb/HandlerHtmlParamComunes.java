/**
 * 
 */
package es.propio.cargadorInfoWeb;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.propio.modeladoInfo.Temporada;

/**
 * Manejador de los Parametros comunes que viene en un HTML.
 * 
 * @author carlos.andres
 * 
 */
public class HandlerHtmlParamComunes {

	private static final String CSS_TABLA_BUSCADA = "tablaclasificacion";

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

		List<ParametroComunHtml> parametrosPrimera = parsearDoc(docPrimera);
		List<ParametroComunHtml> parametrosSegunda = parsearDoc(docSegunda);

		// TODO rellenar temporadas con los parametros ya leidos

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

				param.setNombre(row.getElementsByClass("equipo").get(0).text());
				param.setPosicion(Integer.valueOf(row
						.getElementsByClass("posicion").get(0).text()));
				param.setPartidosJugados(Integer.valueOf(row
						.getElementsByClass("pj").get(0).text()));
				param.setPartidosGanados(Integer.valueOf(row
						.getElementsByClass("pg").get(0).text()));
				param.setPartidosEmpatados(Integer.valueOf(row
						.getElementsByClass("pe").get(0).text()));
				param.setPartidosPerdidos(Integer.valueOf(row
						.getElementsByClass("pp").get(0).text()));
				param.setGolesFavor(Integer.valueOf(row
						.getElementsByClass("gf").get(0).text()));
				param.setGolesContra(Integer.valueOf(row
						.getElementsByClass("gc").get(0).text()));
				param.setPuntos(Integer.valueOf(row.getElementsByClass("pts")
						.get(0).text()));

				// System.out.println(param.toString());

				parametros.add(param);

			}

		}

		return parametros;
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
