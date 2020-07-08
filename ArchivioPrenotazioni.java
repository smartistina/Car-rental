import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ArchivioPrenotazioni {
	
	
	private boolean modificato;
	private String nomefile;
	private Vector<Prenotazione> listaPrenotazioni;
	
	public ArchivioPrenotazioni(String nomefile) {
		this.nomefile = nomefile;
		this.modificato = false;
		try {
			ObjectInputStream file_input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomefile)));
			// legge l'intero vettore da file
			listaPrenotazioni = (Vector<Prenotazione>) file_input.readObject();
			file_input.close();
		} catch (FileNotFoundException e) {
			this.listaPrenotazioni = new Vector<Prenotazione>();
		} catch (ClassNotFoundException e) {
			// gestisce il caso in cui il file non contenga un oggetto
			System.out.println("ERRORE di lettura");
			System.out.println(e);
			this.listaPrenotazioni = new Vector<Prenotazione>();
		} catch (IOException e) {
			// gestisce altri errori di input/output
			System.out.println("ERRORE di I/O");
			System.out.println(e);
			this.listaPrenotazioni = new Vector<Prenotazione>();
		}
	}
	
	
	public boolean checkPrenotazione(Mezzo m, String data) {
		Prenotazione p = new Prenotazione(m, data, "");
		if(!listaPrenotazioni.contains(p))
			return true;	
		else return false;
		
	}
	
	public boolean aggiungiPrenotazione(Prenotazione p) {
		if(checkPrenotazione(p.getMezzo(), p.getDataPrenotazione())) {
			listaPrenotazioni.add(p);
			modificato = true;
			return true;
		}
		return false;
	}
	
	public Vector<Mezzo> getCategoriaData(String data, String categoria, Vector<Mezzo> vm) {
		Vector<Mezzo> lista = new Vector<Mezzo>();
		
		for(int i=0; i<listaPrenotazioni.size(); i++) {
			
			if(listaPrenotazioni.elementAt(i).getDataPrenotazione().equals(data)) {
				if(categoria.equals("Autovettura")) {
					if(listaPrenotazioni.elementAt(i).getMezzo() instanceof Autovettura)
						lista.addElement(listaPrenotazioni.elementAt(i).getMezzo());
				} else if(categoria.equals("Furgone")) {
					if(listaPrenotazioni.elementAt(i).getMezzo() instanceof Furgone)
						lista.addElement(listaPrenotazioni.elementAt(i).getMezzo());
				}
			} 
		}
		return lista;
	}
	
	
	public boolean rimuoviPrenotazione(Mezzo m, String data) {
		Prenotazione p = new Prenotazione(m, data, "");
		if(listaPrenotazioni.isEmpty()) return false;
		else {
			boolean found = false;
			boolean rimosso = false;
			for(int i=0; i<listaPrenotazioni.size(); i++) {
				if(listaPrenotazioni.elementAt(i).equals(p)) {
					listaPrenotazioni.removeElementAt(i);
					rimosso = true;
					modificato = true;
				}
				
			}
			for(int i=0; i<listaPrenotazioni.size(); i++) {
				if(listaPrenotazioni.elementAt(i).equals(p))
					found = true;
			}
			if(!found && rimosso) return true;
			
		}
		return false;
		
	}
	
	public Prenotazione visualizzaPrenotazioneArchivio(Prenotazione p) {
		if(listaPrenotazioni.isEmpty()) return null;
		else {
			
			for(int i=0; i<listaPrenotazioni.size(); i++) {
				if(listaPrenotazioni.elementAt(i).equals(p))
					return p;
			}
		}
		return null;
		
	}
	
	public Vector<Prenotazione> cronologiaPrenotazioniMezzo(String t){
		Vector<Prenotazione> vp = new Vector<Prenotazione>();
		if(!listaPrenotazioni.isEmpty()) {
			for(int i=0; i<listaPrenotazioni.size(); i++) {
				if(listaPrenotazioni.elementAt(i).getMezzo().getTarga().equalsIgnoreCase(t)) 
					vp.addElement(listaPrenotazioni.elementAt(i));
			}
		}
		
		return vp;
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
				file_output.writeObject(listaPrenotazioni);
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

		
	
}
