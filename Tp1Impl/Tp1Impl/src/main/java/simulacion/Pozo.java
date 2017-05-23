package simulacion;

public class Pozo {
	
	EstadoFinancieroYacimiento estadoFinanciero;
	private double profundidadPozoTotal;
	private double profundidadPozoActual = 0;
//	private double presionInicial;
	private double presionActual;
	private boolean construccionFinalizada = false;
	private Rig rigCabando;
	
	
	public void setRigCabando(Rig rigCabando) {
		this.rigCabando = rigCabando;
	}
	
	public double getProfundidadPozoTotal() {
		return profundidadPozoTotal;
	}
	public double getProfundidadPozoActual() {
		return profundidadPozoActual;
	}

	public double getPresionActual() {
		return presionActual;
	}

	public boolean seTerminoConstruccion(){
		return construccionFinalizada;
	}
	
	public void setPresionActual(double presionActual) {
		this.presionActual = presionActual;
	}
	
	
	public void cabarDia() {
		estadoFinanciero.decrementarGanancia(estadoFinanciero.getCostoCombustibleRigs());
		if(profundidadPozoActual + rigCabando.getPoderEscavacionDia() > profundidadPozoTotal){
			profundidadPozoActual = profundidadPozoTotal;
			construccionFinalizada = true;
		}else{
			profundidadPozoActual += rigCabando.getPoderEscavacionDia();
		}
		
	}
	
	
	public Pozo(double profundidadTotal, double presionInicial){
		profundidadPozoTotal = profundidadTotal;
		presionActual = presionInicial;
	}

}
