package simulacion;

public class Pozo {
	
	EstadoFinancieroYacimiento estadoFinanciero;
	private double profundidadPozoTotal;
//	private double presionInicial;
	private double presionActual;
	private boolean construccionFinalizada = false;
	private Rig rigCabando = null; //mejor usar patron null object
	private double profundidadPozoActual = 0;

	
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
	
	
	public boolean cabarDia() {
		estadoFinanciero.decrementarGanancia(estadoFinanciero.getCostoCombustibleRigs());
		if(profundidadPozoActual + rigCabando.getPoderEscavacionDia() > profundidadPozoTotal){
			profundidadPozoActual = profundidadPozoTotal;
			construccionFinalizada = true;
			return true;
		}else{
			profundidadPozoActual += rigCabando.getPoderEscavacionDia();
			return false;
		}
		
	}
	
	
	public Pozo(double profundidadTotal, double presionInicial,EstadoFinancieroYacimiento estadoFinanciero){
		profundidadPozoTotal = profundidadTotal;
		presionActual = presionInicial;
	}

}
