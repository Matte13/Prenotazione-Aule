package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import upo.prenotazioneaule.Aula;
import upo.prenotazioneaule.Aula.Dotazione;
import upo.prenotazioneaule.AulaClassica;
import upo.prenotazioneaule.AulaElettronica;
import upo.prenotazioneaule.AulaInformatica;
import upo.prenotazioneaule.gui.InserimentoAulaView;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.model.InserimentoAulaModel;

public class InserimentoAulaViewController implements ActionListener{
	
	//******************CAMPI**********************************************************
	private MainView mainView;
	private InserimentoAulaView view; 
	private InserimentoAulaModel model;

	//******************COSTRUTTORI****************************************************
	public InserimentoAulaViewController(InserimentoAulaView inserimentoAulaView, InserimentoAulaModel inserimentoAulaModel, MainView mainView) {
		this.view = inserimentoAulaView;
		this.model = inserimentoAulaModel;
		this.mainView = mainView;
		inserimentoAulaView.addListener(this);
	}

	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(((JButton)e.getSource()).getText() == "Home") {
			this.view.setVisible(false);
			this.mainView.setVisible(true);
		}
		else {
			if(this.view.getNome().isEmpty() || this.view.getCapienza().isEmpty() || this.view.getTipologia() == null) {
				this.view.infoBox("Riempi tutti i campi, non ci possono essere campi null o liste non selezionate", "Compila tutti i campi");
			}
			else
			{
				Aula aula;
				String nome = this.view.getNome();
				int capienza = Integer.parseInt(this.view.getCapienza());
				
				switch (this.view.getTipologia()){
					case "Lab Informatica":
						aula = new AulaInformatica(nome, capienza);
						break;
					case "Lab Elettronica":
						aula = new AulaElettronica(nome, capienza);
						break;
					default: aula = new AulaClassica(nome, capienza);
						break;
				}
				
				String dotazioni[];
				dotazioni = this.view.getListDotazioni();
				if(dotazioni != null)
					for(String s : dotazioni)
						aula.addDotazione(Dotazione.valueOf(s));
					
				
				try {
			
					if(this.model.ricercaAula(aula.getNome()) == null)
						if(this.model.addAula(aula) )
							this.view.infoBox("Aula aggiunta con successo", null);
					else 
						this.view.infoBox("Aula già presente", null);	
				}
			
				catch (IllegalArgumentException e1){
					this.view.infoBox("Problemi nell'aggiunta dell'aula", null);
				}
				
				this.view.reset();
			}
		}
		
	}
	

}
