package tanques;

import java.util.List;
import java.util.Random;

public class TanquesFactory {
	private List<TanqueAgua> listTanqAgua;
	private List<TanqueGas> listTanqGas;
	
	
	public TanqueGas obtenerTanqGasRandom(){		
		Random aleat =  new Random();
		TanqueGas tanqRes =  listTanqGas.get(aleat.nextInt(listTanqGas.size()));
		System.out.println("Construccion de tanque gas factory " + tanqRes);
		return tanqRes;
	}
	
	public TanqueAgua obtenerTanqAguaRandom(){
		Random aleat =  new Random();
		TanqueAgua tanqRes =  listTanqAgua.get(aleat.nextInt(listTanqAgua.size()));
		System.out.println("Construccion de tanque agua factory " + tanqRes);
		return tanqRes;
	}
	
	public TanquesFactory(List<TanqueAgua> listTanqAgua, List<TanqueGas> listTanqGas) {
		super();
		this.listTanqAgua = listTanqAgua;
		this.listTanqGas = listTanqGas;
	}
	
	
}
