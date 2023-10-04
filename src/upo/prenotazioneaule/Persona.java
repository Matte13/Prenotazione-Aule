package upo.prenotazioneaule;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Matteo Schirinzi 20035542
 * @title Persona (Class)
 */

public abstract class Persona implements Serializable{
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = -2727489367941661821L;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private String cf;
			
	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - Costruttore 
	 * @param <em>nome</em> : Nome della persona (String)
	 * @param <em>cognome</em> : Cognome della persona (String)
	 * @param <em>dataNascita</em> : Data di nascita della persona (LocalDate)
	 * @param <em>cf</em> : CF della persona (String)
	 * @throws IllegalArgumentException
	 */
	public Persona(String nome, String cognome, LocalDate dataNascita, String cf) throws IllegalArgumentException {
		//****CONTROLLI****
		if(nome == null) throw new IllegalArgumentException("Il nome non può essere nullo");
		if(cognome == null) throw new IllegalArgumentException("Il cognome non può essere nullo");
		if(dataNascita == null) throw new IllegalArgumentException("La data di nascita non può essere nulla");
		if(dataNascita.isAfter(LocalDate.now())) throw new IllegalArgumentException("La data di nascita non può superare la data odierna");
		if(cf == null) throw new IllegalArgumentException("Il cf non può essere nullo");
		if(cf.length() != 16) throw new IllegalArgumentException("Il codice " + cf + "non è un cf valido");
				
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.cf = cf;
	}
	
	/**
	 * @author Torta 20034002, Malavasi 20033809.
	 * @title Persona - Costruttore Alternativo 
	 * @param <em>nome</em> : Nome della persona (String)
	 * @param <em>cognome</em> : Cognome della persona (String)
	 * @param <em>giorno</em> : giorno di nascita della persona (int)
	 * @param <em>mese</em> : mese di nascita della persona (int)
	 * @param <em>anno</em> : anno di nascita della persona (int)
	 * @param <em>cf</em> : CF della persona (String)
	 * @throws IllegalArgumentException
	 */		
	public Persona(String nome, String cognome, int giorno, int mese, int anno, String codiceFiscale) throws IllegalArgumentException{
		this(nome, cognome, LocalDate.of(anno, mese, giorno), codiceFiscale);
	}
			
	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - getNome.
	 * @return <em>nome</em> : Nome della persona.
	 * getNome() - Metodo : Ritorna il nome della persona.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - getCognome.
	 * @return <em>cognome</em> : Cognome della persona.
	 * getCognome() - Metodo : Ritorna il cognome della persona.
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - getCf.
	 * @return <em>cf</em> : Cf della persona.
	 * getCf() - Metodo : Ritorna il cf della persona.
	 */
	public String getCf() {
		return cf;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - getDataNascita.
	 * @return <em>dataNascita</em> : Data di nascita della persona.
	 * getDataNascita() - Metodo : Ritorna la data di nascita della persona.
	 */
	public LocalDate getDataNascita() {
		return dataNascita;
	}

	// Metodi set protected perchè non voglio che l'utente possa cambiare i valori ma il metodo potrebbe essermi utile
	protected void setNome(String nome) {
		this.nome = nome;
	}
	
	protected void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	protected void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	protected void setCf(String cf) {
		this.cf = cf;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - toString
	 * @return <em>string</em> : Istanza sotto forma di string
	 * toString() - Metodo : Ritorna una stringa che rappresenta un istanza di Persona
	 */
	@Override
	public String toString() {
		return "nome=" + this.nome + ", cognome=" + this.cognome + ", dataNascita=" + this.dataNascita + ", cf=" + this.cf ;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Persona - equals
	 * @param <em>obj</em> : Oggetto
	 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di Persona, altrimenti false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(cf, other.cf) && Objects.equals(cognome, other.cognome)
				&& Objects.equals(dataNascita, other.dataNascita) && Objects.equals(nome, other.nome);
		}
}
