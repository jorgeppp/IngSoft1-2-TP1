package simulacion;
import java.util.List;

import plantaseparadoras.PlantasFactory;
import tanques.TanquesFactory;

public class EstadoFinancieroYacimiento {
	
	private static double gananciaTotal;
	
	private static double precioVentaPetroleo;
	private static double precioVentaGas;
	private static double precioCompraAgua;
	private static double costoCombustibleRigs;
	private static PlantasFactory plantasFactory;
	private static TanquesFactory tanquesFactory;
	private static List<Rig> listaRigsDisponibles;


	
	public static double getPrecioVentaPetroleo() {
		return precioVentaPetroleo;
	}
	public static double getPrecioVentaGas() {
		return precioVentaGas;
	}
	public static double getPrecioCompraAgua() {
		return precioCompraAgua;
	}
	public static double getCostoCombustibleRigs() {
		return costoCombustibleRigs;
	}
	public static PlantasFactory getPlantasFactory() {
		return plantasFactory;
	}
	public static TanquesFactory getTanquesFactory() {
		return tanquesFactory;
	}
	public static double getGananciaTotal() {
		return gananciaTotal;
	}
	
	public static void incrementarGanancia(double value) {
		gananciaTotal += value;
	}
	
	public static void decrementarGanancia(double value) {
		gananciaTotal -= value;
	}
	
	public static List<Rig> getListaRigsDisponibles() {
		return listaRigsDisponibles;
	}
	
	

}
