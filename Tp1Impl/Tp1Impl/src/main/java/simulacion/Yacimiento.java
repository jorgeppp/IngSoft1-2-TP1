package simulacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import plantaseparadoras.PlantaSeparadora;
import tanques.*;

public class Yacimiento {
	//datos yacimiento

	private int cantPozosActual = 0; ////pozos construidos 
	private int cantPozosConstruccion=0; //pozos construidos o en construccion

	private Reservorio reservorio;
	private List<Parcela> parcelas;
	private List<PlantaSeparadora> plantasSeparadoras;
	private List<TanqueAgua> tanquesAgua;
	private List<TanqueGas> tanquesGas;	
	private List<PlantaSeparadora> plantasSeparadorasConstruccion = new ArrayList<PlantaSeparadora>();
	private List<TanqueAgua> tanquesAguaConstruccion = new ArrayList<TanqueAgua>();
	private List<TanqueGas> tanquesGasConstruccion = new ArrayList<TanqueGas>();

	public void incrementarCantPozosConstruidos() {
		cantPozosConstruccion++;
	}
	
	public int getCantPozosConstruccion() {
		return cantPozosConstruccion;
	}
	
	private void formula3(double volDispYac,double volTotYac,double volReinyectado, Parcela parc ) {
		double nuevaPresion = ((volDispYac + volReinyectado )/volDispYac) * parc.getPozo().getPresionActual();
		parc.getPozo().setPresionActual(nuevaPresion);	
	}
	
	private void actualizarPresionesReinyeccion(double volAgua) {
		double volDispYac = reservorio.getVolumenDisponible();
		double volTotYac = reservorio.getVolumenTotal();
		//se actualizan las preciones de las parcelas
		for(Parcela parcel : parcelas){
			formula3(volDispYac,volTotYac,volAgua, parcel );
		}	
	}
	
	public void reinyectarAguaYGasTanques(double vol) {
		if(vol + reservorio.getVolumenDisponible() > reservorio.getVolumenTotal()){//inyecto el maximo
			vol = reservorio.getVolumenTotal() - reservorio.getVolumenDisponible();
		}
		double volumenAlmacenadoTanquesAgua = 0;
		for(TanqueAgua tanq : tanquesAgua){
			volumenAlmacenadoTanquesAgua += tanq.getVolumenOcupadoActual();
		}
		
		if(vol <= volumenAlmacenadoTanquesAgua){
			reinyectarAguaTanque(vol);
		}else{
			reinyectarAguaTanque(volumenAlmacenadoTanquesAgua);
			vol -= volumenAlmacenadoTanquesAgua;
			double volumenAlmacenadoTanquesGas = 0;
			for(TanqueGas tanq : tanquesGas){
				volumenAlmacenadoTanquesGas += tanq.getVolumenOcupadoActual();
			}
			if(vol <= volumenAlmacenadoTanquesAgua){
				reinyectarGasTanque(vol);
			}else{
				reinyectarGasTanque(volumenAlmacenadoTanquesAgua);
			}
		}//podria quedar algo de volumen sin reinyectar, y en caso de que los tanques esten vacios, no se reinyecta nada 
	}
	
	public void reinyectarAguaMaxima(double volAgua) {
		if(volAgua + reservorio.getVolumenDisponible() > reservorio.getVolumenTotal()){//inyecto el maximo
			volAgua = reservorio.getVolumenTotal() - reservorio.getVolumenDisponible();
		}
		double volumenAlmacenadoTanques = 0;
		for(TanqueAgua tanq : tanquesAgua){
			volumenAlmacenadoTanques += tanq.getVolumenOcupadoActual();
		}
		
		if(volAgua <= volumenAlmacenadoTanques){
			reinyectarAguaTanque(volAgua);
		}else{
			reinyectarAguaTanque(volumenAlmacenadoTanques);
			reinyectarAguaComprada(volAgua - volumenAlmacenadoTanques );
		}
	}
	
