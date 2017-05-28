package plantaseparadoras;
import java.util.List;
import java.util.Random;


public class PlantasFactory {
	
	private List<PlantaSeparadora> listPlant;
	
	public PlantaSeparadora obtenerPlantaRandom(){

		Random aleat =  new Random();
		PlantaSeparadora plantRes =  listPlant.get(aleat.nextInt(listPlant.size()));
		System.out.println("Construccion de planta en la factory " + plantRes);
		return plantRes;

	}

	public PlantasFactory(List<PlantaSeparadora> listPlant) {
		super();
		this.listPlant = listPlant;
	}
}
