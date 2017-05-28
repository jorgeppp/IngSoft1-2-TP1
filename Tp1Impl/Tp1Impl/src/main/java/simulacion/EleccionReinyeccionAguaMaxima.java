package simulacion;

public class EleccionReinyeccionAguaMaxima extends CriteriosReinyeccion {

	@Override
	public boolean hayQueReinyectar(Simulador sim) {	
		for(Parcela par : sim.getYacimientoSimular().getParcelas()){
			if(par.getPozo().getPresionActual() > sim.getPresionCritica()){
				sim.getYacimientoSimular().reinyectarAguaMaxima(sim.getVolMaxReinyectarDia());
				sim.loggear("Se reinyecta agua, lo maximo permitido en un día");
				System.out.println("Se reinyecta agua, lo maximo permitido en un día");
				return true;
			}
		}
		return false;
	}

}
