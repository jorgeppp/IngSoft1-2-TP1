package simulacion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import plantaseparadoras.PlantaSeparadora;
import plantaseparadoras.PlantasFactory;
import tanques.*;

public class Simulador {
	//datos simulacion
	private double alpha1;
	private double alpha2;
	private int cantPozosARealizar;
	private int cantMaxRIGSSimultaneo;
	private double volMaxReinyectarDia;
	private double presionCritica;
	private int dilucionCritica;
	private int diasMaximoSimulacion;
	
	private Yacimiento yacimientoSimular;

	private EstadoFinancieroYacimiento estadoFinanciero;
	private EquipoIngenieria equipoIngenieria;
	
	private static PlantasFactory plantasFactory;
	private static TanquesFactory tanquesFactory;
	private static List<Rig> listaRigsDisponibles;
	
	private int dia = 0;
	private String loggeo = "";

	
	public PlantasFactory getPlantasFactory() {
		return plantasFactory;
	}
	public TanquesFactory getTanquesFactory() {
		return tanquesFactory;
	}
	public List<Rig> getListaRigsDisponibles() {
		return listaRigsDisponibles;
	}
	public int getDiasMaximoSimulacion() {
		return diasMaximoSimulacion;
	}

	
	public double getVolMaxReinyectarDia() {
		return volMaxReinyectarDia;
	}
	
	public double getPresionCritica() {
		return presionCritica;
	}
	
	public int getDilucionCritica() {
		return dilucionCritica;
	}

	public EquipoIngenieria getEquipoIngenieria() {
		return equipoIngenieria;
	}
	public int getCantPozosARealizar() {
		return cantPozosARealizar;
	}	
	public int getCantMaxRIGSSimultaneo() {
		return cantMaxRIGSSimultaneo;
	}
	public Yacimiento getYacimientoSimular() {
		return yacimientoSimular;
	}
	public EstadoFinancieroYacimiento getEstadoFinanciero() {
		return estadoFinanciero;
	}

	public int getDia() {
		return dia;
	}

	private double formula1(double alpha1,double alpha2, double presionPozoActual,int numPozosActual ) {
		double num = presionPozoActual/numPozosActual;
		double volExtraid = alpha1*num + alpha2*Math.pow(num, 2);
		return volExtraid;
	}
	
	/**
	 * Esta funcion actualiza el estado de las presiones de las parcelas
	 * y el volumen del reservorio, y ademas retorna el volumen diario extraido
	 * 
	 */
	private void extraerPetroleoDia() {
		double volExtrTot = 0;

		for(Parcela parcAct : yacimientoSimular.getParcelas()){
			//se extrae producto del pozo, en el caso de que este terminado y lo decida el equipo ingenieria
			if(parcAct.getPozo().seTerminoConstruccion() && parcAct.isValvulaExtraer() ){	
				double volExtraid = formula1(alpha1,alpha2, parcAct.getPozo().getPresionActual(),yacimientoSimular.getCantPozosActual() );
				//trato de meter todo lo extraido del pozo a las plantas separadoras del mismo
				Iterator<PlantaSeparadora> itPlant = parcAct.getPlantasSeparadorasParcela().iterator();
				while(volExtraid >0 && itPlant.hasNext()){
					PlantaSeparadora plantAct = itPlant.next();
					double cantProcRest = plantAct.getCantidadProcesamientoRestante();
					if(cantProcRest != 0){
						if(cantProcRest != 0 && volExtraid > cantProcRest){//se llena esa planta
							volExtraid -= cantProcRest;
							volExtrTot += cantProcRest;
							plantAct.procesar(cantProcRest);
						}else{//extraigo lo que queda, y la planta no se llena
							volExtrTot += volExtraid;
							plantAct.procesar(volExtraid);
							volExtraid = 0;
						}
					}
				}
			}
		}
		
		double volGasExtraido = volExtrTot * yacimientoSimular.getReservorio().getPorcGas()/100;
		double volAguaExtraido = volExtrTot * yacimientoSimular.getReservorio().getPorcAgua()/100;
		double volPetroleoExtraido = volExtrTot - volGasExtraido - volAguaExtraido;
		
		double volGasExtraidoRestante = volGasExtraido;
		for(Tanque tanq:  yacimientoSimular.getTanquesGas()){
			double volDispTanq = (tanq.getVolumenTotal() - tanq.getVolumenOcupadoActual());
			if(volGasExtraidoRestante > volDispTanq){
				volGasExtraidoRestante -= volDispTanq;
				tanq.llenar(volDispTanq);
			}else{
				tanq.llenar(volGasExtraidoRestante);
				volGasExtraidoRestante = 0;
				break;
			}
		}
		double volAguaExtraidoRestante = volAguaExtraido;
		for(Tanque tanq:  yacimientoSimular.getTanquesAgua()){
			double volDispTanq = (tanq.getVolumenTotal() - tanq.getVolumenOcupadoActual());
			if(volAguaExtraidoRestante > volDispTanq){
				volAguaExtraidoRestante -= volDispTanq;
				tanq.llenar(volDispTanq);
			}else{
				tanq.llenar(volAguaExtraidoRestante);
				volAguaExtraidoRestante = 0;
				break;
			}		
		}
		
		//actualizo los valores del reservorio del yacimiento
		if(volAguaExtraidoRestante ==0  && volGasExtraidoRestante==0 ){
			yacimientoSimular.getReservorio().extraerProducto(volExtrTot);
		}else{
			double volAguaExtraidoFinal = volAguaExtraido -volAguaExtraidoRestante;
			double volGasExtraidoFinal = volGasExtraido - volGasExtraidoRestante;
			yacimientoSimular.getReservorio().extraerAgua(volAguaExtraidoFinal);
			yacimientoSimular.getReservorio().extraerGas(volGasExtraidoFinal);
		}
		
		//se actualizan los valores de las presiones
		for(Parcela parcel : yacimientoSimular.getParcelas()){
			yacimientoSimular.formula2(yacimientoSimular.getReservorio().getVolumenDisponible(),yacimientoSimular.getReservorio().getVolumenTotal(), parcel.getPozo().getPresionActual(),yacimientoSimular.getCantPozosActual(), parcel );
		}
		
	}
	