	/**
	 * @param volAgua
	 * Requiere (volAgua + volumenDisponible de Recervorio) < volumenTotal de Recervorio
	 */
	public void reinyectarAguaComprada(double volAgua) {
		actualizarPresionesReinyeccion(volAgua);
		reservorio.reinyectarAgua(volAgua);
	}
	
	/**
	 * @param volAgua
	 * Requiere (volAgua + volumenDisponible de Recervorio) < volumenTotal de Recervorio
	 * Requiere volAgua  <= suma del agua almacenada en tanques de agua
	 */
	public void reinyectarAguaTanque(double volAgua) {
		double volAReinyectar = volAgua;
		Iterator<TanqueAgua> itList = tanquesAgua.iterator();
		while(itList.hasNext() && volAReinyectar>0 ){
			TanqueAgua tanqueAct = itList.next();
			if(volAReinyectar > tanqueAct.getVolumenOcupadoActual()){
				volAReinyectar -= tanqueAct.getVolumenOcupadoActual();
				tanqueAct.extraerVol(tanqueAct.getVolumenOcupadoActual());
			}else{
				tanqueAct.extraerVol(volAReinyectar);
				volAReinyectar = 0;
			}	
		}
		if(volAReinyectar == 0){
			actualizarPresionesReinyeccion(volAgua);
			reservorio.reinyectarAgua(volAgua);
		}else{
			System.err.println("No se puede reinyectar esa cantidad de agua de los tanques");
		}
	}
	
	/**
	 * @param volGas
	 * Requiere (volGas + volumenDisponible de Recervorio) < volumenTotal de Recervorio
	 * Requiere volGas  < suma del agua almacenada en tanques de gas
	 */
	public void reinyectarGasTanque(double volGas) {
		double volAReinyectar = volGas;
		Iterator<TanqueGas> itList = tanquesGas.iterator();
		while(itList.hasNext() && volAReinyectar>0 ){
			TanqueGas tanqueAct = itList.next();
			if(volAReinyectar > tanqueAct.getVolumenOcupadoActual()){
				volAReinyectar -= tanqueAct.getVolumenOcupadoActual();
				tanqueAct.extraerVol(tanqueAct.getVolumenOcupadoActual());
			}else{
				tanqueAct.extraerVol(volAReinyectar);
				volAReinyectar = 0;
			}	
		}	
		if(volAReinyectar == 0){
			actualizarPresionesReinyeccion(volGas);
			reservorio.reinyectarGas(volGas);
		}else{
			System.err.println("No se puede reinyectar esa cantidad de gas de los tanques");
		}	
	}
	
	public void formula2(double volDispYac,double volTotYac, double presionPozoActual,int numPozosActual, Parcela parc ) {
		double beta = 0.1*(volDispYac/volTotYac)/(Math.pow(cantPozosActual, ((double)2/3)));
		parc.getPozo().setPresionActual(presionPozoActual*Math.pow(Math.E, -beta));	
	}
	
	
	public int getCantPozosActual() {
		return cantPozosActual;
	}
	
	public void incrementarCantPozosConstuidos(){
		cantPozosActual++;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	
	public List<PlantaSeparadora> getPlantasSeparadoras() {
		return plantasSeparadoras;
	}

	public List<TanqueAgua> getTanquesAgua() {
		return tanquesAgua;
	}

	public List<TanqueGas> getTanquesGas() {
		return tanquesGas;
	}

	public Reservorio getReservorio() {
		return reservorio;
	}

	public List<PlantaSeparadora> getPlantasSeparadorasConstruccion() {
		return plantasSeparadorasConstruccion;
	}

	public List<TanqueAgua> getTanquesAguaConstruccion() {
		return tanquesAguaConstruccion;
	}

	public List<TanqueGas> getTanquesGasConstruccion() {
		return tanquesGasConstruccion;
	}


}
