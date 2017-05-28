package simulacion;

public class Pozo {
	
	@Override
	public String toString() {
		return "Pozo (profundidadPozoTotal=" + profundidadPozoTotal
				+ ", presionActual=" + presionActual + ", construccionFinalizada=" + construccionFinalizada
				+ ", rigCabando=" + rigCabando + ", profundidadPozoActual=" + profundidadPozoActual + ")";
	}


	EstadoFinancieroYacimiento estadoFinanciero;
	private double profundidadPozoTotal;
//	private double presionInicial;
	private double presionActual;
	private int cantDiasConstruccionActual = 0;
	private boolean construccionFinalizada = false;
	
	private static final double VELARCI =1.1;
	private static final double VELROC =0.6;
	

	private Rig rigCabando = null; //mejor usar patron null object
	private double profundidadPozoActual = 0;

	
	public Rig getRigCabando() {
		return rigCabando;
	}
	
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
	
	
	public boolean cabarDia(TipoDeTerreno tipTerr) {
		EstadoFinancieroYacimiento.decrementarGanancia(EstadoFinancieroYacimiento.getCostoCombustibleRigs());
		if(profundidadPozoActual + rigCabando.getPoderEscavacionDia() > profundidadPozoTotal){
			profundidadPozoActual = profundidadPozoTotal;
			construccionFinalizada = true;
			return true;
		}else{
	        switch (tipTerr) {
            case ARCILLOSO :
            	profundidadPozoActual += VELARCI * rigCabando.getPoderEscavacionDia();
                break;
            case NORMAL:
            	System.out.println("normall");
            	profundidadPozoActual += rigCabando.getPoderEscavacionDia();
                break;
            case ROCOSO:
            	profundidadPozoActual += VELROC * rigCabando.getPoderEscavacionDia();
                break;
	        }
	        if(cantDiasConstruccionActual > rigCabando.getMinDiasPagarOblig() ){
	        	EstadoFinancieroYacimiento.decrementarGanancia(rigCabando.getCostoAlquilerDia());
	        }
	        cantDiasConstruccionActual++;
			return false;
		}
		
	}
	
	
	public Pozo(double profundidadTotal, double presionInicial,EstadoFinancieroYacimiento estadoFinanciero){
		profundidadPozoTotal = profundidadTotal;
		presionActual = presionInicial;
		this.estadoFinanciero = estadoFinanciero;
	}

}
