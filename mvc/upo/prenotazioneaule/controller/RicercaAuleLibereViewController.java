package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.gui.RicercaAuleLibereView;
import upo.prenotazioneaule.model.PrenotazioneAulaModel;

public class RicercaAuleLibereViewController implements ActionListener{
	
	//******************CAMPI**********************************************************
	private RicercaAuleLibereView view;
	private MainView mainView;
	private PrenotazioneAulaModel model;
	
	//******************COSTRUTTORI****************************************************
	public RicercaAuleLibereViewController(RicercaAuleLibereView ricercaAuleLibereView, MainView mainView, PrenotazioneAulaModel prenotazioneAulaModel) {
		this.view = ricercaAuleLibereView;
		this.mainView = mainView;
		this.model = prenotazioneAulaModel;
		ricercaAuleLibereView.addListener(this);
	}
	
	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText() == "Home") {
			this.view.setVisible(false);
			this.mainView.setVisible(true);
			this.view.clearField();
			this.view.getListAuleLibere().removeAll();
		}
		else
		{
			LocalDate data = this.view.getData();
			LocalTime oraInizio = this.view.getOraInizio();
			LocalTime oraFine = this.view.getOraFine();
			String dotazione = this.view.getDotazione();
			int capienza;
			
			if(this.view.getCapienza().isBlank())
				capienza = 0;
			else
				capienza = Integer.parseInt(this.view.getCapienza());

			
			if(this.view.getListAuleLibere().getItemCount() > 0)
				this.view.getListAuleLibere().removeAll();
			
			ArrayList<Aula> auleLibere = this.model.getAuleLibere(data, oraInizio, oraFine, capienza, dotazione);
			if(auleLibere.isEmpty())
				RicercaAuleLibereView.infoBox("Non ci sono aule libere per questa ricerca", null);
			else
				for(Aula a : auleLibere)
					this.view.getListAuleLibere().add(a.getNome() + " capienza: " + a.getCapienzaMax() + " dotazioni: " + a.getDotazioni());
			
			this.view.clearField();
		
			
		}
		
	}

}
