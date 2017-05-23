package simulacion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import simulacion.EleccionParcelaMenorProfundidad.ComparadorParcela;

public class EleccionRigPoderExcavacion extends CriteriosEleccionRig{
	
	private boolean seOrdeno = false;
	
	public class ComparadorRigs implements Comparator<Rig>{
		public int compare(Rig r1, Rig r2){
			if(r1.getPoderEscavacionDia() < r2.getPoderEscavacionDia()){
				return 1;
			}else{
				return 0;
			}
		}
		
	}
	
	@Override
	public Rig alquilarRig(Simulador sim){
		if(!seOrdeno){
			Collections.sort(EstadoFinancieroYacimiento.getListaRigsDisponibles(), new ComparadorRigs());
			seOrdeno =true;
		}
		
		List<Rig> rigsDisp = EstadoFinancieroYacimiento.getListaRigsDisponibles(); 

		if(!rigsDisp.isEmpty()){
			return rigsDisp.remove(0);
		}else{
			return null; // es mejor utilizar el null object pattern
		}		
	}
}
