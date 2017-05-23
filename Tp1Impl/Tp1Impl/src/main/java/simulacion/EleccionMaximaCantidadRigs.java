package simulacion;

public class EleccionMaximaCantidadRigs extends CriteriosConstruccionPozos {

	
	@Override
	public void construirPozosNuevos(Simulador sim) {//la funcion se ejecuta cada dia
		for(int ind=0; ind <sim.getCantMaxRIGSSimultaneo(); ind++){
			if( sim.getCantPozosConstruccion() == sim.getCantPozosARealizar()){
				break;
			}
			Parcela parcelaPozoNuevo = sim.getEquipoIngenieria().getCriterioEleccionParcela().dameParcela(sim);
			if(parcelaPozoNuevo == null){// no hay parcelas para cavar pozos
				break;
			}
			if(sim.getYacimientoSimular().getPlantasSeparadoras().isEmpty()){//no hay plantas para asignar
				return;
			}
			Rig rigPozo = sim.getEquipoIngenieria().getCriterioEleccionRig().alquilarRig(sim);
			if(rigPozo == null){// no hay rigs disponibles
				break;
			}	
			parcelaPozoNuevo.getPozo().setRigCabando(rigPozo);
			parcelaPozoNuevo.setContruirPozo();
			parcelaPozoNuevo.addPlanta(EstadoFinancieroYacimiento.getPlantasFactory().obtenerPlantaRandom());
			sim.incrementarCantPozosConstruccion();
		}
	}
}
