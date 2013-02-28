package es.propio.modeladoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de los parámetros para usarlos en el análisis.
 * 
 */
public class GestorParametrosAnalisis {

	public GestorParametrosAnalisis() {
	}

	public static TuplaParametrosAnalisis getTuplaDefault() {
		TuplaParametrosAnalisis tupla = new TuplaParametrosAnalisis(
				ParametroNombre.GOLES_EN_CONTRA,
				ParametroNombre.DIFERENCIA_POSICIONES_EN_CLASIFICACION,
				ParametroNombre.GOLESFUERAAFAVOR,
				ParametroNombre.DIFERENCIADEGOLESAFAVOR);
		return tupla;

	}

	public static List<ParametroNombre> getParamsTipoIndividual() {
		List<ParametroNombre> lista = new ArrayList<ParametroNombre>();
		lista.add(ParametroNombre.NUMEROJORNADA);
		lista.add(ParametroNombre.JUEGA_EN_CASA);
		lista.add(ParametroNombre.JUEGA_FUERA);
		lista.add(ParametroNombre.NUMERO_UNOS_ANTERIORES);
		lista.add(ParametroNombre.NUMERO_EQUIS_ANTERIORES);
		lista.add(ParametroNombre.NUMERO_DOSES_ANTERIORES);
		lista.add(ParametroNombre.GOLESENCASAAFAVOR);
		lista.add(ParametroNombre.GOLESENCASAENCONTRA);
		lista.add(ParametroNombre.GOLESFUERAAFAVOR);
		lista.add(ParametroNombre.GOLESFUERAENCONTRA);
		lista.add(ParametroNombre.GOLESTOTALESAFAVOR);
		lista.add(ParametroNombre.GOLESTOTALESENCONTRA);
		lista.add(ParametroNombre.PUNTOSSIMPLES);
		lista.add(ParametroNombre.GANADOS);
		lista.add(ParametroNombre.EMPATADOS);
		lista.add(ParametroNombre.PERDIDOS);
		lista.add(ParametroNombre.PUNTOSNORMALES);
		lista.add(ParametroNombre.DIFERENCIADEGOLESENCONTRA);
		lista.add(ParametroNombre.DIFERENCIADEGOLESAFAVOR);
		lista.add(ParametroNombre.POSICION_EN_CLASIFICACION);
		lista.add(ParametroNombre.PARTIDOS_JUGADOS);
		lista.add(ParametroNombre.PARTIDOS_GANADOS);
		lista.add(ParametroNombre.PARTIDOS_EMPATADOS);
		lista.add(ParametroNombre.PARTIDOS_PERDIDOS);
		lista.add(ParametroNombre.GOLES_A_FAVOR);
		lista.add(ParametroNombre.GOLES_EN_CONTRA);
		lista.add(ParametroNombre.REMATES_EN_CONTRA);
		lista.add(ParametroNombre.JUGADORES_UTILIZADOS);
		lista.add(ParametroNombre.PARADAS_DEL_PORTERO);
		lista.add(ParametroNombre.AF_BALON_PARADO);
		lista.add(ParametroNombre.AF_CABEZA);
		lista.add(ParametroNombre.AF_FALTA_DIRECTA);
		lista.add(ParametroNombre.AF_JUGADA_COLECTIVA);
		lista.add(ParametroNombre.AF_JUGADA_INDIVIDUAL);
		lista.add(ParametroNombre.AF_PENALTY);
		lista.add(ParametroNombre.AF_PIE_IZQUIERDO);
		lista.add(ParametroNombre.AF_PIE_DERECHO);
		lista.add(ParametroNombre.EN_CONTRA);
		lista.add(ParametroNombre.GC_CABEZA);
		lista.add(ParametroNombre.GC_PENALTY);
		lista.add(ParametroNombre.GOLES_TITULAR);
		lista.add(ParametroNombre.GOLES_SUPLENTES);
		lista.add(ParametroNombre.REMATES_FAVOR);
		lista.add(ParametroNombre.RF_BALON_PARADO);
		lista.add(ParametroNombre.RF_CABEZA);
		lista.add(ParametroNombre.RF_FUERA);
		lista.add(ParametroNombre.RF_JUGADA_COLECTIVA);
		lista.add(ParametroNombre.RF_JUGADA_INDIVIDUAL);
		lista.add(ParametroNombre.RF_PENALTY);
		lista.add(ParametroNombre.RF_PIE_IZQUIERDO);
		lista.add(ParametroNombre.RF_PIE_DERECHO);
		lista.add(ParametroNombre.RF_POSTE);
		lista.add(ParametroNombre.RF_PORTERIA);
		lista.add(ParametroNombre.RC_CABEZA);
		lista.add(ParametroNombre.RC_FUERA);
		lista.add(ParametroNombre.RC_PORTERIA);
		lista.add(ParametroNombre.RC_POSTE);
		lista.add(ParametroNombre.TARJETAS_AMARILLAS);
		lista.add(ParametroNombre.TARJETAS_ROJAS);
		lista.add(ParametroNombre.PUNTOS_PONDERADOS);
		lista.add(ParametroNombre.PUNTOS_TENDENCIA);
		lista.add(ParametroNombre.PUNTOS_EN_CASA);
		lista.add(ParametroNombre.PUNTOS_FUERA);
		return lista;
	}

	public static List<ParametroNombre> getParamsTipoComparativo() {
		List<ParametroNombre> lista = new ArrayList<ParametroNombre>();
		lista.add(ParametroNombre.DIFERENCIA_POSICIONES_EN_CLASIFICACION);
		lista.add(ParametroNombre.DIFERENCIA_PUNTOS_PONDERADOS);
		lista.add(ParametroNombre.DIFERENCIA_PUNTOS_PARA_EMPATE);
		lista.add(ParametroNombre.DIFERENCIA_PUNTOS_SIMPLES);
		lista.add(ParametroNombre.DIFERENCIA_PUNTOS_TENDENCIA_PARA_EMPATE);
		lista.add(ParametroNombre.DIFERENCIA_PUNTOS);
		lista.add(ParametroNombre.FORTALEZA_LUGAR);
		lista.add(ParametroNombre.PROBABILIDAD_COMPENSAR_PARA_EMPATE);
		return lista;
	}

}
