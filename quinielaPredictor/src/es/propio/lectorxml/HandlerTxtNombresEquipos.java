package es.propio.lectorxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.Jornada;
import es.propio.modeladoInfo.Partido;

public class HandlerTxtNombresEquipos {

	/**
	 * @uml.property  name="jornada"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Jornada jornada;

	public HandlerTxtNombresEquipos() throws Exception {
		jornada = new Jornada();
	}

	public void leer(final File archivoXML) throws Exception {
		FileReader input = new FileReader(archivoXML);
		BufferedReader bufRead = new BufferedReader(input);
		String linea = null;
		int numLinea = 0;
		while ((linea = bufRead.readLine()) != null) {
			linea = new String(linea.getBytes(), "UTF-8");
			linea = linea.replaceAll("M..LAGA", "MALAGA");
			linea = linea.replaceAll("ALMER..A", "ALMERIA");
			numLinea++;
			if (numLinea == 1) {
				StringTokenizer tokens = new StringTokenizer(linea, ";");
				int numElemento = 0;
				// Only not empty tokens.
				while (tokens.hasMoreTokens()) {
					String elemento = tokens.nextToken();
					numElemento++;
					if (numElemento == 2) {
						Integer numeroJornada = Integer.valueOf((new String(
								elemento.substring(elemento.length() - 3,
										elemento.length() - 1))).trim());
						jornada.setNumeroJornada(numeroJornada);
					}
					if (numElemento == 3) {
						StringTokenizer tokens2 = new StringTokenizer(elemento,
								" ");
						int numElemento2 = 0;
						while (tokens2.hasMoreTokens()) {
							String elemento2 = tokens2.nextToken();
							numElemento2++;
							if (numElemento2 == 2) {
								// Se añaden los dos primeros dígitos al año:
								elemento2 = elemento2.substring(0,
										elemento2.length() - 2)
										+ "20"
										+ elemento2.substring(elemento2
												.length() - 2);
								SimpleDateFormat dt = new SimpleDateFormat(
										"dd-MM-yyyy");
								Date date = dt.parse(elemento2);
								jornada.setFecha(date);
							}
						}
					}
				}
			} else if (numLinea >= 3 && numLinea <= 17) {
				StringTokenizer tokens = new StringTokenizer(linea, ";");
				int numElemento = 0;
				// Only not empty tokens.
				while (tokens.hasMoreTokens()) {
					String elemento = tokens.nextToken();
					numElemento++;
					if (numElemento == 1) {
						elemento = elemento.replaceAll("\\.", "");
						StringTokenizer tokens2 = new StringTokenizer(elemento,
								";");
						int numElemento2 = 0;
						// Only not empty tokens.
						while (tokens2.hasMoreTokens()) {
							String elemento2 = tokens2.nextToken();
							numElemento2++;
							if (numElemento2 == 1) {
								StringTokenizer tokens3 = new StringTokenizer(
										elemento2, "-");
								int numElemento3 = 0;
								String nombreLocal = "invalido", nombreVisitante = "invalido";
								// Only not empty tokens.
								while (tokens3.hasMoreTokens()) {
									String elemento3 = tokens3.nextToken();
									numElemento3++;
									if (numElemento3 == 1) {
										nombreLocal = elemento3;
									} else if (numElemento3 == 2) {
										nombreVisitante = elemento3;
									}
								}
								Partido partido = new Partido(Boolean.TRUE);
//								partido.setPosicion(numLinea - 2);
								Equipo local = new Equipo(nombreLocal);
								Equipo visitante = new Equipo(nombreVisitante);
								partido.setEquipoLocal(local);
								partido.setEquipoVisitante(visitante);
								jornada.getPartidos().add(partido);
							}
						}
					}
				}
			}
		}
		bufRead.close();
//		System.out.println("Jornada (" + jornada.getNumeroJornada()
//				+ ") con nombres de equipos añadida: "
//				+ jornada.getFecha().toString());
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
