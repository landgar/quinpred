/**
 * 
 */
package es.propio.procesadoInfo;

import java.util.ArrayList;
import java.util.List;

import es.propio.modeladoInfo.Equipo;
import es.propio.modeladoInfo.PronosticoJornada;
import es.propio.modeladoInfo.PronosticoPartido;

/**
 * @author nosotros
 * 
 */
public class Algoritmo2 extends AbstractAlgoritmo {

	@Override
	void calcularPronosticoPrimera() throws Exception {
		PronosticoJornada pronosticoJornada=new PronosticoJornada(1, IdAlgoritmoEnum.ALGORTIMO2);
		List<PronosticoPartido> pronosticos=new ArrayList<PronosticoPartido>();
		for(int i=1;i<=15;i++){
			PronosticoPartido pronostico=new PronosticoPartido();
			pronostico.setLocal(new Equipo("Celta"));
			pronostico.setVisitante(new Equipo("Malaga"));
			pronostico.setPosicionPartido(i);
			pronostico.setPorcentaje1(1F);
			pronostico.setPorcentajeX(0F);
			pronostico.setPorcentaje2(0F);
			pronosticos.add(pronostico);
		}
		pronosticoJornada.setPronosticoPartidos(pronosticos);
		setEstimacionJornadaPrimera(pronosticoJornada);
	}

	@Override
	void calcularPronosticoSegunda() throws Exception {
		PronosticoJornada pronosticoJornada=new PronosticoJornada(1, IdAlgoritmoEnum.ALGORTIMO2);
		List<PronosticoPartido> pronosticos=new ArrayList<PronosticoPartido>();
		for(int i=1;i<=15;i++){
			PronosticoPartido pronostico=new PronosticoPartido();
			pronostico.setLocal(new Equipo("Xerez"));
			pronostico.setVisitante(new Equipo("Hercules"));
			pronostico.setPosicionPartido(i);
			pronostico.setPorcentaje1(1F);
			pronostico.setPorcentajeX(0F);
			pronostico.setPorcentaje2(0F);
			pronosticos.add(pronostico);
		}
		pronosticoJornada.setPronosticoPartidos(pronosticos);
		setEstimacionJornadaPrimera(pronosticoJornada);
		
	}

}
