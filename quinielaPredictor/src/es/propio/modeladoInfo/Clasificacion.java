package es.propio.modeladoInfo;

import java.util.Map;

public class Clasificacion {

	private Map<Integer, Equipo> ranking;
	private Division division;

	/**
	 * @return the ranking
	 */
	public Map<Integer, Equipo> getRanking() {
		return ranking;
	}

	/**
	 * @return the division
	 */
	public Division getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(Division division) {
		this.division = division;
	}

	/**
	 * @param ranking
	 *            the ranking to set
	 */
	public void setRanking(Map<Integer, Equipo> ranking) {
		this.ranking = ranking;
	}

}
