package upo.prenotazioneaule;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Matteo Schirinzi 20035542
 * @title Prenotazione (Class)
 */

public class Prenotazione implements Serializable{
	
	//******************CAMPI**********************************************************
	private static final long serialVersionUID = -5565113484970181283L;
	private Integer codPrenotazione;
	private static AtomicInteger seq = new AtomicInteger(0); 
	private Aula aula;
	private Docente docente;
	private LocalDate data;
	private LocalTime oraInizio;
	private LocalTime oraFine;
	private String nomeLezione;
	
	
	//******************COSTRUTTORI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - Costruttore 
	 * @param <em>aula</em> : Aula prenotata (Aula)
	 * @param <em>docente</em> : Docente che ha effettuato la prenotazione (Docente)
	 * @param <em>data</em> : Data della prenotazione (LocalDate)
	 * @param <em>oraInizio</em> : Ora di inizio della prenotazione (LocalTime)
	 * @param <em>oraFine</em> : Ora di fine della prenotazione (LocalTime)
	 * @param <em>nomeLezione</em> : Nome della lezione (String)
	 * @throws IllegalArgumentException
	 * Autoincremento automatico gestito dal sistema del codice della prenotazione
	 */
	public Prenotazione(Aula aula, Docente docente, LocalDate data, LocalTime oraInizio, LocalTime oraFine, String nomeLezione) throws IllegalArgumentException{
		
		//****CONTROLLI****
		if(aula == null) throw new IllegalArgumentException("L'aula non può essere nulla");
		if(docente == null) throw new IllegalArgumentException("Docente non può essere nullo");
		if(data == null) throw new IllegalArgumentException("La data non può essere nulla");
		if(oraInizio == null) throw new IllegalArgumentException("L'ora oraInizio non può essere nulla");
		if(oraFine == null) throw new IllegalArgumentException("L'ora oraFine non può essere nulla");
		if(data.isBefore(LocalDate.now()) || data.equals(LocalDate.now())) throw new IllegalArgumentException("La data deve essere successiva a quella odierna");
		if(oraInizio.isAfter(oraFine)) throw new IllegalArgumentException("L'ora d'oraInizio non può essere maggiore di quella di oraFine");
		if(nomeLezione == null) throw new IllegalArgumentException("Il nome della lezione non può essere null");
		
		this.codPrenotazione = seq.incrementAndGet();
		this.aula = aula;
		this.docente = docente;
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.nomeLezione = nomeLezione;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - Costruttore Alternativo 
	 * @param <em>aula</em> : Aula prenotata (Aula)
	 * @param <em>docente</em> : Docente che ha effettuato la prenotazione (Docente)
	 * @param <em>giorno</em> : giorno della prenotazione (int)
	 * @param <em>mese</em> : mese della prenotazione (int)
	 * @param <em>anno</em> : anno della prenotazione (int)
	 * @param <em>ora</em> : Ora di inizio della prenotazione (int)
	 * @param <em>minuti</em> : Minuti di inizio della prenotazione (int)
	 * @param <em>oraFine</em> : Ora di fine della prenotazione (int)
	 * @param <em>minutiFine</em> : Minuti di fine della prenotazione (int)
	 * @param <em>nomeLezione</em> : Nome della lezione (String)
	 * @throws IllegalArgumentException
	 * Autoincremento automatico gestito dal sistema del codice della prenotazione
	 */
	public Prenotazione(Aula aula, Docente docente, int giorno, int mese, int anno, int ora, int minuti, int oraFine, int minutiFine, String nomeLezione) {
		this(aula, docente, LocalDate.of(anno, mese, giorno), LocalTime.of(ora, minuti), LocalTime.of(oraFine, minutiFine), nomeLezione);
	}


	//******************METODI****************************************************
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getAula.
	 * @return <em>aula</em> : Aula della prenotazione.
	 * getAula() - Metodo : Ritorna l'aula della prenotazione
	 */
	public Aula getAula() {
		return aula;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getDocente.
	 * @return <em>docente</em> : Docente della prenotazione.
	 * getDocente() - Metodo : Ritorna il docente della prenotazione
	 */
	public Docente getDocente() {
		return docente;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getData.
	 * @return <em>data</em> : Data della prenotazione.
	 * getData() - Metodo : Ritorna la data della prenotazione
	 */
	public LocalDate getData() {
		return data;
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getNomeLezione.
	 * @return <em>nomeLezione</em> : Nome della lezione della prenotazione.
	 * getNomeLezione() - Metodo : Ritorna il nome della lezione della prenotazione
	 */
	public String getNomeLezione() {
		return nomeLezione;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getoraInizio.
	 * @return <em>oraInizio</em> : Ora di inizio della prenotazione.
	 * getoraInizio() - Metodo : Ritorna l'ora di inizio della prenotazione
	 */
	public LocalTime getoraInizio() {
		return oraInizio;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getoraFine.
	 * @return <em>oraFine</em> : Ora di fine della prenotazione.
	 * getoraFine() - Metodo : Ritorna l'ora di fine della prenotazione
	 */
	public LocalTime getoraFine() {
		return oraFine;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - getCodPrenotazione.
	 * @return <em>codPrenotazione</em> : Codcie della prenotazione.
	 * getCodPrenotazione() - Metodo : Ritorna il codice della prenotazione
	 */
	public int getCodPrenotazione() {
		return codPrenotazione;
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - toString
	 * @return <em>string</em> : Istanza sotto forma di string
	 * toString() - Metodo : Ritorna una stringa che rappresenta un istanza di Prenotazione
	 */
	@Override
	public String toString() {
		return "Prenotazione [codPrenotazione=" + codPrenotazione + ", aula=" + aula + ", docente=" + docente
				+ ", data=" + data + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine + ", nomeLezione="
				+ nomeLezione + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aula, codPrenotazione, data, docente, nomeLezione, oraFine, oraInizio);
	}

	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - equals
	 * @param <em>obj</em> : Oggetto
	 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di Prenotazione, altrimenti false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return Objects.equals(aula, other.aula) && codPrenotazione == other.codPrenotazione
				&& Objects.equals(data, other.data) && Objects.equals(docente, other.docente)
				&& Objects.equals(nomeLezione, other.nomeLezione) && Objects.equals(oraFine, other.oraFine)
				&& Objects.equals(oraInizio, other.oraInizio);
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Prenotazione - Comparator-Prenotazione.
	 * @return <em>oraInizioComparator</em> : Comparator per oraInzio.
	 * Comparator-Prenotazione - Metodo : Ritorna un comparatore sull'ora di inizio in ordine crescente
	 */
    public static Comparator<Prenotazione> oraInizioComparator = new Comparator<Prenotazione>() {

		public int compare(Prenotazione p1, Prenotazione p2) {
	
		   // Ordine crescente
		   return p1.getoraInizio().compareTo(p2.getoraInizio());
	    }
	};
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title Report (Class)
	 * classe astratta annidata in Prenotazione per la generazione di report
	 */
	public static abstract class Report {
		
		//******************CAMPI**********************************************************
		private LocalDate dataReport;
		private ArrayList<Prenotazione> prenotazioni;

		//******************COSTRUTTORE****************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - Costruttore
		 * @param <em>dataReport</em> : Data del report (LocalDate)
		 * @throws IllegalArgumentException
		 * Istanzia l'ArrayList di prenotazioni
		 */
		public Report(LocalDate dataReport) throws IllegalArgumentException{
			this.dataReport = dataReport;
			this.prenotazioni = new ArrayList<>();
		}
		
		//******************METODI*********************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - addRecords.
		 * @param <em>ArrayList prenotazione</em> : ArrayList di prenotazioni.
		 * addRecords() - Metodo : Aggiunge le prenotazioni al report
		 */
		public void addRecords(ArrayList<Prenotazione> pren) throws RuntimeException{
			if(pren.isEmpty() || pren == null) throw new IllegalArgumentException("L'array non può essere nullo o vuoto");
			this.prenotazioni = new ArrayList<Prenotazione>(pren);	
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - ordinaPerOraInizio.
		 * ordinaPerOraInizio() - Metodo : Ordina per ora inizio i record del report
		 */
		public void ordinaPerOraInizio() { 
			this.prenotazioni.sort(Prenotazione.oraInizioComparator);
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - getAllRecord.
		 * @return <em>ArrayList prenotazione</em> : Comparator per oraInzio.
		 * getAllRecord() - Metodo : Restituisce tutti i record del report
		 */
		public ArrayList<Prenotazione> getAllRecord() {
			return this.prenotazioni;
		}
		
		protected void addRecord(Prenotazione p) {
			this.prenotazioni.add(p);
		}
		
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - getDataReport.
		 * @return <em>dataReport</em> : Data del report.
		 * getDataReport() - Metodo : Restituisce la data del report
		 */
		public LocalDate getDataReport() {
			return this.dataReport;
		}
		
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - isEmpty.
		 * @return <em>boolean</em> : true se presenti record, false altrimenti.
		 * getDataReport() - Metodo : Restituisce true se non ci sono record, false altrimenti
		 */
		public boolean isEmpty() {
			return this.prenotazioni.isEmpty();
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title Report - equals
		 * @param <em>obj</em> : Oggetto
		 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di Report, altrimenti false
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Report other = (Report) obj;
			return Objects.equals(dataReport, other.dataReport) && Objects.equals(prenotazioni, other.prenotazioni);
		}

		
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title ReportDocente (Class)
	 * classe astratta annidata in Prenotazione per la generazione di report per docente
	 */
	public static class ReportDocente extends Report{
		
		//******************CAMPI**********************************************************
		private Docente docente;

		//******************COSTRUTTORE****************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - Costruttore
		 * @param <em>dataReport</em> : Data del report (LocalDate)
		 * @param <em>Docente</em> : Docente per il report (Docente)
		 * @throws IllegalArgumentException
		 */
		public ReportDocente(LocalDate dataReport, Docente docente) throws IllegalArgumentException{
			super(dataReport);
			this.docente = docente;
		}
		
		//******************METODI*********************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - addRecord.
		 * @param <em>p</em> : Prenotazione da aggiungere al report (Prenotazione)
		 * addRecord() - Metodo : Aggiunge un record al report docente
		 */
		public void addRecord(Prenotazione p) throws RuntimeException{
			if(p == null) throw new IllegalArgumentException("La prenotazione non può essere nulla");
			if(!p.getDocente().equals(this.docente)) throw new IllegalArgumentException("Il docente della prenotazione inserita non coincide con il docente di questi report");
			super.addRecord(p);
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - getDocente.
		 * @return <em>Docente</em> : restituisce il docente.
		 * getDocente() - Metodo : Restituisce il docente del report
		 */
		public Docente getDocente() {
			return this.docente;
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - equals
		 * @param <em>obj</em> : Oggetto
		 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di ReportDocente, altrimenti false
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReportDocente other = (ReportDocente) obj;
			return Objects.equals(docente, other.docente);
		}
		
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - stampa.
		 * @return <em>ArrayList-String</em> : array con tutti i record.
		 * stampa() - Metodo : Restituisce un array con tutti i record del report
		 */
		public ArrayList<String> stampa() {
			
			ArrayList<String> array = new ArrayList<>();
			for(Prenotazione p : super.prenotazioni)
				array.add("Aula: " + p.getAula().getNome() + ", Lezione: " + p.getNomeLezione() + ", Inizio: " + p.getoraInizio() + ", Fine: " + p.getoraFine());
			return array;
		}
		
	}
	
	/**
	 * @author Matteo Schirinzi 20035542
	 * @title ReportAula (Class)
	 * classe astratta annidata in Prenotazione per la generazione di report per Aula
	 */
	public static class ReportAula extends Report{
		
		//******************CAMPI**********************************************************
		private Aula aula;
		
		//******************COSTRUTTORE****************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportAula - Costruttore
		 * @param <em>dataReport</em> : Data del report (LocalDate)
		 * @param <em>Aula</em> : Aula per il report (Aula)
		 * @throws IllegalArgumentException
		 */
		public ReportAula(LocalDate dataReport, Aula aula) throws IllegalArgumentException {
			super(dataReport);
			this.aula = aula;
		}
		
		//******************METODI*********************************************************
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportAula - addRecord.
		 * @param <em>p</em> : Prenotazione da aggiungere al report (Prenotazione)
		 * addRecord() - Metodo : Aggiunge un record al report aula
		 */
		public void addRecord(Prenotazione p) throws RuntimeException{
			if(p == null) throw new IllegalArgumentException("La prenotazione non può essere nulla");
			if(!p.getAula().equals(this.aula)) throw new IllegalArgumentException("L'aula della prenotazione inserita non coincide con l'aula di questi report");
			super.addRecord(p);
		}
		
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportDocente - getAula.
		 * @return <em>Aula</em> : restituisce l'aula.
		 * getAula() - Metodo : Restituisce l'aula del report
		 */
		public Aula getAula() {
			return this.aula;
		}

		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportAula - equals
		 * @param <em>obj</em> : Oggetto
		 * equals() - Metodo : Ritorna true se l'oggetto passato come parametro è un istanza uguale di ReportAula, altrimenti false
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReportAula other = (ReportAula) obj;
			return Objects.equals(aula, other.aula);
		}
		
		/**
		 * @author Matteo Schirinzi 20035542
		 * @title ReportAula - stampa.
		 * @return <em>ArrayList-String</em> : array con tutti i record.
		 * stampa() - Metodo : Restituisce un array con tutti i record del report
		 */
		public ArrayList<String> stampa() {
			
			ArrayList<String> array = new ArrayList<>();
			for(Prenotazione p : super.prenotazioni)
				array.add("Docente: " + p.getDocente().getNome() + " " + p.getDocente().getCognome() + ", Lezione: " + p.getNomeLezione() + ", Inizio: " + p.getoraInizio() + ", Fine: " + p.getoraFine());
			return array;
		}

		
		
			
	}


}

