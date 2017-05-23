package plantaseparadoras;
import java.util.List;
import java.util.Random;

public class PlantasFactory {
	
	private List<PlantaSeparadora> listPlant;
	public PlantaSeparadora obtenerPlantaRandom(){
		Random aleat =  new Random();
		return listPlant.get(aleat.nextInt(listPlant.size()));
	}
	public PlantaSeparadora obtenerPlantaModelo(String modelo){
		for(PlantaSeparadora plant : listPlant){
			if(plant.getModel() == modelo) return plant;
		}
		System.err.println("No existen plantas con ese modelo");
		throw new IllegalArgumentException("No existen plantas con ese modelo");
	}
}
