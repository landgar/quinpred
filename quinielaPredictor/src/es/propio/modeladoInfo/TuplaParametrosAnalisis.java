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

	public TuplaParametrosAnalisis(ParametroNombre paramPrimera12,
			ParametroNombre paramPrimeraEmpates,
			ParametroNombre paramSegunda12, ParametroNombre paramSegundaEmpates) {
		super();
		this.paramPrimera12 = paramPrimera12;
		this.paramPrimeraEmpates = paramPrimeraEmpates;
		this.paramSegunda12 = paramSegunda12;
		this.paramSegundaEmpates = paramSegundaEmpates;
	}

	@Override
	public String toString() {
		String str = "Primera_12 = " + paramPrimera12.getNombre() + "   "
				+ "Primera_X = " + paramPrimeraEmpates.getNombre() + "   "
				+ "Segunda_12 = " + paramSegunda12.getNombre() + "   "
				+ "Segunda_X = " + paramSegundaEmpates.getNombre();
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

}
