package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import upo.prenotazioneaule.gui.InserimentoAulaView;
import upo.prenotazioneaule.gui.InserimentoDocenteView;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.PrenotazioneAulaView;
import upo.prenotazioneaule.gui.ReportAulaView;
import upo.prenotazioneaule.gui.ReportDocenteView;
import upo.prenotazioneaule.gui.RicercaAuleLibereView;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class MainViewController implements ActionListener{

	//******************CAMPI**********************************************************
	private MainView mainView;
	private InserimentoAulaView inserimentoAulaView;
	private InserimentoDocenteView inserimentoDocenteView;
	private RicercaAuleLibereView ricercaAuleLibereView;
	private PrenotazioneAulaView prenotazioneAulaView;
	private ReportDocenteView reportDocenteView;
	private ReportAulaView reportAulaView;
	private PrenotazioneAulaModel model;
	private File pren, aule, doc;
	private boolean salvati = false;
	
	//******************COSTRUTTORI****************************************************
	public MainViewController(MainView mainView, InserimentoAulaView inserimentoAulaView, InserimentoDocenteView inserimentoDocenteView, RicercaAuleLibereView ricercaAuleLibereView, PrenotazioneAulaView prenotazioneAulaView, ReportDocenteView reportDocenteView, ReportAulaView reportAulaView, PrenotazioneAulaModel model, File pren, File aule, File doc ) {
		this.mainView = mainView;
		this.inserimentoAulaView = inserimentoAulaView;
		this.inserimentoDocenteView = inserimentoDocenteView;
		this.ricercaAuleLibereView = ricercaAuleLibereView;
		this.prenotazioneAulaView = prenotazioneAulaView;
		this.reportDocenteView = reportDocenteView;
		this.reportAulaView = reportAulaView;
		this.model = model;
		this.pren = pren;
		this.aule = aule;
		this.doc = doc;
		mainView.addListener(this);
	}

	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		String scelta = ((JButton)e.getSource()).getText();
		
		switch(scelta) {
			case "Inserimento Aula": 
				closeAll();
				this.inserimentoAulaView.setVisible(true);
				break;
			case "Ricerca Aule Libere": 
				closeAll();
				this.ricercaAuleLibereView.setVisible(true);
				break;
			case "Prenota Aula":
				closeAll();
				this.prenotazioneAulaView.setVisible(true);
				break;
			case "Aggiungi Docente": 
				closeAll();
				this.inserimentoDocenteView.setVisible(true);
				break;
			case "Report Docente": 
				closeAll();
				this.reportDocenteView.setVisible(true);
				break;
			case "Report Aula": 
				closeAll();
				this.reportAulaView.setVisible(true);
				break;
			case "Salva Dati":
				saveToFile();
				break;
			case "Carica Dati":
				loadFromFile();
				break;
		}
		
	}
	
	private void saveToFile() {
		if(this.model.saveToFile(this.pren, this.aule, this.doc))
		{
			this.mainView.infoBox("Dati salvati correttamente", "Salvataggio Effettuato");
			this.salvati = true;
		}
		else
			this.mainView.infoBox("Dati non salvati", "Errore Salvataggio");
	}
	
	private void loadFromFile() {
		if(salvati)
			if(this.model.loadFromFile(this.pren, this.aule, this.doc))
				this.mainView.infoBox("Dati caricati correttamente", "Caricamento Effettuato");
			else
				this.mainView.infoBox("Dati non caricati", "Errore Caricamento");
		else
			this.mainView.infoBox("Non hai ancora salvato nessun dato,\n quindi non puoi caricare i dati", "Errore Caricamento");
	}
	
	private void closeAll() {
		this.mainView.setVisible(false);
		this.inserimentoAulaView.setVisible(false);
		this.ricercaAuleLibereView.setVisible(false);
		this.inserimentoDocenteView.setVisible(false);
		this.prenotazioneAulaView.setVisible(false);
		this.reportDocenteView.setVisible(false);
		this.reportAulaView.setVisible(false);
	}
	
	
}
