package tanques;

import java.util.List;
import java.util.Random;


public class TanquesFactory {
	private List<TanqueAgua> listTanqAgua;
	private List<TanqueGas> listTanqGas;
	
	
	public TanqueGas obtenerTanqGasRandom(){
		Random aleat =  new Random();
		return listTanqGas.get(aleat.nextInt(listTanqGas.size()));
	}
	
	public TanqueAgua obtenerTanqAguaRandom(){
		Random aleat =  new Random();
		return listTanqAgua.get(aleat.nextInt(listTanqAgua.size()));
	}
	
	
}
