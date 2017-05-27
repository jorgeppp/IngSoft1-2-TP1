package tanques;

import simulacion.EstadoFinancieroYacimiento;

public class TanqueGas extends Tanque {

	EstadoFinancieroYacimiento estadoFinancieroYacimiento;
	/**
	 * 
	 * @param cantVender <= volumenOcupadoActual
	 * 
	 *Requiere cantVender <= volumenOcupadoActual
	 */
	public void vender(double cantVender) {
		volumenOcupadoActual = volumenOcupadoActual - cantVender;
		double ganancia = EstadoFinancieroYacimiento.getPrecioVentaGas() * cantVender;
		EstadoFinancieroYacimiento.incrementarGanancia(ganancia);
	}
	public TanqueGas(String model, int diasDeConstruccionTotal, double costoConstruccion, double volumenTotal, EstadoFinancieroYacimiento estadFinanc) {
		super(model, diasDeConstruccionTotal, costoConstruccion, volumenTotal);
		estadoFinancieroYacimiento = estadFinanc;
	}

}
