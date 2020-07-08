import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class Archivio {
	
	private boolean modificato;
	private String nomefile;
	private Vector <Mezzo> mezziGestiti;
	
	
	public Archivio(String nomefile) {
		 this.modificato = false;
		 this.nomefile = nomefile;
			try {
				ObjectInputStream file_input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)));
				// legge l'intero vettore da file
				mezziGestiti = (Vector<Mezzo>) file_input.readObject();
				file_input.close();
			} catch (FileNotFoundException e) {
				// gestisce il caso in cui il file non sia presente (sarà creato poi...)
				System.out.println("Il file " + nomefile + " non esiste ancora.");
				System.out.println("Verrà creato al primo salvataggio ([S]alvataggio su file o [U]scita).");
				System.out.println();
				this.mezziGestiti = new Vector<Mezzo>();
			} catch (ClassNotFoundException e) {
				// gestisce il caso in cui il file non contenga un oggetto
				System.out.println("ERRORE di lettura.");
				System.out.println(e);
				this.mezziGestiti = new Vector<Mezzo>();
			} catch (IOException e) {
				// gestisce altri errori di input/output
				System.out.println("ERRORE di I/O.");
				System.out.println(e);
				this.mezziGestiti = new Vector<Mezzo>();
			}	 
		 
	}
	
	//setter 
	public boolean aggiungiNuovoMezzo(Mezzo m){
		
		boolean giapresente = false;
		for(int i=0; i<mezziGestiti.size(); i++) {
			if(mezziGestiti.elementAt(i).equals(m)) 
				giapresente = true;
		}
		if(!giapresente) { 
			mezziGestiti.add(m);
			modificato = true;
			return true;
		} 
		return false;
		
	}
	
	public boolean rimuoviMezzo(String targa) {
		int n = 0;
		boolean found = false;
		
		//cerco il mezzo nel vettore dei mezzi dell'archivio, attraverso il confronto tra targhe, se trovato memorizzo la posizione
		for(Mezzo m : mezziGestiti) {
			if(m.getTarga().equalsIgnoreCase(targa)) {
				n = mezziGestiti.indexOf(m);
				found = true;
			}
		}
		//se il mezzo viene trovato allora lo elimino dal vettore
		if(found) {
			mezziGestiti.remove(mezziGestiti.elementAt(n));
			modificato = true;
			return true;
				
		} 
		return false;
	}
	
	
	//getter
	public Mezzo selezionaElemento(String t) {
		Mezzo m = null;
		
		if(mezziGestiti.isEmpty()) m = null;
		else {
			for(int i=0; i<mezziGestiti.size(); i++) {
				if(mezziGestiti.elementAt(i).getTarga().equalsIgnoreCase(t))
						m = mezziGestiti.elementAt(i);
			} 
		}
		return m;			
	}
	
	public Vector<Mezzo> elencoMezziDisposizione() {
		return mezziGestiti;

	}
	
	public Vector<Mezzo> selezionaPerCategoria(String c) {
		Vector<Mezzo> lista = new Vector<Mezzo>();
		for(int i=0; i<mezziGestiti.size(); i++) {
			if(c.equals("Autovettura")) {
				if(mezziGestiti.elementAt(i) instanceof Autovettura) {
					Autovettura auto = (Autovettura) mezziGestiti.elementAt(i);
					lista.addElement(auto);
				}
			}
			else if(c.equals("Furgone")) {
				
				if(mezziGestiti.elementAt(i) instanceof Furgone) {
				 Furgone furg = (Furgone) mezziGestiti.elementAt(i);
				 lista.addElement(furg);
				}
			}
		}
		if(lista.isEmpty()) {
			return null;
		}
		else return lista;
	}
		
	
	// verifica se ci sono modifiche non salvate
		public boolean daSalvare() {
			return modificato;
		}
		
		// salva il registro nel file
		// restituisce true se il salvataggio è andato a buon fine
		public boolean salva() {
			if (daSalvare()) { // salva solo se necessario (se ci sono modifiche)
				try {
					ObjectOutputStream file_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomefile)));
					// salva l'intero oggetto (vettore) nel file
					file_output.writeObject(mezziGestiti);
					file_output.close();
					modificato = false; // le modifiche sono state salvate
					return true;
				} catch (IOException e) {
					System.out.println("ERRORE di I/O");
					System.out.println(e);
					return false;
				}		
			} else return true;
		}
	
	
	
	
	public String toString() {
		
		return mezziGestiti.toString();
	}
		
	
	
}
