package upo.prenotazioneaule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Matteo Schirinzi 20035542
 * @title Aula (Class)
 */

public class Aula implements Serializable{
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 8311291571348804964L;
	private String nome;
	private Integer capienzaMax;
	private ArrayList<Dotazione> dotazioni;
	
	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - Costruttore 
	 * @param <em>nome</em> : Nome dell'aula (String)
	 * @param <em>capienza</em> : Capienza in posti dell'aula (int)
	 * @throws IllegalArgumentException
	 */
	public Aula(String nome, int capienzaMax) throws IllegalArgumentException{
		//****CONTROLLI****
		if(nome == null) throw new IllegalArgumentException("Il nome non può essere nullo");
		if(capienzaMax == 0) throw new IllegalArgumentException("La capienzaMax non può essere = 0");
		
		this.nome = nome;
		this.capienzaMax = capienzaMax;
		this.dotazioni = new ArrayList<Dotazione>();
	}

	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - getNome.
	 * @return <em>nome</em> : Nome dell'aula.
	 * getNome() - Metodo : Ritorna il nome dell'aula relativa all'istanza su cui viene chiamata.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - getCapienza
	 * @return <em>capienza</em> : Capienza aula
	 * getCapienza() - Metodo : Ritorna la capienza dell'aula su cui viene chiamato il metodo.
	 */
	public int getCapienzaMax() {
		return this.capienzaMax;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - getDotazioni
	 * @return <em>dotazioni</em> : Dotazioni dell'aula
	 * getDotazioni() - Metodo : Ritorna le dotazioni dell'aula su cui viene chiamato il metodo.
	 */
	public ArrayList<Dotazione> getDotazioni() {
		return this.dotazioni;
	}

	// Metodi set protected perchè non voglio che l'utente possa cambiare il nome dell'aula ma il metodo potrebbe servirmi
	protected void setNome(String nome) {
		this.nome = nome;
	}
	protected void setCapienzaMax(int capienzaMax) {
		this.capienzaMax = capienzaMax;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - addDotazione
	 * addDotazione() - Metodo : Aggiunge una dotazione all'istanza della classe.
	 */
	public void addDotazione(Dotazione d) {
		this.dotazioni.add(d);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - toString
	 * @return <em>string</em> : Istanza sotto forma di string
	 * toString() - Metodo : Ritorna una stringa che rappresenta un istanza di Aula
	 */
	@Override
	public String toString() {
		return "nome=" + this.nome + ", capienzaMax=" + this.capienzaMax + ", dotazione=" + this.dotazioni.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(capienzaMax, dotazioni, nome);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Aula - equals
	 * @param <em>obj</em> : Oggetto
	 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di Aula, altrimenti false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(capienzaMax, other.capienzaMax) && Objects.equals(nome, other.nome);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Dotazione - Classe enumerativa annidata in Aula per le dotazioni.
	 */
	public enum Dotazione {
		PROIETTORE, STAMPANTE, LAVAGNA, MICROFONO, CASSE, PC, SALDATORE;
	}

	
	
	
	
	
}
