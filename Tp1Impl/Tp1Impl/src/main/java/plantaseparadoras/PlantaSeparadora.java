package plantaseparadoras;

import simulacion.Construible;

public class PlantaSeparadora extends Construible{
		

	private double poderProcesamientoDia;
	private double cantidadProcesamientoRestante = poderProcesamientoDia;
	
	public PlantaSeparadora(String model, int diasDeConstruccionTotal, double costoConstruccion, double poderProcesamientoDia) {
		super(model, diasDeConstruccionTotal, costoConstruccion);
		this.poderProcesamientoDia = poderProcesamientoDia;
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
	public void procesar(double cantidadProcesamiento) {
		this.cantidadProcesamientoRestante -= cantidadProcesamiento;
	}
	


}
