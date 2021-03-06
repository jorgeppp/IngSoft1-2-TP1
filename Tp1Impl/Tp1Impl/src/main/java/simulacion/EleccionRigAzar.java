package simulacion;

import java.util.List;
import java.util.Random;

public class EleccionRigAzar extends CriteriosEleccionRig {

	@Override
	public Rig alquilarRig(Simulador sim) {
		List<Rig> rigsDisp = sim.getListaRigsDisponibles();
		System.out.println("Rigs antes de alquilarrr " + sim.getListaRigsDisponibles());
		if(!rigsDisp.isEmpty()){
			Random aleat =  new Random();
			int index = aleat.nextInt(rigsDisp.size());		 
			Rig rigAlquilado = rigsDisp.remove(index);
			System.out.println("Rigs LUEGO de alquilarrr " + sim.getListaRigsDisponibles());
			double costoAlquiler = rigAlquilado.getCostoAlquilerDia() * rigAlquilado.getMinDiasPagarOblig();
			EstadoFinancieroYacimiento.decrementarGanancia(costoAlquiler);
			return rigAlquilado;
		}else{
			return null; // es mejor utilizar el null object pattern
		}
	}
}
