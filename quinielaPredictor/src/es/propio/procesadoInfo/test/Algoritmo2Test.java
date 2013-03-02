/**
 * 
 */
package es.propio.procesadoInfo.test;

import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author nosotros
 * 
 */
public class Algoritmo2Test extends AbstractAlgoritmoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		setModoMock(Boolean.TRUE);
		calcula(IdAlgoritmoEnum.ALGORITMO2);
	}

}
