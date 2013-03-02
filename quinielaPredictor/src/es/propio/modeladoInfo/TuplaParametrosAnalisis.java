/**
 * 
 */
package es.propio.modeladoInfo;

/**
 * @author carlos.andres
 * 
 */
public class TuplaParametrosAnalisis {

	private ParametroNombre paramPrimera12, paramPrimeraEmpates,
			paramSegunda12, paramSegundaEmpates;
	private Integer numEmpatesPrimera, numEmpatesSegunda;

	public TuplaParametrosAnalisis(ParametroNombre paramPrimera12,
			ParametroNombre paramPrimeraEmpates, Integer numEmpatesPrimera,
			ParametroNombre paramSegunda12,
			ParametroNombre paramSegundaEmpates, Integer numEmpatesSegunda) {
		super();
		this.paramPrimera12 = paramPrimera12;
		this.paramPrimeraEmpates = paramPrimeraEmpates;
		this.paramSegunda12 = paramSegunda12;
		this.paramSegundaEmpates = paramSegundaEmpates;
		this.numEmpatesPrimera = numEmpatesPrimera;
		this.numEmpatesSegunda = numEmpatesSegunda;
	}

	@Override
	public String toString() {
		String str = "Primera_12 = " + paramPrimera12.getNombre() + "   "
				+ "Primera_X = " + paramPrimeraEmpates.getNombre() + "   "
				+ "Primera_X_# = " + numEmpatesPrimera + "   "
				+ "Segunda_12 = " + paramSegunda12.getNombre() + "   "
				+ "Segunda_X = " + paramSegundaEmpates.getNombre() + "   "
				+ "Segunda_X_# = " + numEmpatesSegunda + "   ";
		return str;

	}

	public ParametroNombre getParamPrimera12() {
		return paramPrimera12;
	}

	public void setParamPrimera12(ParametroNombre paramPrimera12) {
		this.paramPrimera12 = paramPrimera12;
	}

	public ParametroNombre getParamPrimeraEmpates() {
		return paramPrimeraEmpates;
	}

	public void setParamPrimeraEmpates(ParametroNombre paramPrimeraEmpates) {
		this.paramPrimeraEmpates = paramPrimeraEmpates;
	}

	public ParametroNombre getParamSegunda12() {
		return paramSegunda12;
	}

	public void setParamSegunda12(ParametroNombre paramSegunda12) {
		this.paramSegunda12 = paramSegunda12;
	}

	public ParametroNombre getParamSegundaEmpates() {
		return paramSegundaEmpates;
	}

	public void setParamSegundaEmpates(ParametroNombre paramSegundaEmpates) {
		this.paramSegundaEmpates = paramSegundaEmpates;
	}

	/**
	 * @return the numEmpatesPrimera
	 */
	public Integer getNumEmpatesPrimera() {
		return numEmpatesPrimera;
	}

	/**
	 * @param numEmpatesPrimera
	 *            the numEmpatesPrimera to set
	 */
	public void setNumEmpatesPrimera(Integer numEmpatesPrimera) {
		this.numEmpatesPrimera = numEmpatesPrimera;
	}

	/**
	 * @return the numEmpatesSegunda
	 */
	public Integer getNumEmpatesSegunda() {
		return numEmpatesSegunda;
	}

	/**
	 * @param numEmpatesSegunda
	 *            the numEmpatesSegunda to set
	 */
	public void setNumEmpatesSegunda(Integer numEmpatesSegunda) {
		this.numEmpatesSegunda = numEmpatesSegunda;
	}

}
