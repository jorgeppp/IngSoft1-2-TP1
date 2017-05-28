package simulacion;

public class EleccionReinyeccionAguaYGasTanques extends CriteriosReinyeccion {

	
	@Override
	public boolean hayQueReinyectar(Simulador sim) {
		for(Parcela par : sim.getYacimientoSimular().getParcelas()){
			if(par.getPozo().getPresionActual() < sim.getPresionCritica()){
				sim.getYacimientoSimular().reinyectarAguaYGasTanques(sim.getVolMaxReinyectarDia());
				sim.loggear("Se reinyecta agua y gas de los tanques");
				System.out.println("Se reinyecta agua y gas de los tanques");
				return true;
			}
		}
		return false;
	}
}
