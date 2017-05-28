package simulacion;

public class EleccionFinSimulacionDilucionCritica extends CriteriosFinalizacion {
	@Override
	public boolean hayQueFinalizarSimulacion(Simulador sim) {
		if ( sim.getDilucionCritica() > sim.getYacimientoSimular().getReservorio().getPorcPetroleo()){
			System.out.println("El porcentaje de petróleo esta por debajo de la dilución crítica");
			sim.loggear("El porcentaje de petróleo esta por debajo de la dilución crítica");
			return true;
		}else if(sim.getDia() > sim.getDiasMaximoSimulacion()){
			System.out.println("Se termino la máxima cantidad de días permitida");
			sim.loggear("Se termino la máxima cantidad de días permitida");
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
