package es.propio.modeladoInfo;

/**
 * Interfaz que obliga a que las clases que la implementen digan el resultado
 * del partido.
 * 
 * @author carlos.andres
 * 
 */
public interface TieneResultadoPartido {
	public ValorResultado getResultadoMasProbable();
}