	public void execute() {
		loggeo +="Inicia Simulación\r\n";
		System.out.println("Inicia Simulación");
		while(true){
			
			if(equipoIngenieria.getCriterioFinalizacion().hayQueFinalizarSimulacion(this)){
				loggeo += "Fin Simulación\r\n";
				System.out.println("Fin Simulación");
				return;
			}
			//las plantas separadoras vuelven a tener su capacidad original
			for(PlantaSeparadora plantSep : yacimientoSimular.getPlantasSeparadoras()){
				plantSep.resetearPoderProcesamiento();
			}
			
			if(!equipoIngenieria.getCriterioReinyeccion().hayQueReinyectar(this)){
				extraerPetroleoDia();
			}
			
			equipoIngenieria.getCriterioVentaGas().venderGas(this);
			
			//costruir pozos
			for(Parcela parc : yacimientoSimular.getParcelas()){
				if(parc.isSeConstruyePozo()){
					if(parc.getPozo().cabarDia()){
						yacimientoSimular.incrementarCantPozosConstuidos();
					}
				}
			}		
			equipoIngenieria.getCriterioConstruccionPozos().construirPozosNuevos(this);
			
			//construir plantasSeparadoras
			Iterator<PlantaSeparadora> itPlant = yacimientoSimular.getPlantasSeparadorasConstruccion().iterator();
			while(itPlant.hasNext()){
				PlantaSeparadora plantAct = itPlant.next();
				plantAct.construirDia();
				if(plantAct.getDiasDeConstruccionActual() == plantAct.getDiasDeConstruccionTotal()){
					yacimientoSimular.getPlantasSeparadoras().add(plantAct);
					itPlant.remove();
				}
			}
			equipoIngenieria.getCriterioConstruccionPlantas().construirPlantasNuevas(this);
			
			
			//construir Tanques	
			Iterator<TanqueAgua> itTanqAgua = yacimientoSimular.getTanquesAguaConstruccion().iterator();
			while(itTanqAgua.hasNext()){
				TanqueAgua tanqAguaAct = itTanqAgua.next();
				tanqAguaAct.construirDia();
				if(tanqAguaAct.getDiasDeConstruccionActual() == tanqAguaAct.getDiasDeConstruccionTotal()){
					yacimientoSimular.getTanquesAguaConstruccion().add(tanqAguaAct);
					itTanqAgua.remove();
				}
			}	
			Iterator<TanqueGas> itTanqGas = yacimientoSimular.getTanquesGasConstruccion().iterator();
			while(itTanqGas.hasNext()){
				TanqueGas tanqGasAct = itTanqGas.next();
				tanqGasAct.construirDia();
				if(tanqGasAct.getDiasDeConstruccionActual() == tanqGasAct.getDiasDeConstruccionTotal()){
					yacimientoSimular.getTanquesGasConstruccion().add(tanqGasAct);
					itTanqAgua.remove();
				}
			}
			equipoIngenieria.getCriterioConstruccionTanques().construirTanquesNuevos(this);
			
			
		
		}
		
	}

}
