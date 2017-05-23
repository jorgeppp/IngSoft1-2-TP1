package simulacion;

import java.util.List;
import java.util.Random;

public class EleccionRigAzar extends CriteriosEleccionRig {

	@Override
	public Rig alquilarRig(Simulador sim) {
		List<Rig> rigsDisp = EstadoFinancieroYacimiento.getListaRigsDisponibles();

		
		if(!rigsDisp.isEmpty()){
			Random aleat =  new Random();
			int index = aleat.nextInt(rigsDisp.size());		 
			Rig rigAlquilado = rigsDisp.remove(index);			
			double costoAlquiler = rigAlquilado.getCostoAlquilerDia() * rigAlquilado.getMinDiasPagarOblig();
			EstadoFinancieroYacimiento.decrementarGanancia(costoAlquiler);
			return rigAlquilado;
		}else{
			return null; // es mejor utilizar el null object pattern
		}
	}

}
