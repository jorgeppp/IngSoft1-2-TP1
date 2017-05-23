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
			List<TanqueGas> tanquesGas = sim.getYacimientoSimular().getTanquesGas();		
			for(TanqueGas tanq : tanquesGas){ //vacio todos los tanques
				tanq.vender(tanq.getVolumenOcupadoActual());
			}	
		}

	}

}
