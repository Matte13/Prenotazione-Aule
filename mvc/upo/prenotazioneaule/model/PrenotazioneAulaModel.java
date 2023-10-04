package upo.prenotazioneaule.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.Aula.Dotazione;
import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Prenotazione;

/**
 * @author Matteo Schirinzi 20035542
 * @title PrenotazioneAulaModel (Class)
 */

public class PrenotazioneAulaModel implements Serializable{

	//******************CAMPI**********************************************************
	private static final long serialVersionUID = -2960095784776373352L;
	private ArrayList<Prenotazione> prenotazioni;
	private InserimentoAulaModel aulaModel;
	private InserimentoDocenteModel docenteModel;

	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - Costruttore 
	 * @param <em>aulaModel</em> : Model per l'inserimento delle aule (InserimentoAulaModel)
	 * @param <em>docenteModel</em> : Model per l'inserimento del docente (InserimentoDocenteModel)
	 */
	public PrenotazioneAulaModel(InserimentoAulaModel aulaModel, InserimentoDocenteModel docenteModel) {
		this.prenotazioni = new ArrayList<>();
		this.aulaModel = aulaModel;
		this.docenteModel = docenteModel;
	}
	
	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - addPrenotazione.
	 * @param <em>p</em> : Prenotazione da aggiugere (Prenotazione)
	 * @return <em>boolean</em> : True se aggiunta la prenotazione, False altrimenti.
	 * @throws IllegalArgumentException
	 */
	public boolean addPrenotazione(Prenotazione p) throws IllegalArgumentException {
		
		if(p.getAula() == null || p.getDocente() == null || p.getData() == null || p.getoraInizio() == null || p.getoraFine() == null) {
			throw new IllegalArgumentException ("Riempire tutti i campi\n");
		}
		else {
			
	        if(p.getData().compareTo(LocalDate.now()) <= 0){ 
	            throw new IllegalArgumentException ("La data non può essere precedente o uguale a quella odierna.\n");
	        }
	        
	        if(p.getoraInizio().compareTo(p.getoraFine()) >=  0) {
	        	throw new IllegalArgumentException ("L'ora d'inizio non può essere successiva o uguale all'ora di fine\n");
	        }
			
	        if(!this.cercaPrenotazione(p))
	        	prenotazioni.add(p);
			return true;
		}
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - cercaPrenotazione.
	 * @param <em>p</em> : Prenotazione da aggiugere (Prenotazione)
	 * @return <em>boolean</em> : True se trovata, null altrimenti.
	 */
	public boolean cercaPrenotazione(Prenotazione p) {
		
		for (Prenotazione e : this.prenotazioni) {
			if(e.getData().equals(p.getData())) {
				if(e.getAula().equals(p.getAula()) && p.getoraInizio().isBefore(e.getoraFine()) && p.getoraInizio().isAfter(e.getoraInizio()) 
						|| p.getoraInizio().equals(e.getoraInizio()) || (p.getoraFine().isAfter(e.getoraInizio()) 
						&& p.getoraFine().isBefore(e.getoraFine()))) {
					return true;
				}
				else if (e.getDocente().getMatricola() == p.getDocente().getMatricola()) {
					if (p.getoraInizio().isBefore(e.getoraFine()) && p.getoraInizio().isAfter(e.getoraInizio()) 
							|| p.getoraInizio().equals(e.getoraInizio()) || p.getoraFine().isAfter(e.getoraInizio()) 
							&& p.getoraFine().isBefore(e.getoraFine())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - getPrenotazioniDocente.
	 * @param <em>d</em> : Docente per la ricerca (Docente)
	 * @param <em>data</em> : data per la ricerca delle prenotazioni (LocalDate)
	 * @return <em>ArrayList-Prenotazione</em> : Restituisce tutte le prenotazioni per un determinato docente.
	 */
	public ArrayList<Prenotazione> getPrenotazioniDocente(Docente d, LocalDate data){
		
		ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
		
		for (Prenotazione e : this.prenotazioni)
			if(e.getDocente().equals(d) && e.getData().equals(data))
				prenotazioni.add(e);
		
		return prenotazioni;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - getPrenotazioniAula.
	 * @param <em>a</em> : Aula per la ricerca (Aula)
	 * @param <em>data</em> : data per la ricerca delle prenotazioni (LocalDate)
	 * @return <em>ArrayList-Prenotazione</em> : Restituisce tutte le prenotazioni per una determinata aula.
	 */
	public ArrayList<Prenotazione> getPrenotazioniAula(Aula a, LocalDate data){
			
		ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
			
		for (Prenotazione e : this.prenotazioni) {
			if(e.getAula().equals(a) && e.getData().equals(data))
				prenotazioni.add(e);
		}
			
		return prenotazioni;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - getAuleLibere.
	 * @param <em>data</em> : data per la ricerca delle prenotazioni (LocalDate)
	 * @param <em>oraInizio</em> : Ora di inzio per la ricerca delle prenotazioni (LocalTime)
	 * @param <em>oraFine</em> : Ora di fine per la ricerca delle prenotazioni (LocalTime)
	 * @return <em>ArrayList-Aula</em> : Restituisce tutte le aule libere.
	 */
	public ArrayList<Aula> getAuleLibere(LocalDate data, LocalTime oraInizio, LocalTime oraFine){
		
		ArrayList<Aula> auleLibere = this.aulaModel.getAule();
		
		for(Prenotazione p : this.prenotazioni)
			if (p.getData().equals(data) && p.getoraInizio().equals(oraInizio) && p.getoraFine().equals(oraFine))
				auleLibere.remove(auleLibere.indexOf(p.getAula()));
		
		return auleLibere;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - getAuleLibere alternativo.
	 * @param <em>data</em> : data per la ricerca delle prenotazioni (LocalDate)
	 * @param <em>oraInizio</em> : Ora di inzio per la ricerca delle prenotazioni (LocalTime)
	 * @param <em>oraFine</em> : Ora di fine per la ricerca delle prenotazioni (LocalTime)
	 * @param <em>capienza</em> : Capienza dell'aula (int)
	 * @param <em>dotazione</em> : Dotazione dell'aula (String)
	 * @return <em>ArrayList-Aula</em> : Restituisce tutte le aule libere.
	 */
	public ArrayList<Aula> getAuleLibere(LocalDate data, LocalTime oraInizio, LocalTime oraFine, int capienza, String dotazione){
		
		ArrayList<Aula> auleLibere = new ArrayList<>();
		HashSet<Aula> auleDel = new HashSet<>();
		
		//Controllo sulla capienza
		if(capienza != 0)
			for(Aula a : this.aulaModel.getAule())
				if(a.getCapienzaMax() != capienza)
					auleDel.add(a);
		
		//Controllo sulla dotazione
		if(dotazione != null)
			for(Aula a : this.aulaModel.getAule())
				if(!a.getDotazioni().contains(Dotazione.valueOf(dotazione)))
					auleDel.add(a);
		
		//controllo prenotazione
		if(data != null && oraInizio != null && oraFine != null)
			for(Prenotazione p : this.prenotazioni)
				if (p.getData().equals(data) && p.getoraInizio().equals(oraInizio))
					auleDel.add(p.getAula());
		
		for(Aula al : this.aulaModel.getAule())
			if(auleDel.isEmpty())
				auleLibere = new ArrayList<>(this.aulaModel.getAule());
			else
				for(Aula a : auleDel)
					if(!al.equals(a))
						auleLibere.add(al);
		
		return auleLibere;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - saveToFile.
	 * @param <em>filePrenotazioni</em> : File per salvare le prenotazioni (File)
	 * @param <em>fileAule</em> : File per salvare le aule (File)
	 * @param <em>fileDocenti</em> : File per salvare i docenti (File)
	 * @return <em>boolean</em> : True se i dati sono stati salvati, false altrimenti.
	 * saveToFile() - Metodo: salva tutti i dati dell'applicazioni divisi nei tre file
	 */
	public boolean saveToFile(File filePrenotazioni, File fileAule, File fileDocenti) { 
		try(ObjectOutputStream pren = new ObjectOutputStream(new FileOutputStream(filePrenotazioni));
				ObjectOutputStream aule = new ObjectOutputStream(new FileOutputStream(fileAule));
				ObjectOutputStream doc = new ObjectOutputStream(new FileOutputStream(fileDocenti));) {
			
			pren.writeObject(this.prenotazioni);
			aule.writeObject(this.aulaModel.getAule());
			doc.writeObject(this.docenteModel.getDocenti());
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title PrenotazioneAulaModel - loadFromFile.
	 * @param <em>filePrenotazioni</em> : File per caricare le prenotazioni (File)
	 * @param <em>fileAule</em> : File per caricare le aule (File)
	 * @param <em>fileDocenti</em> : File per caricare i docenti (File)
	 * @return <em>boolean</em> : True se i dati sono stati caricati, false altrimenti.
	 * saveToFile() - Metodo: caricare tutti i dati dell'applicazioni divisi nei tre file
	 */
	@SuppressWarnings("unchecked")
	public boolean loadFromFile(File filePrenotazioni, File fileAule, File fileDocenti) { 
		try(ObjectInputStream pren = new ObjectInputStream(new FileInputStream(filePrenotazioni));
				ObjectInputStream aule = new ObjectInputStream(new FileInputStream(fileAule));
				ObjectInputStream doc = new ObjectInputStream(new FileInputStream(fileDocenti));){
			this.prenotazioni = (ArrayList<Prenotazione>) pren.readObject();
			this.aulaModel.setAule((ArrayList<Aula>) aule.readObject());
			this.docenteModel.setDocenti((ArrayList<Docente>) doc.readObject());
			
			System.out.println(this.prenotazioni);
			System.out.println(this.aulaModel.getAule());
			System.out.println(this.docenteModel.getDocenti());
			
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	

}
