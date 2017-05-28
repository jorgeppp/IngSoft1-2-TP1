package simulacion;
import java.util.ArrayList;
import java.util.List;

import plantaseparadoras.PlantaSeparadora;

public class Parcela {

	private static int count = 1;
	private int id;


	private TipoDeTerreno tipoTerreno;
	private Pozo pozo;
	private List<PlantaSeparadora> plantasSeparadorasParcela = new ArrayList<PlantaSeparadora>();
	private boolean seConstruyePozo = false; //se emepezo a construir un pozo o se termino de construir uno
	private boolean valvulaExtraer = true; //en caso de estar en true se extrae ese dia
	
	
	public int getId() {
		return id;
	}
	
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

	public Parcela(TipoDeTerreno tipoTerreno, Pozo pozo,PlantaSeparadora plantSep ) {
		this.id= count++;
		this.tipoTerreno = tipoTerreno;
		this.pozo = pozo;
		plantasSeparadorasParcela.add(plantSep);
	}
	
	@Override
	public String toString() {
		return "Parcela (id=" + id + ", tipoTerreno=" + tipoTerreno + ", pozo=" + pozo + ", plantasSeparadorasParcela="
				+ plantasSeparadorasParcela + ", seConstruyePozo=" + seConstruyePozo + ", valvulaExtraer="
				+ valvulaExtraer + ")";
	}

	
	
	


}
