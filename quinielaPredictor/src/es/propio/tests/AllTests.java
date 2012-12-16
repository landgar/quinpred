package es.propio.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import es.propio.cargadorInfoWeb.test.CargadorInformacionWebResultadosTest;
import es.propio.cargadorInfoWeb.test.HandlerHtmlParamPrimeraTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite de quiniela");
		suite.addTestSuite(HandlerHtmlParamPrimeraTest.class);
		suite.addTestSuite(CargadorInformacionWebResultadosTest.class);
		return suite;
	}

}
