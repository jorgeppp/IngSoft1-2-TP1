package simulacion;


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

	public static void setGananciaTotal(double gananciaTotal) {
		EstadoFinancieroYacimiento.gananciaTotal = gananciaTotal;
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
		System.out.println("incrementa ganancia total " + gananciaTotal +"en " + value );
		gananciaTotal += value;
		System.out.println("incrementa ganancia total " + gananciaTotal +"en " + value );

	}
	
	public static void decrementarGanancia(double value) {
		System.out.println("decrementa ganancia total " + gananciaTotal +"en " + value );
		gananciaTotal -= value;
		System.out.println("decrementa ganancia total " + gananciaTotal +"en " + value );

	}
	
	
	

}
