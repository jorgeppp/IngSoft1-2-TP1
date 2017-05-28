package simulacion;

import java.util.Collections;
import java.util.Comparator;

public class EleccionParcelaMenorProfundidad extends CriteriosEleccionParcela{
	private boolean seOrdeno = false;	
	public class ComparadorParcela implements Comparator<Parcela>{
		public int compare(Parcela p1, Parcela p2){
			if(p1.getPozo().getProfundidadPozoTotal() < p2.getPozo().getProfundidadPozoTotal()){
				return -1;
			}else if(p1.getPozo().getProfundidadPozoTotal() == p2.getPozo().getProfundidadPozoTotal()){
				return 0;
			}else{
				return 1;
			}	
		}
		
	}
	@Override
	public Parcela dameParcela(Simulador sim){
		if(!seOrdeno){
			System.out.println("parcelas antes de ordenar " + sim.getYacimientoSimular().getParcelas());
			Collections.sort(sim.getYacimientoSimular().getParcelas(), new ComparadorParcela());
			System.out.println("parcelas DESPUES de ordenar " + sim.getYacimientoSimular().getParcelas());
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
