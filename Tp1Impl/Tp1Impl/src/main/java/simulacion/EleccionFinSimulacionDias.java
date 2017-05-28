
package simulacion;

public class EleccionFinSimulacionDias extends CriteriosFinalizacion {
	@Override
	public boolean hayQueFinalizarSimulacion(Simulador sim) {
		if(sim.getDia() > sim.getDiasMaximoSimulacion()){
			System.out.println("Se termino la máxima cantidad de días permitida");
			sim.loggear("Se termino la máxima cantidad de días permitida");
			System.out.println(sim.getYacimientoSimular().getReservorio().getVolumenDisponible());
			return true;
		}else if (sim.getYacimientoSimular().getReservorio().getVolumenDisponible() < MINIMO_VOL_DISP){
			System.out.println("Se llego al limite de volumen disponible");
			sim.loggear("Se llego al limite de volumen disponible");
			return true;
		}else{
			return false;
		}
	}

}