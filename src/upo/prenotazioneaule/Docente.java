package upo.prenotazioneaule;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matteo Schirinzi 20035542
 * @title Docente (Class)
 */

public class Docente extends Persona{
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = 652982236378962228L;
	private Integer matricola;
	private static AtomicInteger seq = new AtomicInteger(0); 
	
	//******************COSTRUTTORI****************************************************
	/**
	 * @author Torta 20034002, Malavasi 20033809.
	 * @title Docente - Costruttore 
	 * @param <em>nome</em> : Nome della persona (String)
	 * @param <em>cognome</em> : Cognome della persona (String)
	 * @param <em>giorno</em> : giorno di nascita della persona (int)
	 * @param <em>mese</em> : mese di nascita della persona (int)
	 * @param <em>anno</em> : anno di nascita della persona (int)
	 * @param <em>cf</em> : CF della persona (String)
	 * @throws IllegalArgumentException
	 * Docente - instanzia una variabile matricola (int) con un autoincremento automatico gestito dal sistema
	 */
	public Docente(String nome, String cognome, int giorno, int mese, int anno, String cf) throws IllegalArgumentException{
		super(nome, cognome, giorno, mese, anno, cf);
		this.matricola = seq.incrementAndGet();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Docente - Costruttore Alternativo 
	 * @param <em>nome</em> : Nome della persona (String)
	 * @param <em>cognome</em> : Cognome della persona (String)
	 * @param <em>dataNascita</em> : Data di nascita della persona (LocalDate)
	 * @param <em>cf</em> : CF della persona (String)
	 * @throws IllegalArgumentException
	 * Docente - instanzia una variabile matricola (int) con un autoincremento automatico gestito dal sistema
	 */
	public Docente(String nome, String cognome, LocalDate dataNascita, String cf) throws IllegalArgumentException {
		super(nome, cognome, dataNascita, cf);
		this.matricola = seq.incrementAndGet();
		// TODO Auto-generated constructor stub
	}
	
	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Docente - getMatricola.
	 * @return <em>nome</em> : Nome della persona.
	 * getMatricola() - Metodo : Ritorna la matricola del docente
	 */
	public int getMatricola() {
		return matricola;
	}

	// Metodi set protected perchè non voglio che l'utente possa cambiare i valori ma il metodo potrebbe essermi utile
	protected void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Docente - toString
	 * @return <em>string</em> : Istanza sotto forma di string
	 * toString() - Metodo : Ritorna una stringa che rappresenta un istanza di Docente
	 */
	@Override
	public String toString() {
		return super.toString() + ", matricola=" + this.matricola;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Docente - equals
	 * @param <em>obj</em> : Oggetto
	 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di Docente, altrimenti false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Docente other = (Docente) obj;
		return Objects.equals(matricola, other.matricola);
	}

	
	
}
