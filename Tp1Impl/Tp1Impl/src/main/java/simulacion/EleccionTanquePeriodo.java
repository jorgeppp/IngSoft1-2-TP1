package simulacion;

import java.util.Random;

import tanques.TanqueAgua;
import tanques.TanqueGas;

public class EleccionTanquePeriodo extends CriteriosConstruccionTanques{
	private int periodoConstuccion;
	public EleccionTanquePeriodo(int periodoConstuccion) {
		super();
		this.periodoConstuccion = periodoConstuccion;
	}
	@Override
	public void construirTanquesNuevos(Simulador sim) {
		Random aleat =  new Random();
		int val = aleat.nextInt(2);
		if(sim.getDia() % periodoConstuccion == 0){
			if(val ==0 ){
				TanqueAgua tanqueAguaNuev = sim.getTanquesFactory().obtenerTanqAguaRandom();
				EstadoFinancieroYacimiento.decrementarGanancia(tanqueAguaNuev.getCostoConstruccion());
				System.out.println("Se empieza la construción de un tanque de agua " + tanqueAguaNuev);
				sim.loggear("Se empieza la construción de un tanque de agua " +tanqueAguaNuev);
				sim.getYacimientoSimular().getTanquesAguaConstruccion().add(tanqueAguaNuev);		
			}else{
				TanqueGas tanqueGasNuev = sim.getTanquesFactory().obtenerTanqGasRandom();
				EstadoFinancieroYacimiento.decrementarGanancia(tanqueGasNuev.getCostoConstruccion());
				System.out.println("Se empieza la construción de un tanque de gas " +tanqueGasNuev);
				sim.loggear("Se empieza la construción de un tanque de gas " +tanqueGasNuev);
				sim.getYacimientoSimular().getTanquesGasConstruccion().add(tanqueGasNuev);
			}
		}
	}
}
