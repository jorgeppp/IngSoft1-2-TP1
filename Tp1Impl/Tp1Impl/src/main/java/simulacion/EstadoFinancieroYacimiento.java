package simulacion;
import java.util.List;

import plantaseparadoras.PlantasFactory;
import tanques.TanquesFactory;

public class EstadoFinancieroYacimiento {
	
	private static double gananciaTotal =0;
	
	private static double precioVentaPetroleo;
	private static double precioVentaGas;
	private static double precioCompraAgua;
	private static double costoCombustibleRigs;

	
	public static void setPrecioVentaPetroleo(double precioVentaPetroleo) {
		EstadoFinancieroYacimiento.precioVentaPetroleo = precioVentaPetroleo;
	}
	public static void setPrecioVentaGas(double precioVentaGas) {
		EstadoFinancieroYacimiento.precioVentaGas = precioVentaGas;
	}
	public static void setPrecioCompraAgua(double precioCompraAgua) {
		EstadoFinancieroYacimiento.precioCompraAgua = precioCompraAgua;
	}
	public static void setCostoCombustibleRigs(double costoCombustibleRigs) {
		EstadoFinancieroYacimiento.costoCombustibleRigs = costoCombustibleRigs;
	}


	
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

	public static double getGananciaTotal() {
		return gananciaTotal;
	}
	
	public static void incrementarGanancia(double value) {
		gananciaTotal += value;
	}
	
	public static void decrementarGanancia(double value) {
		gananciaTotal -= value;
	}
	
	
	

}
