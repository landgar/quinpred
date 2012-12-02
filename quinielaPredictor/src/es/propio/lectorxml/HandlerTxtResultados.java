package es.propio.lectorxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;
import es.propio.modeladoInfo.ResultadoQuiniela;

public class HandlerTxtResultados {

	/**
	 * @uml.property  name="jornada"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jornada jornada;

	public HandlerTxtResultados() throws Exception {
		jornada = new Jornada();
	}

	public void leer(final File archivoXML) throws Exception {
		FileReader input = new FileReader(archivoXML);
		BufferedReader bufRead = new BufferedReader(input);
		String linea = null;
		int numLinea = 0;
		while ((linea = bufRead.readLine()) != null) {
			linea = new String(linea.getBytes(), "UTF-8");
			numLinea++;
			if (numLinea == 2) {
				StringTokenizer tokens = new StringTokenizer(linea, ";");
				int numElemento = 0;
				// Only not empty tokens.
				while (tokens.hasMoreTokens()) {
					String elemento = tokens.nextToken();
					numElemento++;
					// System.out.println("---------" + numElemento + "--------"
					// + elemento);
					if (numElemento == 4) {
						SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
						Date date = dt.parse(elemento);
						jornada.setFecha(date);
					} else if (numElemento == 7) {
						StringTokenizer resultados = new StringTokenizer(
								elemento, " ");
						int posicion = 0;
						while (resultados.hasMoreTokens()) {
							posicion++;
							final String resultado = resultados.nextToken();
							Partido partido = new Partido();
//							partido.setResultadoQuiniela(new ResultadoQuiniela(
//									resultado));
//							partido.setPosicion(posicion);
							jornada.getPartidos().add(partido);
						}
					} else if (numElemento == 8) {
						Partido partido = new Partido();
						// partido.setResultadoQuiniela(new ResultadoQuiniela(
						// elemento));
//						partido.setPosicion(15);
						jornada.getPartidos().add(partido);
					}
				}
			}

		}
		bufRead.close();
	}

	/**
	 * @return  the jornada
	 * @uml.property  name="jornada"
	 */
	public Jornada getJornada() {
		return jornada;
	}

	/**
	 * @param jornada  the jornada to set
	 * @uml.property  name="jornada"
	 */
	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

}