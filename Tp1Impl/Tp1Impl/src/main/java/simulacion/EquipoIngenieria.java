package simulacion;

public class EquipoIngenieria {

	private CriteriosConstruccionPozos criterioConstruccionPozos;
	private CriteriosConstruccionPlantas criterioConstruccionPlantas;
	private CriteriosConstruccionTanques criterioConstruccionTanques;
	private CriteriosExtraccionPozos criterioExtraccionPozos;
	private CriteriosReinyeccion criterioReinyeccion;
	private CriteriosFinalizacion criterioFinalizacion;
	private CriteriosVentaGas criterioVentaGas;
	private CriteriosEleccionRig criterioEleccionRig;
	private CriteriosEleccionParcela criterioEleccionParcela;
	
	public EquipoIngenieria(CriteriosConstruccionPozos criterioConstruccionPozos,
			CriteriosConstruccionPlantas criterioConstruccionPlantas,
			CriteriosConstruccionTanques criterioConstruccionTanques, CriteriosExtraccionPozos criterioExtraccionPozos,
			CriteriosReinyeccion criterioReinyeccion, CriteriosFinalizacion criterioFinalizacion,
			CriteriosVentaGas criterioVentaGas, CriteriosEleccionRig criterioEleccionRig,
			CriteriosEleccionParcela criterioEleccionParcela) {
		super();
		this.criterioConstruccionPozos = criterioConstruccionPozos;
		this.criterioConstruccionPlantas = criterioConstruccionPlantas;
		this.criterioConstruccionTanques = criterioConstruccionTanques;
		this.criterioExtraccionPozos = criterioExtraccionPozos;
		this.criterioReinyeccion = criterioReinyeccion;
		this.criterioFinalizacion = criterioFinalizacion;
		this.criterioVentaGas = criterioVentaGas;
		this.criterioEleccionRig = criterioEleccionRig;
		this.criterioEleccionParcela = criterioEleccionParcela;
	}
	
	public CriteriosEleccionParcela getCriterioEleccionParcela() {
		return criterioEleccionParcela;
	}
	public CriteriosEleccionRig getCriterioEleccionRig() {
		return criterioEleccionRig;
	}
	public CriteriosConstruccionPozos getCriterioConstruccionPozos() {
		return criterioConstruccionPozos;
	}
	public CriteriosConstruccionPlantas getCriterioConstruccionPlantas() {
		return criterioConstruccionPlantas;
	}
	public CriteriosConstruccionTanques getCriterioConstruccionTanques() {
		return criterioConstruccionTanques;
	}
	public CriteriosExtraccionPozos getCriterioExtraccionPozos() {
		return criterioExtraccionPozos;
	}
	public CriteriosReinyeccion getCriterioReinyeccion() {
		return criterioReinyeccion;
	}
	public CriteriosFinalizacion getCriterioFinalizacion() {
		return criterioFinalizacion;
	}
	public CriteriosVentaGas getCriterioVentaGas() {
		return criterioVentaGas;
	}


}
