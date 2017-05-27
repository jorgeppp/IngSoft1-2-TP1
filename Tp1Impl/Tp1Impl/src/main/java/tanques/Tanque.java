package tanques;

import simulacion.Construible;

public abstract class Tanque extends Construible{
	
	public Tanque(String model, int diasDeConstruccionTotal, double costoConstruccion, double volumenTotal) {
		super(model, diasDeConstruccionTotal, costoConstruccion);
		this.volumenTotal = volumenTotal;
	}

	protected double volumenTotal;
	protected double volumenOcupadoActual = 0;
	
	public double getVolumenTotal() {
		return volumenTotal;
	}
	public double getVolumenOcupadoActual() {
		return volumenOcupadoActual;
	}
	
	public void extraerVol(double vol) {
		this.volumenOcupadoActual -= vol;
	}
	
	public void llenar(double vol) {
		volumenOcupadoActual += vol;
	}

	
}
