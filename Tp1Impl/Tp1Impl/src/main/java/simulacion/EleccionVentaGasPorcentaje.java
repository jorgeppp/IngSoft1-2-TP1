package simulacion;

import java.util.List;

import tanques.TanqueGas;

public class EleccionVentaGasPorcentaje extends CriteriosVentaGas{
	
	private double porcentaje;
	
	public EleccionVentaGasPorcentaje(double porcentaje ){
		this.porcentaje = porcentaje;
	}

	
	@Override
	public void venderGas(Simulador sim) {
		List<TanqueGas> tanquesGas = sim.getYacimientoSimular().getTanquesGas();		
		for(TanqueGas tanq : tanquesGas){
			if(tanq.getVolumenOcupadoActual()/tanq.getVolumenTotal() > porcentaje/100 ){
				tanq.vender(tanq.getVolumenOcupadoActual());//vacio el tanque
			}
		}	
	}
}
