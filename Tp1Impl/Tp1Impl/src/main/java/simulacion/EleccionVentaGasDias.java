package simulacion;

import java.util.List;

import tanques.TanqueGas;

public class EleccionVentaGasDias extends CriteriosVentaGas{	
	private int dias;
	
	public EleccionVentaGasDias(int dias ){
		this.dias = dias;
	}

	@Override
	public void venderGas(Simulador sim) {
		if(sim.getDia() % dias == 0){
			sim.loggear("Se vende el gas de los tanques");
			System.out.println("Se vende el gas de los tanques");
			System.out.println(sim.getYacimientoSimular().getTanquesGas());
			List<TanqueGas> tanquesGas = sim.getYacimientoSimular().getTanquesGas();		
			for(TanqueGas tanq : tanquesGas){ //vacio todos los tanques
				tanq.vender(tanq.getVolumenOcupadoActual());
				System.out.println(sim.getYacimientoSimular().getTanquesGas());
			}	
		}
	}

}
