package simulacion;
import java.util.Iterator;
import java.util.List;

import controller.ResponseDto;
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
	
	private PlantasFactory plantasFactory;
	private TanquesFactory tanquesFactory;
	private List<Rig> listaRigsDisponibles;
	
	private int dia = 0;
	private String loggeo = "";

	public Simulador(double alpha1, double alpha2, int cantPozosARealizar, int cantMaxRIGSSimultaneo,
			double volMaxReinyectarDia, double presionCritica, int dilucionCritica, int diasMaximoSimulacion,
			Yacimiento yacimientoSimular, EstadoFinancieroYacimiento estadoFinanciero,
			EquipoIngenieria equipoIngenieria, PlantasFactory plantasFactory, TanquesFactory tanquesFactory,
			List<Rig> listaRigsDisponibles) {
		super();
		this.alpha1 = alpha1;
		this.alpha2 = alpha2;
		this.cantPozosARealizar = cantPozosARealizar;
		this.cantMaxRIGSSimultaneo = cantMaxRIGSSimultaneo;
		this.volMaxReinyectarDia = volMaxReinyectarDia;
		this.presionCritica = presionCritica;
		this.dilucionCritica = dilucionCritica;
		this.diasMaximoSimulacion = diasMaximoSimulacion;
		this.yacimientoSimular = yacimientoSimular;
		this.estadoFinanciero = estadoFinanciero;
		this.equipoIngenieria = equipoIngenieria;
		this.plantasFactory = plantasFactory;
		this.tanquesFactory = tanquesFactory;
		this.listaRigsDisponibles = listaRigsDisponibles;
	}
	
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
	
	public void loggear(String log) {
		loggeo += log + "\r\n";
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
		if(volExtrTot !=0){//si se extrajo algo de producto
			System.out.println("volextrPlantas " + volExtrTot);
			double volGasExtraido = volExtrTot * yacimientoSimular.getReservorio().getPorcGas()/100;
			double volAguaExtraido = volExtrTot * yacimientoSimular.getReservorio().getPorcAgua()/100;
			double volPetroleoExtraido = volExtrTot - volGasExtraido - volAguaExtraido;

			double volTanqGasDispTot =0;
			for(Tanque tanq:  yacimientoSimular.getTanquesGas()){
				double volDispTanq = (tanq.getVolumenTotal() - tanq.getVolumenOcupadoActual());
				volTanqGasDispTot += volDispTanq;
			}	
			boolean tanquesGasDisp = volTanqGasDispTot>=volGasExtraido;	
			
			double volTanqAguaDispTot =0;
			for(Tanque tanq:  yacimientoSimular.getTanquesAgua()){
				double volDispTanq = (tanq.getVolumenTotal() - tanq.getVolumenOcupadoActual());
				volTanqAguaDispTot += volDispTanq;
			}	
			boolean tanquesAguaDisp = volTanqAguaDispTot>=volAguaExtraido;
			
			if(tanquesGasDisp && tanquesAguaDisp ){//extraigo producto y lleno los tanques			
				loggeo +="Volumen de producto extraído: " + volExtrTot +"\r\n";
				System.out.println("Volumen de producto extraído: " +volExtrTot );
				loggeo +="Volumen de gas extraÃ­do: " + volGasExtraido +"\r\n";
				System.out.println("Volumen de gas extraído: " +volGasExtraido );		
				loggeo +="Volumen de agua extraído: " + volAguaExtraido +"\r\n";
				System.out.println("Volumen de agua extraído: " +volAguaExtraido );		
				loggeo +="Volumen de petróleo extraído: " + volPetroleoExtraido +"\r\n";
				System.out.println("Volumen de petróleo extraído: " +volPetroleoExtraido );
				
				double volGasExtraidoRestante = volGasExtraido;
				for(Tanque tanq:  yacimientoSimular.getTanquesGas()){
					double volDispTanq = (tanq.getVolumenTotal() - tanq.getVolumenOcupadoActual());
					if(volGasExtraidoRestante > volDispTanq){
						volGasExtraidoRestante -= volDispTanq;
						tanq.llenar(volDispTanq);
					}else{
						tanq.llenar(volGasExtraidoRestante);
						volGasExtraidoRestante = 0;
						System.out.println("bien gas");
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
						System.out.println("bienn agua");
						break;
					}		
				}
				EstadoFinancieroYacimiento.incrementarGanancia(volPetroleoExtraido *EstadoFinancieroYacimiento.getPrecioVentaPetroleo() );
				//actualizo los valores del reservorio del yacimiento
				yacimientoSimular.getReservorio().extraerProducto(volExtrTot);
			}else{//en caso contrario todo el producto vuelve y no se llenan los tanques
				loggeo +="Volumen de producto extraÃ­do : 0 \r\n";
				System.out.println("Volumen de producto extraÃ­do : 0");
			}
			//se actualizan los valores de las presiones
			for(Parcela parcel : yacimientoSimular.getParcelas()){
				System.out.println(parcel);
				yacimientoSimular.formula2(yacimientoSimular.getReservorio().getVolumenDisponible(),yacimientoSimular.getReservorio().getVolumenTotal(), parcel.getPozo().getPresionActual(),yacimientoSimular.getCantPozosActual(), parcel );
				System.out.println(parcel);
			}
		}else{
			loggeo +="Volumen de producto extraído : 0 \r\n";
			System.out.println("Volumen de producto extraído : 0");
		}
		
	}
	


	public ResponseDto execute() {
		EstadoFinancieroYacimiento.setGananciaTotal(0);
		System.out.println("ganancia inicial " + EstadoFinancieroYacimiento.getGananciaTotal());
		loggeo +="Inicia Simulación\r\n";
		System.out.println("Inicia Simulación");
		while(true){
			dia++;
			if(equipoIngenieria.getCriterioFinalizacion().hayQueFinalizarSimulacion(this)){
				System.out.println("Ganancia total " + EstadoFinancieroYacimiento.getGananciaTotal() );
				loggear("Ganancia total " + EstadoFinancieroYacimiento.getGananciaTotal());
				loggeo += "Fin Simulación\r\n";
				System.out.println("Fin Simulación");
				
				return new ResponseDto(loggeo, EstadoFinancieroYacimiento.getGananciaTotal());
			}
			
			loggeo +="Día " +dia + "\r\n";
			System.out.println("Día " +dia );
			
			//las plantas separadoras vuelven a tener su capacidad original
			for(PlantaSeparadora plantSep : yacimientoSimular.getPlantasSeparadoras()){
				plantSep.resetearPoderProcesamiento();
			}
			
			equipoIngenieria.getCriterioExtraccionPozos().abrirValbulas(this);
			
			System.out.println(yacimientoSimular.getParcelas());  
						
			if(!equipoIngenieria.getCriterioReinyeccion().hayQueReinyectar(this)){
				extraerPetroleoDia();
			}
			
			equipoIngenieria.getCriterioVentaGas().venderGas(this);
			
			//costruir pozos
			for(Parcela parc : yacimientoSimular.getParcelas()){
				Pozo pozoParc = parc.getPozo();
				if(parc.isSeConstruyePozo() && !pozoParc.seTerminoConstruccion()){
					if(pozoParc.cabarDia(parc.getTipoTerreno())){//se termino de construir un pozo
						yacimientoSimular.incrementarCantPozosConstuidos();
						Rig rigDisp = pozoParc.getRigCabando();
						listaRigsDisponibles.add(rigDisp);
						pozoParc.setRigCabando(null);
						loggeo +="Se termino de construir un pozo en la parcela " +parc.getId() +  "\r\n";
						System.out.println("Se termino de construir un pozo en la parcela " +parc.getId() +  "\r\n");
					}
				}
			}
			
			equipoIngenieria.getCriterioConstruccionPozos().construirPozosNuevos(this);
			System.out.println(listaRigsDisponibles);
			
			//construir plantasSeparadoras
			Iterator<PlantaSeparadora> itPlant = yacimientoSimular.getPlantasSeparadorasConstruccion().iterator();
			while(itPlant.hasNext()){
				PlantaSeparadora plantAct = itPlant.next();
				plantAct.construirDia();
				if(plantAct.getDiasDeConstruccionActual() == plantAct.getDiasDeConstruccionTotal()){
					System.out.println("Se termino de construir una planta separadora");
					loggeo += "Se termino de construir una planta separadora";
					System.out.println(yacimientoSimular.getPlantasSeparadorasConstruccion());
					System.out.println(yacimientoSimular.getPlantasSeparadoras());
					yacimientoSimular.getPlantasSeparadoras().add(plantAct);
					itPlant.remove();
					System.out.println("Se termino de construir una planta separadora");
					loggeo += "Se termino de construir una planta separadora\r\n";
					System.out.println(yacimientoSimular.getPlantasSeparadorasConstruccion());
					System.out.println(yacimientoSimular.getPlantasSeparadoras());
				}
			}
			equipoIngenieria.getCriterioConstruccionPlantas().construirPlantasNuevas(this);
			
			//construir Tanques	
			Iterator<TanqueAgua> itTanqAgua = yacimientoSimular.getTanquesAguaConstruccion().iterator();
			while(itTanqAgua.hasNext()){
				TanqueAgua tanqAguaAct = itTanqAgua.next();
				tanqAguaAct.construirDia();
				if(tanqAguaAct.getDiasDeConstruccionActual() == tanqAguaAct.getDiasDeConstruccionTotal()){
					System.out.println(yacimientoSimular.getTanquesAguaConstruccion());
					System.out.println(yacimientoSimular.getTanquesAgua());
					yacimientoSimular.getTanquesAgua().add(tanqAguaAct);
					itTanqAgua.remove();
					System.out.println("Se termino de construir un tanque de agua");
					loggeo += "Se termino de construir un tanque de agua\r\n";
					System.out.println(yacimientoSimular.getTanquesAguaConstruccion());
					System.out.println(yacimientoSimular.getTanquesAgua());
				}
			}	
			Iterator<TanqueGas> itTanqGas = yacimientoSimular.getTanquesGasConstruccion().iterator();
			while(itTanqGas.hasNext()){
				TanqueGas tanqGasAct = itTanqGas.next();
				tanqGasAct.construirDia();
				if(tanqGasAct.getDiasDeConstruccionActual() == tanqGasAct.getDiasDeConstruccionTotal()){
					System.out.println(yacimientoSimular.getTanquesGasConstruccion());
					System.out.println(yacimientoSimular.getTanquesGas());
					yacimientoSimular.getTanquesGas().add(tanqGasAct);
					itTanqGas.remove();				
					System.out.println("Se termino de construir un tanque de gas");
					loggeo += "Se termino de construir un tanque de gas\r\n";
					System.out.println(yacimientoSimular.getTanquesGasConstruccion());
					System.out.println(yacimientoSimular.getTanquesGas());
				}
			}
			equipoIngenieria.getCriterioConstruccionTanques().construirTanquesNuevos(this);
		}
		
	}

}
