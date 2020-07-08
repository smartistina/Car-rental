public class Autovettura extends Mezzo{

	private static final long serialVersionUID = 1L;
	private int numPosti;
	private String tipologiaAlimentazione;
	private String tipologiaAutovettura;
	
	public Autovettura(String t, String m, int posti, String alimentazione, String tipologia) throws IllegalArgumentException{
		super(t,m);
		
		
		if (!(this.numPosti == ('2') || this.numPosti == ('4') || this.numPosti == ('5'))) throw new IllegalArgumentException("Numeri posti non valido.");
		else this.numPosti = posti;
		 
		
		
		if(alimentazione.equalsIgnoreCase("diesel") || alimentazione.equalsIgnoreCase("benzina"))
			this.tipologiaAlimentazione = alimentazione.substring(0,1).toUpperCase()+alimentazione.substring(1).toLowerCase();
		else throw new IllegalArgumentException("Tipologia d'alimentazione non valida.");
		
		if(tipologia.equalsIgnoreCase("utilitaria") || tipologia.equalsIgnoreCase("media"))
			this.tipologiaAutovettura = tipologia.substring(0,1).toUpperCase()+tipologia.substring(1).toLowerCase();
		else throw new IllegalArgumentException("Tipologia dell'autovettura non valida.");
		
	}
	

	//getter
	public int getNumPosti() {
		return numPosti;
	}
	
	public String getAlimentazione() {
		return tipologiaAlimentazione;
	}
	
	public String getTipologiaAuto() {
		return tipologiaAutovettura;
	}
		
	public String toString() {
		return ("[Autovettura]:" +targa+"\nModello: "+modello+"\nTipologia: "+tipologiaAutovettura+"\nPosti: " +numPosti+"\nAlimentazione: "+tipologiaAlimentazione+"\n");
	}
	
	
}
