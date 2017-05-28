package simulacion;

public class EleccionMaximaCantidadRigs extends CriteriosConstruccionPozos {

	
	@Override
	public void construirPozosNuevos(Simulador sim) {//la funcion se ejecuta cada dia
		for(int ind=0; ind <sim.getCantMaxRIGSSimultaneo(); ind++){
			if( sim.getYacimientoSimular().getCantPozosConstruccion() == sim.getCantPozosARealizar()){
				sim.loggear("Ya se construyo el máximo de pozos");
				System.out.println("Ya se construyo el máximo de pozos");
				return;
			}
			Parcela parcelaPozoNuevo = sim.getEquipoIngenieria().getCriterioEleccionParcela().dameParcela(sim);
			if(parcelaPozoNuevo == null){// no hay parcelas para cavar pozos
				sim.loggear("No hay mas parcelas para cavar pozos");
				System.out.println("No hay mas parcelas para cavar pozos");
				return;
			}
			if(sim.getYacimientoSimular().getPlantasSeparadoras().isEmpty()){//no hay plantas para asignar
				sim.loggear("No hay plantas para asignar al pozo");
				System.out.println("No hay plantas para asignar al pozo");
				return;
			}
			Rig rigPozo = sim.getEquipoIngenieria().getCriterioEleccionRig().alquilarRig(sim);
			if(rigPozo == null){// no hay rigs disponibles
				sim.loggear("No hay rigs para asignar al pozo");
				System.out.println("No hay rigs para asignar al pozo");
				return;
			}
			parcelaPozoNuevo.getPozo().setRigCabando(rigPozo);
			parcelaPozoNuevo.setContruirPozo();
			parcelaPozoNuevo.addPlanta(sim.getPlantasFactory().obtenerPlantaRandom());
			sim.getYacimientoSimular().incrementarCantPozosConstruidos();
			sim.loggear("Se empezo a construir un pozo en parcela " +parcelaPozoNuevo.getId() );
			System.out.println("Se empezo a construir un pozo en parcela " +parcelaPozoNuevo.getId());
		}
	}
}
