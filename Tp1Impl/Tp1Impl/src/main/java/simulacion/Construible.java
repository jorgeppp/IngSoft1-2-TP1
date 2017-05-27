package simulacion;

public abstract class Construible {

	private String model;
	private int diasDeConstruccionTotal;
	private double costoConstruccion;
	private int diasDeConstruccionActual = 0;

	public Construible(String model, int diasDeConstruccionTotal, double costoConstruccion) {
		super();
		this.model = model;
		this.diasDeConstruccionTotal = diasDeConstruccionTotal;
		this.costoConstruccion = costoConstruccion;
	}
	
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
	
	public void construirDia() {
		diasDeConstruccionActual++;
	}
}
