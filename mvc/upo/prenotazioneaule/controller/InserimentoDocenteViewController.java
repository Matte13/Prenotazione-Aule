package upo.prenotazioneaule.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import upo.prenotazioneaule.Docente;
import upo.prenotazioneaule.gui.InserimentoDocenteView;
import upo.prenotazioneaule.gui.MainView;
import upo.prenotazioneaule.model.InserimentoDocenteModel;

public class InserimentoDocenteViewController implements ActionListener{

	//******************CAMPI**********************************************************
	private MainView mainView;
	private InserimentoDocenteView view;
	private InserimentoDocenteModel model;
	
	//******************COSTRUTTORI****************************************************
	public InserimentoDocenteViewController(InserimentoDocenteView inserimentoDocenteView, InserimentoDocenteModel inserimentoDocenteModel, MainView mainView) {
		this.view = inserimentoDocenteView;
		this.model = inserimentoDocenteModel;
		this.mainView = mainView;
		this.view.addListener(this);
	}

	//******************METODI****************************************************
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText() == "Home") {
			this.view.setVisible(false);
			this.mainView.setVisible(true);
		}
		else
		{
			Docente doc;
			String nome = this.view.getNome();
			String cognome = this.view.getCognome();
			String cf = this.view.getCF();
			LocalDate dataNascita = this.view.getDataNascita();
			
			if(nome.isBlank() || cognome.isBlank() || cf.isBlank() || dataNascita == null)
				this.view.infoBox("I campi non possono essere vuoti", null);
			else if(dataNascita.compareTo(LocalDate.now()) >= 0)
					this.view.infoBox("La data di nascita non può essere maggiore o uguale alla data odierna", null);
				else if(cf.length() != 16)
						this.view.infoBox("Il codice fiscale deve essere lungo 16 caratteri", null);
					else if(this.model.cercaDocente(nome, cognome) != null)
							this.view.infoBox("Docente già presente", null);
						else
						{
							try {
								doc = new Docente (nome, cognome, dataNascita, cf);
								if(this.model.addDocente(doc))
									this.view.infoBox("Docente aggiunto con successo", null);
							}
							catch (IllegalArgumentException e1){
								this.view.infoBox("Problemi nell'aggiunta del docente", null);
							}
						}

		}
		
		this.view.reset();
		
	}

}
