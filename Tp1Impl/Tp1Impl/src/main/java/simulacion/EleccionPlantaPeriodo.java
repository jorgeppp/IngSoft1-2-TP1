package simulacion;

import plantaseparadoras.PlantaSeparadora;

public class EleccionPlantaPeriodo extends CriteriosConstruccionPlantas {
	
	private int periodoConstuccion;

	public EleccionPlantaPeriodo(int periodoConstuccion) {
		super();
		this.periodoConstuccion = periodoConstuccion;
	}

	@Override
	public void construirPlantasNuevas(Simulador sim) {
		if(sim.getDia() % periodoConstuccion == 0){// construir un planta
			PlantaSeparadora plantNuev = sim.getPlantasFactory().obtenerPlantaRandom();
			System.out.println("Se empieza la construción de una planta nueva " + plantNuev);
			sim.loggear("Se empieza la construción de una planta nueva " + plantNuev);
			EstadoFinancieroYacimiento.decrementarGanancia(plantNuev.getCostoConstruccion());
			sim.getYacimientoSimular().getPlantasSeparadorasConstruccion().add(plantNuev);			
		}
	}

}
