/**
 * 
 */
package es.propio.modeladoInfo;

import java.util.Date;
import java.util.Map;

/**
 * @author nosotros
 * 
 */
public class Boleto {
	private Integer numeroBoleto;
	private Date fecha;
	private Map<Integer, Partido> partidos;

	/**
	 * @return the numeroBoleto
	 */
	public Integer getNumeroBoleto() {
		return numeroBoleto;
	}

	/**
	 * @param numeroBoleto
	 *            the numeroBoleto to set
	 */
	public void setNumeroBoleto(Integer numeroBoleto) {
		this.numeroBoleto = numeroBoleto;
	}

	/**
	 * @return the partidos
	 */
	public Map<Integer, Partido> getPartidos() {
		return partidos;
	}

	/**
	 * @param partidos
	 *            the partidos to set
	 */
	public void setPartidos(Map<Integer, Partido> partidos) {
		this.partidos = partidos;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
