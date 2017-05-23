package simulacion;
import java.util.ArrayList;
import java.util.List;

import plantaseparadoras.PlantaSeparadora;

public class Parcela {
	
	private static int count = 0;
	private int id;
	private TipoDeTerreno tipoTerreno;
	private Pozo pozo;
	private List<PlantaSeparadora> plantasSeparadorasParcela = new ArrayList<PlantaSeparadora>();
	private boolean seConstruyePozo = false;
	private boolean valvulaExtraer = true; //en caso de estar en true se extrae ese dia
	
	
	public void setValvulaExtraer(boolean valvulaExtraer) {
		this.valvulaExtraer = valvulaExtraer;
	}
	
	public boolean isValvulaExtraer() {
		return valvulaExtraer;
	}
	
	public boolean isSeConstruyePozo() {
		return seConstruyePozo;
	}

	public void setContruirPozo() {
		this.seConstruyePozo = true;
	}

	public TipoDeTerreno getTipoTerreno() {
		return tipoTerreno;
	}

	public List<PlantaSeparadora> getPlantasSeparadorasParcela() {
		return plantasSeparadorasParcela;
	}

	public Pozo getPozo() {
		return pozo;
	}
	
	public void addPlanta(PlantaSeparadora pl) {
		plantasSeparadorasParcela.add(pl);
	}

	public Parcela(TipoDeTerreno tipoTerreno, Pozo pozo) {
		this.id= count++;
		this.tipoTerreno = tipoTerreno;
		this.pozo = pozo;
	}
	


	
	
	


}
