package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Prenotazione;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.PrenotazioneAulaView;
import upo.prenotazioneaule.model.InserimentoAulaModel;
import upo.prenotazioneaule.model.InserimentoDocenteModel;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class PrenotazioneAulaViewController implements ActionListener{

	//******************CAMPI**********************************************************
	private MainView mainView;
	private PrenotazioneAulaView view;
	private PrenotazioneAulaModel model;
	private InserimentoDocenteModel modelDocente;
	private InserimentoAulaModel modelAula;
	
	//******************COSTRUTTORI****************************************************
	public PrenotazioneAulaViewController(MainView mainView, PrenotazioneAulaView view, PrenotazioneAulaModel model, InserimentoDocenteModel modelDocente, InserimentoAulaModel modelAula) {
		this.mainView = mainView;
		this.view = view;
		this.model = model;
		this.modelDocente = modelDocente;
		this.modelAula = modelAula;
		this.view.addListener(this);
		this.modelDocente.addListener(this.view);
	}

	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String scelta = ((JButton)e.getSource()).getText();
		
		switch(scelta) {
		case "Cerca Aula":
			cercaAula();
			break;
		case "Prenota":
			prenota();
			break;
		case "Home":
			this.view.setVisible(false);
			this.view.reset();
			this.view.disable();
			this.mainView.setVisible(true);
			break;
		}
		
	}
	
	private void cercaAula() {
		
		LocalDate data = this.view.getData();
		LocalTime oraInizio = this.view.getOraInizio();
		LocalTime oraFine = this.view.getOraFine();
		
		if(data == null || oraInizio == null || oraFine == null)
			this.view.infoBox("I campi data e ora non possono essere vuoti", null);
		else if(data.compareTo(LocalDate.now()) < 0)
				this.view.infoBox("La data non può essere antecedente alla data odierna", null);
			else {
				ArrayList<Aula> auleLibere = this.model.getAuleLibere(data, oraInizio, oraFine);
				if(auleLibere.isEmpty())
					this.view.infoBox("Non ci sono aule libere per data e ora inidicati", null);
				else
				{
					for(Aula a : auleLibere)
						this.view.getListAuleLibere().add(a.getNome() + " capienza: " + a.getCapienzaMax() + " dotazioni: " + a.getDotazioni());
					
					this.view.enabled();
				}
			}
		
	}
	
	private void prenota() {
		
		LocalDate data = this.view.getData();
		LocalTime oraInizio = this.view.getOraInizio();
		LocalTime oraFine = this.view.getOraFine();
		String nomeAula = this.view.getNomeAula();
		String nomeDoc = this.view.getNomeDocente();
		String cognomeDoc = this.view.getCognomeDocente();
		Aula a = this.modelAula.ricercaAula(nomeAula);
		Docente d = this.modelDocente.cercaDocente(nomeDoc, cognomeDoc);
		String nomeLezione = this.view.getNomeLezione();
		
		if(nomeLezione.isBlank() || nomeLezione.isEmpty())
			this.view.infoBox("Il nome della lezione non può essere vuoto", null);
		else {
			try {
				Prenotazione p = new Prenotazione(a, d, data, oraInizio, oraFine, nomeLezione);
				this.model.addPrenotazione(p);
				this.view.reset();
				this.view.infoBox("Prenotazione aggiunta con successo", null);
			}
			catch(IllegalArgumentException e1) {
				this.view.infoBox("Problemi nell'inserimento della prenotazione", e1.getMessage());
			}
		}
		
	}
	

	
	
	
	
}
