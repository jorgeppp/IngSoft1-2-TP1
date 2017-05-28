package simulacion;

public abstract class CriteriosFinalizacion {
	protected final double MINIMO_VOL_DISP = 5;
	public abstract boolean hayQueFinalizarSimulacion(Simulador sim);

}
