package simulacion;

public class EleccionPozoPeriodo extends CriteriosConstruccionPozos {
	
	private int periodoConstuccion;
	@Override
	public void construirPozosNuevos(Simulador sim) {//la funcion se ejecuta cada dia	
		if(sim.getDia() % periodoConstuccion == 0){// construir un pozo
			if( sim.getCantPozosConstruccion() == sim.getCantPozosARealizar()){
				return;
			}
			Parcela parcelaPozoNuevo = sim.getEquipoIngenieria().getCriterioEleccionParcela().dameParcela(sim);
			if(parcelaPozoNuevo == null){// no hay parcelas para cavar pozos
				return;
			}
			if(sim.getYacimientoSimular().getPlantasSeparadoras().isEmpty()){//no hay plantas para asignar
				return;
			}
			
			Rig rigPozo = sim.getEquipoIngenieria().getCriterioEleccionRig().alquilarRig(sim);
			if(rigPozo == null){// no hay rigs disponibles
				return;
			}	
			parcelaPozoNuevo.getPozo().setRigCabando(rigPozo);
			parcelaPozoNuevo.setContruirPozo();
			parcelaPozoNuevo.addPlanta(EstadoFinancieroYacimiento.getPlantasFactory().obtenerPlantaRandom());
			sim.incrementarCantPozosConstruccion();
		}
	}
}