package es.propio.graficos;

import java.util.Set;

import es.propio.modeladoInfo.PronosticoJornada;

/**
 * @author yo
 * 
 */
public interface AlgoritmoPintable {

	public Set<PronosticoJornada> ejecutar() throws Exception;

}
