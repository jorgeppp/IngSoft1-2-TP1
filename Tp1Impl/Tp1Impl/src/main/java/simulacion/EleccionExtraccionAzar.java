package simulacion;

import java.util.Random;

public class EleccionExtraccionAzar extends CriteriosExtraccionPozos {
	@Override
	public void abrirValbulas(Simulador sim) {
		Random aleat =  new Random();
		for(Parcela par : sim.getYacimientoSimular().getParcelas()){
			if(aleat.nextInt(1) == 0){
				par.setValvulaExtraer(false);
			}else{
				par.setValvulaExtraer(true);
			}
		}
	}
}
