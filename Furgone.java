
public class Furgone extends Mezzo{
	
	private static final long serialVersionUID = 1L;
	private char patente;
	private String dimensioni;
	
	public Furgone(String targa, String modello, char pat, String d) throws IllegalArgumentException{
		super(targa, modello);
		
		if(pat=='B'||pat=='b')
			this.patente = 'B';
		else if(pat=='C'||pat=='c')
			this.patente = 'C';
		else throw new IllegalArgumentException("Patente non valida.");
	
		
		if(d.equalsIgnoreCase("Piccolo") || d.equalsIgnoreCase("Medio") || d.equalsIgnoreCase("grande")) 
			this.dimensioni = d.substring(0,1).toUpperCase() + d.substring(1).toLowerCase();
		else throw new IllegalArgumentException("Dimensione del furgone non valida.");
		
		
		
	}
	
	//getter 
	public char getPatente() {
		return patente;
	}
	public String getDimensioni() {
		return dimensioni;
	}
	public String toString() {
		return("[Furgone]: "+targa+"\nModello: "+modello+"\nPatente: "+patente+"\nDimensioni: "+dimensioni+"\n");
	}
}
