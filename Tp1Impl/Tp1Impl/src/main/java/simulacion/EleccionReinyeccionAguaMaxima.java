package simulacion;

public class EleccionReinyeccionAguaMaxima extends CriteriosReinyeccion {

	@Override
	public boolean hayQueReinyectar(Simulador sim) {	
		for(Parcela par : sim.getYacimientoSimular().getParcelas()){
			if(par.getPozo().getPresionActual() < sim.getPresionCritica()){
				sim.getYacimientoSimular().reinyectarAguaMaxima(sim.getVolMaxReinyectarDia());
				return true;
			}
		}
		return false;
	}

}
