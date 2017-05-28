package tanques;

import simulacion.EstadoFinancieroYacimiento;

public class TanqueGas extends Tanque implements Cloneable{

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
	
	   @Override
	public String toString() {
		return "TanqueGas (getVolumenTotal()=" + getVolumenTotal() + ", getVolumenOcupadoActual()="
				+ getVolumenOcupadoActual() + ", getModel()=" + getModel() + ", getDiasDeConstruccionActual()="
				+ getDiasDeConstruccionActual() + ", getDiasDeConstruccionTotal()=" + getDiasDeConstruccionTotal()
				+ ", getCostoConstruccion()=" + getCostoConstruccion() + ")";
	}
	
	   public Object clone() {
		      Object clone = null;
		      
		      try {
		         clone = super.clone();
		         
		      } catch (CloneNotSupportedException e) {
		         e.printStackTrace();
		      }
		      
		      return clone;
		   }

}
