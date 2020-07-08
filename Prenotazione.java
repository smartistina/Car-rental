import java.io.Serializable;

public class Prenotazione implements Serializable {
	
	static final long serialVersionUID = 1;
	
	private Mezzo mezzo;
	private String data;
	private String nome;
	
	public Prenotazione(Mezzo m, String d, String n){
		this.mezzo = m;
		this.data = d;
		this.nome = n;
	}
	/*
	public Prenotazione(Mezzo m, String d) {
		this.mezzo = m;
		this.data = d;
	}
	*/
	public Mezzo getMezzo() {
		return mezzo;
	}
	public String getDataPrenotazione() {
		return data;
	}
	public String getNomePrenotazione() {
		return nome;
	}
	
	
	public boolean equals(Object o) {
		if(o instanceof Prenotazione) {
			Prenotazione pr = (Prenotazione) o;
			return ((mezzo.equals(pr.mezzo)) && (data.equals(pr.data)));
		} else return false;
	}
	
	//toString metodo
	public String toString() {
		return "Prenotazione del mezzo " + mezzo + "a nome: " + nome + "\nin data: " + data+"\n";
	}
	
}
