package plantaseparadoras;

import simulacion.Construible;

public class PlantaSeparadora extends Construible implements Cloneable{
		

	private double poderProcesamientoDia;
	private double cantidadProcesamientoRestante;
	
	public PlantaSeparadora(String model, int diasDeConstruccionTotal, double costoConstruccion, double poderProcesamientoDia) {
		super(model, diasDeConstruccionTotal, costoConstruccion);
		this.poderProcesamientoDia = poderProcesamientoDia;
		this.cantidadProcesamientoRestante = poderProcesamientoDia;
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
	
	public double getPoderProcesamientoDia() {
		return poderProcesamientoDia;
	}
	public double getCantidadProcesamientoRestante() {
		return cantidadProcesamientoRestante;
	}
	public void resetearPoderProcesamiento() {
		this.cantidadProcesamientoRestante = poderProcesamientoDia;
	}
	@Override
	public String toString() {
		return "PlantaSeparadora (poderProcesamientoDia=" + poderProcesamientoDia + ", cantidadProcesamientoRestante="
				+ cantidadProcesamientoRestante + ", " + super.toString() + ")";
	}

	public void procesar(double cantidadProcesamiento) {
		this.cantidadProcesamientoRestante -= cantidadProcesamiento;
	}
	


}
