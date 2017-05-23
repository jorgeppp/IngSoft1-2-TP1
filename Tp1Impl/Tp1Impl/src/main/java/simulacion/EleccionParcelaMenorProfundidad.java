package simulacion;

import java.util.Collections;
import java.util.Comparator;

public class EleccionParcelaMenorProfundidad extends CriteriosEleccionParcela{
	private boolean seOrdeno = false;	
	public class ComparadorParcela implements Comparator<Parcela>{
		public int compare(Parcela p1, Parcela p2){
			if(p1.getPozo().getProfundidadPozoTotal() < p2.getPozo().getProfundidadPozoTotal()){
				return 1;
			}else{
				return 0;
			}
		}
		
	}
	@Override
	public Parcela dameParcela(Simulador sim){
		if(!seOrdeno){
			Collections.sort(sim.getYacimientoSimular().getParcelas(), new ComparadorParcela());
			seOrdeno =true;
		}
		for(Parcela par : sim.getYacimientoSimular().getParcelas()){
			if(!par.isSeConstruyePozo()){
				return par;
			}		
		}
		return null; // seria mejor crear las clases Parela real y parcela vacia
		
	}


}
