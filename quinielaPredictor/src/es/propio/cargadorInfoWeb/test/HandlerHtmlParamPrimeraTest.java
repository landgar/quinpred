package es.propio.cargadorInfoWeb.test;

import junit.framework.TestCase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.propio.cargadorInfoWeb.HandlerHtmlParamPrimera;
import es.propio.cargadorInfoWeb.ParametroAvanzadoPrimeraHtml;
import es.propio.modeladoInfo.ParametroNombre;

public class HandlerHtmlParamPrimeraTest extends TestCase {

	private HandlerHtmlParamPrimera handler;

	@Override
	protected void setUp() {
		handler = new HandlerHtmlParamPrimera("",
				ParametroNombre.REMATES_FAVOR);
	}

	public void testCrearParamAvanzado() {

		String rowStr = "<table id=\"ordenacioncss_listado_equipos\" cellspacing=\"0\"><thead></thead><tbody><tr class=\"p\">	<th scope=\"row\"><a href=\"/todo-sobre/equipo-futbol/Athletic/Athletic/Club/Bilbao/5/\"><strong>Athletic Club</strong></a></th><td class=\"total\"><strong>88</strong></td><td>5,50</td></tr>";

		Document doc = Jsoup.parse(rowStr);
		Element tabla = doc.getElementById("ordenacioncss_listado_equipos");

		try {
			Elements tableRowElements = tabla.select(":not(thead) tr");
			ParametroAvanzadoPrimeraHtml out = handler
					.crearParamAvanzado(tableRowElements.get(0));

			assertNotNull(out);
			assertNotNull(out.getNombreEquipo());

			boolean algunParametro = (out.getRematesFavor() != null)
					|| (out.getRematesContra() != null)
					|| (out.getTarjetasAmarillas() != null)
					|| (out.getTarjetasRojas() != null)
					|| (out.getJugadoresUtilizados() != null)
					|| (out.getParadasPortero() != null);
			assertTrue(algunParametro);

		} catch (Exception e) {
			System.err.println("Error!!!!!!");
			e.printStackTrace();
		}

	}
}
