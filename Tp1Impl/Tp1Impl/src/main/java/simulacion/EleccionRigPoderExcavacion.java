package simulacion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class EleccionRigPoderExcavacion extends CriteriosEleccionRig{
	
	public class ComparadorRigs implements Comparator<Rig>{
		public int compare(Rig r1, Rig r2){
			if(r1.getPoderEscavacionDia() < r2.getPoderEscavacionDia()){
				return -1;
			}else if(r1.getPoderEscavacionDia() == r2.getPoderEscavacionDia()){
				return 0;
			}else{
				return 1;
			}
		}
	}
	
	@Override
	public Rig alquilarRig(Simulador sim){
		System.out.println("alquilando riggg");
		System.out.println("antes de oredenarr ");
		System.out.println(sim.getListaRigsDisponibles());
		Collections.sort(sim.getListaRigsDisponibles(), new ComparadorRigs());
		System.out.println("luegoo " + sim.getListaRigsDisponibles());
		
		System.out.println(sim.getListaRigsDisponibles());
		List<Rig> rigsDisp = sim.getListaRigsDisponibles(); 
		System.out.println(rigsDisp);
		if(!rigsDisp.isEmpty()){
			return rigsDisp.remove(0);
		}else{
			return null; // es mejor utilizar el null object pattern
		}		
	}
}
