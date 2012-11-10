package es.propio.modeladoInfo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Jornada {
	private Date fecha;
	private Set<Partido> partidos;
	private Integer numeroJornada;

	public Jornada() {
		super();
		partidos = new HashSet<Partido>();
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
	 */
	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	/**
	 * @param numeroJornada
	 *            the numeroJornada to set
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
				// La jornada m�s pr�xima debe estar a 3 o mesnos d�as de
				// proximidad
				if (minDiff < 4)
					jornadaMasProxima = jornada_i;
			}
		}
		if (jornadaMasProxima == null) {
			System.out.println("ERROR: jornada m�s pr�xima a "
					+ this.getFecha().toString() + " no encontrada.");
		}
		return jornadaMasProxima;
	}

	public Partido getPartidoDondeJuega(final Equipo equipo) {
		Partido partido = null;
		for (Partido partido_i : getPartidos()) {
			if (partido_i.getEquipoLocal() != null)
				if (partido_i.getEquipoLocal().getValor()
						.equals(equipo.getValor())
						|| partido_i.getEquipoVisitante().getValor()
								.equals(equipo.getValor())) {
					partido = partido_i;
				}
		}
		if (partido == null) {
			System.out.println("WARNING: no hay partidos de "
					+ equipo.getValor() + " en la jornada " + this.getFecha()
					+ " con n�mero de jornada: " + this.getNumeroJornada());
		}
		return partido;
	}

}
