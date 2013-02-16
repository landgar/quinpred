/**
 * 
 */
package es.propio.procesadoInfo.test;

import es.propio.procesadoInfo.IdAlgoritmoEnum;

/**
 * @author nosotros
 * 
 */
public class Algoritmo3Test extends AbstractAlgoritmoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		setModoMock(Boolean.FALSE);
		calcula(IdAlgoritmoEnum.ALGORITMO3);
	}

}
