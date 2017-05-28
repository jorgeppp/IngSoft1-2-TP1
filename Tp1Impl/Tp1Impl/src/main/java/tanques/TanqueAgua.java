package tanques;

public class TanqueAgua extends Tanque implements Cloneable{

	public TanqueAgua(String model, int diasDeConstruccionTotal, double costoConstruccion, double volumenTotal) {
		super(model, diasDeConstruccionTotal, costoConstruccion, volumenTotal);
		
	}
	
	   @Override
	public String toString() {
		return "TanqueAgua (getVolumenTotal()=" + getVolumenTotal() + ", getVolumenOcupadoActual()="
				+ getVolumenOcupadoActual() + ", getModel()=" + getModel() + ", getDiasDeConstruccionActual()="
				+ getDiasDeConstruccionActual() + ", getDiasDeConstruccionTotal()=" + getDiasDeConstruccionTotal()
				+ ", getCostoConstruccion()=" + getCostoConstruccion() + ")";
	}

	public Object clone() {
		      Object clone = null;
		      
		      try {
		         clone = super.clone();
		         
		      } catch (CloneNotSupportedException e) {
		         e.printStackTrace();
		      }
		      
		      return clone;
		   }




	

}
