package simulacion;

public class EleccionPlantaPeriodo extends CriteriosConstruccionPlantas {
	
	private int periodoConstuccion;
	@Override
	public void construirPlantasNuevas(Simulador sim) {
		if(sim.getDia() % periodoConstuccion == 0){// construir un planta
			sim.getYacimientoSimular().getPlantasSeparadorasConstruccion().add(EstadoFinancieroYacimiento.getPlantasFactory().obtenerPlantaRandom());
		}
	}

}