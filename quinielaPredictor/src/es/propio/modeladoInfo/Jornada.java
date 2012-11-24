package es.propio.modeladoInfo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class Jornada {

	static final Logger logger = Logger.getLogger(Jornada.class);
	/**
	 * @uml.property name="fecha"
	 */
	private Date fecha;
	/**
	 * @uml.property name="partidos"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType="es.propio.modeladoInfo.Partido"
	 */
	private Set<Partido> partidos;
	/**
	 * @uml.property name="numeroJornada"
	 */
	private Integer numeroJornada;

	public Jornada() {
		super();
		partidos = new HashSet<Partido>();
	}

	/**
	 * @return the fecha
	 * @uml.property name="fecha"
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 * @uml.property name="fecha"
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the partidos
	 */
	public Set<Partido> getPartidos() {
		return partidos;
	}

	/**
	 * @param partidos
	 *            the partidos to set
	 */
	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	/**
	 * @return the numeroJornada
	 * @uml.property name="numeroJornada"
	 */
	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	/**
	 * @param numeroJornada
	 *            the numeroJornada to set
	 * @uml.property name="numeroJornada"
	 */
	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}

	/**
	 * 
	 * @param jornadas
	 * @return
	 */
	public Jornada getNearestDate(Set<Jornada> jornadas) {
		long minDiff = -1, currentTime = this.getFecha().getTime();
		Jornada jornadaMasProxima = null;
		for (Jornada jornada_i : jornadas) {
			long diff = Math.abs(currentTime - jornada_i.getFecha().getTime());
			if ((minDiff == -1) || (diff < minDiff)) {
				minDiff = diff;
				// La jornada más próxima debe estar a 3 o mesnos días de
				// proximidad
				if (minDiff < 4)
					jornadaMasProxima = jornada_i;
			}
		}
		if (jornadaMasProxima == null) {
			logger.error("ERROR: jornada más próxima a "
					+ this.getFecha().toString() + " no encontrada.");
		}
		return jornadaMasProxima;
	}

	public Partido getPartidoDondeJuega(final Equipo equipo) {
		Partido partido = null;
		for (Partido partido_i : getPartidos()) {
			if (partido_i.getEquipoLocal() != null)
				if (partido_i.getEquipoLocal().getNombre()
						.equals(equipo.getNombre())
						|| partido_i.getEquipoVisitante().getNombre()
								.equals(equipo.getNombre())) {
					partido = partido_i;
				}
		}
		if (partido == null) {
			logger.warn("WARNING: no hay partidos de " + equipo.getNombre()
					+ " en la jornada " + this.getFecha()
					+ " con número de jornada: " + this.getNumeroJornada());
		}
		return partido;
	}

}
