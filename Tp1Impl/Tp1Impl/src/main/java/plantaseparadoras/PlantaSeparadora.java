package plantaseparadoras;

public class PlantaSeparadora{
	
	
	private String model;
	private int diasDeConstruccionActual;
	private int diasDeConstruccionTotal;
	private double costoConstruccion;
	private double poderProcesamientoDia;
	private double cantidadProcesamientoRestante;
	
	public String getModel() {
		return model;
	}
	public int getDiasDeConstruccionActual() {
		return diasDeConstruccionActual;
	}
	public int getDiasDeConstruccionTotal() {
		return diasDeConstruccionTotal;
	}
	public double getCostoConstruccion() {
		return costoConstruccion;
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
	
	public void construirDia() {
		this.diasDeConstruccionActual++;
	}
	
	
	
	PlantaSeparadora(){
		
	}


}
