package upo.prenotazioneaule.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import upo.prenotazioneaule.Docente;

/**
 * @author Matteo Schirinzi 20035542
 * @title InserimentoDocenteModel (Class)
 */

public class InserimentoDocenteModel {

	//******************CAMPI**********************************************************
	private ArrayList<Docente> docenti;
	private PropertyChangeSupport support;
	
	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - Costruttore 
	 * Istanzia l'ArrayList dei docenti e support per la proprietà del change support
	 */
	public InserimentoDocenteModel() {
		this.docenti = new ArrayList<>();
		this.support = new PropertyChangeSupport(this);
	}
	
	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - addDocente.
	 * @return <em>boolean</em> : True se aggiunto il docente, False altrimenti.
	 * @throws IllegalArgumentException
	 */
	public boolean addDocente (Docente docente) throws IllegalArgumentException {															
		
		if(docente.getNome().isEmpty() || docente.getCognome().isEmpty() || docente.getCf().isEmpty() || docente.getDataNascita().equals(null))
			throw new IllegalArgumentException("Non puo' avere campi nulli\n");
		else if(docente.getDataNascita().compareTo(LocalDate.now()) >= 0)
				throw new IllegalArgumentException("La data di nascita non può essere uguale o maggiore di quella odierna\n");
			else
			{
				this.docenti.add(docente);
				this.support.firePropertyChange("aggiunta docente", null, new ArrayList<Docente>(this.docenti));
				return true;
			}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - cercaDocente.
	 * @return <em>Docente</em> : Docente se trovato, null altrimenti.
	 * @throws IllegalArgumentException
	 */
	public Docente cercaDocente(String nome, String cognome) throws IllegalArgumentException{			
		
		if(nome.isBlank() || cognome.isBlank()) {
			throw new IllegalArgumentException("Inserire parametri validi\n");
		}
		
		for(Docente d : docenti) {
			if(d.getNome().equals(nome) && d.getCognome().equals(cognome) ) {
				return d;
			}
		}
		return null;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - getDocenti.
	 * @return <em>ArrayList-Docente</em> : Restituisce tutti i docenti.
	 */
	public ArrayList<Docente> getDocenti(){
		ArrayList<Docente> copyDocente = new ArrayList<>(this.docenti);
	 	return copyDocente;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - setDocenti.
	 * @param <em>docenti</em> : ArrayList-Docente docenti
	 * setDocenti() - istanzia i docenti del model con i docenti passati come parametro
	 */
	public void setDocenti(ArrayList<Docente> docenti){
		this.docenti = new ArrayList<>(docenti);
		this.support.firePropertyChange("aggiunta docenti", null, new ArrayList<Docente>(this.docenti));
	}
	
	public void addListener(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}
}
