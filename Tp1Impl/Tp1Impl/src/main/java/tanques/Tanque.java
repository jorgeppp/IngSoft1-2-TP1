package tanques;

public abstract class Tanque {

	protected int diasDeConstruccionActual;
	protected int diasDeConstruccionTotal;
	protected double costoConstruccion;
	protected double volumenTotal;
	protected double volumenOcupadoActual;
	protected String modelo;
	
	public double getVolumenTotal() {
		return volumenTotal;
	}
	public double getVolumenOcupadoActual() {
		return volumenOcupadoActual;
	}
	
	public double getCostoConstruccion() {
		return costoConstruccion;
	}
	
	public void extraerVol(double vol) {
		this.volumenOcupadoActual -= vol;
	}
	
	public void llenar(double vol) {
		volumenOcupadoActual += vol;
	}
	public String getModelo() {
		return modelo;
	}

	
}
