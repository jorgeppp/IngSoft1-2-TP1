package simulacion;
public class Reservorio {

	private double volumenTotal;//es el volumen antes de iniciarse su explotaci�n
	private double volumenDisponible;
	private double porcPetroleo;
	private double porcGas;
	private double porcAgua;
	
	public double getVolumenTotal() {
		return volumenTotal;
	}
	public double getVolumenDisponible() {
		return volumenDisponible;
	}
	public double getPorcPetroleo() {
		return porcPetroleo;
	}
	public double getPorcGas() {
		return porcGas;
	}
	public double getPorcAgua() {
		return porcAgua;
	}
	
	
	/**
	 * Calcula los nuevos porcentages del Recervorio luego de una reinyeccion de agua
	 * Equivale a formula4
	 * @param volAguaReinyecc
	 */
	public void reinyectarAgua(double volAguaReinyecc){
		porcPetroleo = porcPetroleo * volumenDisponible/(volumenDisponible +volAguaReinyecc );
		porcGas = porcGas * volumenDisponible/(volumenDisponible +volAguaReinyecc );
		porcAgua = (porcAgua * volumenDisponible + 100*volAguaReinyecc)/(volumenDisponible +volAguaReinyecc ) ;
		volumenDisponible += volAguaReinyecc;
	}

	/**
	 * Calcula los nuevos porcentages del Recervorio luego de una reinyeccion de agua
	 * @param volGasReinyecc
	 */
	public void reinyectarGas(double volGasReinyecc){
		porcPetroleo = porcPetroleo * volumenDisponible/(volumenDisponible +volGasReinyecc );
		porcAgua = porcAgua * volumenDisponible/(volumenDisponible +volGasReinyecc );
		porcGas = (porcGas * volumenDisponible + 100*volGasReinyecc)/(volumenDisponible +volGasReinyecc ) ;
		volumenDisponible += volGasReinyecc;

	}
	
	/**
	 * Calcula los nuevos porcentages del Recervorio luego de una extraccion de agua
	 * @param volAguaExtraid
	 */
	public void extraerAgua(double volAguaExtraid){
		porcPetroleo = porcPetroleo * volumenDisponible/(volumenDisponible - volAguaExtraid );
		porcGas = porcGas * volumenDisponible/(volumenDisponible - volAguaExtraid );
		porcAgua = (porcAgua * volumenDisponible - 100*volAguaExtraid)/(volumenDisponible - volAguaExtraid ) ;
		volumenDisponible -= volAguaExtraid;

	}
	
	 /** Calcula los nuevos porcentages del Recervorio luego de una extraccion de gas
	 * @param volGasExtraid
	 */
	public void extraerGas(double volGasExtraid){
		porcPetroleo = porcPetroleo * volumenDisponible/(volumenDisponible -volGasExtraid );
		porcAgua = porcAgua * volumenDisponible/(volumenDisponible - volGasExtraid );
		porcGas = (porcGas * volumenDisponible - 100*volGasExtraid)/(volumenDisponible +volGasExtraid ) ;
		volumenDisponible -= volGasExtraid;

	}
	
	public void extraerProducto(double volProductoExtraid){
		volumenDisponible -= volProductoExtraid;
	}
	
	

}
