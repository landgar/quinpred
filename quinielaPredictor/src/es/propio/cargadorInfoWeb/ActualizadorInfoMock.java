package es.propio.cargadorInfoWeb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Actualiza los ficheros con las paginas de datos mock.
 * 
 * @author carlos.andres
 * 
 */
public class ActualizadorInfoMock {

	public static boolean actualizarTodasPaginasMock() throws Exception {
		System.out.println("Actualizando Paginas de mock ...");
		actualizarPaginasTemporadaMock();
		actualizarPaginasParamsComunesMock();
		actualizarPaginasParamsAvanzadosPrimeraMock();
		System.out.println("Paginas de mock actualizadas");
		return true;
	}

	private static boolean actualizarPaginasTemporadaMock() throws Exception {
		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_CALENDARIO_PRIMERA,
				CargadorInformacionWebResultados.EL_PAIS_CALENDARIO_PRIMERA_MOCK);
		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_CALENDARIO_SEGUNDA,
				CargadorInformacionWebResultados.EL_PAIS_CALENDARIO_SEGUNDA_MOCK);

		return true;
	}

	private static boolean actualizarPaginasParamsComunesMock()
			throws Exception {
		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.MARCA_CLASIFICACION_PRIMERA,
				CargadorInformacionWebResultados.MARCA_CLASIFICACION_PRIMERA_MOCK);
		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.MARCA_CLASIFICACION_SEGUNDA,
				CargadorInformacionWebResultados.MARCA_CLASIFICACION_SEGUNDA_MOCK);

		return true;
	}

	private static boolean actualizarPaginasParamsAvanzadosPrimeraMock()
			throws Exception {
		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_BALON_PARADO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_BALON_PARADO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_CABEZA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_CABEZA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_FALTA_DIRECTA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_FALTA_DIRECTA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_JUGADA_COLECTIVA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_JUGADA_INDIVIDUAL_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PENALTY,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PENALTY_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PIE_IZQUIERDO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PIE_DERECHO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_AF_PIE_DERECHO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GC_CABEZA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GC_CABEZA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GC_PENALTY,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GC_PENALTY_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GOLES_TITULAR,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GOLES_TITULAR_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GOLES_SUPLENTES,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_GOLES_SUPLENTES_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_REMATES_FAVOR,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_REMATES_FAVOR_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_BALON_PARADO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_BALON_PARADO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_CABEZA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_CABEZA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_FUERA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_FUERA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_JUGADA_COLECTIVA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_JUGADA_INDIVIDUAL_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PENALTY,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PENALTY_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PIE_IZQUIERDO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PIE_DERECHO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PIE_DERECHO_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_POSTE,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_POSTE_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PORTERIA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RF_PORTERIA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_REMATES_CONTRA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_REMATES_CONTRA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_CABEZA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_CABEZA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_FUERA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_FUERA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_PORTERIA,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_PORTERIA_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_POSTE,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_RC_POSTE_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_TARJETAS_AMARILLAS,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_TARJETAS_AMARILLAS_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_TARJETAS_ROJAS,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_TARJETAS_ROJAS_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_JUGADORES_UTILIZADOS_MOCK);

		actualizarUnaPaginaMock(
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_PARADAS_PORTERO,
				CargadorInformacionWebResultados.EL_PAIS_PRIMERA_PARADAS_PORTERO_MOCK);

		return true;

	}

	/**
	 * @param url
	 * @param pathRelativoFicheroMock
	 * @return
	 * @throws Exception
	 */
	private static boolean actualizarUnaPaginaMock(String url,
			String pathRelativoFicheroMock) throws Exception {

		StringBuffer contenido = getContenido(url);

		// System.out.println("CODIGO FUENTE:");
		// System.out.println(contenido);

		rellenarFicheroMock(pathRelativoFicheroMock, contenido);

		return true;
	}

	private static StringBuffer getContenido(String url) throws Exception {
		BufferedReader reader = read(url);
		StringBuffer buffer = new StringBuffer();

		String line = "";

		while (line != null) {
			line = reader.readLine();
			if (line == null) {
				break;
			} else {
				buffer.append(line);
			}
		}

		return buffer;
	}

	private static BufferedReader read(String url) throws Exception {
		return new BufferedReader(new InputStreamReader(
				new URL(url).openStream()));
	}

	private static boolean rellenarFicheroMock(String pathRelativoFicheroMock,
			StringBuffer contenido) throws IOException {

		File fichero = new File(pathRelativoFicheroMock);
		fichero.delete();

		File fichero2 = new File(pathRelativoFicheroMock);
		fichero2.createNewFile();

		FileWriter fstream = new FileWriter(pathRelativoFicheroMock);
		BufferedWriter writer = new BufferedWriter(fstream);
		writer.write(contenido.toString());
		writer.close();

		return true;

	}
}
