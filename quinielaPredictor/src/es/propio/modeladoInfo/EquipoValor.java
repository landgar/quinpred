/**
 * 
 */
package es.propio.modeladoInfo;

/**
 * @author i3casa
 * 
 */
public enum EquipoValor {

	ESPANYOL("ESPANYOL"), ATHLELICCLUB("ATHLETIC CLUB"), BETIS("BETIS"), LEVANTE(
			"LEVANTE"), ATMADRID("AT MADRID"), ZARAGOZA("ZARAGOZA"), VALLADOLID(
			"VALLADOLID"), MALLORCA("MALLORCA"), BARCELONA("BARCELONA"), RSOCIEDAD(
			"R SOCIEDAD"), DEPORTIVO("DEPORTIVO"), OSASUNA("OSASUNA"), RMADRID(
			"R MADRID"), CELTA("CELTA"), MALAGA("MALAGA"), VALENCIA("VALENCIA"), SEVILLA(
			"SEVILLA"), GETAFE("GETAFE"), RAYOVALLECANO("RAYO VALLECANO"), GRANADA(
			"GRANADA"), HERCULES("H�RCULES"), RACING("RACING"), LASPALMAS(
			"LAS PALMAS"), MURCIA("MURCIA"), CORDOBA("C�RDOBA"), NUMANCIA(
			"NUMANCIA"), SPORTING("SPORTING"), XEREZ("XEREZ"), RECREATIVO(
			"RECREATIVO"), LUGO("LUGO"), RMADRIDCASTILLA("R MADRID CASTILLA"), SABADELL(
			"SABADELL"), GUADALAJARA("GUADALAJARA"), BARCELONAB("BARCELONA B"), PONFERRADINA(
			"PONFERRADINA"), VILLARREAL("VILLARREAL"), GIRONA("GIRONA"), ALCORCON(
			"ALCORC�N"), HUESCA("HUESCA"), MIRANDES("MIRAND�S"), OURENSE(
			"OURENSE"), TENERIFE("TENERIFE"), REALUNION("REAL UNI�N"), ALAVES(
			"ALAV�S"), REUSDEPORTIVO("REUS DEPORTIVO"), ELCHE("ELCHE"), MELILLA(
			"MELILLA"), CACERE�O("CACERE�O"), ALMERIA("ALMERIA"), SANTANDREU(
			"SANT ANDREU"), PRIMERA("PRIMERA"), SEGUNDA("SEGUNDA");

	/**
	 * @uml.property  name="nombre"
	 */
	private String nombre;

	private EquipoValor(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return  the nombre
	 * @uml.property  name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre  the nombre to set
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
