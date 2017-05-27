package simulacion;

import java.util.Random;

public class EleccionTanquePeriodo extends CriteriosConstruccionTanques{
	private int periodoConstuccion;
	public EleccionTanquePeriodo(int periodoConstuccion) {
		super();
		this.periodoConstuccion = periodoConstuccion;
	}
	@Override
	public void construirTanquesNuevos(Simulador sim) {
		Random aleat =  new Random();
		int val = aleat.nextInt(1);
		if(sim.getDia() % periodoConstuccion == 0){// construir un planta
			if(val ==0 ){
				sim.getYacimientoSimular().getTanquesAguaConstruccion().add(sim.getTanquesFactory().obtenerTanqAguaRandom());
			}else{
				sim.getYacimientoSimular().getTanquesGasConstruccion().add(sim.getTanquesFactory().obtenerTanqGasRandom());
			}
		}
	}
}
