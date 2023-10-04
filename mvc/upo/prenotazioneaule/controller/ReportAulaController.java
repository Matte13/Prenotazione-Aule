package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.Prenotazione.ReportAula;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.ReportAulaView;
import upo.prenotazioneaule.model.InserimentoAulaModel;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class ReportAulaController implements ActionListener{
	
	//******************CAMPI**********************************************************
	private MainView mainView;
	private ReportAulaView view;
	private InserimentoAulaModel modelAula;
	private PrenotazioneAulaModel modelPrenotazione;

	//******************COSTRUTTORI****************************************************
	public ReportAulaController(MainView mainView, ReportAulaView view, InserimentoAulaModel modelAula, PrenotazioneAulaModel modelPrenotazione) {
		this.mainView = mainView;
		this.view = view;
		this.modelAula = modelAula;
		this.modelPrenotazione = modelPrenotazione;
		this.modelAula.addListener(this.view);
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
		String nomeAula = this.view.getNomeAula();
		
		this.view.reset();
		
		if(data == null)
			this.view.infoBox("La data deve essere selezionata", null);
		else if(data.equals(LocalDate.now()))
				this.view.infoBox("La data non può coincidere con la data di oggi", null);
			else if(nomeAula == null)
					this.view.infoBox("Seleziona l'aula", null);
				else
				{
					Aula a = this.modelAula.ricercaAula(nomeAula);
					
					if(this.modelPrenotazione.getPrenotazioniAula(a, data).isEmpty())
					{
						this.view.infoBox("Non è stato possibile creare un report\nperchè non ci sono prenotazioni per l'aula: " + nomeAula, null);
						this.view.reset();
					}
					else
					{
						ReportAula report = new ReportAula(data, a);
						report.addRecords(this.modelPrenotazione.getPrenotazioniAula(a, data));
						this.view.addListReport(report.stampa());
					}
				}
		
	}

}
