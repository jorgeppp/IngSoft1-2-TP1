package simulacion;

public class Rig {

	private double poderEscavacionDia;
	private double costoAlquilerDia;
	private int minDiasPagarOblig;
	private double consumCombustDia;
	
	public Rig(double poderEscavacionDia, double costoAlquilerDia, int minDiasPagarOblig, double consumCombustDia) {
		super();
		this.poderEscavacionDia = poderEscavacionDia;
		this.costoAlquilerDia = costoAlquilerDia;
		this.minDiasPagarOblig = minDiasPagarOblig;
		this.consumCombustDia = consumCombustDia;
	}
	
	
	public double getPoderEscavacionDia() {
		return poderEscavacionDia;
	}

	public double getCostoAlquilerDia() {
		return costoAlquilerDia;
	}

	public int getMinDiasPagarOblig() {
		return minDiasPagarOblig;
	}

	public double getConsumCombustDia() {
		return consumCombustDia;
	}



}
