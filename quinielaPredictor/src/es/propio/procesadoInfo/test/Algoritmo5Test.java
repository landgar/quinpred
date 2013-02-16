/**
 * 
 */
package es.propio.procesadoInfo.test;

import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author nosotros
 * 
 */
public class Algoritmo5Test extends AbstractAlgoritmoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		setModoMock(true);
		calcula(IdAlgoritmoEnum.ALGORITMO5);
	}

}
