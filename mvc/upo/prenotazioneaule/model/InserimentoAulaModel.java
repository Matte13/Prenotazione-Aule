package upo.prenotazioneaule.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import upo.prenotazioneaule.Aula;

/**
 * @author Matteo Schirinzi 20035542
 * @title InserimentoAulaModel (Class)
 */

public class InserimentoAulaModel {
	
	//******************CAMPI**********************************************************
	private ArrayList<Aula> aule;
	private PropertyChangeSupport support;

	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title InserimentoDocenteModel - Costruttore 
	 * Istanzia l'ArrayList delle aule e support per la proprietà del change support
	 */
	public InserimentoAulaModel() {
		this.aule = new ArrayList<>();
		this.support = new PropertyChangeSupport(this);
	}
	
	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - addAula.
	 * @param <em>aula</em> : Aula da aggiungere (Aula)
	 * @return <em>boolean</em> : True se aggiunta l'aula, False altrimenti.
	 * @throws IllegalArgumentException
	 */
	public boolean addAula (Aula aula) throws IllegalArgumentException {															
		
		if(aula.getNome().isEmpty() || aula.getCapienzaMax() <= 0 )
			throw new IllegalArgumentException("Non puo' avere campi nulli\n");
		else {
			this.aule.add(aula);
			this.support.firePropertyChange("aggiunta aula", null, new ArrayList<Aula>(this.aule));
			return true;
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - ricercaAula.
	 * @param <em>nome</em> : Nome da ricercare (String)
	 * @return <em>Aula</em> : Aula se trovata, null altrimenti.
	 * @throws IllegalArgumentException
	 */
	public Aula ricercaAula(String nome) throws IllegalArgumentException{
		
		if(nome.isEmpty()) {
			throw new IllegalArgumentException ("Il campo non puo' essere vuoto");
		}
		
		for(Aula a : this.aule) {
			if(nome.equals(a.getNome())) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - getAule.
	 * @return <em>ArrayList-Aula</em> : restituisce tutte le aule.
	 */
	public ArrayList<Aula> getAule(){
		ArrayList<Aula> copyAula = new ArrayList<>(this.aule);
	 	return copyAula;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - setAule.
	 * @param <em>ArrayList-Aula</em> : Aule da aggiungere (ArrayList-Aula aule)
	 * setAule() - Metodo: istanzia l'array delle aule con le aule passate come parametro 
	 */
	public void setAule(ArrayList<Aula> aule){
		this.aule = new ArrayList<>(aule);
		this.support.firePropertyChange("aggiunta aule", null, new ArrayList<Aula>(this.aule));
	}
	
	public void addListener(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}
	

}
