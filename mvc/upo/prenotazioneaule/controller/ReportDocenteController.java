package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.Prenotazione.ReportDocente;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.ReportDocenteView;
import upo.prenotazioneaule.model.InserimentoDocenteModel;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class ReportDocenteController implements ActionListener{
	
	//******************CAMPI**********************************************************
	private MainView mainView;
	private ReportDocenteView view;
	private InserimentoDocenteModel modelDocente;
	private PrenotazioneAulaModel modelPrenotazione;

	//******************COSTRUTTORI****************************************************
	public ReportDocenteController(MainView mainView, ReportDocenteView view, InserimentoDocenteModel modelDocente, PrenotazioneAulaModel modelPrenotazione) {
		this.mainView = mainView;
		this.view = view;
		this.modelDocente = modelDocente;
		this.modelPrenotazione = modelPrenotazione;
		this.modelDocente.addListener(this.view);
		this.view.addListener(this);
	}

	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String scelta = ((JButton)e.getSource()).getText();
		
		switch(scelta) {
		case "Crea Report":
			creaReport();
			break;
		case "Home":
			this.view.setVisible(false);
			this.view.reset();
			this.mainView.setVisible(true);
			break;
		}
		
	}
	
	private void creaReport() {
		
		LocalDate data = this.view.getData();
		String nomeDoc = this.view.getNomeDocente();
		String cognomeDoc = this.view.getCognomeDocente();
		
		this.view.reset();
		
		if(data == null)
			this.view.infoBox("La data deve essere selezionata", null);
		else if(data.equals(LocalDate.now()))
				this.view.infoBox("La data non può coincidere con la data di oggi", null);
			else if(nomeDoc == null || cognomeDoc == null)
					this.view.infoBox("Seleziona il docente", null);
				else
				{
					Docente d = this.modelDocente.cercaDocente(nomeDoc, cognomeDoc);
					
					if(this.modelPrenotazione.getPrenotazioniDocente(d, data).isEmpty())
					{
						this.view.infoBox("Non è stato possibile creare un report\nperchè non ci sono prenotazioni per " + nomeDoc + " " + cognomeDoc, cognomeDoc);
						this.view.reset();
					}
					else
					{
						ReportDocente report = new ReportDocente(data, d);
						report.addRecords(this.modelPrenotazione.getPrenotazioniDocente(d, data));
						this.view.addListReport(report.stampa());
					}
				}
		
	}

}
