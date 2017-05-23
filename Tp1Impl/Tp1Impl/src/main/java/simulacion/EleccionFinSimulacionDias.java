
package simulacion;

public class EleccionFinSimulacionDias extends CriteriosFinalizacion {
	@Override
	public boolean hayQueFinalizarSimulacion(Simulador sim) {
		return ( sim.getDia() > sim.getDiasMaximoSimulacion());
	}

}