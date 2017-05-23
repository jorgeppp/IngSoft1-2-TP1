package simulacion;

public class EleccionFinSimulacionDilucionCritica extends CriteriosFinalizacion {
	@Override
	public boolean hayQueFinalizarSimulacion(Simulador sim) {
		return ( sim.getDilucionCritica() > sim.getYacimientoSimular().getReservorio().getPorcPetroleo() || sim.getDia() > sim.getDiasMaximoSimulacion());
	}

}
